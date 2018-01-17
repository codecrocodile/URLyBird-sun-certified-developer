/*
 * @(#)ApplicationContext.java    1.0    27 Feb 2010
 * 
 * Candidate: Christopher Hatton 
 * Prometric ID: SR6265633 
 * Candidate ID: 657115
 * 
 * Sun Certified Developer for Java 2 Platform, Standard Edition Programming
 * Assignment (GB-CX-310-252A)
 * 
 * This class is part of the Programming Assignment of the Sun Certified
 * Developer for Java 2 Platform, Standard Edition certification program, must
 * not be used out of this context and must be used exclusively by Oracle and
 * Sun Microsystems, Inc.
 */

package suncertify.application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Chris Hatton
 * 
 */
public class ApplicationContext {

	private static ApplicationContext context;

	private OperatingMode mode = OperatingMode.NETWORKED_CLIENT;

	private Level standaloneClientLoggingLevel = Level.INFO;

	private Level networkClientLoggingLevel = Level.INFO;

	private Level serverLoggingLevel = Level.INFO;

	private int standaloneClientRegistryPort = 1099;

	private int networkClientRegistryPort = 1099;

	private int serverRegistryPort = 1099;

	private String standloneClientDatabasePath = "";

	private String networkClientDatabasePath = "";

	private String serverDatabasePath = "";

	private String host = "127.0.0.1";

	private ContextPersistanceManager persistanceManager;

	private ApplicationContext() {
		super();
		persistanceManager = new ContextPersistanceManager();
	}

	/**
	 * 
	 * @return
	 */
	public synchronized static ApplicationContext getInstance() {
		if (context == null) {
			context = new ApplicationContext();
			return context;
		} else {
			return context;
		}
	}

	/**
	 * 
	 * @return
	 */
	public OperatingMode getOperatingMode() {
		return mode;
	}

	/**
	 * 
	 * @param operatingMode
	 */
	public void setOperatingMode(OperatingMode mode) {
		this.mode = mode;
	}

	/**
	 * 
	 * @return
	 */
	public Level getLoggingLevel() {
		if (mode == OperatingMode.STANDALONE_CLIENT) {
			return standaloneClientLoggingLevel;
		} else if (mode == OperatingMode.NETWORKED_CLIENT) {
			return networkClientLoggingLevel;
		} else {
			return serverLoggingLevel;
		}
	}

	/**
	 * 
	 * @param loggingLevel
	 */
	public void setLoggingLevel(Level loggingLevel) {
		if (mode == OperatingMode.STANDALONE_CLIENT) {
			standaloneClientLoggingLevel = loggingLevel;
		} else if (mode == OperatingMode.NETWORKED_CLIENT) {
			networkClientLoggingLevel = loggingLevel;
		} else {
			serverLoggingLevel = loggingLevel;
		}
	}

	/**
	 * 
	 * @return
	 */
	public int getRegistryPort() {
		if (mode == OperatingMode.STANDALONE_CLIENT) {
			return standaloneClientRegistryPort;
		} else if (mode == OperatingMode.NETWORKED_CLIENT) {
			return networkClientRegistryPort;
		} else {
			return serverRegistryPort;
		}
	}

	/**
	 * 
	 * @param registryPort
	 */
	public void setRegistryPort(int registryPort) {
		if (mode == OperatingMode.STANDALONE_CLIENT) {
			standaloneClientRegistryPort = registryPort;
		} else if (mode == OperatingMode.NETWORKED_CLIENT) {
			networkClientRegistryPort = registryPort;
		} else {
			serverRegistryPort = registryPort;
		}
	}

	/**
	 * 
	 * @return
	 */
	public String getDatabasePath() {
		if (mode == OperatingMode.STANDALONE_CLIENT) {
			return standloneClientDatabasePath;
		} else if (mode == OperatingMode.NETWORKED_CLIENT) {
			return networkClientDatabasePath;
		} else {
			return serverDatabasePath;
		}
	}

	/**
	 * 
	 * @param databasePath
	 */
	public void setDatabasePath(String databasePath) {
		if (mode == OperatingMode.STANDALONE_CLIENT) {
			standloneClientDatabasePath = databasePath;
		} else if (mode == OperatingMode.NETWORKED_CLIENT) {
			networkClientDatabasePath = databasePath;
		} else {
			serverDatabasePath = databasePath;
		}
	}

	/**
	 * 
	 * @return
	 */
	public String getHost() {
		return host;
	}

	/**
	 * 
	 * @param host
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * 
	 */
	public void initContext() {
		persistanceManager.initApplicationContext();
	}

	/**
	 * 
	 */
	public void persistContext() {
		persistanceManager.persistApplicationContext();
	}

	private class ContextPersistanceManager {

		private final Logger logPersist = Logger
				.getLogger(ContextPersistanceManager.class.getName());

		private final String STANDALONE_CLIENT_LOGGING_LEVEL = "StandaloneClientLoggingLevel";

		private final String NETWORK_CLIENT_LOGGING_LEVEL = "NetworkClientLoggingLevel";

		private final String SERVER_LOGGING_LEVEL = "ServerLoggingLevel";

		private final String STANDALONE_CLIENT_REGISTRY_PORT = "StandaloneClientRegistryPort";

