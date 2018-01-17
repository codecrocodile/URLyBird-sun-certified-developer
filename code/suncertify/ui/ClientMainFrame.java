/*
 * @(#)ClientMainFrame.java 1.0 21 Feb 2010
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
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import suncertify.application.ApplicationContext;

/**
 * @author Chris Hatton
 * 
 */
@SuppressWarnings("serial")
public class ClientMainFrame extends JFrame { // TODO should i create a menu
												// manager. does

	private static Logger log = Logger.getLogger(ClientMainFrame.class
			.getName());
	// this do more thanone job?

	private static final String MAKE_RESERVATION_ACTION = "Make Reservation";

	private static final String Cancel_RESERVATION_ACTION = "Cancel Reservation";

	private static final String EXIT_ACTION = "Exit Application";

	private static final String OPTIONS_ACTION = "Options";

	private static final String HELP_ACTION = "Help";

	private static final String ABOUT_ACTION = "About";

	private MenuListener menuListener;

	private WindowListener windowListener;

	private JPanel containerPanel;

	private Map<String, JPanel> instanciatedPanels;

	public ClientMainFrame() {
		super("URLyBird Reservation");
		this.setSize(800, 600); // TODO check for the user's screen size
		this.setMinimumSize(new Dimension(600, 400));

		this.menuListener = new MenuListener();
		this.windowListener = new WindowListener();
		this.addWindowListener(windowListener);
		this.buildMenu();

		this.instanciatedPanels = new HashMap<String, JPanel>();
		this.containerPanel = new JPanel(new BorderLayout());
		this.add(containerPanel, BorderLayout.CENTER);

		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * 
	 * @param mainFrame
	 */
	private void buildMenu() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');

		JMenuItem makeReservationMenuItem = new JMenuItem("Make Reservation",
				KeyEvent.VK_M);
		makeReservationMenuItem.getAccessibleContext()
				.setAccessibleDescription("Make Reservation");
		makeReservationMenuItem.setActionCommand(MAKE_RESERVATION_ACTION);
		makeReservationMenuItem.addActionListener(menuListener);

		JMenuItem cancelReservationMenuItem = new JMenuItem(
				"Cancel Reservation", KeyEvent.VK_C);
		cancelReservationMenuItem.getAccessibleContext()
				.setAccessibleDescription("Cancel Reservation");
		cancelReservationMenuItem.setActionCommand(Cancel_RESERVATION_ACTION);
		cancelReservationMenuItem.addActionListener(menuListener);

		JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
		exitMenuItem.getAccessibleContext().setAccessibleDescription("Exit");
		exitMenuItem.setActionCommand(EXIT_ACTION);
		exitMenuItem.addActionListener(menuListener);

		JMenu toolsMenu = new JMenu("Tools");
		toolsMenu.setMnemonic('T');
		JMenuItem optionsMenuItem = new JMenuItem("Options", KeyEvent.VK_O);
		optionsMenuItem.getAccessibleContext().setAccessibleDescription(
				"Options");
		optionsMenuItem.setActionCommand(OPTIONS_ACTION);
		optionsMenuItem.addActionListener(menuListener);

		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');
		JMenuItem helpContentsMenuItem = new JMenuItem("Help Contents",
				KeyEvent.VK_H);
		helpContentsMenuItem.getAccessibleContext().setAccessibleDescription(
				"All help content");
		helpContentsMenuItem.setActionCommand(HELP_ACTION);
		helpContentsMenuItem.addActionListener(menuListener);

		JMenuItem aboutMenuItem = new JMenuItem("About URLyBird Booking",
				KeyEvent.VK_A);
		aboutMenuItem.getAccessibleContext().setAccessibleDescription(
				"About URLyBird Booking");
		aboutMenuItem.setActionCommand(ABOUT_ACTION);
		aboutMenuItem.addActionListener(menuListener);

		fileMenu.add(makeReservationMenuItem);
		fileMenu.add(cancelReservationMenuItem);
		fileMenu.add(new JSeparator());
		fileMenu.add(exitMenuItem);

		toolsMenu.add(optionsMenuItem);

		helpMenu.add(helpContentsMenuItem);
		helpMenu.add(new JSeparator());
		helpMenu.add(aboutMenuItem);

		menuBar.add(fileMenu);
		menuBar.add(toolsMenu);
		menuBar.add(helpMenu);

		this.setJMenuBar(menuBar);
	}

	/*
     * 
     */
	private class MenuListener implements ActionListener {

		/*
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();

			// TODO do nothing if the panel is already loaded
			if (actionCommand.equals(MAKE_RESERVATION_ACTION)) {
				if (instanciatedPanels.containsKey(actionCommand)) {
					swapDisplayedPanel(instanciatedPanels.get(actionCommand));
				} else {
					ReservationModel reservationModel = new ReservationModel();
					ReservationController reservationController = new ReservationController(
							reservationModel);
					ReservationPanel reservationPanel = reservationController
							.getReservationPanel();

					instanciatedPanels.put(actionCommand, reservationPanel);
					swapDisplayedPanel(reservationPanel);
				}

			} else if (actionCommand.equals(Cancel_RESERVATION_ACTION)) {

			} else if (actionCommand.equals(EXIT_ACTION)) {
				ClientMainFrame.this.exitApplication();
			} else if (actionCommand.equals(OPTIONS_ACTION)) {
				OptionsDialog od = new OptionsDialog(ClientMainFrame.this, true);
				od.setVisible(true);
			} else if (actionCommand.equals(HELP_ACTION)) {

			} else if (actionCommand.equals(ABOUT_ACTION)) {

			}
		}

		private void swapDisplayedPanel(JPanel panelToDisplay) {
			containerPanel.removeAll();
			containerPanel.add(panelToDisplay, BorderLayout.CENTER);
			containerPanel.revalidate();
		}
	}

	private class WindowListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			exitApplication();
		}
	}

	private void exitApplication() {
		ApplicationContext.getInstance().persistContext();
		this.dispose();
		System.exit(0);
	}
}
