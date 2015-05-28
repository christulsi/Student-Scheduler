package view;

import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import org.javalite.activejdbc.Base;

import controller.GPACalculatorHandler;
import model.Course;

public class CourseTableModel extends AbstractTableModel{
	
	private String[] columnNames = {"Course Number", "Course Name","M/O","Credit","Grade","Points"};
	
	//set number of column for table
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}
	
	//set number of rowa for table
	@Override
	public int getRowCount() {
		int i = 0;
		try {
			Base.open("org.sqlite.JDBC", "jdbc:sqlite:student.db", "root", "root");
			List<Course> course = Course.findAll();
			i = course.toArray().length;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Base.close();
		}
		return i;//row count based on DB 
	}
	
	//set column Names for table
	public String getColumnName(int col) {
        return columnNames[col];
    }
	
	//set editable cells for table
	public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col > 4) {
            return false;
        } else {
            return true;
        }
    }
	
	//set default value for table
	@Override
	public Object getValueAt(int row, int col) {
		//populate Table with DB info
		return GPACalculatorHandler.getInstance().read(row, col);
	}
	
	//set updated values for table
	@Override
	public void setValueAt(Object data, int row, int col) {	
		//update Course Info
		GPACalculatorHandler.getInstance().update(data.toString(), row, col);
		fireTableCellUpdated(row, col);	//refresh cell
		fireTableDataChanged();			//refreshes table
	}
}
