package ch.g_7.util.listner;

public interface IListener<T extends IEvent> {

	void onAction(T event);
	
}
