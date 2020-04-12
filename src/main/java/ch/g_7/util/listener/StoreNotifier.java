package ch.g_7.util.listener;

public class StoreNotifier<T extends Event> extends Notifier<T> {

    private final IActionIdentifier<?> actionId;

    public StoreNotifier(IActionIdentifier<?> actionId) {
        this.actionId = actionId;
    }

    public IActionIdentifier<?> getActionId() {
        return actionId;
    }
}
