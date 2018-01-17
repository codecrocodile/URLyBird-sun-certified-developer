/* 
 * @(#)ServerPanel.java    1.0    27 Feb 2010 
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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import suncertify.application.ApplicationContext;

/**
 * @author Chris Hatton
 * 
 */
@SuppressWarnings("serial")
class ServerPanel extends JPanel {

	private static Logger log = Logger.getLogger(ServerPanel.class.getName());

	private JTextField dbFileLocationTxt;

	private JButton findBtn;

	private JFileChooser fileChooser;

	private JTextField portNumberTxt;

	private JComboBox logLevelCbo;

	private JButton startServerBtn;

	private JButton stopServerBtn;

	private ServerModel serverModel;

	ServerPanel(final ServerModel serverModel) {
		this.serverModel = serverModel;
		this.setLayout(new BorderLayout());
		this.add(buildCenterPanel(), BorderLayout.CENTER);
		this.add(buildButtonPanel(), BorderLayout.SOUTH);

		this.initComponentValues();
		this.addComponentListeners();
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
		DefaultComboBoxModel comboModel = new DefaultComboBoxModel(serverModel
				.getLoggingLevels());
		logLevelCbo.setModel(comboModel);
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
		JPanel panel = new JPanel();

		startServerBtn = new JButton("Start Server");
		startServerBtn.setMnemonic('S');
		stopServerBtn = new JButton("Stop Server");
		stopServerBtn.setMnemonic('x');
		stopServerBtn.setEnabled(false);

		LayoutUtil.equalizeButtonSizes(startServerBtn, stopServerBtn);

		panel.add(startServerBtn);
		panel.add(stopServerBtn);

		return panel;
	}

	private void initComponentValues() {
		ApplicationContext appContext = ApplicationContext.getInstance();
		dbFileLocationTxt.setText(appContext.getDatabasePath());
		portNumberTxt.setText(Integer.toString(appContext.getRegistryPort()));
		logLevelCbo.setSelectedItem(appContext.getLoggingLevel());
		
		//TODO save the last values that actually worked and not just what is showing in the components???s
	}

	private void addComponentListeners() {
		findBtn.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.
			 * ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				int option = fileChooser.showOpenDialog(ServerPanel.this);

				if (option == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					dbFileLocationTxt.setText(selectedFile.getAbsolutePath());
				}
			}
		});

		startServerBtn.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.
			 * ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				if (validateInput()) {
					try {
						serverModel.startServer();
						stopServerBtn.setEnabled(true);
						startServerBtn.setEnabled(false);
						dbFileLocationTxt.setEnabled(false);
						portNumberTxt.setEnabled(false);
						logLevelCbo.setEnabled(false);

						findBtn.setEnabled(false);
					} catch (RemoteException e1) {
						System.out.println("ddddddddddddddddd");
						// e1.printStackTrace();

						// TODO log and inform the user
					} catch (MalformedURLException e1) {
						// e1.printStackTrace();
					} catch (AlreadyBoundException e1) {

						// e1.printStackTrace();
					}
				} else {
					return;
				}
			}

			private boolean validateInput() {
				if (dbFileLocationTxt.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(SwingUtilities
							.getWindowAncestor(ServerPanel.this),
							"Please specify a database file.",
							"URLyBird Server Application",
							JOptionPane.WARNING_MESSAGE);

					return false;
				}

				if (portNumberTxt.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(SwingUtilities
							.getWindowAncestor(ServerPanel.this),
							"Please specify a port number.",
							"URLyBird Server Application",
							JOptionPane.WARNING_MESSAGE);

					return false;
				}

				int portNumber = Integer.parseInt(portNumberTxt.getText()
						.trim());
				if (serverModel.isPortnumberValid(portNumber) == false) {
					JOptionPane.showMessageDialog(SwingUtilities
							.getWindowAncestor(ServerPanel.this),
							"Invalid port number. Please use "
									+ ServerModel.DEFAULT_PORT_NUMBER
									+ " or a value in\n" + "the range "
									+ ServerModel.LOWEST_PORT_NUMBER + " - "
									+ ServerModel.HIGHEST_PORT_NUMBER,
							"URLyBird Server Application",
							JOptionPane.WARNING_MESSAGE);

					return false;
				} else {
					ApplicationContext appContext = ApplicationContext
							.getInstance();

					appContext.setRegistryPort(portNumber);
					appContext.setDatabasePath(dbFileLocationTxt.getText()
							.trim());
					appContext.setLoggingLevel((Level) logLevelCbo
							.getSelectedItem());
					// TODO actually set the logging level of of the context
					

					return true;
				}
			}
		});

		stopServerBtn.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.
			 * ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				try {
					serverModel.stopServer();
					stopServerBtn.setEnabled(false);
					startServerBtn.setEnabled(true);
					portNumberTxt.setEnabled(true);
					logLevelCbo.setEnabled(true);
					dbFileLocationTxt.setEnabled(true);
					findBtn.setEnabled(true);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
}
