package view;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
	
public class TimeTableModel extends AbstractTableModel{
	
	private String[] columnNames = {"Time", "Monday","Tuesday","Wednesday",
            "Thursday", "Friday","Saturday"};

	private Object[][] data = {
		    {"8:15-9:10"  , null, null, null, null,  null, null},
		    {"9:15-10:10" , null, null, null, null,  null, null},
		    {"10:15-11:10", null, null, null, null,  null, null},
		    {"11:15-12:10", null, null, null, null,  null, null},
		    {"12:15-1:10" , null, null, null, null,  null, null},
		    {"13:15-14:10", null, null, null, null,  null, null},
		    {"14:15-15:10", null, null, null, null,  null, null},
		    {"15:15-16:10", null, null, null, null,  null, null},
		    {"16:15-17:10", null, null, null, null,  null, null},
		    {"17:15-18:10", null, null, null, null,  null, null},
		    {"18:15-19:10", null, null, null, null,  null, null},
		    {"19:15-20:10", null, null, null, null,  null, null},
		    {"20:15-21:10", null, null, null, null,  null, null},
		    {"21:15-22:10", null, null, null, null,  null, null},
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
	
}
