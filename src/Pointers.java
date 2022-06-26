import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySession;

import static java.lang.foreign.ValueLayout.JAVA_INT;


/**
 * Panama Pointers
 */
public class Pointers {
    public static void main(String[] args) {
      try (var memorySession = MemorySession.openConfined()) {
        System.out.println("\nCreating Pointers:");

        // int x = 5;
        var x = memorySession.allocate(JAVA_INT, 5);

        // int *ptr;
        MemoryAddress address = x.address();             // obtain address

        // ptr = &x;
        MemoryAddress ptr = address;
        //MemorySegment ptrVal = MemorySegment.ofAddress(ptr, 8, scope);
        // Output value: x = 5 and ptr's value = 5
        System.out.printf("           x = %d    address = %x %n", x.get(JAVA_INT, 0), x.address().toRawLongValue());
        System.out.printf(" ptr's value = %d    address = %x %n", ptr.get(JAVA_INT, 0), ptr.address().toRawLongValue());
//        System.out.printf(" ptr's value = %d    address = %x %n", ptrVal.get(C_INT, 0), ptrVal.address().toRawLongValue());

         // Change x = 10;
        x.set(JAVA_INT, 0, 10);
        System.out.printf(" Changing x's value to: %d %n", x.get(JAVA_INT, 0));

        // Output after change
        System.out.printf("           x = %d    address = %x %n", x.get(JAVA_INT, 0), x.address().toRawLongValue());
        System.out.printf(" ptr's value = %d    address = %x %n", ptr.get(JAVA_INT, 0), ptr.address().toRawLongValue());
      }
   }
}

