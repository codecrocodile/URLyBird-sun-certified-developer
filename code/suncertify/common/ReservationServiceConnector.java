/* 
 * @(#)ReservationServiceConnector.java    1.0    22 Feb 2010 
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

package suncertify.common;

import suncertify.service.ReservationServiceImpl;

/**
 * @author Chris Hatton
 * 
 */
public class ReservationServiceConnector implements
	ServiceConnector<ReservationServiceImpl> {

    /*
     * (non-Javadoc)
     * 
     * @see suncertify.common.ServiceConnector#getGetReservationService()
     */
    @Override
    public ReservationServiceImpl getGetReservationService() {
	// TODO implement factory method for returning local or remote
	// ReservationService
	return null;
    }

}
