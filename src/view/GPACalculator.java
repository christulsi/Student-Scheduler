package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import controller.GPACalculatorHandler;
import controller.TimeTableHandler;

public class GPACalculator extends JPanel{
	
	private JButton addButton;
	
	public GPACalculator(){
		
		setLayout(new BoxLayout( this, BoxLayout.Y_AXIS));
		addButton = new JButton("Add Course");
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		add(addButton);
		createCourse();
	}
	
	private void createCourse(){
		
		JTable course = new JTable(new CourseTableModel());
		course.setRowHeight(30);
		course.getTableHeader().setReorderingAllowed(false); //disables reordering of column
		
		//center table cells
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		course.setDefaultRenderer(Object.class, centerRenderer);
		
		//Set selection to single cell
		course.setColumnSelectionAllowed(true);
		course.setRowSelectionAllowed(true);
		course.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		add(new JScrollPane(course));
	}
	
}

