#!/bin/sh -x
rm -rf classes/org/mylib
rm -rf src/org/mylib

export C_INCLUDE_PATH=/usr/include

# jextract mylib.h
jextract --source --output src \
  -t org.mylib \
  -I $C_INCLUDE_PATH \
  -I . \
  -l mylib \
  mylib.h

jextract --output classes \
  -t org.mylib \
  -I $C_INCLUDE_PATH \
  -l mylib \
  mylib.h