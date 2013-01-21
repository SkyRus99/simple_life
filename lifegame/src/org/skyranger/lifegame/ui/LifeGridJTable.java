package org.skyranger.lifegame.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

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
 * JTable custom class that extends JTable and add mouse listener 
 * to grid
 */

public class LifeGridJTable extends JTable {

	private static final long serialVersionUID = 1L;

	public LifeGridJTable() {
		super();

		final JTable table = this;
		
		this.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mousePressed(MouseEvent e) {
				int row=table.rowAtPoint(e.getPoint());
				int col= table.columnAtPoint(e.getPoint());
				if ((int)table.getValueAt(row,col)==1)
				{
					table.setValueAt(0,row,col);
				}
				else
				{
					table.setValueAt(1,row,col);
				}
	        }
		});		
	}

	public TableCellRenderer getCellRenderer(int row, int column)
    {
        return new ColorCellRenderer();
    }
}
