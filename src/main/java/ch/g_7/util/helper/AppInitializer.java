package ch.g_7.util.helper;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import ch.g_7.util.io.IOUtil;
import ch.g_7.util.io.IResourceLoader;
import ch.g_7.util.logging.LogLevel;
import ch.g_7.util.logging.Logger;
import ch.g_7.util.logging.adapter.UncaughtExceptionHandlerAdapter;
import ch.g_7.util.logging.writer.StreamWriter;
import ch.g_7.util.properties.IProperties;
import ch.g_7.util.properties.PropertyProducer;

public final class AppInitializer {

	private static final Logger LOGGER = Logger.getInstance();

	private final boolean debugMode;
	private final String appRootPath;
	private final IResourceLoader resourceLoader;


	public AppInitializer(boolean debugMode, String name, IResourceLoader resourceLoader) {
		this.appRootPath  = System.getenv("APPDATA") + "/"+name+"/";
		this.debugMode = debugMode;
		this.resourceLoader = resourceLoader;
	}

	public void runDefaults(String defaultPropertiesFile){
		runDefaults(resourceLoader.loadResourceThrowRuntime(defaultPropertiesFile));
	}

	public void runDefaults(InputStream defaultProperties) {
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandlerAdapter(LOGGER));
		try {
			if(debugMode)
				addConsoleLoggers();
			addFileLoggers();
			initPropFiles(defaultProperties);
			addAppConfigParams();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		if(debugMode)
			LOGGER.log(LogLevel.WARNING, "Starting in debug mode");
	}

	public void initPropFiles(InputStream defaultProperties) throws IOException {
		String properties = "";
		if (debugMode || !IOUtil.doesFileExist(appRootPath + "/properties.prop")) {
			if(debugMode)
				LOGGER.log(LogLevel.DEBUG, "Debug mode enabled, property file will be overwritten");
			else
				LOGGER.log(LogLevel.DEBUG, "No existing property file, new will be created");

			properties = IOUtil.toString(defaultProperties);
			IOUtil.writeExternalString(appRootPath + "/properties.prop", properties);
		} else {
			LOGGER.log(LogLevel.DEBUG, "existing property file found");
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
		LOGGER.addWriter(new StreamWriter(IOUtil.getExternalOutputStream(appRootPath + "/logs/error "+dateTime+".log"), "ERROR_FILE", LogLevel.FATAL, LogLevel.WARNING, LogLevel.ERROR));
		LOGGER.addWriter(new StreamWriter(IOUtil.getExternalOutputStream(appRootPath + "/logs/debug "+dateTime+".log"), "DEBUG_FILE", LogLevel.FATAL, LogLevel.WARNING, LogLevel.ERROR, LogLevel.INFO, LogLevel.DEBUG));
	}

}
