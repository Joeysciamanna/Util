package ch.g_7.util.helper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ch.g_7.util.logging.LogLevel;
import ch.g_7.util.logging.Logger;
import ch.g_7.util.logging.adapter.UncaughtExceptionHandlerAdapter;
import ch.g_7.util.logging.writer.StreamWriter;
import ch.g_7.util.properties.IProperties;
import ch.g_7.util.properties.PropertyProducer;

public final class AppInitializer {

	private Logger logger;
	

	private boolean debugMode;
	
	private final String appRootPath;
	private final Object sourceLocator;
	
	public AppInitializer(String appRootPath, Object sourceLocator) {
		this.appRootPath = appRootPath;
		this.sourceLocator = sourceLocator;
	}
	

	public AppInitializer initDefaultPropFiles(String internalPropertiesFilePath) throws IOException {
		String properties = "";
		if (!IOUtil.doesFileExist(appRootPath + "/properties.prop")) {
			logger.log(LogLevel.DEBUG, "No existing properties file, new will be created");
			properties = IOUtil.readInternalString(internalPropertiesFilePath, sourceLocator);
			IOUtil.writeExternalString(appRootPath + "/properties.prop", properties);
		} else {
			logger.log(LogLevel.DEBUG, "existing properties file found");
			properties = IOUtil.readExternalString(appRootPath + "/properties.prop");
		}
		PropertyProducer.setDefaultProperties(PropertyProducer.getProperties(properties));
		return this;
	}
	
	public AppInitializer addDefaultAppConfigParams() {
		IProperties appConfig = PropertyProducer.getAppConfig();
		appConfig.set("DT.mm", ()->Formator.fill(String.valueOf(Calendar.getInstance().get(Calendar.MINUTE)), '0', 2));
		appConfig.set("DT.ss", ()->Formator.fill(String.valueOf(Calendar.getInstance().get(Calendar.SECOND)), '0', 2));
		appConfig.set("DT.hh", ()->Formator.fill(String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)), '0', 2));
		appConfig.set("DT.dd", ()->Formator.fill(String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)), '0', 2));
		appConfig.set("DT.MM", ()->Formator.fill(String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1), '0', 2));
		appConfig.set("DT.yyyy", ()->String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
		appConfig.set("DT.yy",   ()->String.valueOf(Calendar.getInstance().get(Calendar.YEAR)).substring(2));
		appConfig.set("app.root", appRootPath);
		appConfig.set("app.debug", String.valueOf(debugMode));
		return this;
	}
	
	public AppInitializer initLogger() {
		logger = Logger.getInstance();
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandlerAdapter(logger));
		return this;
	}
	
	public AppInitializer addConsoleLoggers() {
		logger.addWriter(new StreamWriter(System.err, "ERROR_CONSOLE", LogLevel.FATAL, LogLevel.WARNING, LogLevel.ERROR));
		
		if(debugMode) {
			logger.addWriter(new StreamWriter(System.out, "DEBUG_CONSOLE", LogLevel.INFO, LogLevel.DEBUG));
		}else {
			logger.addWriter(new StreamWriter(System.out, "DEBUG_CONSOLE", LogLevel.INFO));
		}
		
		return this;
	}
	
	public AppInitializer addFileLoggers() throws IOException {
		String dateTime = new SimpleDateFormat("HH꞉mm dd-MM-yyyy").format(new Date());
		logger.addWriter(new StreamWriter(IOUtil.getExternalOutputStream(appRootPath + "/logs/ERROR "+dateTime+".log"), "ERROR_FILE", LogLevel.FATAL, LogLevel.WARNING, LogLevel.ERROR));
		
		
		if(debugMode) {
			logger.addWriter(new StreamWriter(IOUtil.getExternalOutputStream(appRootPath + "/logs/DEBUG "+dateTime+".log"), "DEBUG_FILE", LogLevel.INFO, LogLevel.DEBUG));
		}else {
			logger.addWriter(new StreamWriter(IOUtil.getExternalOutputStream(appRootPath + "/logs/DEBUG "+dateTime+".log"), "DEBUG_FILE", LogLevel.INFO));
		}
		return this;
	}

	public AppInitializer setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
		return this;
	}
	
}
