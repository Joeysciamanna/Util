package ch.g_7.util.listener;

public class StoreEvent extends Event {

    private final IActionIdentifier<?> actionId;

    public StoreEvent(IActionIdentifier<?> actionId) {
        this.actionId = actionId;
    }

    public IActionIdentifier<?> getActionId() {
        return actionId;
    }
}
