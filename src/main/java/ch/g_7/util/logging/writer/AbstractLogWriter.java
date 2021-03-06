package ch.g_7.util.logging.writer;

import java.io.IOException;

import ch.g_7.util.logging.ILogFormator;
import ch.g_7.util.logging.LogFormatorFactory;
import ch.g_7.util.logging.LogLevel;
import ch.g_7.util.logging.LogMessage;

public abstract class AbstractLogWriter implements ILogWriter {

	private final String name;
	private LogLevel[] levels;
	private ILogFormator formator;
	
	public AbstractLogWriter(String name, LogLevel... levels) {
		this.name = name;
		this.levels = levels;
		this.formator = LogFormatorFactory.getDefault();
	}
	
	@Override
	public boolean isWriting(LogLevel level) {
		for (LogLevel l : levels) {
			if(l == level) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void write(LogMessage message) throws IOException {
		write(formator.format(message));
	}
	
	public abstract void write(String log) throws IOException;
	
	@Override
	public void setFormator(ILogFormator formator) {
		this.formator = formator;
	}
	
	protected void removeLevel(LogLevel level) {
		if(isWriting(level)) {
			LogLevel[] newLevels = new LogLevel[levels.length - 1];
			int i = 0;
			for (LogLevel logLevel : levels) {
				if(!(logLevel == level)) {
					newLevels[i++] = level;
				}
			}
			levels = newLevels;
		}
	}
	
	protected void addLevel(LogLevel level) {
		if(!isWriting(level)) {
			LogLevel[] newLevels = new LogLevel[levels.length + 1];
			int i = 0;
			for (LogLevel logLevel : levels) {
				newLevels[i++] = logLevel;
			}
			newLevels[i] = level;
			levels = newLevels;
		}
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void close() throws IOException {}
	
}
