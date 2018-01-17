/*
 * @(#)ReservationModel.java 1.0 2 Mar 2010
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import suncertify.common.RoomDTO;
import suncertify.common.SearchCriteriaDTO;

/**
 * @author Chris Hatton
 * 
 */
class ReservationModel extends Observable {

	enum ModelUpdate {
		SEARCH_RESULTS, RESERVATION_MADE, RESERVATION_CANCELED
	}

	private List<RoomDTO> searchResults = new ArrayList<RoomDTO>();

	{ // test data

		RoomDTO r = null;
		r = new RoomDTO();
		r.setCity("Glasgow");
		r.setDateAvalable(new Date());
		r.setHotelName("Hilton");
		r.setMaximumOccupancy((byte) 4);
		r.setPricePerNight(105.55);
		r.setSmokingRoom(false);

		searchResults.add(r);

		r = new RoomDTO();
		r.setCity("Edinburgh");
		r.setDateAvalable(new Date());
		r.setHotelName("Royal Crown");
		r.setMaximumOccupancy((byte) 2);
		r.setPricePerNight(45.99);
		r.setSmokingRoom(true);

		searchResults.add(r);

		r = new RoomDTO();
		r.setCity("Glasgow");
		r.setDateAvalable(new Date());
		r.setHotelName("The Cherry Tree Inn");
		r.setMaximumOccupancy((byte) 6);
		r.setPricePerNight(200.99);
		r.setSmokingRoom(false);

		searchResults.add(r);

		r = new RoomDTO();
		r.setCity("Glasgow");
		r.setDateAvalable(new Date());
		r.setHotelName("Hilton");
		r.setMaximumOccupancy((byte) 2);
		r.setPricePerNight(95.99);
		r.setSmokingRoom(false);

		searchResults.add(r);

	}

	/**
     * 
     */
	ReservationModel() {
		super();
	}

	/**
	 * 
	 * @param searchCriteria
	 */
	void search(SearchCriteriaDTO searchCriteria) {

		setChanged();
		notifyObservers(ModelUpdate.SEARCH_RESULTS);
	}

	/**
     * 
     */
	void makeReservation() {

		setChanged();
		notifyObservers(ModelUpdate.RESERVATION_MADE);
	}

	/**
     * 
     */
	void cancelReservation() {

		setChanged();
		notifyObservers(ModelUpdate.RESERVATION_CANCELED);
	}

	/**
	 * @return the searchResults
	 */
	List<RoomDTO> getSearchResults() {
		return searchResults;
	}
}
