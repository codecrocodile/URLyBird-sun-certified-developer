/*
 * @(#)Server.java 1.0 28 Feb 2010
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

package suncertify.server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

import suncertify.application.ApplicationContext;

/**
 * @author Chris Hatton
 * 
 */
public class Server {
	
	private static Logger log = Logger.getLogger(Server.class.getName());

	private static final String RESERVATION_SERVICE_BIND_NAME = "ReservationService";

	private ApplicationContext applicationContext;
	
	private ServiceFactory serviceFactory;
	
	private Registry registry;

	public Server() {
		this.applicationContext = ApplicationContext.getInstance();

	}

	public void startServer() throws RemoteException, MalformedURLException,
			AlreadyBoundException {
		
		String serverDatabasePath = applicationContext.getDatabasePath();
		serviceFactory = new ReservationServiceFactory(serverDatabasePath);
	
		/* 0 value lets RMI or the underlying operating system choose port */
		Remote server = UnicastRemoteObject.exportObject(serviceFactory, 0);  
		
		if (registry == null) {
			int portNumber = applicationContext.getRegistryPort();
			registry = LocateRegistry.createRegistry(portNumber);	
		}
		  
		registry.rebind(RESERVATION_SERVICE_BIND_NAME, server);  
	}
	
	public void stopServer() throws RemoteException, NotBoundException {
		if (registry != null) {
			registry.unbind(RESERVATION_SERVICE_BIND_NAME);
			UnicastRemoteObject.unexportObject(serviceFactory, false);
		}
	}
}
