# A Webinar on Java's Project Panama
This webinar is a workshop as an introduction to OpenJDK's Project Panama. Mainly focused on the Foreign Function and Memory Access APIs ([JEP 424](https://openjdk.org/jeps/424)).

A three part webinar series:
- Project Panama: [What’s it All About](https://www.azul.com/resources-hub/webinars-2/project-panama-what-it-s-all-about)  
- Project Panama: [Seeing it in Action](https://www.azul.com/resources-hub/webinars-2/project-panama-webinar-seeing-it-in-action-americas)
- Project Panama: A Deeper Dive

# Instructions
**Important:** This Webinar now **requires** the `jextract` tool. Because jextract is a separate tool (not part of the standard OpenJDK download) you will need to [download](https://jdk.java.net/jextract/) the pre built binaries for your specific platform OS. 

**Note:** For arm64 MacOS you'll need to build the `jextract` tool yourself ([instructions](https://foojay.io/today/building-project-panamas-jextract-tool-by-yourself/)). 


See **Step 2** below.

If you use [SDKMan](http://sdkman.io/) than you can skip to step 2. 
1. Download JDK 19-ea from [Azul](https://azul.com/downloads) or [Oracle OpenJDK](https://jdk.java.net/19/)
2. Download the `jextract` tool - https://jdk.java.net/jextract/
   1. Set an environment variable `JEXTRACT_HOME` to `path/to/jextract_folder`
   2. Add `$JEXTRACT_HOME/bin` to `PATH`
3. Clone the repo [PanamaWebinar](https://github.com/carldea/PanamaWebinar)
   1. ```shell
       git clone git@github.com:carldea/PanamaWebinar.git
      ```
4. Download JavaFX 18.0.2
   1. Download JavaFX 18.0.2 from GluonHQ https://gluonhq.com/products/javafx/
   2. Set environment variable `PATH_TO_FX` to  `path/to/unzipped_javafx/lib` directory
5. Setup `JAVA_HOME` & `PATH` 
   1. Set environment variable `JAVA_HOME` to path/to/jdk_directory. If If downloaded from jdk.java.net for MacOS it is `path/to/jdk_directory/Content/Home`
   2. Add `$JAVA_HOME/bin` to `PATH`. 
6. Quick Check of `jextract` and `java`
   1. `java -version`
   2. `jextract -h`

On a MacOS your PATH environment variable should look similar to the following:
```shell
echo $PATH
/Users/jdoe/sdks/zulu19.0.67-ea-jdk19.0.0-ea.29-macosx_x64/bin:/Users/jdoe/sdks/jextract/bin:
```
Notice the `<JAVA_HOME>/bin` is first and `<JEXTRACT_HOME/bin>` is next. This is very important. 

The 1st and 2nd Webinar examples you'll need to generate Panama code using `jextract` against `foo.h` file.
```shell 
# Generating panama binding code

# MacOS
bash jextract_foo.h_macos.sh

# Linux
bash jextract_foo.h_linux.sh

# Windows
C:\PanamaWebinar> jextract_foo.h_windows.cmd

```

**Compile and run example code.**
If you've already set up `JAVA_HOME`, `JEXTRACT`, and `PATH` skip to `javac`. 
```shell
# Set JAVA_HOME to point to your JDK 19 area
export JAVA_HOME=path/to/jdk_directory

# Set PATH
export PATH=$JAVA_HOME/bin:$PATH

# Compiling 
javac --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.web --enable-preview --source 19 -d classes -cp classes src/PanamaNativeTimeFX.java

# Running 
java --module-path $PATH_TO_FX --add-modules=javafx.controls,javafx.web --enable-native-access=ALL-UNNAMED --enable-preview -cp classes PanamaNativeTimeFX
```

The following uses `jextract` generated binding code:
- `HelloWorld.java` - `foo.h`
- `PanamaTime.java` - `foo.h`
- `PanamNativeTimeFX.java` - `foo.h`
- `PanamaCallback.java` - `mylib.h`

**Note:** OpenJDK from https://jdk.java.net/19 the `JAVA_HOME` will be `<untarred_dir>/jdk-19/Contents/Home`. When obtained from vendors such as Azul the `JAVA_HOME` will be `<untarred_dir>/jdk-19` and other Linux/Unix OSes (convenient symbolic links will be created).

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
OpenJDK Runtime Environment (build 19-ea+30-2169)
OpenJDK 64-Bit Server VM (build 19-ea+30-2169, mixed mode)
```

# Getting Started
A HelloWorld.java uses Java Panama's Foreign Function and Memory Access APIs to access C native functions. 

To run the example execute the following:
```sh
java -classpath classes --enable-native-access=ALL-UNNAMED --enable-preview --source 19 src/HelloWorld.java
```

**Output:**
```sh
Note: src/HelloWorld.java uses preview features of Java SE 19.
Note: Recompile with -Xlint:preview for details.
Hello World! Panama style
Convert to Java String: Hello World! Panama style

(Addressable,Addressable,int)int
Life, the Universe and Everything is 42 
Millennium Falcon?…It's the ship that made the Kessel Run in less than 12 parsecs.
```

# Working with Primitives
A Primitive.java uses Java Panama's Foreign Function and Memory Access APIs to access C native functions. To run the example execute the following:
```sh
java -classpath classes --enable-native-access=ALL-UNNAMED --enable-preview --source 19 src/Primitive.java
```
**Output:**
```sh
Note: src/Primitive.java uses preview features of Java SE 19.
Note: Recompile with -Xlint:preview for details.
A slice of 3.141593 
Math.PI * 2 = 6.283185
```

# Working with Arrays of Primitives
A PrimitiveArray.java uses Java Panama's Foreign Function and Memory Access APIs to access C native functions. To run the example execute the following:
```sh
java -classpath classes --enable-native-access=ALL-UNNAMED --enable-preview --source 19 src/PrimitiveArray.java
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
An array of data (doubled)
 2.000000  4.000000  6.000000  8.000000 
 2.000000  2.000000  2.000000  2.000000 
 6.000000  8.000000  10.000000  12.000000 
 10.000000  12.000000  14.000000  16.000000  
```

# Working with C Structs
A Structs.java uses Java Panama's Foreign Function and Memory Access APIs to access C native functions. To run the example execute the following:
```sh
java -classpath classes --enable-native-access=ALL-UNNAMED --enable-preview --source 19 src/Structs.java
```

**Output:**

```sh
Note: src/Structs.java uses preview features of Java SE 19.
Note: Recompile with -Xlint:preview for details.

Create one Point struct:
cPoint = (100, 200) 
cPerson = (12345, John Doe)
```

# Working with an array of C Structs
A StructsArray.java uses Java Panama's Foreign Function and Memory Access APIs to access C native functions. To run the example execute the following:
```sh
java -classpath classes --enable-native-access=ALL-UNNAMED --enable-preview --source 19 src/StructsArray.java
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
# JavaFX Clock using Native time.h library
This demo requires JavaFX, JDK 19, jextract to generate binding code.
- 1st terminal/console
    - Set `JAVA_HOME` to `<path to >/jextract/build/jextract` directory
    - Set `PATH` to $JAVA_HOME/bin:$PATH
    - Quick check `jextract -h`
    - cd to `PanamaWebinar` directory
- 2nd terminal/console
    - Set `JAVA_HOME` to `<path to jdk>/zulu19.0.67-ea-jdk19.0.0-ea.29` directory (depends on your JDK for your OS. Check directory)
    - Set `PATH` to $JAVA_HOME/bin:$PATH
    - Quick check `java -version`
    - cd to `PanamaWebinar` directory


**For MacOS and Linux:**
Assuming your `JAVA_HOME`, `JEXTRACT_HOME` and `PATH` is correctly set.

Execute the following commands for your OS:
```shell
# Mac
bash jextract_foo.h_macos.sh 

# Linux
bash jextract_foo.h_linux.sh 

# Windows
jextract_foo.h_windows.cmd
```

Now you're ready to compile and run the JavaFX application code.
If you've already set up `JAVA_HOME`, `JEXTRACT`, and `PATH` skip to `javac`.

```shell
# JavaFX libraries Set PATH_TO_FX to <path/to/javafx_unzipped_directoy>/lib 
export PATH_TO_FX=<path/to/javafx_unzipped_directoy>/lib

# OpenJDK 19-ea Set JAVA_HOME to point to your JDK 19 area
export JAVA_HOME=<path to jdk>/zulu19.0.67-ea-jdk19.0.0-ea.29

# Set PATH
export PATH=$JAVA_HOME/bin:$PATH

# Compiling 
javac --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.web --enable-preview --source 19 -d classes -cp classes src/PanamaNativeTimeFX.java

# Running 
java --module-path $PATH_TO_FX --add-modules=javafx.controls,javafx.web --enable-native-access=ALL-UNNAMED --enable-preview -cp classes PanamaNativeTimeFX
```
 

**For Windows:**
```shell
rem Set PATH_TO_FX to <path\to\javafx_unzipped_directoy>\lib 
set PATH_TO_FX=<path\to\javafx_unzipped_directoy>\lib

rem Set JAVA_HOME to point to your JDK 19 area
set JAVA_HOME=<path to unzipped jdk>\zulu19.0.67-ea-jdk19.0.0-ea.29

rem Set PATH
set PATH=%JAVA_HOME%/bin:%PATH%

rem Compiling 
javac --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.web --enable-preview --source 19 -d classes -cp classes src\PanamaNativeTimeFX.java

rem Running 
java --module-path %PATH_TO_FX% --add-modules=javafx.controls,javafx.web --enable-native-access=ALL-UNNAMED --enable-preview -cp classes PanamaNativeTimeFX
```

# Working with C Pointers
A Pointers.java uses Java Panama's Foreign Function and Memory Access APIs to access C native functions. To run the example execute the following:
```sh
java -classpath classes --enable-native-access=ALL-UNNAMED --enable-preview --source 19 src/Pointers.java
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

# Working with C Function Pointers 
A `PanamaCallback.java` uses Java Panama's Foreign Function and Memory Access APIs to access C function pointers. 

### Install a C compiler for your operating system

**Step 1:** Install C compiler

**MacOS**
Installing a C compiler:
```
xcode-select —install
```
> Password:

**Linux**
```
sudo apt update
sudo apt install build-essential
```

**Windows**

Download Mingw at: https://sourceforge.net/projects/mingw-w64/

Once install please reference the directory unzipped into. You may need to update shell scripts. The scripts assume paths to C header files and library directory locations.

**Step 2:** To verify type the following:
```
gcc --version
```

### Compile mylib.c into a binary library.
To compile `mylib.h` and `mylib.c` do the following:
```shell
# Mac
bash compile_mylib.c_macos.sh

# Linux
bash compile_mylib.c_linux.sh
```
```shell
# Windows
compile_mylib.c_windows.cmd
```
### Generating Panama binding against mylib.h using `jextract`
If you've already set up `JAVA_HOME`, `JEXTRACT`, and `PATH` skip to `javac`.
```shell
# MacOS
bash jextract_mylib.h_macos.sh

# Linux
bash jextract_mylib.h_linux.sh
```
```shell
# Windows
jextract_mylib.h_windows.cmd
```

### Compile `PanamaCallback.java` class
Be in a terminal window session that has the JDK's (non jextract) `JAVA_HOME` and `PATH`. This is neccessary to run the scripts below.
The `PanamaCallback.java` depends on the `mylib` library and the generated code from jextract targeting `mylib.h`. 

Assuming the mylib library exists and the Panama binding code exists. Run the following:
```shell
# MacOS or Linux
bash compile_PanamaCallback.java.sh
```

```shell
# Windows
compile_PanamaCallback.java_windows.cmd
```

### Run PanamaCallback.java 
To run the following commands this assumes you've set up the following environment variables `JAVA_HOME`, `JEXTRACT`, and `PATH` and performed a `jextract` on `mylib.h`.

```shell
# MacOS or Linux
bash run_PanamaCallback.java.sh
```

```shell
# Windows
run_PanamaCallback.java.cmd
```
**Output:**
```shell
[Java] Callbacks! Panama style
[C] Inside mylib's C function my_callback_function().
[C]   Now invoking Java's callMePlease() static method.
[JAVA] Inside callMePlease() method - I'm being called from C.
[C] Inside mylib's C function my_callback_function2().
[C]   Now invoking Java's doubleMe(int) static method.
[JAVA] Inside doubleMe() method, 123 times 2 = 246.
```