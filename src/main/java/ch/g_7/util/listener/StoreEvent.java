package ch.g_7.util.listener;

public class StoreEvent<T extends IActionIdentifier<?>> extends Event {

    private final T actionId;

    public StoreEvent(T actionId) {
        this.actionId = actionId;
    }

    public T getActionId() {
        return actionId;
    }
}
