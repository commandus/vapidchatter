#!/bin/bash
# wget https://curl.haxx.se/download/curl-7.69.1.tar.gz
# tar xvfz curl-7.69.1.tar.gz
# https://github.com/robertying/openssl-curl-android/releases
#
echo Download from https://github.com/robertying/openssl-curl-android/releases
exit 0

H=/home/andrei
ROOT_PROJECT=$H/src/vapidchatter

OPENSSL_VER=1.1.1f
ROOT_OPENSSL=$H/lib/openssl-$OPENSSL_VER

CURL_VER=7.69.1
ROOT_CURL=$H/lib/curl-$CURL_VER

export ANDROID_NDK_HOME=$H/Android/Sdk/ndk/21.0.6113669
TARGET=android-29

SAV_PATH=$PATH

HOST_TAG=linux-x86_64

# arm64
# export TARGET_HOST=aarch64-linux-android
# armeabi
export TARGET_HOST=arm-linux-androideabi

export TOOLCHAIN=$ANDROID_NDK_HOME/toolchains/llvm/prebuilt/$HOST_TAG
PATH=$TOOLCHAIN/bin:$SAV_PATH
PATH=$ANDROID_NDK_HOME/toolchains/llvm/prebuilt/linux-x86_64/bin:$ANDROID_NDK_HOME/toolchains/arm-linux-androideabi-4.9/prebuilt/linux-x86_64/bin:$SAV_PATH

export AR=$TOOLCHAIN/bin/$TARGET_HOST-ar
export AS=$TOOLCHAIN/bin/$TARGET_HOST-as
export CC=$TOOLCHAIN/bin/$TARGET_HOST$MIN_SDK_VERSION-clang
export CXX=$TOOLCHAIN/bin/$TARGET_HOST$MIN_SDK_VERSION-clang++
export LD=$TOOLCHAIN/bin/$TARGET_HOST-ld
export RANLIB=$TOOLCHAIN/bin/$TARGET_HOST-ranlib
export STRIP=$TOOLCHAIN/bin/$TARGET_HOST-strip


export SYSROOT=$TOOLCHAIN/sysroot
export ARCH=armv7
#export CC=$TOOLCHAIN/bin/arm-linux-androideabi-gcc
#export CXX=$TOOLCHAIN/bin/arm-linux-androideabi-g++
export AR=$TOOLCHAIN/bin/arm-linux-androideabi-ar
export AS=$TOOLCHAIN/bin/arm-linux-androideabi-as
export LD=$TOOLCHAIN/bin/arm-linux-androideabi-ld
export RANLIB=$TOOLCHAIN/bin/arm-linux-androideabi-ranlib
export NM=$TOOLCHAIN/bin/arm-linux-androideabi-nm
export STRIP=$TOOLCHAIN/bin/arm-linux-androideabi-strip
export CHOST=$TOOLCHAIN/bin/arm-linux-androideabi

cd $ROOT_CURL
./configure --host=$TARGET_HOST \
  --target=$TARGET_HOST \
  --prefix=$PWD/build/arm64-v8a \
  --with-ssl=$ROOT_OPENSSL \
  --disable-shared

make

exit 0


