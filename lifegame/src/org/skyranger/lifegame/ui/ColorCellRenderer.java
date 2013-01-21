package org.skyranger.lifegame.ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

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
 * CellRenderer class to colors cells in table 
 * to grid
 */

public class ColorCellRenderer extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	public Component  getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column) {

		  Component rendererComp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,row, column);

		  if ((int)value==1)
		  {
			  rendererComp.setForeground(Color.green);
			  rendererComp.setBackground(Color.green);
		  }
		  else
		  {
			  rendererComp.setBackground(Color.white);			  
			  rendererComp.setForeground(Color.white);
		  }

		 return rendererComp ;
		 }

}



