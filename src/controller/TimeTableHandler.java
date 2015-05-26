package controller;

import java.util.List;

import org.javalite.activejdbc.Base;

import model.CourseTime;

public class TimeTableHandler{
	
	private static TimeTableHandler instance;
	
	public static synchronized TimeTableHandler getInstance()
	{
		if (instance == null)
			instance = new TimeTableHandler();
		return instance;
	}
	
	public TimeTableHandler(){}
	
	public void createCourseTime(String number, int row, String col){
		//DB may thrown table constraint exception
		try {
			Base.open("org.sqlite.JDBC", "jdbc:sqlite:student.db", "root", "root");
			CourseTime.createIt("course_number", number, "time", row, "day", col);
		} catch (Exception e) {	
			e.printStackTrace();
		}finally{
			Base.close();
        }
	}

    public void updateCourseName(String name,int row, String col){
    	
		try {
			Base.open("org.sqlite.JDBC", "jdbc:sqlite:student.db", "root", "root");
			CourseTime e = CourseTime.findFirst("time = ? AND day = ?",row, col);
			System.out.println(e.get("course_number").toString());
			e.set("course_number",name).saveIt();
			
			System.out.println(name+"looooooooooooooooo");
			
		} catch (Exception e) {	
			e.printStackTrace();
		}finally{
			Base.close();
		}
	
		
    }
    
public void deleteCourseTime(int row, String col){
    	
    	try {
    		Base.open("org.sqlite.JDBC", "jdbc:sqlite:student.db", "root", "root");
			List<CourseTime> courseTime = CourseTime.where("time = ? AND day = ?",row, col);
			CourseTime e = courseTime.get(0);
			e.delete();
		} catch (Exception e) { 
			e.printStackTrace();
		}finally{
			Base.close();
        }
	}
    
    public String getCourseName(int row, String col){
    	//DB may throw null pointer exception
		try {
			Base.open("org.sqlite.JDBC", "jdbc:sqlite:student.db", "root", "root");
			List<CourseTime> courseTime = CourseTime.where("time = ? AND day = ?",row, col);
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
