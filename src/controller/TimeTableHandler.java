package controller;

import java.util.List;

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
	
	public void createCourseTime(String number, int row, int col){
		//DB may thrown table constraint exception
		try {
			CourseTime.createIt("course_number", number, "time", row, "day", col);
		} catch (Exception e) {	e.printStackTrace();}
	}

    public void deleteCourseTime(int row, int col){
    	try {
			List<CourseTime> courseTime = CourseTime.where("time = ? AND day = ?",row, col);
			CourseTime e = courseTime.get(0);
			e.delete();
		} catch (Exception e) { e.printStackTrace();}
	}
    
    public String getCourseName(int row, int col){
    	//DB may throw null pointer exception
		try {
			List<CourseTime> courseTime = CourseTime.where("time = ? AND day = ?",row, col);
			CourseTime e = courseTime.get(0);
			String coursenum = e.get("course_number").toString();
				
			if(!coursenum.equals(null))
				return coursenum;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
    }
    
    public String updateCourseName(String name,int row, int col){
    	
		try {
			List<CourseTime> courseTime = CourseTime.where("time = ? AND day = ?",row, col);
			CourseTime e = courseTime.get(0);
			String coursenum = e.get("course_number").toString();
				
			if(!coursenum.equals(null))
				return coursenum;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "";
    }
}
