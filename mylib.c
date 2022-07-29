#include <stdio.h>
#include "mylib.h"

void my_function() {
   printf("This is a normal function.\n");
}

void my_callback_function(void (*ptrToFunction)()) {
   printf("[C] Inside mylib's C function my_callback_function().\n");
   printf("[C]   Now invoking Java's callMePlease() static method.\n");

   // Calling the passed in callback
   (*ptrToFunction)();
}

void my_callback_function2(void (*ptrToFunction)(int)) {
   printf("[C] Inside mylib's C function my_callback_function2().\n");
   printf("[C]   Now invoking Java's doubleMe(int) static method.\n");
   int x = 123;
   (*ptrToFunction)(x);   //calling the callback function
}

int my_function_struct(struct Point pt) {
   printf("pt.x + pt.y = %d\n", pt.x + pt.y);
   return pt.x + pt.y;
}
void my_callback_function3(int (*ptrToFunction)(struct Point)) {
   struct Point pt;
   pt.x = 5;
   pt.y = 4;

   int z = (*ptrToFunction)(pt);
   printf("z = %d\n", z);
}

int main() {
   printf("[C] Callbacks! \n");
   void (*ptr)() = &my_function;
   my_callback_function(ptr);

   int (*ptr2)(struct Point) = &my_function_struct;
   my_callback_function3(ptr2);

   return 0;
}