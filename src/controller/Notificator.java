package controller;

import java.time.LocalTime;

public class Notificator {
	
	public Notificator(){
		
		LocalTime currentTime = LocalTime.now();
		System.out.println(currentTime);
	}
}
