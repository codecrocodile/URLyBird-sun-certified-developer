/*
 * @(#)ReservationPanel.java 1.0 27 Feb 2010
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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

import suncertify.common.RoomDTO;
import suncertify.common.SearchCriteriaDTO;
import suncertify.common.SearchLogic;
import suncertify.ui.ReservationModel.ModelUpdate;

/**
 * @author Chris Hatton
 * 
 */
@SuppressWarnings("serial")
class ReservationPanel extends JPanel implements Observer {

	private JTextField hotelNameTxt;

	private ButtonGroup buttonGroup;

	private JRadioButton orRdo;

	private JRadioButton andRdo;

	private JTextField cityTxt;

	private JButton clearBtn;

	private JButton searchBtn;

	private RoomsTableModel roomsTableModel;

	private JTable roomsTable;

	private JButton makeReservationBtn; //TODO disable if room is already booked

	private ReservationModel reservationModel;
	
	private ReservationController reservationController;

	// TODO add in customer id as it is part of the spec and is needed for
	// cancelation ????

	// TODO might need to reduce the number of field the user can search by
	// might be an idea to auto fill the with the hotel names

	// TODO create cell renderer for the currency column
	// TODO also might need one to format the date correctly

	ReservationPanel(final ReservationModel reservationModel, 
			final ReservationController reservationController) {
		this.reservationModel = reservationModel;
		this.reservationController = reservationController;
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

		this.add(buildConditionPanel(), BorderLayout.NORTH);
		this.add(buildTableResultsPanel(), BorderLayout.CENTER);
		this.add(buildButtonPanel(), BorderLayout.SOUTH);

		this.addComponentListeners();
	}

