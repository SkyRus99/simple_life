package org.skyranger.lifegame.model;

import java.util.HashSet;
import java.util.Set;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 * This is simple realization of math game - Life
 * It allow u to define game grid, seeds it with life 
 * and than iterate it step by step, and watch what 
 * happens with it thru generations
 * 
 * @author Vasiliy Altunin
 * @version %I%, %G%
 * @since 1.0
 * 
 * This is TableModel class that allow u to add data to JTable
 */
public class GridJTableModel extends AbstractTableModel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

	private int[][] cells;	
	

	public GridJTableModel(int[][] cells) {
		this.cells = cells;
	}



	public void addTableModelListener(TableModelListener listener) {
		listeners.add(listener);
	}

	public Class<?> getColumnClass(int columnIndex) {
		Object value=this.getValueAt(0,columnIndex);  
		return (value==null?Object.class:value.getClass());
	}

	public int getRowCount() {
		return this.cells.length;
	}


	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.cells[rowIndex][columnIndex];
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	public void removeTableModelListener(TableModelListener listener) {
		listeners.remove(listener);
	}

	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		this.cells[rowIndex][columnIndex]=(int) value;
		this.fireTableCellUpdated(rowIndex, columnIndex);
	}



	@Override
	public int getColumnCount() {
		return this.cells[0].length;
	}
	

}
