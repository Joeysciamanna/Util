package ch.g_7.util.listener;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public abstract class Store<K extends IActionIdentifier<?>> {

    private final List<StoreNotifier<K, ?>> notifiers;

    public Store() {
        this.notifiers = new ArrayList<>();
    }

    public <T extends StoreEvent<K>> void register(T event){
        apply(event.getActionId(), (n)->n.register(event));
    }

    public <T extends StoreEvent<K>> void report(T event){
        apply(event.getActionId(), (n)->n.report(event));
    }

    public <T extends StoreEvent<K>> void override(T event){
        apply(event.getActionId(), (n)->n.override(event));
    }

    public void reportLatest(K actionId){
        apply(actionId, Notifier::reportLatest);
    }

    public void reportLatest(){
        for (Notifier<?> notifier : notifiers) {
            notifier.reportLatest();
        }
    }

    public void reportAll(K actionId){
        apply(actionId, Notifier::reportAll);
    }

    public void reportAll(){
        for (Notifier<?> notifier : notifiers) {
            notifier.reportAll();
        }
    }

    public void clear(K actionId){
        apply(actionId, Notifier::clear);
    }

    public void clear(){
        for (Notifier<?> notifier : notifiers) {
            notifier.clear();
        }
    }

    public <T extends StoreEvent<K>> void addListener(IListener<T> listener, K actionId){
        if(!apply(actionId, (Notifier<T> n)->n.addListner(listener))){
            StoreNotifier<K,T> notifier = new StoreNotifier<>(actionId);
            notifier.addListner(listener);
            notifiers.add(notifier);
        }
    }

    private <T extends StoreEvent<K>> boolean apply(K actionId, Consumer<Notifier<T>> handler){
        for (StoreNotifier<K, ?> notifier : notifiers) {
            if(notifier.getActionId().equals(actionId)){
                handler.accept((Notifier<T>)notifier);
                return true;
            }
        }
        return false;
    }

}
