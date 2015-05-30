package controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import model.CourseTime;

import org.javalite.activejdbc.Base;

public class TimeTableHandler{
	
	private static TimeTableHandler instance;
	
	public static synchronized TimeTableHandler getInstance()
	{
		if (instance == null)
			instance = new TimeTableHandler();
		return instance;
	}
	
	public TimeTableHandler(){}
	
	public void createCourseTime(String number, LocalTime row, String col){
		//DB may thrown table constraint exception
		try {
			Base.open("org.sqlite.JDBC", "jdbc:sqlite:resources/student.db", "root", "root");
			CourseTime.createIt("course_number", number, "time", row, "day", col);
		} catch (Exception e) {	
			e.printStackTrace();
		}finally{
			Base.close();
        }
	}

    public boolean updateCourseName(String name,LocalTime row, String col){
    	
		try {
			Base.open("org.sqlite.JDBC", "jdbc:sqlite:resources/student.db", "root", "root");
			CourseTime e = CourseTime.findFirst("time = ? AND day = ?",row, col);
			
			if(e == null)
				return false;
			e.set("course_number",name).saveIt();
			
		} catch (Exception e) {	
			e.printStackTrace();
		}finally{
			Base.close();
		}
		
		return true;
    }
    
public void deleteCourseTime(LocalTime row, String col){
    	
    	try {
    		Base.open("org.sqlite.JDBC", "jdbc:sqlite:resources/student.db", "root", "root");
    		CourseTime e = CourseTime.findFirst("time = ? AND day = ?", row, col);
			e.delete();
		} catch (Exception e) { 
			e.printStackTrace();
		}finally{
			Base.close();
        }
	}
    
    public String getCourseName(LocalTime row, String col){
    	//DB may throw null pointer exception
		try {
			Base.open("org.sqlite.JDBC", "jdbc:sqlite:resources/student.db", "root", "root");
			CourseTime e = CourseTime.findFirst("time = ? AND day = ?",row, col);
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
    
    public ArrayList<LocalTime> getTime(String day){
    	
    	ArrayList<LocalTime> time = new ArrayList<>();
    	try {
    		
			Base.open("org.sqlite.JDBC", "jdbc:sqlite:resources/student.db", "root", "root");
			List<CourseTime> e = CourseTime.find("day = ?", day);
			
			for (CourseTime courseTime : e) {
				time.add((LocalTime)courseTime.get("time"));
			}
			
			if(e == null) return null;
				
		} catch (Exception e) {	
			e.printStackTrace(); 
		}finally{
			Base.close();
        }
    	
    	return time;
    	
    }
}
