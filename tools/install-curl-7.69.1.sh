#! /bin/bash
VERSION=7.69.1
if [ ! -d curl-${VERSION} ]; then
  wget -c --no-check-certificate https://curl.haxx.se/download/curl-${VERSION}.tar.gz
  tar xzf curl-${VERSION}.tar.gz
fi;
cd curl-${VERSION}
./configure
make
# sudo make install 
