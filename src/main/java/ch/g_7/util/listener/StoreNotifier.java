package ch.g_7.util.listener;

public class StoreNotifier<K extends IActionIdentifier<?>, T extends StoreEvent<K>> extends Notifier<T> {

    private final K actionId;

    public StoreNotifier(K actionId) {
        this.actionId = actionId;
    }

    public K getActionId() {
        return actionId;
    }
}