real_path() {
	[[ $1 = /* ]] && echo "$1" || echo "$PWD/${1#./}"
}

#Change this env variable to the number of processors you have
if [ -f /proc/cpuinfo ]; then
	JOBS=$(grep flags /proc/cpuinfo |wc -l)
elif [ ! -z $(which sysctl) ]; then
	JOBS=$(sysctl -n hw.ncpu)
else
	JOBS=2
fi

REL_SCRIPT_PATH="$(dirname $0)"
SCRIPTPATH=$(real_path $REL_SCRIPT_PATH)
CURLPATH="$H/lib/curl-7.69.1"
SSLPATH="$H/lib/openssl-1.1.1f"

if [ -z "$NDK_ROOT" ]; then
	echo "Please set your NDK_ROOT environment variable first"
	exit 1
fi

if [[ "$NDK_ROOT" == .* ]]; then
	echo "Please set your NDK_ROOT to an absolute path"
	exit 1
fi

#Configure OpenSSL
cd $SSLPATH
./Configure android-arm -D__ANDROID_API__=29 no-asm no-shared no-cast no-idea no-camellia
EXITCODE=$?
if [ $EXITCODE -ne 0 ]; then
	echo "Error running the ssl configure program"
	cd $PWD
	exit $EXITCODE
fi

SAV_PATH=$PATH
# Build static libssl and libcrypto, required for cURL's configure
cd $SCRIPTPATH
PATH=$ANDROID_NDK_HOME/toolchains/llvm/prebuilt/linux-x86_64/bin:$ANDROID_NDK_HOME/toolchains/arm-linux-androideabi-4.9/prebuilt/linux-x86_64/bin:$SAV_PATH
make clean;make
EXITCODE=$?
if [ $EXITCODE -ne 0 ]; then
	echo "Error building the libssl and libcrypto"
	cd $PWD
	exit $EXITCODE
fi

#Configure toolchain
TOOLCHAIN=$SCRIPTPATH/../toolchain
if [ -d "$TOOLCHAIN" ]; then
	echo Removing existing toolchain
	rm -rf "$TOOLCHAIN"
fi
$NDK_ROOT/build/tools/make-standalone-toolchain.sh --arch=arm --platform=$TARGET --install-dir=$TOOLCHAIN

# Setup cross-compile environment
export SYSROOT=$TOOLCHAIN/sysroot
export ARCH=armv7
export CC=$TOOLCHAIN/bin/arm-linux-androideabi-gcc
export CXX=$TOOLCHAIN/bin/arm-linux-androideabi-g++
export AR=$TOOLCHAIN/bin/arm-linux-androideabi-ar
export AS=$TOOLCHAIN/bin/arm-linux-androideabi-as
export LD=$TOOLCHAIN/bin/arm-linux-androideabi-ld
export RANLIB=$TOOLCHAIN/bin/arm-linux-androideabi-ranlib
export NM=$TOOLCHAIN/bin/arm-linux-androideabi-nm
export STRIP=$TOOLCHAIN/bin/arm-linux-androideabi-strip
export CHOST=$TOOLCHAIN/bin/arm-linux-androideabi

#Configure cURL
cd $CURLPATH
if [ ! -x "$CURLPATH/configure" ]; then
	echo "Curl needs external tools to be compiled"
	echo "Make sure you have autoconf, automake and libtool installed"

	./buildconf

	EXITCODE=$?
	if [ $EXITCODE -ne 0 ]; then
		echo "Error running the buildconf program"
		cd $PWD
		exit $EXITCODE
	fi
fi

export CFLAGS="--sysroot=$SYSROOT -march=$ARCH -mthumb"
export CPPFLAGS="$CFLAGS -I$TOOLCHAIN/include -DANDROID -DCURL_STATICLIB"
export LIBS="-lssl -lcrypto"
export LDFLAGS="-march=$ARCH -L$SCRIPTPATH/obj/local/armeabi-v7a"
./configure \
	--host=arm-linux-androideabi \
	--target=arm-linux-androideabi \
	--with-ssl=$SSLPATH \
	--enable-static \
	--disable-shared \
	--disable-verbose \
	--enable-threaded-resolver \
	--enable-libgcc \
	--enable-ipv6

EXITCODE=$?
if [ $EXITCODE -ne 0 ]; then
	echo "Error running the configure program"
	cd $PWD
	exit $EXITCODE
fi

#Build cURL
$NDK_ROOT/ndk-build -j$JOBS -C $SCRIPTPATH curl
EXITCODE=$?
if [ $EXITCODE -ne 0 ]; then
	echo "Error running the ndk-build program"
	exit $EXITCODE
fi

#Strip debug symbols and copy to the prebuilt folder
PLATFORMS=(arm64-v8a x86_64 armeabi-v7a x86)
DESTDIR=$SCRIPTPATH/../prebuilt-with-ssl/android

for p in ${PLATFORMS[*]}; do
  mkdir -p $DESTDIR/$p
  STRIP=$($SCRIPTPATH/ndk-which strip $p)

  SRC=$SCRIPTPATH/obj/local/$p/libcurl.a
  DEST=$DESTDIR/$p/libcurl.a

  if [ -z "$STRIP" ]; then
    echo "WARNING: Could not find 'strip' for $p"
    cp $SRC $DEST
  else
    $STRIP $SRC --strip-debug -o $DEST
  fi
done

#Copying cURL headers
if [ -d "$DESTDIR/include" ]; then
	echo "Cleaning headers"
	rm -rf "$DESTDIR/include"
fi
cp -R $CURLPATH/include $DESTDIR/
rm $DESTDIR/include/curl/.gitignore

cd $PWD
exit 0
