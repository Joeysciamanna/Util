package ch.g_7.util.task.checked;

public interface CheckedFunction<T, R> {

    R apply(T t) throws Exception;

}
