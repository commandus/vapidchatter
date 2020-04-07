#!/bin/sh
H=/home/andrei
VER=1.2.11
TAR_ZLIB=zlib-$VER.tar.gz
URL_ZLIB=http://zlib.net/zlib-$VER.tar.gz
# wget http://zlib.net/zlib-$VER.tar.gz
# tar xvfz zlib-$VER.tar.gz
ROOT_ZLIB=$H/lib/zlib-$VER
ROOT_PROJECT=$H/src/vapidchatter
export ANDROID_NDK_HOME=$H/Android/Sdk/ndk/21.0.6113669

LIBA=$ROOT_PROJECT/app/src/main/jniLibs
LIB_EABI=$LIBA/armeabi-v7a
LIB_86=$LIBA/x86
LIB_ARM64=$LIBA/arm64-v8a
LIB_X86_64=$LIBA/x86_64

rm $LIB_EABI/libz.a
rm $LIB_EABI/libz.so
rm $LIB_86/libz.a
rm $LIB_86/libz.so
rm $LIB_ARM64/libz.a
rm $LIB_ARM64/libz.so
rm $LIB_X86_64/libz.a
rm $LIB_X86_64/libz.so

cd $ROOT_ZLIB
SAV_PATH=$PATH
LIBS="$ROOT_ZLIB/libz.so $ROOT_ZLIB/libz.a"

export CC=armv7a-linux-androideabi29-clang++
export CPP=armv7a-linux-androideabi29-clang++
export AR=arm-linux-androideabi-ar

# aarch64-linux-android-4.9  arm-linux-androideabi-4.9  x86-4.9  x86_64-4.9
# 32bit

export prefix=$LIB_EABI
if [ ! -e $LIB_EABI/libz.so ]; then
PATH=$ANDROID_NDK_HOME/toolchains/llvm/prebuilt/linux-x86_64/bin:$ANDROID_NDK_HOME/toolchains/arm-linux-androideabi-4.9/prebuilt/linux-x86_64/bin:$SAV_PATH
./configure
make clean;make
cp $LIBS $LIB_EABI
fi

exit 0

if [ ! -e $LIB_86/libz.so ]; then
PATH=$ANDROID_NDK_HOME/toolchains/llvm/prebuilt/linux-x86_64/bin:$ANDROID_NDK_HOME/toolchains/x86-4.9/prebuilt/linux-x86_64/bin:$SAV_PATH
./configure
make clean;make
cp $LIBS $LIB_86
fi

# 64bit
if [ ! -e $LIB_ARM64/libz.so ]; then
PATH=$ANDROID_NDK_HOME/toolchains/llvm/prebuilt/linux-x86_64/bin:$ANDROID_NDK_HOME/toolchains/aarch64-linux-android-4.9/prebuilt/linux-x86_64/bin:$SAV_PATH
./configure
make clean;make
cp $LIBS $LIB_ARM64
fi

if [ ! -e $LIB_X86_64/libz.so ]; then
PATH=$ANDROID_NDK_HOME/toolchains/llvm/prebuilt/linux-x86_64/bin:$ANDROID_NDK_HOME/toolchains/x86_64-4.9/prebuilt/linux-x86_64/bin:$SAV_PATH
./configure
make clean;make
cp $LIBS $LIB_X86_64
fi
