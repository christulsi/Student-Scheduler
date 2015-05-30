package studentScheduler;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.swing.JOptionPane;

import controller.TimeTableHandler;

public class Notificator extends Thread{
	
	public Notificator(){
		
		//Get current date and time
		LocalDateTime currentDateTime = LocalDateTime.now();
		//Get current day and convert to lowercase
		String input= currentDateTime.getDayOfWeek().toString().toLowerCase();
		//set first char to Uppercase  
		List<LocalTime> time = TimeTableHandler.getInstance().getTime(input.substring(0, 1).toUpperCase() + input.substring(1));
		
		
		while(true){
			currentDateTime = LocalDateTime.now();
			
			for (LocalTime localTime : time) {
				
				if(localTime.getHour() == currentDateTime.getHour() &&
					localTime.getMinute() == currentDateTime.getMinute()){
					
					int result = JOptionPane.showConfirmDialog(null,"You Have class now");
				}
			}
		}
	}
	
	
}
