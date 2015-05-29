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
	
	public void create(String number,String name,int credit,String m_o, String grade, int points){
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
	
	public Object read(int row, int col){
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

    public boolean update(String change, int row, int col){
    	
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
					if(course.get(row).get("grade").equals("A"))
						course.get(row).set("points",16).saveIt();
					else if(course.get(row).get("grade").equals("B"))
						course.get(row).set("points",12).saveIt();
					else if(course.get(row).get("grade").equals("C"))
						course.get(row).set("points",8).saveIt();
					else if(course.get(row).get("grade").equals("D"))
						course.get(row).set("points",4).saveIt();
					else if(course.get(row).get("grade").equals("F"))
						course.get(row).set("points",0).saveIt();
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
    
    public void delete(int row, String col){
    	
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
    
    public float calculateGPA(){
    	
    	float sum = 0; 
    	try {
    		Base.open("org.sqlite.JDBC", "jdbc:sqlite:student.db", "root", "root");
    		List<Course> course = Course.findAll();
			
    		for (Course course2 : course) {
    			int point = (int)course2.get("points");
    			sum += point;
			}		
    		
    		sum /= (course.size()*4);
    		
		} catch (Exception e) { 
			e.printStackTrace();
		}finally{
			Base.close();
        }
    	
    	return sum;
	}
    
    public float calculateMajorGPA(){
    	
    	float sum = 0; 
    	try {
    		Base.open("org.sqlite.JDBC", "jdbc:sqlite:student.db", "root", "root");
    		List<Course> course = Course.find("m_o= ?", "M");
			
    		for (Course course2 : course) {
    			int point = (int)course2.get("points");
    			sum += point;
			}		
    		
    		sum /= (course.size()*4);
    		
		} catch (Exception e) { 
			e.printStackTrace();
		}finally{
			Base.close();
        }
    	
    	return sum;
	}
    
    public float calculateOtherGPA(){
    	
    	float sum = 0; 
    	try {
    		Base.open("org.sqlite.JDBC", "jdbc:sqlite:student.db", "root", "root");
    		List<Course> course = Course.find("m_o", "O");
			
    		for (Course course2 : course) {
    			int point = (int)course2.get("points");
    			sum += point;
			}		
    		
    		sum /= (course.size()*4);
    		
		} catch (Exception e) { 
			e.printStackTrace();
		}finally{
			Base.close();
        }
    	
    	return sum;
	}
    
    
}
