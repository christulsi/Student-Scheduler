package view;

import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddCourse extends JPanel{
	
	private JTextField courseNumber = new JTextField(6);
    private JTextField courseName = new JTextField(6);
    private JTextField m_o = new JTextField(6);
    private JTextField credits = new JTextField(6);
    private JTextField grade = new JTextField(6);

	
	public AddCourse(){
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
	   
	    add(new JLabel("Course Number:"));
	    add(courseNumber);
	    add(Box.createHorizontalStrut(15)); // a spacer
	    add(new JLabel("Course Name:"));
	    add(courseName); 
	    add(new JLabel("M/O:"));
	    add(m_o);
	    add(new JLabel("Credits:"));
	    add(credits);
	    add(new JLabel("Grade:"));
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


	public JTextField getM_o() {
		return m_o;
	}


	public void setM_o(JTextField m_o) {
		this.m_o = m_o;
	}


	public JTextField getCredits() {
		return credits;
	}


	public void setCredits(JTextField credits) {
		this.credits = credits;
	}


	public JTextField getGrade() {
		return grade;
	}


	public void setGrade(JTextField grade) {
		this.grade = grade;
	}
}
