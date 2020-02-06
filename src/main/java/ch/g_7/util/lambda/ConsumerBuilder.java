package ch.g_7.util.lambda;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ConsumerBuilder<T> {
	
	private Consumer<T> consumer;
	
	public ConsumerBuilder(Consumer<T> consumer) {
		this.consumer = consumer;
	}
	
	public void addIf(Supplier<Boolean> condition) {
		consumer = (t)->{
			if(condition.get()) {
				consumer.accept(t);
			}
		};
	}

	public void addIfElse(Supplier<Boolean> condition, Consumer<T> otherwise) {
		consumer = (t)->{
			if(condition.get()) {
				consumer.accept(t);
			} else {
				otherwise.accept(t);
			}
		};
	}

	
	public void addWhile(Supplier<Boolean> condition) {
		consumer = (t)->{
			while(condition.get()) {
				consumer.accept(t);
			}
		};
	}

	
}
