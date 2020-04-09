package ch.g_7.util.task.checked;

public interface CheckedBiFunction<T, R, K> {

    R apply(T t, K k) throws Exception;

}