		private final String NETWORK_CLIENT_REGISTRY_PORT = "NetworkClientRegistryPort";

		private final String SERVER_REGISTRY_PORT = "ServerRegistryPort";

		private final String STANDLONE_CLIENT_DATABASE_PATH = "StandloneClientDatabasePath";

		private final String NETWORK_CLIENT_DATABASE_PATH = "NetworkClientDatabasePath";

		private final String SERVER_DATABASE_PATH = "ServerDatabasePath";

		private final String REMOTE_HOST = "RemoteHost";

		private final Map<String, Level> propertyValueToLevel = new HashMap<String, Level>();

		ContextPersistanceManager() {
			super();

			propertyValueToLevel.put("OFF", Level.OFF);
			propertyValueToLevel.put("SEVERE", Level.SEVERE);
			propertyValueToLevel.put("WARNING", Level.WARNING);
			propertyValueToLevel.put("INFO", Level.INFO);
			propertyValueToLevel.put("CONFIG", Level.CONFIG);
			propertyValueToLevel.put("FINE", Level.FINE);
			propertyValueToLevel.put("FINER", Level.FINER);
			propertyValueToLevel.put("FINEST", Level.FINEST);
			propertyValueToLevel.put("ALL", Level.ALL);
		}

		private void initApplicationContext() {
			try {
				Properties savedProps = new Properties();
				FileInputStream in = new FileInputStream(
						"suncertify.properties");
				savedProps.load(in);
				in.close();
				setSavedContextValues(savedProps);
			} catch (FileNotFoundException e) {
				logPersist.info("Properties file not found. "
						+ "Writing default properties file.");
				persistApplicationContext();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private void setSavedContextValues(Properties savedProps) {
			/* Set logging levels */
			String standaloneClientLoggingValue = savedProps
					.getProperty(STANDALONE_CLIENT_LOGGING_LEVEL);
			standaloneClientLoggingLevel = propertyValueToLevel
					.get(standaloneClientLoggingValue);
			String networkClientLoggingValue = savedProps
					.getProperty(NETWORK_CLIENT_LOGGING_LEVEL);
			networkClientLoggingLevel = propertyValueToLevel
					.get(networkClientLoggingValue);
			String serverLoggingValue = savedProps
					.getProperty(SERVER_LOGGING_LEVEL);
			serverLoggingLevel = propertyValueToLevel.get(serverLoggingValue);

			/* Set registry port values */
			String standaloneClientRegPortValue = savedProps
					.getProperty(STANDALONE_CLIENT_REGISTRY_PORT);
			standaloneClientRegistryPort = Integer
					.parseInt(standaloneClientRegPortValue);
			String networkClientRegPortValue = savedProps
					.getProperty(NETWORK_CLIENT_REGISTRY_PORT);
			networkClientRegistryPort = Integer
					.parseInt(networkClientRegPortValue);
			String serverRegPortValue = savedProps
					.getProperty(SERVER_REGISTRY_PORT);
			serverRegistryPort = Integer.parseInt(serverRegPortValue);

			/* Set database paths */
			standloneClientDatabasePath = savedProps
					.getProperty(STANDLONE_CLIENT_DATABASE_PATH);
			networkClientDatabasePath = savedProps
					.getProperty(NETWORK_CLIENT_DATABASE_PATH);
			serverDatabasePath = savedProps.getProperty(SERVER_DATABASE_PATH);

			/* Set host URL */
			host = savedProps.getProperty(REMOTE_HOST);
		}

		private void persistApplicationContext() {
			try {
				Properties unsavedProps = new Properties();
				setContextValuesToSave(unsavedProps);
				FileOutputStream out = new FileOutputStream(
						"suncertify.properties");
				unsavedProps.store(out, "URLyBird Configuration Settings");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private void setContextValuesToSave(Properties unsavedProps) {
			/* Set logging levels */
			unsavedProps.setProperty(STANDALONE_CLIENT_LOGGING_LEVEL,
					standaloneClientLoggingLevel.toString());
			unsavedProps.setProperty(NETWORK_CLIENT_LOGGING_LEVEL,
					networkClientLoggingLevel.toString());
			unsavedProps.setProperty(SERVER_LOGGING_LEVEL, serverLoggingLevel
					.toString());

			/* Set registry port values */
			unsavedProps.setProperty(STANDALONE_CLIENT_REGISTRY_PORT, Integer
					.toString(standaloneClientRegistryPort));
			unsavedProps.setProperty(NETWORK_CLIENT_REGISTRY_PORT, Integer
					.toString(networkClientRegistryPort));
			unsavedProps.setProperty(SERVER_REGISTRY_PORT, Integer
					.toString(serverRegistryPort));

			/* Set database paths */
			unsavedProps.setProperty(STANDLONE_CLIENT_DATABASE_PATH,
					standloneClientDatabasePath);
			unsavedProps.setProperty(NETWORK_CLIENT_DATABASE_PATH,
					networkClientDatabasePath);
			unsavedProps.setProperty(SERVER_DATABASE_PATH, serverDatabasePath);

			/* Set host URL */
			unsavedProps.setProperty(REMOTE_HOST, host);
		}
	}
}
