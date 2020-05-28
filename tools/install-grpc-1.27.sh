#! /bin/bash

VER=v1.27.x
if [ ! -d grpc ]; then
  git clone --recurse-submodules -b $VER https://github.com/grpc/grpc.git
fi;
cd grpc
mkdir -p cmake/build
pushd cmake/build
cmake cmake ../..
if [ $? -eq 0 ]; then
  make -j 1
  sudo make install
fi
popd
