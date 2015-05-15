package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class Calendar extends JPanel implements ActionListener{
	
	private static JLabel month;
    private static JLabel year;
    private static JButton next;
    private static JButton previous;
    private static JTable calendarTable;
    private static JComboBox yearMenu;
    private static Container pane;
    private static DefaultTableModel model;
    private static JScrollPane scroller;
    private final JPanel panel;
    private GregorianCalendar Gcalendar;
    private int realDay, realMonth, realYear, currentMonth, currentYear;
    
    String[] dayOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    public Calendar() {
        
    	Gcalendar = new GregorianCalendar();
        month = new JLabel("January");
        year = new JLabel("change year:");
        yearMenu = new JComboBox();
        next = new JButton("Next >>");
        next.addActionListener(this);
        next.setToolTipText("next");
        previous = new JButton("<< Previous");
        previous.setToolTipText("previous");
        previous.addActionListener(this);
        
        model= new DefaultTableModel(){   
		        @Override
		        public boolean isCellEditable(int rowIndex, int mColIndex){return true;}
		};
		
        calendarTable = new JTable(model);
        scroller = new JScrollPane(calendarTable);
        panel = new JPanel(null);

       // pane = this.getContentPane();
       //panel.setBorder(BorderFactory.createTitledBorder("Calendar"));

        add(panel);
        panel.add(month);
        panel.add(year);
        panel.add(yearMenu);
        panel.add(next);
        panel.add(previous);
        panel.add(scroller);

        panel.setBounds(0, 0, 1366, 760);
        month.setBounds(660 , 25, 100, 25);
        year.setBounds(1000, 25, 80, 20);
        yearMenu.setBounds(1100, 25, 80, 20);
        previous.setBounds(10, 25, 120, 25);
        next.setBounds(1230, 25, 120, 25);
        scroller.setBounds(10, 50, 1340, 700);

        realDay = Gcalendar.get(GregorianCalendar.DAY_OF_MONTH);
        realMonth = Gcalendar.get(GregorianCalendar.MONTH);
        realYear = Gcalendar.get(GregorianCalendar.YEAR);
        currentMonth = realMonth;
        currentYear = realYear;
 
        populateYearMenu();
        populateCalendar();
        initialize();
        refreshCalendar(currentMonth,2015);

        setLayout(null);
    }

    public void populateYearMenu() {
        for (int i = realYear - 100; i <= realYear + 100; i++) {
            yearMenu.addItem(String.valueOf(i));
        }
    }

    public void populateCalendar() {

        for (int i = 0; i < 7; i++) {
            model.addColumn(dayOfWeek[i]);
        }

    }

    public void initialize() {
    	
        calendarTable.getParent().setBackground(calendarTable.getBackground());
        calendarTable.getTableHeader().setResizingAllowed(false);
        calendarTable.getTableHeader().setReorderingAllowed(false);
        calendarTable.setColumnSelectionAllowed(true);
        calendarTable.setRowSelectionAllowed(true);
        calendarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        calendarTable.setRowHeight(95);
        model.setColumnCount(7);
        model.setRowCount(6);
    }

    public void refreshCalendar(int selectedMonth, int selectedYear) {

        int numberdays;
        int monthStarts;

        previous.setEnabled(true); //Enable buttons at first
        next.setEnabled(true);

        if (selectedMonth == 0 && selectedYear <= realYear - 10) {
            previous.setEnabled(false);
        } //Too early

        if (selectedMonth == 11 && selectedYear >= realYear + 100) {
            next.setEnabled(false);
        } //Too late

        month.setText(months[selectedMonth]);
        month.setBounds(160 - month.getPreferredSize().width / 2, 25, 180, 25);
        yearMenu.setSelectedItem(String.valueOf(selectedYear));

        GregorianCalendar newCalendar = new GregorianCalendar(selectedYear, selectedMonth, 1);
        numberdays = newCalendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        monthStarts = newCalendar.get(GregorianCalendar.DAY_OF_WEEK);

        //Clear table
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                model.setValueAt(null, i, j);
            }
        }

        for (int i = 1; i <= numberdays; i++) {
            int row = new Integer((i + monthStarts - 2) / 7);
            int column = (i + monthStarts - 2) % 7;
            model.setValueAt(i, row, column);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source instanceof JButton) {

            switch (((JButton) e.getSource()).getToolTipText()) {

                case "previous":
                    if (currentMonth == 0) { //Back one year
                        currentMonth = 11;
                        currentYear -= 1;
                    } else { //Back one month
                        currentMonth -= 1;
                    }
                    refreshCalendar(currentMonth, currentYear);
                    break;

                case "next":
                    if(currentMonth == 11){ //Foward one year
                        currentMonth = 0;
                        currentYear += 1;
                        
                    } else{ //Foward one month
                        currentMonth += 1;    
                    }
                    refreshCalendar(currentMonth, currentYear);
                    break;

            }
        }

        
        else if(e.getSource().equals(yearMenu)){
        if (yearMenu.getSelectedItem()!= null){
            String value = yearMenu.getSelectedItem().toString();
            currentYear = Integer.parseInt(value); 
            refreshCalendar(currentMonth, currentYear); 
        }

    }

    }
}
