/*
 * @(#)SearchLogic.java 1.0 2 Mar 2010
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

/**
 * @author Chris Hatton
 * 
 */
public enum SearchLogic {

	OR {
		/*
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return "or";
		}
	},

	AND {
		/*
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return "and";
		}
	}
}
