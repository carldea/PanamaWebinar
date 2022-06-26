import java.lang.foreign.Addressable;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.*;

/**
 * Creating primitive values such as a C double.
 */
public class Primitive {

    public static void main(String[] args) {
       try (var memorySession = MemorySession.openConfined()) {
           // Creating a C double
           var cDouble = memorySession.allocate(JAVA_DOUBLE, Math.PI);
           var msStr = "A slice of %f \n";
           System.out.printf(msStr, cDouble.get(JAVA_DOUBLE, 0));
           cDouble.set(JAVA_DOUBLE,  0, cDouble.get(JAVA_DOUBLE, 0) * 2);
           System.out.printf(msStr, cDouble.get(JAVA_DOUBLE, 0));


//           var msgStr = memorySession.allocateUtf8String("A slice of %f \n");
//           MethodHandle printfMH = Utils.create("printf",
//                   FunctionDescriptor.of(JAVA_INT, ADDRESS, JAVA_DOUBLE));
//
//           printfMH.invoke(msgStr,  cDouble.get(JAVA_DOUBLE, 0));
//           msgStr.getUtf8String(0);

       } catch (Throwable e) {
           throw new RuntimeException(e);
       }
    }
}

