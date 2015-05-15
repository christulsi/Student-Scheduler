package studentScheduler;

import org.javalite.activejdbc.Base;

import view.UI;

public class StudentScheduler {

	public static void main(String[] args) {
		
		try {
			//Connect to Database
			Base.open("org.sqlite.JDBC", "jdbc:sqlite:student.db", "root", "root");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UI.getInstance(); 
	
	}
	
}
