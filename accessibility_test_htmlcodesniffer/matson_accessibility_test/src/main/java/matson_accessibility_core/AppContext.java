package matson_accessibility_core;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class AppContext {
	private static final String LOCATOR_PROPERTIES_FILE_NAME = "/locators.properties";
	private static final String APPLICATION_CONFIG_PROPERTIES_FILE_NAME = "/appConfig.properties";
	private static final String DEFAULT_BROWSER_KEY = "browser";
	private static final String DEFAULT_PLATFORM_KEY = "platform";
	private static final String MAX_RETRY_COUNT_KEY = "maxRetryCount";
	private static AppContext instance = new AppContext();
	private static Properties locatorProps = new Properties();
	private static Properties appConfigProps = new Properties();
	

	private AppContext() {
		// Do-nothing..Do not allow to initialize this class from outside
	}

	public static AppContext getInstance() {
		return instance;
	}

	public Properties getLocatorProps() {
		if (locatorProps.isEmpty()) {
			locatorProps = loadProperties(LOCATOR_PROPERTIES_FILE_NAME);
		}
		return locatorProps;
	}

	public Properties getAppConfigProps() {
		if (appConfigProps.isEmpty()) {
			appConfigProps = loadProperties(APPLICATION_CONFIG_PROPERTIES_FILE_NAME);
		}
		return appConfigProps;
	}

	public void loadDefaultProperties() {
		if (locatorProps.isEmpty()) {
			locatorProps = loadProperties(LOCATOR_PROPERTIES_FILE_NAME);
		}
		if (appConfigProps.isEmpty()) {
			appConfigProps = loadProperties(APPLICATION_CONFIG_PROPERTIES_FILE_NAME);
		}
	}

	public Properties loadProperties(final String fileName) {
		InputStream inputStream = null;
		Properties props = new Properties();
		try {
			String propFileName = fileName;
			inputStream = getClass().getResourceAsStream(propFileName);

			if (inputStream != null) {
				props.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();

				}
			}
		}
		return props;
	}

	public String getDefaultBrowser() {
		String browser = null;
		if (appConfigProps.containsKey(DEFAULT_BROWSER_KEY)) {
			browser = appConfigProps.getProperty(DEFAULT_BROWSER_KEY);
		}
		return browser;
	}

	public String getPlatformName() {
		String platform = null;
		if (appConfigProps.containsKey(DEFAULT_PLATFORM_KEY)) {
			platform = appConfigProps.getProperty(DEFAULT_PLATFORM_KEY);
		}
		return platform;
	}

	public static String getApplicationUrl() {
		System.out.println("url1:"+appConfigProps.getProperty("application.base.url"));
		
		return appConfigProps.getProperty("application.base.url");
	}

	public String getOnlineServerGui() {
		return appConfigProps.getProperty("online.server.name.gui");
	}

	public int getMaxRetryCount() {
		return Integer.parseInt(appConfigProps.getProperty(MAX_RETRY_COUNT_KEY));
	}

	

	

	

	

}



