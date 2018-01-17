/* 
 * @(#)OptionsDialog.java    1.0    21 Feb 2010 
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

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Chris Hatton
 * 
 */
@SuppressWarnings("serial")
class OptionsDialog extends JDialog {
	
	private JTextField dbFileLocationTxt;

	private JButton findBtn;

	private JFileChooser fileChooser;

	private JTextField portNumberTxt;

	private JComboBox logLevelCbo;
	
	private JButton okBtn;
	
	private JButton cancelBtn;

	OptionsDialog(Frame owner, boolean modal) {
		super(owner, "Options", modal);
		this.setSize(500, 250);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

		this.add(buildCenterPanel(), BorderLayout.CENTER);
		this.add(buildButtonPanel(), BorderLayout.SOUTH);
		this.setLocationRelativeTo(owner);
	}
	
	private JPanel buildCenterPanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		JPanel containerPanel = new JPanel(new GridBagLayout());
		containerPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Server Properties"),
				BorderFactory.createEmptyBorder(12, 12, 11, 12)));

		JLabel dbFileLocationLbl = new JLabel("Database:");
		dbFileLocationLbl.setDisplayedMnemonic('D');
		dbFileLocationTxt = new JTextField(30);
		dbFileLocationLbl.setLabelFor(dbFileLocationTxt);

		findBtn = new JButton("Find...");
		findBtn.setMnemonic('d');

		String currentDirectory = ".";
		fileChooser = new JFileChooser(currentDirectory);
		fileChooser.addChoosableFileFilter(new DatabaseFileFilter());

		JLabel portNumberLbl = new JLabel("Port Number:");
		portNumberLbl.setDisplayedMnemonic('P');
		portNumberTxt = new JTextField(5);
		portNumberTxt.setDocument(new PositiveIntegerDocument());
		portNumberTxt.setColumns(5);
		portNumberLbl.setLabelFor(portNumberTxt);

		JLabel logLevelLbl = new JLabel("Logging Level:");
		logLevelCbo = new JComboBox();
//		DefaultComboBoxModel comboModel = new DefaultComboBoxModel(serverModel
//				.getLoggingLevels());
//		logLevelCbo.setModel(comboModel);
		logLevelLbl.setLabelFor(logLevelCbo);
		logLevelLbl.setDisplayedMnemonic('L');

		containerPanel.add(dbFileLocationLbl, LayoutUtil.getBuilder().gridX(0)
				.gridY(0).anchor(GridBagConstraints.LINE_END).build());

		containerPanel.add(dbFileLocationTxt, LayoutUtil.getBuilder().gridX(1)
				.gridY(0).fill(GridBagConstraints.HORIZONTAL).insets(
						LayoutUtil.getInsets(0, 5, 5, 0)).build());

		containerPanel.add(findBtn, LayoutUtil.getBuilder().gridX(2).gridY(0)
				.anchor(GridBagConstraints.LINE_START).insets(
						LayoutUtil.getInsets(0, 5, 5, 0)).build());

		containerPanel.add(portNumberLbl, LayoutUtil.getBuilder().gridX(0)
				.gridY(1).anchor(GridBagConstraints.LINE_END).build());

		containerPanel.add(portNumberTxt, LayoutUtil.getBuilder().gridX(1)
				.gridY(1).gridwidth(2).anchor(GridBagConstraints.LINE_START)
				.insets(LayoutUtil.getInsets(0, 5, 5, 0)).build());

		containerPanel.add(logLevelLbl, LayoutUtil.getBuilder().gridX(0).gridY(
				2).anchor(GridBagConstraints.LINE_START).insets(
				LayoutUtil.getInsets(0, 5, 5, 0)).build());

		containerPanel.add(logLevelCbo, LayoutUtil.getBuilder().gridX(1).gridY(
				2).gridwidth(2).anchor(GridBagConstraints.LINE_START).insets(
				LayoutUtil.getInsets(0, 5, 5, 0)).build());

		panel.add(containerPanel, LayoutUtil.getBuilder().gridX(0).gridY(0)
				.build());

		return panel;
	}

	private JPanel buildButtonPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		okBtn = new JButton("Ok");
		cancelBtn = new JButton("Cancel");
		LayoutUtil.equalizeButtonSizes(okBtn, cancelBtn);
		
		panel.add(okBtn);
		panel.add(cancelBtn);
		
		return panel;
	}
}
