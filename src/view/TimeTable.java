package view;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

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
		
		//retrieve values from database 
		for (int i = 1; i < 7; i++) {
			for (int j = 0; j < this.getRowCount(); j++) {	
				this.setValueAt(TimeTableHandler.getInstance().getCourseName(j, i), j, i);
			}
		}
	}

}
