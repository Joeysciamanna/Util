package ch.g_7.util.listener;

public class Event {

    private boolean consumed;



    public void consume(){
        consumed = true;
    }

    public boolean isConsumed() {
        return consumed;
    }
}
