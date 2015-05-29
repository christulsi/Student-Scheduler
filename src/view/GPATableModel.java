package view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import controller.GPACalculatorHandler;
import controller.StudentHandler;
import model.Course;
import model.Student;

public class GPATableModel extends AbstractTableModel{
	
	private Object[][] data = {
		    {"Overall GPA" , null},
		    {"Major GPA" , null},
		    {"Other GPA", null},
		};
	private static GPATableModel instance;
	
	public static synchronized GPATableModel getInstance()
	{
		if (instance == null)
			instance = new GPATableModel();
		return instance;
	}
	
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
		return StudentHandler.getInstance().read(row, col);
	}
	
	@Override
	public String getColumnName(int col) {
        return null;
    }
	
	@Override
	public void setValueAt(Object data, int row, int col) {	
		//update Course Info
		StudentHandler.getInstance().update();	
		fireTableCellUpdated(row, col);	//refresh cell
		fireTableDataChanged();			//refreshes table
	}

}
