package studentScheduler;

import view.UI;
import controller.StudentHandler;

public class StudentScheduler {

	public static void main(String[] args) {
		
		UI.getInstance();
		new Notificator().start();
		
	}
	
}
