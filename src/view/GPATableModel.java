package view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Course;

public class GPATableModel extends AbstractTableModel{
	
	private Object[][] data = {
		    {"Overall GPA" , null},
		    {"Major GPA" , null},
		    {"Other GPA", null},
		};
	
	//set number of column for table
	@Override
	public int getColumnCount() {
		return 2;
	}
	
	//set number of row for table
	@Override
	public int getRowCount() {
		return 3;
	}
	
	//set default value for table
	@Override
	public Object getValueAt(int row, int col) {

		return null;
	}

}
