package view;

import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class AddCourse extends JPanel{
	
	private JTextField courseNumber = new JTextField(6);
    private JTextField courseName = new JTextField(6);
    private JComboBox<String> m_o = new JComboBox<>();
    private JTextField credits = new JTextField(6);
    private JComboBox<String> grade = new JComboBox<>();

	
	public AddCourse(){
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
	   
	    add(new JLabel("Course Number:"));
	    add(courseNumber);
	    add(Box.createHorizontalStrut(15)); // a spacer
	    add(new JLabel("Course Name:"));
	    add(courseName); 
	    add(new JLabel("M/O:"));
	    
	    //add M/O combobox to dialog
	    DefaultComboBoxModel<String> cmodel = new DefaultComboBoxModel<>();
	    cmodel.addElement("M");
	    cmodel.addElement("O");
	    m_o.setModel(cmodel);
	    add(m_o);
	    
	    add(new JLabel("Credits:"));
	    add(credits);
	    add(new JLabel("Grade:"));
	    
	    DefaultComboBoxModel<String> gmodel = new DefaultComboBoxModel<>();
	    gmodel.addElement("A");
	    gmodel.addElement("B");
	    gmodel.addElement("C");
	    gmodel.addElement("D");
	    gmodel.addElement("F");
	    grade.setModel(gmodel);
	    add(grade);
	    
	}


	public JTextField getCourseNumber() {
		return courseNumber;
	}


	public void setCourseNumber(JTextField courseNumber) {
		this.courseNumber = courseNumber;
	}


	public JTextField getCourseName() {
		return courseName;
	}


	public void setCourseName(JTextField courseName) {
		this.courseName = courseName;
	}


	public JComboBox<String> getM_o() {
		return m_o;
	}


	public void setM_o(JComboBox<String> m_o) {
		this.m_o = m_o;
	}


	public JTextField getCredits() {
		return credits;
	}


	public void setCredits(JTextField credits) {
		this.credits = credits;
	}


	public JComboBox<String> getGrade() {
		return grade;
	}


	public void setGrade(JComboBox grade) {
		this.grade = grade;
	}
}
