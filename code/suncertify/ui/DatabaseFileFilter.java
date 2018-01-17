/* 
 * @(#)DatabaseFileFilter.java    1.0    27 Feb 2010 
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

package suncertify.ui;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * @author Chris Hatton
 * 
 */
class DatabaseFileFilter extends FileFilter {

	private static final String DATABASE_FILE_EXTENSION = ".db";

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File f) {
		if (f.isFile()) {
			String fileExtension = f.getName().substring(
					f.getName().lastIndexOf('.'));
			if (fileExtension.equals(DATABASE_FILE_EXTENSION)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.filechooser.FileFilter#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Database File";
	}
}
