package ch.g_7.util.bitkey;

public class BitKeyUtil {

    public static int merge(int bitKey1, int bitKey2) {
        return bitKey1 | bitKey2;
    }

    public static int remove(int bitKey1, int bitKey2) {
        return bitKey1 & (~bitKey2);
    }

    public static boolean contains(int bitKey1, int bitKey2) {
        return (bitKey1 & bitKey2) != 0;
    }
}
