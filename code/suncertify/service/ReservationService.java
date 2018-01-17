/* 
 * @(#)ReservationService.java    1.0    28 Feb 2010 
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
import java.util.List;

import suncertify.common.RoomDTO;
import suncertify.common.SearchCriteriaDTO;
import suncertify.db.DuplicateKeyException;
import suncertify.db.RecordNotFoundException;

/**
 * @author Chris Hatton
 * 
 */
public interface ReservationService {

	/**
	 * reserves a room.
	 * 
	 * @param reservableRoom
	 * @return
	 * @throws RemoteException
	 * @throws DuplicateKeyException
	 */
	public RoomDTO createReservableRoom(RoomDTO reservableRoom)
			throws RemoteException, DuplicateKeyException;

	public RoomDTO deleteReservableRoom(RoomDTO reservableRoom)
			throws RemoteException, RecordNotFoundException;

	public List<RoomDTO> searchForRoom(SearchCriteriaDTO searchCriteria)
			throws RemoteException, RecordNotFoundException;

	public RoomDTO makeReservation(RoomDTO reservableRoom)
			throws RemoteException, RecordNotFoundException;

	public void updateReservableRoomDetails(RoomDTO reservableRoom)
			throws RemoteException, RecordNotFoundException;
}
