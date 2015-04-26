package studentScheduler;

import org.javalite.activejdbc.Base;

import model.Course;
import model.DBconnect;
import view.UI;

public class StudentScheduler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UI.getInstance();
		//new DBconnect(); 
		
		Base.open("com.sqlite.jdbc.Driver", "jdbc:sqlite:student.db", "root", "root");
		Course e = new Course();
		e.set("course_number", "CSI1101");
		e.saveIt();
	}

}
