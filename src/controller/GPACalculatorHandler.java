package controller;

import java.util.List;

import model.Course;
import model.CourseTime;

import org.javalite.activejdbc.Base;

public class GPACalculatorHandler {

	private static GPACalculatorHandler instance;
	
	public static synchronized GPACalculatorHandler getInstance()
	{
		if (instance == null)
			instance = new GPACalculatorHandler();
		return instance;
	}
	
	public GPACalculatorHandler(){}
	
	public void createCourseGrade(String number,String name,int credit,String m_o, String grade, int points){
		//DB may thrown table constraint exception
		try {
			Base.open("org.sqlite.JDBC", "jdbc:sqlite:student.db", "root", "root");
			Course.createIt("course_number", number, "course_name", name, "credit", credit,
					"m_o", m_o, "grade", grade,"points", points );
		} catch (Exception e) {	
			e.printStackTrace();
		}finally{
			Base.close();
        }
	}

    public boolean updateCourseName(String change, int row, int col){
    	
		try {
			Base.open("org.sqlite.JDBC", "jdbc:sqlite:student.db", "root", "root");
			List<Course> course = Course.findAll();
			
			switch(col){
			case 0: course.get(row).set("course_number",change).saveIt();
					break;
			case 1: course.get(row).set("course_name",change).saveIt();
					break;
			case 2: course.get(row).set("m_o",change).saveIt();
					break;
			case 3: course.get(row).set("credit",change).saveIt();
					break;
			case 4: course.get(row).set("grade",change).saveIt();
					break;
			case 5: course.get(row).set("points",change).saveIt();
					break;
			}
			return false;
			
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
    
    public Object getCourseInfo(int row, int col){
    	//DB may throw null pointer exception
		try {
			Base.open("org.sqlite.JDBC", "jdbc:sqlite:student.db", "root", "root");
			List<Course> course = Course.findAll();
			
			switch(col){
			case 0: return course.get(row).get("course_number");
			case 1: return course.get(row).get("course_name");
			case 2: return course.get(row).get("m_o");
			case 3: return course.get(row).get("credit");
			case 4: return course.get(row).get("grade");
			case 5: return course.get(row).get("points");
			}
		} catch (Exception e) {	
			e.printStackTrace(); 
		}finally{
			Base.close();
        }
		
		return null;
    }
}
