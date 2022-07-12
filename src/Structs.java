
import java.lang.foreign.*;
import java.lang.invoke.VarHandle;

import static java.lang.foreign.ValueLayout.*;


/**
 * Panama Structs
 */
public class Structs {
    public static void main(String[] args) {
      try (var memorySession = MemorySession.openConfined()) {
        /*
            struct Point {
               int x;
               int y;
            };
        */

        System.out.println("\nCreate one Point struct:");
        GroupLayout pointStruct = MemoryLayout.structLayout(
                JAVA_INT.withName("x"),
                JAVA_INT.withName("y")
        );

        var cPoint = memorySession.allocate(pointStruct);
        VarHandle VHx = pointStruct.varHandle(MemoryLayout.PathElement.groupElement("x"));
        VarHandle VHy = pointStruct.varHandle(MemoryLayout.PathElement.groupElement("y"));
        VHx.set(cPoint, 100);
        VHy.set(cPoint, 200);
        int x = (int) VHx.get(cPoint);
        int y = (int) VHy.get(cPoint);

        System.out.printf("cPoint = (%d, %d) \n",  x, y);

//        var pCPoint = allocator.allocate(C_POINTER, cPoint);
//        System.out.printf("pCPoint = (%d) \n",  VHx.get(pCPoint.address()));

        /*
            struct Person {
               long id;
               char *name;
            };
        */
        GroupLayout personStruct = MemoryLayout.structLayout(
                JAVA_LONG.withName("id"),
                ADDRESS.withName("name")
        );
        var cPerson = memorySession.allocate(personStruct);
        VarHandle VHid = personStruct.varHandle(MemoryLayout.PathElement.groupElement("id"));
        VarHandle VHname = personStruct.varHandle(MemoryLayout.PathElement.groupElement("name"));

        VHid.set(cPerson, 12345);
        VHname.set(cPerson, memorySession.allocateUtf8String("John Doe").address());

        long id = (long) VHid.get(cPerson);
        MemoryAddress cNameTmp = (MemoryAddress) VHname.get(cPerson);

        System.out.printf("cPerson = (%d, %s) \n", id, cNameTmp.getUtf8String(0));
      }
    }
}

