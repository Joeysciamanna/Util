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

	private static final Logger LOGGER = Logger.getInstance();

	private final String appRootFolder;
	private final boolean debugMode;
	private final String appRootPath;
	private final Object sourceLocator;
	
	public AppInitializer(boolean debugMode, String name, Object sourceLocator) {
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandlerAdapter(LOGGER));
		this.appRootFolder  = System.getenv("APPDATA") + "/name/";
		this.debugMode = debugMode;
		this.appRootPath = name;
		this.sourceLocator = sourceLocator;
	}


	public void runDefaults() {
		try {
			if(debugMode)
				addConsoleLoggers();
			addFileLoggers();
			initPropFiles("properties.prop");
			addAppConfigParams();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		if(debugMode)
			LOGGER.log(LogLevel.WARNING, "Starting in debug mode");
	}

	public void initPropFiles(String internalPropertiesFilePath) throws IOException {
		String properties = "";
		if (!IOUtil.doesFileExist(appRootPath + "/properties.prop")) {
			LOGGER.log(LogLevel.DEBUG, "No existing properties file, new will be created");
			properties = IOUtil.readInternalString(internalPropertiesFilePath, sourceLocator);
			IOUtil.writeExternalString(appRootPath + "/properties.prop", properties);
		} else {
			LOGGER.log(LogLevel.DEBUG, "existing properties file found");
			properties = IOUtil.readExternalString(appRootPath + "/properties.prop");
		}
		PropertyProducer.setDefaultProperties(PropertyProducer.getProperties(properties));
	}
	
	public void addAppConfigParams() {
		IProperties appConfig = PropertyProducer.getAppConfig();
		appConfig.set("app.root", appRootPath);
		appConfig.set("app.debug", String.valueOf(debugMode));
	}

	
	public void addConsoleLoggers() {
		LOGGER.addWriter(new StreamWriter(System.err, "ERROR_CONSOLE", LogLevel.FATAL, LogLevel.WARNING, LogLevel.ERROR));
		LOGGER.addWriter(new StreamWriter(System.out, "DEBUG_CONSOLE", LogLevel.INFO, LogLevel.DEBUG));
	}
	
	public void addFileLoggers() throws IOException {
		String dateTime = new SimpleDateFormat("HH꞉mm dd-MM-yyyy").format(new Date());
		LOGGER.addWriter(new StreamWriter(IOUtil.getExternalOutputStream(appRootPath + "/logs/ERROR "+dateTime+".log"), "ERROR_FILE", LogLevel.FATAL, LogLevel.WARNING, LogLevel.ERROR));
		LOGGER.addWriter(new StreamWriter(IOUtil.getExternalOutputStream(appRootPath + "/logs/DEBUG "+dateTime+".log"), "DEBUG_FILE", LogLevel.INFO, LogLevel.DEBUG));
	}

}
