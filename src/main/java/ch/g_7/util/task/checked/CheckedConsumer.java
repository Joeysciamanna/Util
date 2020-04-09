package ch.g_7.util.task.checked;

public interface CheckedConsumer<T> {

    void apply(T t) throws Exception;
}
