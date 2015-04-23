package studentScheduler;

import model.Course;
import model.DBconnect;
import view.UI;

public class StudentScheduler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UI.getInstance();
		new DBconnect(); 
		Course e = new Course();
		e.set("course_number", "CSI1101");
		e.saveIt();
	}

}
