package controller;

import java.util.List;

import model.CourseTime;
import model.Student;

import org.javalite.activejdbc.Base;

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
		
		try {
			Base.open("org.sqlite.JDBC", "jdbc:sqlite:student.db", "root", "root");
			Student.createIt("overall_gpa", GPACalculatorHandler.getInstance().calculateGPA()
					, "major_gpa", GPACalculatorHandler.getInstance().calculateMajorGPA()
					, "other_gpa", GPACalculatorHandler.getInstance().calculateOtherGPA());
			
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
			
			if(col == 0) return attribute.get(row+1).toUpperCase();
			
			return null;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Base.close();
		}
		
		return null;
	}
	
	public void update(){
		
		try {
			Base.open("org.sqlite.JDBC", "jdbc:sqlite:student.db", "root", "root");
			List<Student> students = Student.findAll();
			Student e = students.get(0);
			e.set("overall_gpa", GPACalculatorHandler.getInstance().calculateGPA()
				, "major_gpa", GPACalculatorHandler.getInstance().calculateMajorGPA()
				, "other_gpa", GPACalculatorHandler.getInstance().calculateOtherGPA())
			  .saveIt();
			
		} catch (Exception e) {	
			e.printStackTrace();
		}finally{
			Base.close();
		}
	}
	
	public void delete(){
		
		try {
			Base.open("org.sqlite.JDBC", "jdbc:sqlite:student.db", "root", "root");
			List<Student> students = Student.find("id =?", 1);
			Student e = students.get(0);
			e.delete();
			
		} catch (Exception e) {	
			e.printStackTrace();
		}finally{
			Base.close();
		}
	}
	
}
