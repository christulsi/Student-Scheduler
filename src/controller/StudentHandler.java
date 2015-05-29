package controller;

import java.util.List;

import model.CourseTime;
import model.Student;

import org.javalite.activejdbc.Base;

import view.GPATableModel;

public class StudentHandler {
	
	private static StudentHandler instance;
	
	public static synchronized StudentHandler getInstance()
	{
		if (instance == null)
			instance = new StudentHandler();
		return instance;
	}
	
	public StudentHandler(){}
	
	public void create(){
		
		double gpa = GPACalculatorHandler.getInstance().calculateGPA();
		double major = GPACalculatorHandler.getInstance().calculateMajorGPA();
		double other = GPACalculatorHandler.getInstance().calculateOtherGPA();
		
		try {
			Base.open("org.sqlite.JDBC", "jdbc:sqlite:student.db", "root", "root");
			Student e = Student.findById(1);
			if(e == null){
				Student.createIt("overall_gpa", gpa
						, "major_gpa", major
						, "other_gpa", other);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Base.close();
		}
		
	}
	
	public String read(int row , int col){
		
		try {
			Base.open("org.sqlite.JDBC", "jdbc:sqlite:student.db", "root", "root");
			List<String> attribute = Student.attributes();
			Student student = Student.findById(1);
			
			if(col == 0) return attribute.get(row+1).toUpperCase();
			else if(row == 0) return student.get(attribute.get(row+1)).toString();
			else if(row == 1) return student.get(attribute.get(row+1)).toString();
			else if(row == 2) return student.get(attribute.get(row+1)).toString();
					
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Base.close();
		}
		
		return null;
	}
	
	public void update(){
		double gpa = GPACalculatorHandler.getInstance().calculateGPA();
		double major = GPACalculatorHandler.getInstance().calculateMajorGPA();
		double other = GPACalculatorHandler.getInstance().calculateOtherGPA();
		
		try {
			Base.open("org.sqlite.JDBC", "jdbc:sqlite:student.db", "root", "root");
			Student e = Student.findById(1);
			e.set("overall_gpa", gpa).set("major_gpa", major)
				.set("other_gpa", other).saveIt();
			Thread.sleep(500);
			GPATableModel.getInstance().fireTableDataChanged();
			
		} catch (Exception e) {	
			e.printStackTrace();
		}finally{
			Base.close();
		}
	}
	
	public void delete(){
		
		try {
			Base.open("org.sqlite.JDBC", "jdbc:sqlite:student.db", "root", "root");
			Student e = Student.findById(1);
			e.delete();
			
		} catch (Exception e) {	
			e.printStackTrace();
		}finally{
			Base.close();
		}
	}
	
}
