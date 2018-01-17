/* 
 * @(#)ReservationServiceFactory.java    1.0    28 Feb 2010 
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

import java.io.Serializable;
import java.rmi.NoSuchObjectException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;
import java.rmi.server.RemoteStub;
import java.util.logging.Logger;

import suncertify.db.DatabaseAccessException;

/**
 * @author Chris Hatton
 * 
 */
public class ReservationServiceFactory implements
		ServiceFactory<ReservationServiceRemote> , Serializable {
	
	private static Logger log = Logger
			.getLogger(ReservationServiceFactory.class.getName());

	private String serverDatabasePath;

	public ReservationServiceFactory() {
		super();
	}
	
	/**
	 * @throws RemoteException
	 */
	public ReservationServiceFactory(final String serverDatabasePath)
			throws RemoteException {
		super();
		this.serverDatabasePath = serverDatabasePath;
		log.info("creating factory");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see suncertify.server.ServiceFactory#getService()
	 */
	@Override
	public ReservationServiceRemote getService()
			throws RemoteException {
		ReservationServiceRemote rs;
		try {
			rs = new ReservationServiceRemote(serverDatabasePath);
		} catch (DatabaseAccessException e) {
			e.printStackTrace();
			log.severe("Problem locating the database");
			throw new RemoteException(e.getMessage());
		}

		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * Remote stubs instances should be regarded as equivalent to the instances
	 * that they were created from. It might be the case in the future that the
	 * stub could be passed back to the server by the client.
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ReservationServiceFactory) {
			return (obj == this);
		} else if (obj instanceof RemoteStub) {
			try {
				RemoteStub ourStub = (RemoteStub) RemoteObject.toStub(this);
				System.out.println("return true");
				return ourStub.equals(obj);
			} catch (NoSuchObjectException e) {
				log.fine("Objects not equal as we are not listening on port.");
			}
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		try {
			Remote ourStub = RemoteObject.toStub(this);
			return ourStub.hashCode();
		} catch (NoSuchObjectException e) {
			log.fine("Objects not equal as we are not listening on port.");
		}
		
		return super.hashCode();
	}
}
