package controller;

import java.text.DecimalFormat;
import java.util.List;

import model.Student;

import org.javalite.activejdbc.Base;

import view.GPATableModel;
import view.UI;

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
		
		DecimalFormat df = new DecimalFormat("#.##");
		double gpa = Double.parseDouble(df.format(GPACalculatorHandler.getInstance().calculateGPA()));
		double major = Double.parseDouble(df.format(GPACalculatorHandler.getInstance().calculateMajorGPA()));
		double other = Double.parseDouble(df.format(GPACalculatorHandler.getInstance().calculateOtherGPA()));
		
		try {
			Base.open("org.sqlite.JDBC", "jdbc:sqlite:resources/student.db", "root", "root");
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
			Base.open("org.sqlite.JDBC", "jdbc:sqlite:resources/student.db", "root", "root");
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
		
		DecimalFormat df = new DecimalFormat("#.##");
		double gpa = Double.parseDouble(df.format(GPACalculatorHandler.getInstance().calculateGPA()));
		double major = Double.parseDouble(df.format(GPACalculatorHandler.getInstance().calculateMajorGPA()));
		double other = Double.parseDouble(df.format(GPACalculatorHandler.getInstance().calculateOtherGPA()));
		
		try {
			Base.open("org.sqlite.JDBC", "jdbc:sqlite:resources/student.db", "root", "root");
			Student e = Student.findById(1);
			e.set("overall_gpa", gpa).set("major_gpa", major)
				.set("other_gpa", other).saveIt();
		
			GPATableModel.getInstance().fireTableDataChanged();//refresh the gpa table
			UI.getInstance().refresh();
			
		} catch (Exception e) {	
			e.printStackTrace();
		}finally{
			Base.close();
		}
	}
	
	public void delete(){
		
		try {
			Base.open("org.sqlite.JDBC", "jdbc:sqlite:resources/student.db", "root", "root");
			Student e = Student.findById(1);
			e.delete();
			
		} catch (Exception e) {	
			e.printStackTrace();
		}finally{
			Base.close();
		}
	}
	
}
