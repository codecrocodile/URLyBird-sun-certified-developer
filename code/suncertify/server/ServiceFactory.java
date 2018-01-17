/* 
 * @(#)ServiceFactory.java    1.0    28 Feb 2010 
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

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Chris Hatton
 * 
 */
public interface ServiceFactory<T> extends Remote {

	public T getService() throws RemoteException;
}
