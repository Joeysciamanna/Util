package ch.g_7.util.listner;

public interface IListener<T extends Event> {

	void handle(T event);
	
}
