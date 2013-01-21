package org.skyranger.lifegame.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import org.skyranger.lifegame.LifeGame;
import org.skyranger.lifegame.model.GridJTableModel;

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
 * This is main form that handle user interactions and data output 
 * to grid
 */
public class GridForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private LifeGridJTable table;
	private LifeGame lg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GridForm frame = new GridForm();
					frame.setTitle("Life game");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GridForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		lg = new LifeGame(20, 20);
		
		lg.seed(4, 3);
		lg.seed(4, 4);
		lg.seed(4, 5);
		lg.seed(3, 4);
		
		GridJTableModel gmodel = new GridJTableModel(lg.getGrid());
		
		table = new LifeGridJTable();
		
		table.setModel(gmodel);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for (int i=0;i<=lg.getGrid()[4].length-1;i++)
		{
			table.getColumnModel().getColumn(i).setMinWidth(5);
		}
			
		
		
		contentPane.add(table, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lg.doLife();
				GridJTableModel gmodel = new GridJTableModel(lg.getGrid());
				table.setModel(gmodel);
				contentPane.validate();
			}
		});
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
	}

}

