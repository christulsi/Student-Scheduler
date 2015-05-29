package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import controller.GPACalculatorHandler;
import controller.StudentHandler;

public class GPACalculator extends JPanel{
	
	private JButton addButton;
	private CourseTableModel courseModel = new CourseTableModel();
	private GPATableModel gpaModel = new GPATableModel();
	
	public GPACalculator(){
		
		setLayout(new BoxLayout( this, BoxLayout.Y_AXIS));
		
		//Create addButton and  attached listener
		addButton = new JButton("Add Course");
		
		
		add(addButton);			//add button to panel
		createCourseTable();	//add course Table to panel
		createGPATable();		//add GPA Table to panel
		
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				AddCourse addPanel = new AddCourse();
				
				int result = JOptionPane.showConfirmDialog(null, addPanel, 
			               "Add Course", JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
			      if (result == JOptionPane.OK_OPTION) {
			    	  GPACalculatorHandler.getInstance().create(addPanel.getCourseNumber().getText(), addPanel.getCourseName().getText(), 
			    			  Integer.parseInt(addPanel.getCredits().getText()), addPanel.getM_o().getText(), addPanel.getGrade().getText());
			    	  courseModel.fireTableDataChanged(); 	  
     		    	  StudentHandler.getInstance().update();
			      }
				
			}
		});
	}
	
	//Create Course Table 
	private void createCourseTable(){
		
		JTable course = new JTable(courseModel);
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
		
		JScrollPane scroll = new JScrollPane(course);
		scroll.setBorder(BorderFactory.createEmptyBorder());
		
		add(scroll);
	}
	
	//Create GPATable 
		private void createGPATable(){
			
			JTable gpa = new JTable(gpaModel);
			gpa.setRowHeight(30);
			gpa.getTableHeader().setReorderingAllowed(false); //disables reordering of column
			
			//center table cells
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment( JLabel.CENTER );
			gpa.setDefaultRenderer(Object.class, centerRenderer);
			
			//Set selection to single cell
			gpa.setColumnSelectionAllowed(true);
			gpa.setRowSelectionAllowed(true);
			gpa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			JScrollPane scroll = new JScrollPane(gpa);
			scroll.setBorder(BorderFactory.createEmptyBorder());
			scroll.setMaximumSize(new Dimension(500, 300));
			
			StudentHandler.getInstance().create();//Create Student if non existent
			
			add(scroll);
		}
	
}

