/* 
 * @(#)DatabaseAccessException.java    1.0    20 Feb 2010 
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
 * Exception used to indicate to a client that there has been a problem 
 * accessing the database.
 * 
 * @author Chris Hatton
 * 
 */
public class DatabaseAccessException extends Exception {

    /** Generated UID */
    private static final long serialVersionUID = -5533017379632376143L;

    public DatabaseAccessException() {
	super();
    }

    public DatabaseAccessException(String message) {
	super(message);
    }
}
