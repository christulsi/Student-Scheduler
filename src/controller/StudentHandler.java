package controller;

import java.util.List;

import model.Student;

import org.javalite.activejdbc.Base;

public class StudentHandler {
	
	public void create(){
		
	}
	
	public String read(int row , int col){
		
		try {
			Base.open("org.sqlite.JDBC", "jdbc:sqlite:student.db", "root", "root");
			List<String> attribute = Student.attributes();
			
			if(col == 0) return attribute.get(row);
			
			return null;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Base.close();
		}
		
		return null;
	}
	
	public void update(){
		
	}
	
	public void delete(){
		
	}
}
