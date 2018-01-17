/* 
 * @(#)ReservationServiceImpl.java    1.0 20 Feb 2010 
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

package suncertify.service;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import suncertify.common.RoomDTO;
import suncertify.common.SearchCriteriaDTO;
import suncertify.db.DBMain;
import suncertify.db.Data;
import suncertify.db.DatabaseAccessException;
import suncertify.db.DuplicateKeyException;
import suncertify.db.RecordNotFoundException;

/**
 * @author Chris Hatton
 * 
 */
public class ReservationServiceImpl implements ReservationService {
	private static Logger log 
			= Logger.getLogger(ReservationServiceImpl.class.getName());

	// TODO this will be the class where I can use a map for the read and write
	// locks for the higher level logical locking of the records and I can also
	// use this current instance of the ReservatoinService if there is a new one
	// created eahc time that client requests a service with rmi

	// not sure about this

	private DBMain dbMain;

	public ReservationServiceImpl(final String dbFilePath) throws RemoteException,
			DatabaseAccessException {
		dbMain = new Data(dbFilePath);
	
	}

	public RoomDTO createReservableRoom(RoomDTO reservableRoom)
			throws DuplicateKeyException {
		return null;
	}

	public RoomDTO deleteReservableRoom(RoomDTO reservableRoom)
			throws RecordNotFoundException {
		return null;
	}

	public List<RoomDTO> searchForRoom(SearchCriteriaDTO searchCriteria)
			throws RecordNotFoundException {
		log.info("calling service method");
		return new ArrayList();
	}

	public RoomDTO makeReservation(RoomDTO reservableRoom)
			throws RecordNotFoundException {
		return null;
	}

	public void updateReservableRoomDetails(RoomDTO reservableRoom)
			throws RecordNotFoundException {
	}
}
