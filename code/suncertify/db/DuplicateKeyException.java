/*
 * @(#)DuplicateKeyException.java    1.0    20 Feb 2010
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
 * 
 * @author Chris Hatton
 *
 */
public class DuplicateKeyException extends Exception {

    /**  Generated UID */
    private static final long serialVersionUID = -6689165809485807888L;

    public DuplicateKeyException() {
	super();
    }

    public DuplicateKeyException(String message) {
	super(message);
    }
}
