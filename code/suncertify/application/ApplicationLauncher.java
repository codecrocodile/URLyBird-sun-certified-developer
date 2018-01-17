/*
 * @(#)ApplicationLauncher.java 1.0 26 Feb 2010
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

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import suncertify.ui.ClientMainFrame;
import suncertify.ui.ServerMainFrame;

/**
 * @author Chris Hatton
 * 
 */
class ApplicationLauncher {

	private static Logger log = Logger.getLogger("suncertify.client");

	/**
     * 
     */
	public static void main(String... args) 
			throws SecurityException, IOException {
		initLookAndFeel();

		ApplicationContext appContext = ApplicationContext.getInstance();
		appContext.initContext();

		if (args.length == 1 && args[0].equalsIgnoreCase("server")) {
			appContext.setOperatingMode(OperatingMode.SERVER);
		} else if (args.length == 1 && args[0].equalsIgnoreCase("alone")) {
			appContext.setOperatingMode(OperatingMode.STANDALONE_CLIENT);
		} else if (args.length == 0) {
			appContext.setOperatingMode(OperatingMode.STANDALONE_CLIENT);
		} else {
			throw new IllegalArgumentException(
					"Please use \"server\" or \"alone\" as the argument.");
		}

		setLogging(appContext.getLoggingLevel());
		createAndShowGUI();
	}

	private static void setLogging(Level loggingLevel)
			throws SecurityException, IOException {
		FileHandler fileHandeler = new FileHandler("RunLog.txt");
		fileHandeler.setFormatter(new SimpleFormatter());
		log.addHandler(fileHandeler);
		log.setLevel(loggingLevel);
	}

	private static void initLookAndFeel() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			log.warning("UnsupportedLookAndFeelException");
		} catch (ClassNotFoundException e) {
			log.warning("ClassNotFoundException");
		} catch (InstantiationException e) {
			log.warning("InstantiationException");
		} catch (IllegalAccessException e) {
			log.warning("IllegalAccessException");
		}
	}

	private static void createAndShowGUI() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ApplicationContext appConfig = ApplicationContext.getInstance();
				OperatingMode mode = appConfig.getOperatingMode();
				switch (mode) {
				case SERVER:
					new ServerMainFrame();
					break;
				default: 
					new ClientMainFrame();
				}
			}
		});
	}
}
