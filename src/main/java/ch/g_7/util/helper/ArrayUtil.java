package ch.g_7.util.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class ArrayUtil {


    @SafeVarargs
    public static <T> T[] genArrayOfNullables(Supplier<T>... suppliers){
        List<T> ts = new ArrayList<>();
        for (Supplier<T> supplier : suppliers) {
            try {
                ts.add(supplier.get());
            }catch (NullPointerException e){ }
        }
        @SuppressWarnings("unchecked")
        T[] array = (T[]) new Object[ts.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = ts.get(i);
        }
        return array;
    }


    public static int[] toUnsignedBytes(byte[] bytes) {
        int[] uBytes = new int[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            uBytes[i] = bytes[i] & 0xff;
        }
        return uBytes;
    }

    public static byte[] toSignedBytes(int[] uBytes) {
        byte[] bytes = new byte[uBytes.length];
        for (int i = 0; i < uBytes.length; i++) {
            bytes[i] = (byte) uBytes[i];
        }
        return bytes;
    }

    public static <T, K> K[] mapArray(T[] from, K[] to, Function<T, K> mapper) {
        for (int i = 0; i < from.length; i++) {
            to[i] = mapper.apply(from[i]);
        }
        return to;
    }
}
