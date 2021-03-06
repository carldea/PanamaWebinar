#!/bin/sh -x
rm -rf classes/org/unix
rm -rf src/org/unix

export C_INCLUDE_PATH=/usr/include

# jextract stdio.h
jextract --source --output src \
  -t org.unix \
  -I $C_INCLUDE_PATH \
  foo.h

jextract --output classes \
  -t org.unix \
  -I $C_INCLUDE_PATH \
  foo.h