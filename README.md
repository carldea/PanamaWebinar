# A Webinar on Java's Project Panama
This webinar is a workshop as a brief introduction to OpenJDK's Project Panama. Mainly focused on the Foreign Function and Memory Access APIs ([JEP 424](https://openjdk.org/jeps/424)).

A three part webinar series:
- Project Panama: What’s it All About  
- Project Panama: Seeing it in Action
- Project Panama: A Deeper Dive

# Instructions
If you use [SDKMan](http://sdkman.io/) than you can skip the following steps. 
1. Download JDK 19-ea from https://azul.com/downloads 
2. Setup JAVA_HOME & PATH 
3. Do a quick check - java -version

## Setting JAVA_HOME & PATH
After downloading the JDK unzip or decompress the file into a directory. Use the directory path to set up the `JAVA_HOME`. Open a console or terminal to setup environment variables.

**MacOS/Linux**
```
export JAVA_HOME=<untarred_dir>/jdk-19
export PATH=$JAVA_HOME/bin:$PATH
```

**Windows**
```
set JAVA_HOME=<unzipped_dir>\jdk-19
set PATH=%JAVA_HOME%\bin;%PATH%
```

**Quick Check**

Run Java to determine the version is 19 or later as shown below:

```
$ java -version

openjdk version "19-ea" 2022-09-20
OpenJDK Runtime Environment Zulu19+59-CA (build 19-ea+25)
OpenJDK 64-Bit Server VM Zulu19+59-CA (build 19-ea+25, mixed mode, sharing)
```

# Getting Started
A HelloWorld.java uses Java Panama's Foreign Function and Memory Access APIs to access C native functions. To run the example execute the following:
```sh
java --enable-native-access=ALL-UNNAMED --enable-preview --source 19 src/HelloWorld.java
```
Or in JShell
```sh
jshell --enable-native-access=ALL-UNNAMED --enable-preview src/HelloWorld.java
```

Then,
```sh
> HelloWorld.main()

```

**Output:**
```sh
Hello World! Panama Style!
```

# Working with Primitives
A Primitive.java uses Java Panama's Foreign Function and Memory Access APIs to access C native functions. To run the example execute the following:
```sh
java --enable-native-access=ALL-UNNAMED --enable-preview --source 19 src/Primitive.java
```
Or in JShell
```sh
jshell --enable-native-access=ALL-UNNAMED --enable-preview src/Primitive.java
```

Then,
```sh
> Primitive.main()

```

**Output:**
```sh
Note: src/Primitive.java uses preview features of Java SE 19.
Note: Recompile with -Xlint:preview for details.
A slice of 3.141593 
```

# Working with Arrays of Primitives
A PrimitiveArray.java uses Java Panama's Foreign Function and Memory Access APIs to access C native functions. To run the example execute the following:
```sh
java --enable-native-access=ALL-UNNAMED --enable-preview --source 19 src/PrimitiveArray.java
```
Or in JShell
```sh
jshell --enable-native-access=ALL-UNNAMED --enable-preview src/PrimitiveArray.java
```

Then,
```sh
> PrimitiveArray.main()

```

**Output:**
```sh
Note: src/PrimitiveArray.java uses preview features of Java SE 19.
Note: Recompile with -Xlint:preview for details.
An array of data
 1.000000  2.000000  3.000000  4.000000 
 1.000000  1.000000  1.000000  1.000000 
 3.000000  4.000000  5.000000  6.000000 
 5.000000  6.000000  7.000000  8.000000 
```

# Working with C Pointers
A Pointers.java uses Java Panama's Foreign Function and Memory Access APIs to access C native functions. To run the example execute the following:
```sh
java --enable-native-access=ALL-UNNAMED --enable-preview --source 19 src/Pointers.java
```
Or in JShell
```sh
jshell --enable-native-access=ALL-UNNAMED --enable-preview src/Pointers.java
```

Then,
```sh
> Pointers.main()

```

**Output:**
```sh
Note: src/Pointers.java uses preview features of Java SE 19.
Note: Recompile with -Xlint:preview for details.

Creating Pointers:
           x = 5    address = 60000353b080 
 ptr's value = 5    address = 60000353b080 
 Changing x's value to: 10 
           x = 10    address = 60000353b080 
 ptr's value = 10    address = 60000353b080  
```

# Working with C Structs
A Structs.java uses Java Panama's Foreign Function and Memory Access APIs to access C native functions. To run the example execute the following:
```sh
java --enable-native-access=ALL-UNNAMED --enable-preview --source 19 src/Structs.java
```
Or in JShell
```sh
jshell --enable-native-access=ALL-UNNAMED --enable-preview src/Structs.java
```

Then,
```sh
> Structs.main()

```

**Output:**

```sh
Note: src/Structs.java uses preview features of Java SE 19.
Note: Recompile with -Xlint:preview for details.

Create one Point struct:
cPoint = (100, 200) 

```

# Working with an array of C Structs
A StructsArray.java uses Java Panama's Foreign Function and Memory Access APIs to access C native functions. To run the example execute the following:
```sh
java --enable-native-access=ALL-UNNAMED --enable-preview --source 19 src/StructsArray.java
```
Or in JShell
```sh
jshell --enable-native-access=ALL-UNNAMED --enable-preview src/StructsArray.java
```

Then,
```sh
> StructsArray.main()

```

**Output:**

```sh
Note: src/StructsArray.java uses preview features of Java SE 19.
Note: Recompile with -Xlint:preview for details.

Create A Sequence of Point structs:
 points[0] = (70,  60) 
 points[1] = (23,  88) 
 points[2] = (79,  73) 
 points[3] = (28,  21) 
 points[4] = (95,  23)

```
