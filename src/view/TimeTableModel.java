package view;

import java.time.LocalTime;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import controller.TimeTableHandler;
	
public class TimeTableModel extends AbstractTableModel{
	
	private String[] columnNames = {"Time", "Monday","Tuesday","Wednesday",
            "Thursday", "Friday","Saturday"};

	private Object[][] data = {
		    {LocalTime.of(8, 15) , null, null, null, null,  null, null},
		    {LocalTime.of(9, 15) , null, null, null, null,  null, null},
		    {LocalTime.of(10, 15), null, null, null, null,  null, null},
		    {LocalTime.of(11, 15), null, null, null, null,  null, null},
		    {LocalTime.of(12, 15), null, null, null, null,  null, null},
		    {LocalTime.of(13, 15), null, null, null, null,  null, null},
		    {LocalTime.of(14, 15), null, null, null, null,  null, null},
		    {LocalTime.of(15, 15), null, null, null, null,  null, null},
		    {LocalTime.of(16, 15), null, null, null, null,  null, null},
		    {LocalTime.of(17, 15), null, null, null, null,  null, null},
		    {LocalTime.of(18, 15), null, null, null, null,  null, null},
		    {LocalTime.of(19, 15), null, null, null, null,  null, null},
		    {LocalTime.of(20, 15), null, null, null, null,  null, null},
		    {LocalTime.of(21, 15), null, null, null, null,  null, null},
		};
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 14;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return data[rowIndex][columnIndex];
	}
	
	public String getColumnName(int col) {
        return columnNames[col];
    }
	
	public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 1) {
            return false;
        } else {
            return true;
        }
    }
	
	public void setValueAt(Object value, int row, int col) {
		
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
	
	@Override
	public void addTableModelListener(TableModelListener l) {
		
		super.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				int row = e.getLastRow();  
		        int col = e.getColumn();  
		        String colName = getColumnName(col);
		        TableModel model = (TableModel)e.getSource();  
		        LocalTime time = (LocalTime)model.getValueAt(row, 0);
		        Object data = model.getValueAt(row, col);
				
		        if(data.equals("")){
		        	TimeTableHandler.getInstance().deleteCourseTime(time, colName);
		        }else if(!TimeTableHandler.getInstance().updateCourseName(data.toString(), time, colName)){
		        	TimeTableHandler.getInstance().createCourseTime(data.toString(), time, colName);
		        }
			}
		});
	}

}
