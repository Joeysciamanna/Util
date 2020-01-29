package ch.g_7.util.listner;

public interface IListner<T extends IEvent> {

	void onAction(T event);
	
}
