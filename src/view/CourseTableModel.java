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
	
	String[] columnNames = {"Course Number", "Course Name","M/O","Credit","Grade","Points"};

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		int i = 4;
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
		return i;
	}
	
	public String getColumnName(int col) {
        return columnNames[col];
    }
	
	public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col > 4) {
            return false;
        } else {
            return true;
        }
    }
	
	@Override
	public Object getValueAt(int row, int col) {
		return GPACalculatorHandler.getInstance().getCourseInfo(row, col);
	}
	
	public void setValueAt(Object value, int row, int col) {
		
        fireTableCellUpdated(row, col);
    }
	
	@Override
	public void addTableModelListener(TableModelListener l) {
		
		super.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				int row = e.getLastRow();  
		        int col = e.getColumn();  
		        TableModel model = (TableModel)e.getSource();  
		        Object data = model.getValueAt(row, col);
			
		        if(data.equals("")){
		        	//GPACalculatorHandler.getInstance().deleteCourseTime(row, col);
		        }else if(!GPACalculatorHandler.getInstance().updateCourseName(data.toString(), row, col)){
		        	//GPACalculatorHandler.getInstance().createCourseTime(data.toString(), row, col);
		        }
			}
		});
	}

}
