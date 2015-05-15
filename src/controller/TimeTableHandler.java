package controller;

import java.util.List;

import org.javalite.activejdbc.Base;

import model.CourseTime;

public class TimeTableHandler {
	
	private static TimeTableHandler instance;
	
	public static synchronized TimeTableHandler getInstance()
	{
		if (instance == null)
			instance = new TimeTableHandler();
		return instance;
	}
	
	public TimeTableHandler(){
	}
	
	public void createCourseTime(String number, int row, String col){
		//DB may thrown table constraint exception
		try {
			CourseTime.createIt("course_number", number, "time", row, "day", col);
		} catch (Exception e) {	e.printStackTrace();}
	}

    public void deleteCourseTime(int row, String col){
    	try {
			List<CourseTime> courseTime = CourseTime.where("time = ? AND day = ?",row, col);
			CourseTime e = courseTime.get(0);
			e.delete();
		} catch (Exception e) { e.printStackTrace();}
	}
    
    public String getCourseName(int row, String col){
    	//DB may throw null pointer exception
		try {
			List<CourseTime> courseTime = CourseTime.where("time = ? AND day = ?",row, col);
			CourseTime e = courseTime.get(0);
			String coursenum = e.get("course_number").toString();
				
			if(!coursenum.equals(null))
				return coursenum;
		} catch (Exception e) {	e.printStackTrace(); }
		
		return "";
    }
    
    public void updateCourseName(String name,int row, String col){
    	
		try {
			CourseTime e = CourseTime.findFirst("time = ? AND day = ?",row, col);
			System.out.println(e.get("course_number").toString());
			e.set("course_number",name).saveIt();
			
			System.out.println(name+"looooooooooooooooo");
			
		} catch (Exception e) {	e.printStackTrace();
		System.out.println(name+"winn");
		}
		
    }
}
