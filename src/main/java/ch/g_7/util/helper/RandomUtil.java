package ch.g_7.util.helper;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {

    private static final ThreadLocalRandom random = ThreadLocalRandom.current();

    public static int random(int from, int to){
        return random.nextInt(from, to + 1);
    }

    public static boolean chance(float decimalChance){
        return random.nextFloat() <= decimalChance;
    }

    public static <T> T randomFormList(List<T> list){
        int i = random(0, list.size() - 1);
        return list.get(i);
    }

    @SafeVarargs
    public static <T> T randomFormArray(T... ts){
        return randomFormList(Arrays.asList(ts));
    }



}
