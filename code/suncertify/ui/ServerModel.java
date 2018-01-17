/* 
 * @(#)ServerModel.java    1.0    27 Feb 2010 
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

package suncertify.ui;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;

import suncertify.application.ApplicationContext;
import suncertify.server.Server;

/**
 * @author Chris Hatton
 * 
 */
class ServerModel {

	/** Default port number used by RMI */
	static final int DEFAULT_PORT_NUMBER = 1099;

	/** Lowest Dynamic and/or Private port number permissible */
	static final int LOWEST_PORT_NUMBER = 49152;

	/** Highest Dynamic and/or Private port number permissible */
	static final int HIGHEST_PORT_NUMBER = 65535;

	private ApplicationContext applicationContext;

	private Server server;

	ServerModel() {
		applicationContext = ApplicationContext.getInstance();
		server = new Server();
	}
	
	Level[] getLoggingLevels(){
		Level[] loggingLevels = new Level[9];
		loggingLevels[0] = Level.OFF;
		loggingLevels[1] = Level.SEVERE;
		loggingLevels[2] = Level.WARNING;
		loggingLevels[3] = Level.INFO;
		loggingLevels[4] = Level.CONFIG;
		loggingLevels[5] = Level.FINE;
		loggingLevels[6] = Level.FINER;
		loggingLevels[7] = Level.FINEST;
		loggingLevels[8] = Level.OFF;
		
		return loggingLevels;
	}

	/**
	 * 
	 * @param portNumber
	 * @return
	 */
	boolean isPortnumberValid(int portNumber) {
		boolean isDefault = portNumber == DEFAULT_PORT_NUMBER;
		boolean isIncorrectRange = portNumber >= LOWEST_PORT_NUMBER
				&& portNumber <= HIGHEST_PORT_NUMBER;
		if (isDefault || isIncorrectRange) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @throws RemoteException
	 * @throws AlreadyBoundException
	 * @throws MalformedURLException
	 */
	void startServer() throws RemoteException, MalformedURLException,
			AlreadyBoundException {
		server.startServer();
	}

	/**
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * 
	 */
	void stopServer() throws RemoteException, NotBoundException {
		server.stopServer();
	}
}
