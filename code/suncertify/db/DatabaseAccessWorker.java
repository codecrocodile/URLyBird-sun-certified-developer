/* 
 * @(#)DatabaseAccessWorker.java    1.0    20 Feb 2010 
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

import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Logger;

/**
 * @author Chris Hatton
 * 
 */
class DatabaseAccessWorker {

    private static Logger log = Logger.getLogger(
	    DatabaseAccessWorker.class.getName());

    private static RandomAccessFile database;

    private static Map<String, Long> recordNumber;

    private static ReadWriteLock recordNumberLock;

    static {
	recordNumber = new HashMap<String, Long>();
	recordNumberLock = new ReentrantReadWriteLock();
    }

    DatabaseAccessWorker(String dbFilePath) throws DatabaseAccessException {

    }

    int create(String[] data) throws DuplicateKeyException {
	return 0;
    }

    void delete(int recNo) throws RecordNotFoundException {

    }

    int[] find(String[] criteria) throws RecordNotFoundException {
	return null;
    }

    String[] read(int recNo) throws RecordNotFoundException {
	return null;
    }

    void update(int recNo, String[] data) throws RecordNotFoundException {

    }
}
