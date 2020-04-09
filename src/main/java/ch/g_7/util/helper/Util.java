package ch.g_7.util.helper;

import ch.g_7.util.task.checked.Checked;

import java.util.function.Consumer;
import java.util.function.Function;

public class Util {

    @SafeVarargs
	public static <T> boolean isEqual(T self, Object o, Function<T,Object>... extractors) {
    	if(self == o) return true;
    	if(!self.getClass().equals(o.getClass())) return false;
    	
    	@SuppressWarnings("unchecked")
		T obj = (T) o;
    	for (Function<T, Object> extractor : extractors) {
			if(!extractor.apply(self).equals(extractor.apply(obj))) return false;
		}
    	return true;
    }
    

    public static <T> void ifNotNull(T t, Consumer<T> consumer){
    	if(t != null){
			consumer.accept(t);
    	}
	}
    
	@SuppressWarnings("unchecked")
	public static <T> T cast(Object object) {
		return (T) object;
	}

	public static void close(AutoCloseable... closeables){
		for (AutoCloseable closeable : closeables) {
			Checked.run(closeable::close);
		}
	}
}
