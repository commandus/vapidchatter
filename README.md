# vapidchatter

## Build SSL

Download OpenSSL 1.1.1f
```
wget -c https://www.openssl.org/source/old/1.1.1/openssl-1.1.1f.tar.gz
```

```
export NDK_ROOT=~/Android/Sdk/ndk-bundle/
export ANDROID_NDK_HOME=~/Android/Sdk/ndk-bundle/
PATH=$ANDROID_NDK_HOME/toolchains/llvm/prebuilt/linux-x86_64/bin:$ANDROID_NDK_HOME/toolchains/arm-linux-androideabi-4.9/prebuilt/linux-x86_64/bin:$PATH

make build_generated

./Configure android-arm64 -static -D__ANDROID_API__=21
make build_libs

make clean
./Configure android-arm -static -D__ANDROID_API__=21
make build_libs
```

Copy SSL headers

```
cd /home/andrei/git/curl-android-ios/openssl/include/openssl
cp asn1.h crypto.h ecdsa.h opensslv.h  srtp.h stack.h bio.h dh.h ec.h lhash.h ossl_typ.h rand.h symhacks.h bn.h dsa.h e_os2.h objects.h pem2.h rsa.h ssl2.h tls1.h buffer.h dtls1.h evp.h obj_mac.h pem.h safestack.h ssl3.h x509.h comp.h ecdh.h hmac.h opensslconf.h pkcs7.h sha.h ssl.h x509_vfy.h cryptoerr.h comperr.h bioerr.h buffererr.h evperr.h asn1err.h bnerr.h objectserr.h ecerr.h rsaerr.h dherr.h dsaerr.h x509err.h pkcs7err.h pemerr.h async.h asyncerr.h ct.h cterr.h randerr.h kdf.h kdferr.h /home/andrei/src/vapidchatter/app/src/main/third_party/openssl
```

https://github.com/gcesarmza/curl-android-ios

https://github.com/gcesarmza/curl-android-ios.git

git submodule init && git submodule update

cd curl-android-ios/curl-compile-scripts
 export NDK_ROOT=~/Android/Sdk/ndk-bundle/
./build_Android.sh


## Install gRPC

``` 
cd tools/
install-grpc-1.19.sh
```
Link to ~/lib/grpc directory

## Tests

2a00:1fa3:520:bb4a::7f
