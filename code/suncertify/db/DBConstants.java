/* 
 * @(#)DbConstants.java    1.0    20 Feb 2010 
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

package suncertify.db;

/**
 * @author Chris Hatton
 * 
 */
public class DBConstants {
    
    protected static final int MAGIC_COOKIE_NUMBER = 257;

    protected static final int MAGIC_COOKIE_LENGTH = 4;

    protected static final int OVERALL_RECORD_LENGTH = 4;

    protected static final int NUMBER_OF_FIELDS_LENGTH = 2;

    protected static final int RECORD_START_POSITION = 
	MAGIC_COOKIE_LENGTH + OVERALL_RECORD_LENGTH + NUMBER_OF_FIELDS_LENGTH;

    protected static final int FIELD_NAME_LENGTH = 2;
    
    //TODO should I also put in the field name value lengths

    protected static final int HOTEL_NAME_LENGTH = 64;

    protected static final int CITY_LENGTH = 64;

    protected static final int MAXIMUM_OCCUPANCY_LENGTH = 4;

    protected static final int SMOKING_LENGTH = 1;

    protected static final int PRICE_PER_NIGHT_LENGTH = 8;

    protected static final int DATE_AVAILABLE_LENGTH = 10;

    protected static final int CUSTOMER_ID_LENGTH = 8;

    protected static final int DELETED_LENGTH = 1;

    /** The character encoding of database file */
    protected static final String ENCODING = "US-ASCII";
}
