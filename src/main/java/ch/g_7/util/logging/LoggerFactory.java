package ch.g_7.util.logging;

public final class LoggerFactory {

//	private static ILogger logger;
//	
//	private LoggerFactory() {}
//	
//	public static void setLogger(ILogger logger) {
//		if(logger == null) {
//			LoggerFactory.logger = loger;
//		}else {
//			throw new IllegalStateException("Default logger allredy set");
//		}
//	}
//	
//	public static ILogger getLogger() {
//		if(logger == null) {
//			logger = new DefaultLogger();
//		}
//		return logger;
//	}
//	
//	@SuppressWarnings("unchecked")
//	public static <T extends ILogger> T getLogger(Class<T> clazz) {
//		if(logger != null && logger.getClass().equals(clazz)) {
//			return (T) logger;
//		}
//		throw new IllegalStateException("No or different logger configured");
//	
//	}
}
