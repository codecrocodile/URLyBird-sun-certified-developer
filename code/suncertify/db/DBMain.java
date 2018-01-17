/*
 * @(#)DBMain.java 1.0 20 Feb 2010
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
public interface DBMain {
    // Reads a record from the file. Returns an array where each
    // element is a record value.
    public String[] read(int recNo) throws RecordNotFoundException;

    // Modifies the fields of a record. The new value for field n
    // appears in data[n].
    public void update(int recNo, String[] data) throws RecordNotFoundException;

    // Deletes a record, making the record number and associated disk
    // storage available for reuse.
    public void delete(int recNo) throws RecordNotFoundException;

    // Returns an array of record numbers that match the specified
    // criteria. Field n in the database file is described by
    // criteria[n]. A null value in criteria[n] matches any field
    // value. A non-null value in criteria[n] matches any field
    // value that begins with criteria[n]. (For example, "Fred"
    // matches "Fred" or "Freddy".)
    public int[] find(String[] criteria) throws RecordNotFoundException;

    // Creates a new record in the database (possibly reusing a
    // deleted entry). Inserts the given data, and returns the record
    // number of the new record.
    public int create(String[] data) throws DuplicateKeyException;

    // Locks a record so that it can only be updated or deleted by this client.
    // If the specified record is already locked, the current thread gives up
    // the CPU and consumes no CPU cycles until the record is unlocked.
    public void lock(int recNo) throws RecordNotFoundException;

    // Releases the lock on a record.
    public void unlock(int recNo) throws RecordNotFoundException;

    // Determines if a record is currenly locked. Returns true if the
    // record is locked, false otherwise.
    public boolean isLocked(int recNo) throws RecordNotFoundException;
}
