package view;

import java.awt.Container;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

public class UI extends JFrame{
	
	private static UI instance;
	private JMenuBar menuBar;
	private JMenu file;
	private JMenuItem exit;
	private Container pane;
	private JTabbedPane tabbedPane;
	
	
	public UI(){
		
		createMenu(); //create menu
		pane = getContentPane();
		tabbedPane = new JTabbedPane();
		calender();
		timeTableView();
		gpaCalculator();
		
		//JFrame properties 
		setTitle("Student Scheduler");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(900,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static synchronized UI getInstance()
	{
		if (instance == null)
			instance = new UI();
		return instance;
	}
	
	//Create MenuBar
	private void createMenu(){
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);	//set menuBAr
		
		//add File menus to menuBar
		file = new JMenu("File");
		menuBar.add(file);
		
		file.addSeparator();
		exit = new JMenuItem("Exit");
		file.add(exit);
			
	}
	
	public void calender(){
		
		String[] calendarColumnNames = {"Sunday", "Monday","Tuesday","Wednesday",
                "Thursday", "Friday","Saturday"};
		
		Object[][] data = {
			    {null, null, null, null, null,  null, null},
			    {null, null, null, null, null,  null, null},
			    {null, null, null, null, null,  null, null},
			    {null, null, null, null, null,  null, null},
			    {null, null, null, null, null,  null, null},
			};
		
		//JPanel panel1 = new JPanel();
		//ImageIcon icon = createImageIcon("images/middle.gif");
		
		JTable calendarTable = new JTable(data, calendarColumnNames);
		calendarTable.setRowHeight(120);
		calendarTable.getTableHeader().setResizingAllowed(false);
		calendarTable.getTableHeader().setReorderingAllowed(false);
		calendarTable.setColumnSelectionAllowed(true);
		calendarTable.setRowSelectionAllowed(true);
		calendarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//center table cells
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		calendarTable.setDefaultRenderer(Object.class, centerRenderer);


		//panel1.add(new JScrollPane(table));
		tabbedPane.addTab("Calender",new ImageIcon("images/calendar.png"), new JScrollPane(calendarTable),"Calendar");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		pane.add(tabbedPane);
	}
	
	public void timeTableView(){
	  
		JTable timeTable = new JTable(new TimeTableModel());
		timeTable.setRowHeight(45);
		timeTable.getTableHeader().setReorderingAllowed(false); //disables reordering of column
		
		//center table cells
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		timeTable.setDefaultRenderer(Object.class, centerRenderer);
		
		//Set selection to single cell
		timeTable.setColumnSelectionAllowed(true);
		timeTable.setRowSelectionAllowed(true);
		timeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	    tabbedPane.addTab("Time Table", new ImageIcon("images/time.png"), new JScrollPane(timeTable), "TimeTable");		
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);			//ALT + 2
	}
	
	public void gpaCalculator(){
	
		String[] columnNames = {"Course", "Course Name","M/O","Credit","Grade","Points"};
		Object[][] data = {
			    {"", null, null, null, null,null},
			    {"", null, null, null, null,null},
			    {"", null, null, null, null,null},
			    {"", null, null, null, null,null},
			    {"", null, null, null, null,null},
			    {"", null, null, null, null,null},
			    {"", null, null, null, null,null},
			};
		
		JTable table = new JTable(data, columnNames);
		table.setRowHeight(45);
		table.getTableHeader().setReorderingAllowed(false); //disables reordering of column
		
		//center table cells
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.setDefaultRenderer(Object.class, centerRenderer);
		
		//Set selection to single cell
		table.setColumnSelectionAllowed(true);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tabbedPane.addTab("GPA Calulator",new ImageIcon("images/gpa.png"),new JScrollPane(table),"GPA Calculator");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);			//ALT + 2
	}
	
	
}
