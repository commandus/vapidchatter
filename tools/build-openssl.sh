#!/bin/sh
# last known is 29
ANDROIDAPI=21

H=/home/andrei
VER=1.1.1f
TAR_OPENSSL=openssl-$VER.tar.gz
URL_OPENSSL=https://www.openssl.org/source/$TAR_OPENSSL
ROOT_OPENSSL=$H/lib/openssl-$VER
ROOT_PROJECT=$H/src/vapidchatter
export ANDROID_NDK_HOME=$H/Android/Sdk/ndk/21.0.6113669

cd $ROOT_OPENSSL
SAV_PATH=$PATH
LIBA=$ROOT_PROJECT/app/src/main/jniLibs
LIB_EABI=$LIBA/armeabi-v7a
LIB_86=$LIBA/x86
LIB_ARM64=$LIBA/arm64-v8a
LIB_X86_64=$LIBA/x86_64

mkdir -p $LIB_EABI
mkdir -p $LIB_86
mkdir -p $LIB_ARM64
mkdir -p $LIB_X86_64

# rm $LIB_EABI/libssl.a $LIB_EABI/libcrypto.a
# rm $LIB_86/libssl.a $LIB_86/libcrypto.a
# rm $LIB_ARM64/libssl.a $LIB_ARM64/libcrypto.a
# rm $LIB_X86_64/libssl.a $LIB_X86_64/libcrypto.a

LIBS="libcrypto.a libssl.a"

# Archs are:
# OpenSSL: android-arm, android-arm64, android-mips, android-mip64, android-x86 and android-x86_64
# NDK: aarch64-linux-android-4.9  arm-linux-androideabi-4.9  x86-4.9  x86_64-4.9

# 32bit
if [ ! -e $LIB_EABI/libcrypto.a ]; then
PATH=$ANDROID_NDK_HOME/toolchains/llvm/prebuilt/linux-x86_64/bin:$ANDROID_NDK_HOME/toolchains/arm-linux-androideabi-4.9/prebuilt/linux-x86_64/bin:$SAV_PATH
./Configure android-arm -D__ANDROID_API__=$ANDROIDAPI no-shared
make clean;make
cp $LIBS $LIB_EABI
fi

if [ ! -e $LIB_86/libcrypto.a ]; then
PATH=$ANDROID_NDK_HOME/toolchains/llvm/prebuilt/linux-x86_64/bin:$ANDROID_NDK_HOME/toolchains/x86-4.9/prebuilt/linux-x86_64/bin:$SAV_PATH
./Configure android-x86 -D__ANDROID_API__=$ANDROIDAPI no-shared
make clean;make
cp $LIBS $LIB_86
fi

# 64bit
if [ ! -e $LIB_ARM64/libcrypto.a ]; then
PATH=$ANDROID_NDK_HOME/toolchains/llvm/prebuilt/linux-x86_64/bin:$ANDROID_NDK_HOME/toolchains/aarch64-linux-android-4.9/prebuilt/linux-x86_64/bin:$SAV_PATH
./Configure android-arm64 -D__ANDROID_API__=$ANDROIDAPI no-shared
make clean;make
cp $LIBS $LIB_ARM64
fi

if [ ! -e $LIB_X86_64/libcrypto.a ]; then
PATH=$ANDROID_NDK_HOME/toolchains/llvm/prebuilt/linux-x86_64/bin:$ANDROID_NDK_HOME/toolchains/x86_64-4.9/prebuilt/linux-x86_64/bin:$SAV_PATH
./Configure android-x86_64 -D__ANDROID_API__=$ANDROIDAPI no-shared
make clean;make
cp $LIBS $LIB_X86_64
fi
