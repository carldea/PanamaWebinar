// Generated by jextract

package org.unix;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
public class accessx_descriptor {

    static final  GroupLayout $struct$LAYOUT = MemoryLayout.structLayout(
        Constants$root.C_INT$LAYOUT.withName("ad_name_offset"),
        Constants$root.C_INT$LAYOUT.withName("ad_flags"),
        MemoryLayout.sequenceLayout(2, Constants$root.C_INT$LAYOUT).withName("ad_pad")
    ).withName("accessx_descriptor");
    public static MemoryLayout $LAYOUT() {
        return accessx_descriptor.$struct$LAYOUT;
    }
    static final VarHandle ad_name_offset$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("ad_name_offset"));
    public static VarHandle ad_name_offset$VH() {
        return accessx_descriptor.ad_name_offset$VH;
    }
    public static int ad_name_offset$get(MemorySegment seg) {
        return (int)accessx_descriptor.ad_name_offset$VH.get(seg);
    }
    public static void ad_name_offset$set( MemorySegment seg, int x) {
        accessx_descriptor.ad_name_offset$VH.set(seg, x);
    }
    public static int ad_name_offset$get(MemorySegment seg, long index) {
        return (int)accessx_descriptor.ad_name_offset$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void ad_name_offset$set(MemorySegment seg, long index, int x) {
        accessx_descriptor.ad_name_offset$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle ad_flags$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("ad_flags"));
    public static VarHandle ad_flags$VH() {
        return accessx_descriptor.ad_flags$VH;
    }
    public static int ad_flags$get(MemorySegment seg) {
        return (int)accessx_descriptor.ad_flags$VH.get(seg);
    }
    public static void ad_flags$set( MemorySegment seg, int x) {
        accessx_descriptor.ad_flags$VH.set(seg, x);
    }
    public static int ad_flags$get(MemorySegment seg, long index) {
        return (int)accessx_descriptor.ad_flags$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void ad_flags$set(MemorySegment seg, long index, int x) {
        accessx_descriptor.ad_flags$VH.set(seg.asSlice(index*sizeof()), x);
    }
    public static MemorySegment ad_pad$slice(MemorySegment seg) {
        return seg.asSlice(8, 8);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(int len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemoryAddress addr, MemorySession session) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, session); }
}


