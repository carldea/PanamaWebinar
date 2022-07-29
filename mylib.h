#include <stdio.h>

void my_function();
void my_callback_function(void (*ptrToFunction)());
void my_callback_function2(void (*ptrToFunction)(int));

struct Point {
  int x;
  int y;
};

int my_function_struct(struct Point pt);
void my_callback_function3(int (*ptrToFunction)(struct Point));