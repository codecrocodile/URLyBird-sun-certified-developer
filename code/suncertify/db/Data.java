/*
 * @(#)Data.java    1.0    20 Feb 2010
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
 * This class has been written as a facade for database related operations.
 * 
 * @author Chris Hatton
 * 
 */
public class Data implements DBMain {

    /** Worker class for database CRUD operations */
    private static DatabaseAccessWorker dbAccessWorker;

    /** Worker class for maintaining record locking operations */
    private static RecordLocker recordLockingWorker;

    static {
	 recordLockingWorker = new RecordLocker();
    }
    
    public Data(String dbFilePath) throws DatabaseAccessException {
	dbAccessWorker = new DatabaseAccessWorker(dbFilePath);
    }

    /*
     * (non-Javadoc)
     * 
     * @see suncertify.db.DBMain#create(java.lang.String[])
     */
    public int create(String[] data) throws DuplicateKeyException {
	return dbAccessWorker.create(data);
    }

    /*
     * (non-Javadoc)
     * 
     * @see suncertify.db.DBMain#delete(int)
     */
    public void delete(int recNo) throws RecordNotFoundException {
	dbAccessWorker.delete(recNo);
    }

    /*
     * (non-Javadoc)
     * 
     * @see suncertify.db.DBMain#find(java.lang.String[])
     */
    public int[] find(String[] criteria) throws RecordNotFoundException {
	return dbAccessWorker.find(criteria);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see suncertify.db.DBMain#update(int, java.lang.String[])
     */
    @Override
    public void update(int recNo, String[] data) throws RecordNotFoundException {
	dbAccessWorker.update(recNo, data);
    }

    /*
     * (non-Javadoc)
     * 
     * @see suncertify.db.DBMain#read(int)
     */
    public String[] read(int recNo) throws RecordNotFoundException {
	return dbAccessWorker.read(recNo);
    }

    /*
     * (non-Javadoc)
     * 
     * @see suncertify.db.DBMain#isLocked(int)
     */
    public boolean isLocked(int recNo) throws RecordNotFoundException {
	return recordLockingWorker.isLocked(recNo, this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see suncertify.db.DBMain#lock(int)
     */
    public void lock(int recNo) throws RecordNotFoundException {
	recordLockingWorker.lock(recNo, this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see suncertify.db.DBMain#unlock(int)
     */
    public void unlock(int recNo) throws RecordNotFoundException {
	recordLockingWorker.unlock(recNo, this);
    }
}
