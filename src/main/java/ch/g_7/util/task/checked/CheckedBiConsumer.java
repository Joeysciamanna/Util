package ch.g_7.util.task.checked;

public interface CheckedBiConsumer<T, R> {

    void apply(T t, R r) throws Exception;

}
