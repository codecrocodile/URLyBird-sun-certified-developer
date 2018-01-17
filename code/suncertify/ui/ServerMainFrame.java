/* 
 * @(#)ServerMainFrame.java    1.0    27 Feb 2010 
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import suncertify.application.ApplicationContext;

/**
 * @author Chris Hatton
 * 
 */
@SuppressWarnings("serial")
public class ServerMainFrame extends JFrame {

	private static final String EXIT_ACTION = "Exit Application";

	private MenuListener menuListener;

	private WindowListener windowListener;
	
	private ServerModel serverModel;

	public ServerMainFrame() {
		super("URLyBird Server Application");
		this.setSize(500, 250);
		this.setResizable(false);
		
		this.menuListener = new MenuListener();
		this.windowListener = new WindowListener();
		this.addWindowListener(windowListener);
		this.setJMenuBar(buildMenu());
		this.serverModel = new ServerModel();
		this.add(new ServerPanel(serverModel), BorderLayout.CENTER);

		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private JMenuBar buildMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');

		JMenuItem exitMenuItem = new JMenuItem("Stop/Exit Server", KeyEvent.VK_X);
		exitMenuItem.getAccessibleContext().setAccessibleDescription("Exit");
		exitMenuItem.setActionCommand(EXIT_ACTION);
		exitMenuItem.addActionListener(menuListener);
		fileMenu.add(exitMenuItem);

		menuBar.add(fileMenu);

		return menuBar;
	}
	
	private class MenuListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener
		 * #actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();

			if (actionCommand.equals(EXIT_ACTION)) {
				exitApplication();
			}
		}
	}

	private class WindowListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			exitApplication();
		}
	}

	private void exitApplication() {
		try {
			serverModel.stopServer();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		ApplicationContext.getInstance().persistContext();
		this.dispose();
		System.exit(0);
	}
}
