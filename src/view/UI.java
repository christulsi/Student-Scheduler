package view;

import java.awt.Container;
import java.awt.event.ActionListener;
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

import controller.TimeTableHandler;

public class UI extends JFrame{
	
	private static UI instance;
	private JMenuBar menuBar;
	private JMenu file;
	private JMenuItem exit;
	private Container pane;
	private JTabbedPane tabbedPane;
	private TimeTableHandler tableHandler;
	
	private UI(){
		
		createMenu(); //create menu
		pane = getContentPane();
		tabbedPane = new JTabbedPane();
		calender();
		addtimeTableView();
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
		//create and add ActionListener using Lamdha expression
		ActionListener act = (e) -> System.exit(0);
		exit.addActionListener(act);
		file.add(exit);
		
		
	}
	
	private void calender(){
		
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
		tabbedPane.addTab("Calender",new ImageIcon("resources/images/calendar.png"), new JScrollPane(new Calendar()),"Calendar");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		pane.add(tabbedPane);
	}
	
	private void addtimeTableView(){
	  
		TimeTable timeTable = new TimeTable();
	    tabbedPane.addTab("Time Table", new ImageIcon("resources/images/time.png"), new JScrollPane(timeTable), "TimeTable");		
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);			//ALT + 2
	}
	
	private void gpaCalculator(){
			
		GPACalculator gpa = new GPACalculator();
		tabbedPane.addTab("GPA Calulator",new ImageIcon("resources/images/gpa.png"),new JScrollPane(gpa),"GPA Calculator");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);			//ALT + 2
	}
	
	public void refresh(){
		repaint();
	}
}
