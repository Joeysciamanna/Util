package ch.g_7.util.listener;

public interface IListener<T extends Event> {

	void handle(T event);

}
