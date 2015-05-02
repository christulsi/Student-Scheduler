package studentScheduler;

import org.javalite.activejdbc.Base;

import view.UI;

public class StudentScheduler {

	public static void main(String[] args) {
		
		//Connect to Database
		Base.open("org.sqlite.JDBC", "jdbc:sqlite:student.db", "root", "root");
		
		UI.getInstance(); 
	}

}
