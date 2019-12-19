package ch.g_7.util.logging;

import java.text.SimpleDateFormat;
import java.util.Date;

import ch.g_7.util.helper.BiValue;
import ch.g_7.util.helper.Formator;

public final class LogFormatorFactory {

	private LogFormatorFactory() {}
	
	/**
	 * [ERROR] [dd-MM-yyy HH:mm] Some simple error message:
	 * Caused by IOException....
	 * 
	 * @return
	 */
	public static ILogFormator getDefault() {
		return new ILogFormator() {
			@Override
			public String format(LogLevel level, LogMessage message) {
				String details = message.getDetails();
				String simpleMessage = message.getSimpleMessage();
				String date = new SimpleDateFormat("dd-MM-yyy HH:mm").format(new Date());
				return  "[" + level + "] " + "["+ date +"]  " +
				simpleMessage + (details.isBlank() ? "\n" : (":\n" + details));
			}
		};
	}
	
	/**
	 * [ERROR] [dd-MM-yyy HH:mm] Some simple error message
	 * 
	 * @return
	 */
	public static ILogFormator getOneLiner() {
		return new ILogFormator() {
			@Override
			public String format(LogLevel level, LogMessage message) {
				return "[" + level + "] " + "["+ new SimpleDateFormat("dd-MM-yyy HH:mm").format(new Date()) +"]  " +
						message.getSimpleMessage();
			}
		};
	}
	
	/**
	 * [ERROR] Some simple error message:
	 * Caused by IOException....
	 * 
	 * @return
	 */
	public static ILogFormator getTimeLose() {
		return new ILogFormator() {
			@Override
			public String format(LogLevel level, LogMessage message) {
				return "[" + level + "] " + message.getSimpleMessage() + ":\n" + message.getDetails();
			}
		};
	}
	
	/**
	 * "log.level", level.toString()
	 * "log.simpleMessage", message.getSimpleMessage()
	 * "log.details", message.getDetails()
	 * "log.message", message.getMessage()
	 * "log.throwableMessage", message.getThrowable().getMessage()
	 * 
	 * @return
	 */
	public static ILogFormator getCustom(String pattern) {
		return new ILogFormator() {
			@Override
			public String format(LogLevel level, LogMessage message) {
				return Formator.format(pattern, "log.level", level.toString(),
												"log.simpleMessage", message.getSimpleMessage(),
												"log.details", message.getDetails(),
												"log.message", message.getMessage(),
												"log.throwableMessage", message.getThrowable().getMessage());
			}
		};
	}
	
	/**
	 * [ERROR] [dd-MM-yyy HH:mm] [user123221]@[TrMigrationReport] Some simple error message:
	 * Caused by IOException....
	 * 
	 * @return
	 */
	@Deprecated
	public static ILogFormator getDetailed() {
		return new ILogFormator() {
			@Override
			public String format(LogLevel level, LogMessage message) {
				return "[" + level + "] " + "["+ new SimpleDateFormat("dd-MM-yyy HH:mm").format(new Date()) +"]  " +
						message.getSimpleMessage() + ":\n" + message.getDetails();
			}
		};
	}
	
}
