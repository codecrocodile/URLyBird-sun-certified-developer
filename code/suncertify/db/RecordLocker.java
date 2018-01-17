/* 
 * @(#)RecordLocker.java    1.0    20 Feb 2010 
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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Chris Hatton
 * 
 */
class RecordLocker {
    
    private static final Map<Data, Long> lockedrecorded = new HashMap<Data, Long>();

    RecordLocker() {
	
    }

    boolean isLocked(int recNo, Data clientId) throws RecordNotFoundException {
	return false;
    }

    void lock(int recNo, Data clientId) throws RecordNotFoundException {

    }

    void unlock(int recNo, Data clientId) throws RecordNotFoundException {
    }
}
