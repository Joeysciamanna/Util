package ch.g_7.util.task.checked;

public interface CheckedSupplier<T> {

    T get() throws Exception;

}
