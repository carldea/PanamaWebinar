
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import static java.lang.foreign.ValueLayout.JAVA_DOUBLE;

/**
 * Primitive Arrays of floating point values (C double).
 */
public class PrimitiveArray {
    public static void main(String[] args) {
       try (var memorySession = MemorySession.openConfined()) {

           System.out.println("An array of data");
           MemorySegment cDoubleArray = memorySession.allocateArray(JAVA_DOUBLE, new double[] {
                   1.0, 2.0, 3.0, 4.0,
                   1.0, 1.0, 1.0, 1.0,
                   3.0, 4.0, 5.0, 6.0,
                   5.0, 6.0, 7.0, 8.0
           });

           for (long i = 0; i < 16; i++) {
               if (i>0 && i % 4 == 0) {
                   System.out.println();
               }
               System.out.printf(" %f ", cDoubleArray.getAtIndex(JAVA_DOUBLE, i ));
           }
           System.out.println();
           System.out.println("An array of data (doubled)");
           for (long i = 0; i < 16; i++) {
               cDoubleArray.setAtIndex(JAVA_DOUBLE, i,cDoubleArray.getAtIndex(JAVA_DOUBLE, i ) * 2);
           }
           for (long i = 0; i < 16; i++) {
               if (i>0 && i % 4 == 0) {
                   System.out.println();
               }
               System.out.printf(" %f ", cDoubleArray.getAtIndex(JAVA_DOUBLE, i ));
           }
           System.out.println();
       }
    }
}