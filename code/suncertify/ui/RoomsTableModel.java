/*
 * @(#)RoomsTableModel.java 1.0 2 Mar 2010
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

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import suncertify.common.RoomDTO;

/**
 * @author Chris Hatton
 * 
 */
@SuppressWarnings("serial")
class RoomsTableModel extends AbstractTableModel {

	/** Column names to be displayed */
	private List<String> columnNames = new ArrayList<String>(7);

	/** Search results to be displayed */
	private List<RoomDTO> rooms = new ArrayList<RoomDTO>(0);

	{
		columnNames.add("Hotel Name");
		columnNames.add("City");
		columnNames.add("Max Persons");
		columnNames.add("Smoking");
		columnNames.add("Price per Night ($)");
		columnNames.add("Date Available");
		columnNames.add("Reserved");
	}

	RoomsTableModel() {
		super();
	}

	RoomsTableModel(List<RoomDTO> rooms) {
		super();
		this.rooms = rooms;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	public String getColumnName(int column) {
		return columnNames.get(column);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return columnNames.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return rooms.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		RoomDTO room = rooms.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return room.getHotelName();
		case 1:
			return room.getCity();
		case 2:
			return room.getMaximumOccupancy();
		case 3:
			boolean isSmokingRoom = room.isSmokingRoom();
			return isSmokingRoom ? "Yes" : "No";
		case 4:
			return room.getPricePerNight();// including currency symbol?
		case 5:
			return room.getDateAvalable(); // will need to make sure it follows
											// the format yyyy/mm/dd
		case 6:
			boolean isRoomReserved = room.isReserved();
			return isRoomReserved ? "Yes" : "No";
		default:
			return "";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == 2) {
			return Byte.class;
		} else if (columnIndex == 4) {
			return Double.class;
		} else if (columnIndex == 5) {
			return Date.class;
		} else {
			return String.class;
		}
	}

	/**
	 * Updates the model with the search results.
	 * 
	 * @param rooms
	 *            <code>List</code> containing all the <code>RoomDTO</code>
	 *            objects to be displayed.
	 */
	void updateModel(List<RoomDTO> rooms) {
		this.rooms = rooms;
		this.fireTableDataChanged();
	}
}
