/*
 * @(#)SearchCriteriaDTO.java 1.0 2 Mar 2010
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

/**
 * @author Chris Hatton
 *
 */
public class SearchCriteriaDTO implements Serializable {

    private String hotelName;
    
    private SearchLogic booleanCondition;
    
    private String city;
    

    /**
     * 
     */
    public SearchCriteriaDTO() {
	super();
    }

    /**
     * @return the hotelName
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * @param hotelName the hotelName to set
     */
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    /**
     * @return the booleanCondition
     */
    public SearchLogic getBooleanCondition() {
        return booleanCondition;
    }

    /**
     * @param booleanCondition the booleanCondition to set
     */
    public void setBooleanCondition(SearchLogic booleanCondition) {
        this.booleanCondition = booleanCondition;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }
    
    public boolean isSearchAllCondition() { // TODO this might become ielivent if I return the string array required o the servr side
	return hotelName.equals("") && city.equals("");
    }
}
