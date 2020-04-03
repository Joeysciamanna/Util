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


    public <T> void canI(IEvent<?> event, T newValue, T oldValue, String additionalData, Consumer<T> resetter){
        CompletableFuture.runAsync(()->{
            CanISender<T> sender = (CanISender<T>) canISenders.get(event);
            if(!sender.canI(newValue, oldValue, additionalData)){
                resetter.accept(oldValue);
            }
        });
    }

    public <T> void canI(IEvent<?> event, String data, Runnable resetter){
        CompletableFuture.runAsync(()->{
            CanISender<T> sender = (CanISender<T>) canISenders.get(event);
            if(!sender.canI(data)){
                resetter.run();
            }
        });
    }


    public <T> void canI(IEvent<?> event, T newValue, T oldValue, String additionalData, Runnable resetter){
        canI(event, newValue, oldValue, additionalData, (v)->resetter.run());
    }




    public void register(IEvent<?> event, CanISender<?> sender){
        canISenders.put(event, sender);
    }
}
