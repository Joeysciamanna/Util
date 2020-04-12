package ch.g_7.util.listner;

public class Event {

    private final IActionIdentifier<?> actionId;
    private boolean consumed;

    public Event(IActionIdentifier<?> actionId) {
        this.actionId = actionId;
    }


    public IActionIdentifier<?> getActionId() {
        return actionId;
    }

    public void consume(){
        consumed = true;
    }

    public boolean isConsumed() {
        return consumed;
    }
}
