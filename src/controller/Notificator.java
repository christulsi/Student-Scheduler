package controller;

import java.time.LocalTime;

public class Notificator extends Thread{
	
	public Notificator(){
		
		
		LocalTime currentTime = LocalTime.now();
		while(true){
		System.out.println(currentTime);
		}
	}
}
