package ch.g_7.util.task.checked;

import ch.g_7.util.common.Closeable;
import ch.g_7.util.logging.Logging;
import ch.g_7.util.task.checked.CheckedRunnable;
import ch.g_7.util.task.checked.CheckedSupplier;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Checked {

    public static void run(CheckedRunnable runnable){
        run(runnable, (e)->{throw new RuntimeException(e);});
    }

    public static void run(CheckedRunnable runnable, Consumer<Exception> onFail){
        try {
            runnable.run();
        } catch (Exception e) {
            onFail.accept(e);
        }
    }

    public static <T> T get(CheckedSupplier<T> supplier){
        return get(supplier, (e)->{throw new RuntimeException(e);}).get();
    }

    public static <T> Optional<T> get(CheckedSupplier<T> supplier, Consumer<Exception> onFail){
        try {
            return Optional.of(supplier.get());
        } catch (Exception e) {
            onFail.accept(e);
        }
        return Optional.empty();
    }

    public static Runnable checkedRunnable(CheckedRunnable runnable){
        return checkedRunnable(runnable, (e)->{throw new RuntimeException(e);});
    }

    public static Runnable checkedRunnable(CheckedRunnable runnable, Consumer<Exception> onFail){
        return ()->{
            try {
                runnable.run();
            } catch (Exception e) {
                onFail.accept(e);
            }
        };
    }

    public static <T> Supplier<T> checkedSupplier(CheckedSupplier<T> supplier){
        return ()->{
            try {
                return supplier.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static <T> Supplier<Optional<T>> checkedSupplier(CheckedSupplier<T> supplier, Consumer<Exception> onFail){
        return ()->{
            try {
                return Optional.of(supplier.get());
            } catch (Exception e) {
                onFail.accept(e);
            }
            return Optional.empty();
        };
    }

    public static <T> Consumer<T> checkedConsumer(CheckedConsumer<T> consumer){
        return checkedConsumer(consumer, (e)->{throw new RuntimeException(e);});
    }

    public static <T> Consumer<T> checkedConsumer(CheckedConsumer<T> consumer, Consumer<Exception> onFail){
        return (t)->{
            try {
                consumer.apply(t);
            } catch (Exception e) {
                onFail.accept(e);
            }
        };
    }

    public static <T,R> Function<T, R> checkedFunction(CheckedFunction<T, R> function){
        return (t)->{
            try {
                return function.apply(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static <T,R> Function<T, Optional<R>> checkedFunction(CheckedFunction<T, R> function, Consumer<Exception> onFail){
        return (t)->{
            try {
                return Optional.of(function.apply(t));
            } catch (Exception e) {
                onFail.accept(e);
            }
            return Optional.empty();
        };
    }
}
