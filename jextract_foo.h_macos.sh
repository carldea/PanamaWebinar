#!/bin/sh -x
rm -rf classes
rm -rf src/org

export C_INCLUDE_PATH=/Applications/Xcode.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX.sdk/usr/include

# jextract stdio.h
jextract --source --output src \
  -t org.unix \
  -I $C_INCLUDE_PATH \
  foo.h

jextract --output classes \
  -t org.unix \
  -I $C_INCLUDE_PATH \
  foo.h