	private void addComponentListeners() {
		CriteriaDocumentListener criteriaDocListner = new CriteriaDocumentListener();
		hotelNameTxt.getDocument().addDocumentListener(criteriaDocListner);
		cityTxt.getDocument().addDocumentListener(criteriaDocListner);

		clearBtn.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.
			 * ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				hotelNameTxt.setText("");
				cityTxt.setText("");
			}
		});

		searchBtn.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.
			 * ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				System.out.println("search");

				SearchCriteriaDTO searchCiteria = new SearchCriteriaDTO();
				searchCiteria.setHotelName(hotelNameTxt.getText().trim());
				if (orRdo.isSelected()) {
					searchCiteria.setBooleanCondition(SearchLogic.OR);
				} else {
					searchCiteria.setBooleanCondition(SearchLogic.AND);
				}
				searchCiteria.setCity(cityTxt.getText().trim());

				// TODO show wait dialog

				reservationModel.search(searchCiteria);
			}
		});

		makeReservationBtn.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.
			 * ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				System.out.println("make reservation");
			}
		});

		roomsTable.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * javax.swing.event.ListSelectionListener#valueChanged(
					 * javax.swing.event.ListSelectionEvent)
					 */
					public void valueChanged(ListSelectionEvent e) {
						if (!e.getValueIsAdjusting()) {
							boolean isRowSelected = !((ListSelectionModel) e
									.getSource()).isSelectionEmpty();
							if (isRowSelected) {
								makeReservationBtn.setEnabled(true);
							} else {
								makeReservationBtn.setEnabled(false);
							}
						}
					}
				});
	}

	private class CriteriaDocumentListener implements DocumentListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * javax.swing.event.DocumentListener#changedUpdate(javax.swing.event
		 * .DocumentEvent)
		 */
		@Override
		public void changedUpdate(DocumentEvent e) {
			documentUpdate();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * javax.swing.event.DocumentListener#insertUpdate(javax.swing.event
		 * .DocumentEvent)
		 */
		@Override
		public void insertUpdate(DocumentEvent e) {
			documentUpdate();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * javax.swing.event.DocumentListener#removeUpdate(javax.swing.event
		 * .DocumentEvent)
		 */
		@Override
		public void removeUpdate(DocumentEvent e) {
			documentUpdate();
		}

		private void documentUpdate() {
			String hotelNameValue = hotelNameTxt.getText().trim();
			String cityValue = cityTxt.getText().trim();
			if (!hotelNameValue.equals("") || !cityValue.equals("")) {
				clearBtn.setEnabled(true);
			} else {
				clearBtn.setEnabled(false);
			}
		}
	}

	private JPanel buildConditionPanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0));

		JPanel containerPanel = new JPanel(new GridBagLayout());
		containerPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Search Conditions"),
				BorderFactory.createEmptyBorder(12, 12, 11, 12)));

		hotelNameTxt = new JTextField(25);
		JLabel hotelNameLbl = new JLabel("Hotel Name:");
		hotelNameLbl.setDisplayedMnemonic('H');
		hotelNameLbl.setLabelFor(hotelNameTxt);

		orRdo = new JRadioButton("OR");
		orRdo.setMnemonic('O');
		andRdo = new JRadioButton("AND");
		andRdo.setMnemonic('A');

		buttonGroup = new ButtonGroup();
		buttonGroup.add(orRdo);
		buttonGroup.add(andRdo);
		buttonGroup.setSelected(orRdo.getModel(), true);

		cityTxt = new JTextField(25);
		JLabel cityLbl = new JLabel("City:");
		cityLbl.setDisplayedMnemonic('C');
		cityLbl.setLabelFor(cityTxt);

		clearBtn = new JButton("Clear");
		clearBtn.setMnemonic('r');
		clearBtn.setToolTipText("Clear the search criteria");
		clearBtn.setEnabled(false);

		searchBtn = new JButton("Search");
		searchBtn.setMnemonic('S');
		searchBtn.setToolTipText("Search for rooms based on search criteria");

		roomsTableModel = new RoomsTableModel();
		roomsTable = new JTable(roomsTableModel);
		roomsTable.setRowSorter(new TableRowSorter<RoomsTableModel>(
				roomsTableModel));

		LayoutUtil.equalizeButtonSizes(clearBtn, searchBtn);

		containerPanel.add(hotelNameLbl, LayoutUtil.getBuilder().gridX(0)
				.gridY(0).insets(LayoutUtil.getInsets(0, 0, 0, 11)).anchor(
						GridBagConstraints.LINE_END).build());

		containerPanel.add(hotelNameTxt, LayoutUtil.getBuilder().gridX(1)
				.gridY(0).gridwidth(3).insets(LayoutUtil.getInsets(0, 0, 5, 0))
				.build());

		containerPanel.add(orRdo, LayoutUtil.getBuilder().gridX(1).gridY(1)
				.insets(LayoutUtil.getInsets(0, 0, 5, 0)).anchor(
						GridBagConstraints.LINE_START).build());

		containerPanel.add(andRdo, LayoutUtil.getBuilder().gridX(2).gridY(1)
				.insets(LayoutUtil.getInsets(0, 0, 5, 0)).anchor(
						GridBagConstraints.LINE_START).build());

		containerPanel.add(cityLbl, LayoutUtil.getBuilder().gridX(0).gridY(2)
				.insets(LayoutUtil.getInsets(0, 0, 0, 11)).anchor(
						GridBagConstraints.LINE_END).build());

		containerPanel.add(cityTxt, LayoutUtil.getBuilder().gridX(1).gridY(2)
				.gridwidth(3).insets(LayoutUtil.getInsets(0, 0, 5, 0)).build());

		containerPanel.add(clearBtn, LayoutUtil.getBuilder().gridX(2).gridY(3)
				.insets(LayoutUtil.getInsets(0, 0, 0, 5)).weightx(1).anchor(
						GridBagConstraints.LINE_END).build());

		containerPanel.add(searchBtn, LayoutUtil.getBuilder().gridX(3).gridY(3)
				.anchor(GridBagConstraints.LINE_END).build());

		panel.add(containerPanel, LayoutUtil.getBuilder().gridX(0).gridY(0)
				.build());

		return panel;
	}

	private JPanel buildTableResultsPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0));
		roomsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(roomsTable);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scrollPane, BorderLayout.CENTER);

		return panel;
	}

	private JPanel buildButtonPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		makeReservationBtn = new JButton("Make Reservation");
		makeReservationBtn.setMnemonic('M');
		makeReservationBtn
				.setToolTipText("Makes a reservation for the selected room");
		makeReservationBtn.setEnabled(false);

		// TODO IMPLEMENT CANCEL OPTION

		panel.add(makeReservationBtn);

		return panel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {

		ModelUpdate update = (ModelUpdate) arg;

		switch (update) {
		case SEARCH_RESULTS:
			List<RoomDTO> searchResults = reservationModel.getSearchResults();
			roomsTableModel.updateModel(searchResults);
			break;
		case RESERVATION_MADE:

			break;

		case RESERVATION_CANCELED:

			break;
		}
	}

}
