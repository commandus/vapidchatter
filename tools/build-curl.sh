#!/bin/bash
# wget https://curl.haxx.se/download/curl-7.69.1.tar.gz
# tar xvfz curl-7.69.1.tar.gz
#
# https://github.com/robertying/openssl-curl-android/releases
# mkdir -p app/src/main/jniLibs/x86/ app/src/main/jniLibs/x86_64/ app/src/main/jniLibs/armeabi-v7a/ app/src/main/jniLibs/arm64-v8a/
#
# echo Download from https://github.com/robertying/openssl-curl-android/releases
# exit 0

DEST=~/src/vapidchatter/app/src/main/jniLibs
H=~
HOST_TAG=linux-x86_64
OPENSSL_VER=1.1.1f
ROOT_OPENSSL=$H/lib/openssl-$OPENSSL_VER
JNI_LIBS=%H/src/vapidchatter/app/src/main/jniLibs

CURL_VER=7.69.1
ROOT_CURL=$H/lib/curl-$CURL_VER

export ANDROID_NDK_HOME=$H/Android/Sdk/ndk/21.0.6113669
export TOOLCHAIN=$ANDROID_NDK_HOME/toolchains/llvm/prebuilt/$HOST_TAG
# 29 is ok
MIN_SDK_VERSION=21

cd $ROOT_CURL
autoreconf -fi

export AR=$TOOLCHAIN/bin/llvm-ar
export LD=$TOOLCHAIN/bin/ld
export RANLIB=$TOOLCHAIN/bin/llvm-ranlib
export STRIP=$TOOLCHAIN/bin/llvm-strip

TargetHosts=( "aarch64-linux-android" "armv7a-linux-androideabi" "i686-linux-android" "x86_64-linux-android")
AndroidArchs=( "arm64-v8a" "armeabi-v7a" "x86" "x86_64")
Archslength=${#TargetHosts[@]}
for (( a=0; a<${Archslength}; a++ ));
do
	export ANDROID_ARCH=${AndroidArchs[$a]}
	if [ ! -f $DEST/$ANDROID_ARCH/libcurl.a ]; then
		export TARGET_HOST=${TargetHosts[$a]}
		echo "================ $ANDROID_ARCH ================"
		export CC=$TOOLCHAIN/bin/$TARGET_HOST$MIN_SDK_VERSION-clang
		export CXX=$TOOLCHAIN/bin/$TARGET_HOST$MIN_SDK_VERSION-clang++
		export LDFLAGS="-L$DEST/$ANDROID_ARCH"
		make clean
		./configure --host=$TARGET_HOST --target=$TARGET_HOST --with-openssl=$ROOT_OPENSSL --with-pic --disable-shared
		make
		mkdir -p $DEST/$ANDROID_ARCH
		cp $ROOT_CURL/lib/.libs/libcurl.a $DEST/$ANDROID_ARCH
	fi
done
