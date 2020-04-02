package ch.g_7.util.fdt.function.cani;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class CanI {

    private final Map<IEvent<?>, CanISender<?>> canISenders;

    public CanI() {
        canISenders = new HashMap<>();
    }


    public <T> void canI(IEvent<?> event, T newValue, T oldValue, Consumer<T> resetter){
        CompletableFuture.runAsync(()->{
            CanISender<T> sender = (CanISender<T>) canISenders.get(event);
            if(!sender.canI(newValue, oldValue)){
                resetter.accept(oldValue);
            }
        });
    }

    public <T> void canI(IEvent<?> event, T newValue, T oldValue, Runnable resetter){
        canI(event, newValue, oldValue, (v)->resetter.run());
    }

    public void register(IEvent<?> event, CanISender<?> sender){
        canISenders.put(event, sender);
    }
}
