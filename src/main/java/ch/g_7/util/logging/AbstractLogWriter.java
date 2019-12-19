package ch.g_7.util.logging;

import java.io.IOException;

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
	public void write(LogLevel level, LogMessage message) throws IOException {
		write(level, formator.format(level, message));
	}
	
	public abstract void write(LogLevel level, String log) throws IOException;
	
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
