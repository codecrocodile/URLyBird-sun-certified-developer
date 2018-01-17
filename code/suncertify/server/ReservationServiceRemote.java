/* 
 * @(#)ReservationServiceRemote.java    1.0    28 Feb 2010 
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
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.logging.Logger;

import suncertify.common.RoomDTO;
import suncertify.common.SearchCriteriaDTO;
import suncertify.db.DatabaseAccessException;
import suncertify.db.DuplicateKeyException;
import suncertify.db.RecordNotFoundException;
import suncertify.service.ReservationService;
import suncertify.service.ReservationServiceImpl;

/**
 * This is the <code>Remote</code> class for <code>ReservationService</code> 
 * from which its instances will be used to generate stubs to be sent back to 
 * the client.
 * 
 * @author Chris Hatton
 * @version 1.0
 */
public class ReservationServiceRemote 
		implements Remote, ReservationService, Serializable {

	private static final long serialVersionUID = 7289108780353633024L;

	private static Logger log 
			= Logger.getLogger(ReservationServiceRemote.class.getName());

	private ReservationService reservationService;
	
	public ReservationServiceRemote() {
		super();
	}

	/**
	 * Creates instances of this class.
	 * 
	 * @throws RemoteException
	 * 		on network error.
	 * @throws DatabaseAccessException
	 * 		if there has been a problem locating database.
	 */
	public ReservationServiceRemote(final String databasePath)
			throws RemoteException, DatabaseAccessException {
		super();
		this.reservationService = new ReservationServiceImpl(databasePath);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see suncertify.db.ReservationService
	 * 		#createReservableRoom(suncertify.common.RoomDTO)
	 */
	public RoomDTO createReservableRoom(final RoomDTO reservableRoom)
			throws RemoteException, DuplicateKeyException {
		return reservationService.createReservableRoom(reservableRoom);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see suncertify.db.ReservationService#deleteReservableRoom(suncertify.common
	 * .RoomDTO)
	 */
	public RoomDTO deleteReservableRoom(final RoomDTO reservableRoom)
			throws RemoteException, RecordNotFoundException {
		return reservationService.deleteReservableRoom(reservableRoom);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see suncertify.db.ReservationService
	 * 		#makeReservation(suncertify.common.RoomDTO)
	 */
	public RoomDTO makeReservation(final RoomDTO reservableRoom)
			throws RemoteException, RecordNotFoundException {
		return reservationService.makeReservation(reservableRoom);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see suncertify.db.ReservationService
	 * 		#searchForRoom(suncertify.common.SearchCriteriaDTO)
	 */
	public List<RoomDTO> searchForRoom(final SearchCriteriaDTO searchCriteria)
			throws RemoteException, RecordNotFoundException {
		return reservationService.searchForRoom(searchCriteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see suncertify.db.ReservationService
	 * 		#updateReservableRoomDetails(suncertify.common.RoomDTO)
	 */
	public void updateReservableRoomDetails(final RoomDTO reservableRoom)
			throws RemoteException, RecordNotFoundException {
		reservationService.updateReservableRoomDetails(reservableRoom);
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
		if (obj instanceof ReservationServiceRemote) {
			return (obj == this);
		} else if (obj instanceof RemoteStub) {
			try {
				RemoteStub ourStub = (RemoteStub) RemoteObject.toStub(this);
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
