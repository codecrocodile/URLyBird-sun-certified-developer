/*
 * @(#)RoomDTO.java 1.0 20 Feb 2010
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

import java.io.Serializable;
import java.util.Date;

/**
 * @author Chris Hatton
 * 
 */
public class RoomDTO implements Serializable {

	// TODO DEFAULT strings of "" should be given (kind of null obmject pattern)
	
	private int primaryKey;

	private int owner; // not sure if this should be here

	private String hotelName;

	private String city;

	private byte maximumOccupancy;

	private boolean smokingRoom;

	private double pricePerNight; // might change this to larger value

	private Date dateAvalable;

	public RoomDTO() {
		super();
	}
	
	/**
	 * @return the primaryKey
	 */
	public int getPrimaryKey() {
		return primaryKey;
	}

	/**
	 * @param primaryKey the primaryKey to set
	 */
	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * @return the owner
	 */
	public int getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(int owner) {
		this.owner = owner;
	}

	/**
	 * Gets the reservation status of the room.
	 * 
	 * @return true if room is reserved, false otherwise.
	 */
	public boolean isReserved() {
		if (owner == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @return the hotelName
	 */
	public String getHotelName() {
		return hotelName;
	}

	/**
	 * @param hotelName
	 *            the hotelName to set
	 */
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the maximumOccupancy
	 */
	public byte getMaximumOccupancy() {
		return maximumOccupancy;
	}

	/**
	 * @param maximumOccupancy
	 *            the maximumOccupancy to set
	 */
	public void setMaximumOccupancy(byte maximumOccupancy) {
		this.maximumOccupancy = maximumOccupancy;
	}

	/**
	 * @return the smokingRoom
	 */
	public boolean isSmokingRoom() {
		return smokingRoom;
	}

	/**
	 * @param smokingRoom
	 *            the smokingRoom to set
	 */
	public void setSmokingRoom(boolean smokingRoom) {
		this.smokingRoom = smokingRoom;
	}

	/**
	 * @return the pricePerNight
	 */
	public double getPricePerNight() {
		return pricePerNight;
	}

	/**
	 * @param pricePerNight
	 *            the pricePerNight to set
	 */
	public void setPricePerNight(double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

	/**
	 * @return the dateAvalable
	 */
	public Date getDateAvalable() {
		return dateAvalable;
	}

	/**
	 * @param dateAvalable
	 *            the dateAvalable to set
	 */
	public void setDateAvalable(Date dateAvalable) {
		this.dateAvalable = dateAvalable;
	}
}
