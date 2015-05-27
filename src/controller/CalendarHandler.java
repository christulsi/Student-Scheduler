package controller;

import java.util.List;

import model.Course;
import model.CourseTime;

import org.javalite.activejdbc.Base;

public class CalendarHandler {
	
private static CalendarHandler instance;
	
	public static synchronized CalendarHandler getInstance()
	{
		if (instance == null)
			instance = new CalendarHandler();
		return instance;
	}
	
	public CalendarHandler(){}
	
	public void createCourseGrade(String number,int date){
		//DB may thrown table constraint exception
		try {
			Base.open("org.sqlite.JDBC", "jdbc:sqlite:student.db", "root", "root");
			Course.createIt("course_number", number, "date", date);
		} catch (Exception e) {	
			e.printStackTrace();
		}finally{
			Base.close();
        }
	}

    public boolean updateCourseName(String number){
    	
		try {
			Base.open("org.sqlite.JDBC", "jdbc:sqlite:student.db", "root", "root");
			Course e = CourseTime.findFirst("course_number = ? ", number);
			
			if(e == null)
				return false;
			e.set("course_number",number).saveIt();
			
		} catch (Exception e) {	
			e.printStackTrace();
		}finally{
			Base.close();
		}
		
		return true;
    }
    
public void deleteCourseTime(int row, String col){
    	
    	try {
    		Base.open("org.sqlite.JDBC", "jdbc:sqlite:student.db", "root", "root");
    		CourseTime e = CourseTime.findFirst("time = ? AND day = ?",row, col);
			e.delete();
		} catch (Exception e) { 
			e.printStackTrace();
		}finally{
			Base.close();
        }
	}
    
    public String getCourseName(String number){
    	//DB may throw null pointer exception
		try {
			Base.open("org.sqlite.JDBC", "jdbc:sqlite:student.db", "root", "root");
			List<CourseTime> courseTime = CourseTime.where("course_number = ? ", number);
			CourseTime e = courseTime.get(0);
			String coursenum = e.get("course_number").toString();
				
			if(!coursenum.equals(null))
				return coursenum;
		} catch (Exception e) {	
			e.printStackTrace(); 
		}finally{
			Base.close();
        }
		
		return "";
    }

}
