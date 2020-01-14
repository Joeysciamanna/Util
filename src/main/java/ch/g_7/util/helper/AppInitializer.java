package ch.g_7.util.helper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ch.g_7.util.logging.LogLevel;
import ch.g_7.util.logging.Logger;
import ch.g_7.util.logging.adapter.UncaughtExceptionHandlerAdapter;
import ch.g_7.util.logging.writer.StreamWriter;
import ch.g_7.util.properties.Properties;

public final class AppInitializer {

	private Logger logger;
	private Properties properties;

	private boolean debugMode;
	
	private final String appRootPath;
	private final Object sourceLocator;
	
	public AppInitializer(String appRootPath, Object sourceLocator) {
		this.appRootPath = appRootPath;
		this.sourceLocator = sourceLocator;
	}
	

	public AppInitializer initProperties(String internalPropertiesFilePath) throws IOException {
		String properties = "";
		if (!IOUtil.doesFileExist(appRootPath + "/properties.prop")) {
			logger.log(LogLevel.DEBUG, "No existing properties file, new will be created");
			properties = IOUtil.readInternalString(internalPropertiesFilePath, sourceLocator);
			IOUtil.writeExternalString(appRootPath + "/properties.prop", properties);
		} else {
			logger.log(LogLevel.DEBUG, "existing properties file found");
			properties = IOUtil.readExternalString(appRootPath + "/properties.prop");
		}
		this.properties = Properties.read(properties);
		return this;
	}
	
	public AppInitializer addDefaultConfigParams() {
		properties.set("DT.mm", ()->Formator.fill(Calendar.getInstance().get(Calendar.MINUTE), '0', 2));
		properties.set("DT.ss", ()->Formator.fill(Calendar.getInstance().get(Calendar.SECOND), '0', 2));
		properties.set("DT.hh", ()->Formator.fill(Calendar.getInstance().get(Calendar.HOUR_OF_DAY), '0', 2));
		properties.set("DT.dd", ()->Formator.fill(Calendar.getInstance().get(Calendar.DAY_OF_MONTH), '0', 2));
		properties.set("DT.MM", ()->Formator.fill(Calendar.getInstance().get(Calendar.MONTH)+1, '0', 2));
		properties.set("DT.yyyy", ()->String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
		properties.set("DT.yy",   ()->String.valueOf(Calendar.getInstance().get(Calendar.YEAR)).substring(2));
		properties.set("app.root", appRootPath);
		properties.set("app.debug", String.valueOf(debugMode));
		return this;
	}
	
	public AppInitializer initLogger() {
		logger = Logger.getInstance();
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandlerAdapter(logger));
		return this;
	}
	
	public AppInitializer addConsoleLogWriters() {
		logger.addWriter(new StreamWriter(System.err, "ERROR_CONSOLE", LogLevel.FATAL, LogLevel.WARNING, LogLevel.ERROR));
		logger.addWriter(new StreamWriter(System.out, "DEBUG_CONSOLE", LogLevel.INFO, LogLevel.DEBUG));
		return this;
	}
	
	public AppInitializer addFileLogWriters() throws IOException {
		String dateTime = new SimpleDateFormat("HH:mm dd-MM-yyyy").format(new Date());
		StreamWriter errorWriter = new StreamWriter(IOUtil.getExternalOutputStream(appRootPath + "/logs/ERROR "+dateTime+".log"), "ERROR_FILE", LogLevel.FATAL, LogLevel.WARNING, LogLevel.ERROR);
		StreamWriter debugWriter = new StreamWriter(IOUtil.getExternalOutputStream(appRootPath + "/logs/DEBUG "+dateTime+".log"), "DEBUG_FILE", LogLevel.INFO, LogLevel.DEBUG);
		logger.addWriter(errorWriter);
		logger.addWriter(debugWriter);
		return this;
	}

	public AppInitializer setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
		return this;
	}
	
}
