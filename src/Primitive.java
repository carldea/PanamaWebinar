import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static java.lang.foreign.ValueLayout.*;

/**
 * Creating primitive values such as a C double.
 */
public class Primitive {

    public static void main(String[] args) {
       try (var memorySession = MemorySession.openConfined()) {
           // Creating a C double
           // x = 3.141593
           MemorySegment cDouble = memorySession.allocate(JAVA_DOUBLE, Math.PI);
           MemorySegment cString = memorySession.allocateUtf8String("A slice of %f \n");
           double x = cDouble.get(JAVA_DOUBLE, 0);
           String jString = cString.getUtf8String(0);
           System.out.printf(jString, x);

           // Get value
//           printf(cString, x);
//           fflush(NULL());

           // Output
           // A slice of 3.141593

           // x = Math.PI * 2;
           cDouble.set(JAVA_DOUBLE,  0, x * 2);
           double tempDouble = cDouble.get(JAVA_DOUBLE, 0);
           System.out.printf("Math.PI * 2 = %f \n", tempDouble);

           // Output
           // Math.PI * 2 = 6.283185
       } catch (Throwable e) {
           throw new RuntimeException(e);
       }
    }
}

