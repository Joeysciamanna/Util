package ch.g_7.util.listener;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class Store {

    private final List<StoreNotifier<?>> notifiers;

    public Store() {
        this.notifiers = new ArrayList<>();
    }

    public <T extends StoreEvent> void register(T event){
        apply(event.getActionId(), (n)->n.register(event));
    }

    public <T extends StoreEvent> void report(T event){
        apply(event.getActionId(), (n)->n.report(event));
    }

    public <T extends StoreEvent> void override(T event){
        apply(event.getActionId(), (n)->n.override(event));
    }

    public void reportLatest(IActionIdentifier<?> actionId){
        apply(actionId, Notifier::reportLatest);
    }

    public void reportLatest(){
        for (Notifier<?> notifier : notifiers) {
            notifier.reportLatest();
        }
    }

    public void reportAll(IActionIdentifier<?> actionId){
        apply(actionId, Notifier::reportAll);
    }

    public void reportAll(){
        for (Notifier<?> notifier : notifiers) {
            notifier.reportAll();
        }
    }

    public void clear(IActionIdentifier<?> actionId){
        apply(actionId, Notifier::clear);
    }

    public void clear(){
        for (Notifier<?> notifier : notifiers) {
            notifier.clear();
        }
    }

    public <T extends StoreEvent> void addListener(IListener<T> listener, IActionIdentifier<?> actionId){
        if(!apply(actionId, (Notifier<T> n)->n.addListner(listener))){
            StoreNotifier<T> notifier = new StoreNotifier<>(actionId);
            notifier.addListner(listener);
            notifiers.add(notifier);
        }
    }

    private <T extends StoreEvent> boolean apply(IActionIdentifier<?> actionId, Consumer<Notifier<T>> handler){
        for (StoreNotifier<?> notifier : notifiers) {
            if(notifier.getActionId().equals(actionId)){
                handler.accept((Notifier<T>)notifier);
                return true;
            }
        }
        return false;
    }

}
