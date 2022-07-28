set C_INCLUDE_PATH=C:\devtools\MinGW\include

# jextract mylib.h
jextract --source --output src -t org.mylib -I %C_INCLUDE_PATH% -I . -l mylib mylib.h

jextract --output classes -t org.mylib -I %C_INCLUDE_PATH% -I . -l mylib mylib.h