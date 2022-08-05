# vapidchatter

## Prerequisites

- JDK >1.8
- Gradle (optional). You can use Android Studio's Gradle plugin instead.

### Install JDK
```
sudo apt install openjdk-17-jre-headless
```

### Install Gradle
```
wget --c https://downloads.gradle-dn.com/distributions/gradle-7.5-bin.zip
mkdir /opt/gradle
unzip -d /opt/gradle gradle-7.5-bin.zip
export PATH=$PATH:/opt/gradle/gradle-7.5/bin
```

[How to install Gradle manually](https://gradle.org/install/)


### Build OpenSSL

Download OpenSSL 1.1.1f
```
wget -c --no-check-certificate https://www.openssl.org/source/old/1.1.1/openssl-1.1.1f.tar.gz
```

#### Build OpenSSL using script

Check paths
```
vi tools/build-curl.sh
```

Build

```
tools/build-curl.sh
```

#### Build OpenSSL manually

```
export ANDROID_NDK_HOME=~/Android/Sdk/ndk-bundle/

or

export ANDROID_NDK_HOME=/home/andrei/Android/Sdk/ndk/21.0.6113669

./Configure android-arm -static -D__ANDROID_API__=2


Please note 25.0.8775105 does not work!

export PATH=$ANDROID_NDK_HOME/toolchains/llvm/prebuilt/linux-x86_64/bin:$ANDROID_NDK_HOME/toolchains/arm-linux-androideabi-4.9/prebuilt/linux-x86_64/bin:$PATH
or
export PATH=/home/andrei/Android/Sdk/ndk/21.0.6113669/toolchains/llvm/prebuilt/linux-x86_64/bin:/opt/gradle/gradle-7.5/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/snap/bin

make build_generated

./Configure android-arm64 -static -D__ANDROID_API__=21
make build_libs
cp libcrypto.a libssl.a ~/src/vapidchatter/app/src/main/jniLibs/arm64-v8a/

make clean
./Configure android-arm -static -D__ANDROID_API__=21
make build_libs
cp libcrypto.a libssl.a ~/src/vapidchatter/app/src/main/jniLibs/armeabi-v7a/
```

Required OpenSSL headers already in the source directory app/src/main/third_party/openssl.

### Build Curl

First install OpenSSL (Curl depends on it).

Download Curl source
```
cd ~/lib
wget https://curl.haxx.se/download/curl-7.69.1.tar.gz
tar xvfz curl-7.69.1.tar.gz
```

Check paths
```
vi tools/build-curl.sh
```

Build

```
tools/build-curl.sh
```

Required Curl headers already in the source directory app/src/main/third_party/curl.


## Build

```
cd ~/src/vapidchatter
tools/copy-dependencies.sh
gradle build
```

## Tests

Run emulator using tools/start-emulator.sh or:
```
cd ~/Android/Sdk/tools
./emulator -list-avds
./emulator -avd Nexus_5_API_24
```

Start tests:
```
./gradlew connectedAndroidTest
```

Check output files for each test:
```
cd ~/src/vapidchatter/app/build/outputs/androidTest-results/connected/
ls ~/src/vapidchatter/app/build/outputs/androidTest-results/connected/*
```


## Install gRPC

``` 
cd tools/
install-grpc-1.19.sh
```
Link to ~/lib/grpc directory

## Tests

2a00:1fa3:520:bb4a::7f
