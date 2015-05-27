package view;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import org.javalite.activejdbc.Base;

import controller.TimeTableHandler;

public class TimeTable extends JTable{
	
	public TimeTable(){
		
		setModel(new TimeTableModel());
		setRowHeight(45);
		getTableHeader().setReorderingAllowed(false);
		
		//center table cells
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		setDefaultRenderer(Object.class, centerRenderer);
				
		//Set selection to single cell
		setColumnSelectionAllowed(true);
		setRowSelectionAllowed(true);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		retrieveDBdata();
		
	}
	
	private void retrieveDBdata(){
		//retrieve values from database 
		for (int i = 1; i < 7; i++) {
			for (int j = 0; j < this.getRowCount(); j++) {	
				this.setValueAt(TimeTableHandler.getInstance().getCourseName(j , getColumnName(i)), j, i);
			}
		}
	}

}
