rd classes\org\unix /s
rd src\org\unix /s

REM Assumes MinGW is installed in the root path
set C_INCLUDE_PATH=C:\MinGW\include

jextract --source --output src -t org.unix -I %C_INCLUDE_PATH% foo.h

jextract --output classes -t org.unix -I %C_INCLUDE_PATH% foo.h