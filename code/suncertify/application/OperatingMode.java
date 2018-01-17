/*
 * @(#)OperatingMode.java    1.0    20 Feb 2010
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

package suncertify.application;

/**
 * This enum is used to specify the operating mode in which the client wishes to
 * run the application.
 * 
 * @author Chris Hatton
 * 
 */
enum OperatingMode {

    /**
     * 
     */
    SERVER,

    /**
     * 
     */
    NETWORKED_CLIENT,

    /**
     * 
     */
    STANDALONE_CLIENT
}
