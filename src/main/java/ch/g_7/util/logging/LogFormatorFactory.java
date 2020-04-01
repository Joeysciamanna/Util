package ch.g_7.util.logging;

import java.text.SimpleDateFormat;
import java.util.Date;

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
			public String format(LogMessage message) {
				String details = message.getStackTrace();
				String simpleMessage = message.getSimpleMessage();
				String date = new SimpleDateFormat("dd-MM-yyy HH:mm").format(new Date());
				return  Formator.fillEnd("[" + message.getLevel() + "] ",' ', 9) + "["+ date +"]  " +
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
			public String format(LogMessage message) {
				return Formator.fillEnd("[" + message.getLevel() + "] ",' ', 9) + "["+ new SimpleDateFormat("dd-MM-yyy HH:mm").format(new Date()) +"]  " +
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
			public String format(LogMessage message) {
				return Formator.fillEnd("[" + message.getLevel() + "] ",' ', 9) + message.getSimpleMessage() + ":\n" + message.getStackTrace();
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
			public String format(LogMessage message) {
				return Formator.format(pattern, "log.level", message.getLevel().toString(),
												"log.simpleMessage", message.getSimpleMessage(),
												"log.details", message.getStackTrace(),
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
			public String format(LogMessage message) {
				return Formator.fillEnd("[" + message.getLevel() + "] ",' ', 9) + "["+ new SimpleDateFormat("dd-MM-yyy HH:mm").format(new Date()) +"]  " +
						message.getSimpleMessage() + ":\n" + message.getStackTrace();
			}
		};
	}
	
}
