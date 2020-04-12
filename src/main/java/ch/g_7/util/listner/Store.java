package ch.g_7.util.listner;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class Store {

    private final List<Notifier<?>> notifiers;

    public Store() {
        this.notifiers = new ArrayList<>();
    }

    public <T extends Event> void register(T event){
        apply(event.getActionId(), (n)->n.register(event));
    }

    public <T extends Event> void report(T event){
        apply(event.getActionId(), (n)->n.report(event));
    }

    public <T extends Event> void override(T event){
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

    public <T extends Event> void addListener(IListener<T> listener, IActionIdentifier<?> actionId){
        if(!apply(actionId, (Notifier<T> n)->n.addListner(listener))){
            Notifier<T> notifier = new Notifier<>(actionId);
            notifier.addListner(listener);
            notifiers.add(notifier);
        }
    }

    private <T extends Event> boolean apply(IActionIdentifier<?> actionId, Consumer<Notifier<T>> handler){
        for (Notifier<?> notifier : notifiers) {
            if(notifier.getActionId().equals(actionId)){
                handler.accept((Notifier<T>)notifier);
                return true;
            }
        }
        return false;
    }

}
