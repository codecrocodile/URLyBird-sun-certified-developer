/* 
 * @(#)PositiveIntegerDocument.java    1.0    27 Feb 2010 
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

import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Simple <code>Document</code> that only allows positive integers to be
 * inserted into it. Emits a system beep if anything other than a positive
 * integer insert is attempted.
 * 
 * @author Chris Hatton
 * 
 */
@SuppressWarnings("serial")
class PositiveIntegerDocument extends PlainDocument {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.text.Document 
	 * 		#insertString(int, java.lang.String, javax.swing.text.AttributeSet)
	 */
	@Override
	public void insertString(int offset, String str, AttributeSet a)
			throws BadLocationException {

		try {
			if(super.getLength() > 0) {
				String nextDocumentValue = 
					super.getText(0, super.getLength()) + str; 
				Integer.parseInt(nextDocumentValue);
			}
			
			int insertedInteger = Integer.parseInt(str);
			if (insertedInteger < 0 || 
					(str.substring(0, 1).equals("0") && offset == 0)) {
				Toolkit.getDefaultToolkit().beep();				
			} else {
				super.insertString(offset, str, a);
			}
		} catch (NumberFormatException e) {
			Toolkit.getDefaultToolkit().beep();
		}
	}
}
