package model;

import org.javalite.activejdbc.Model;

public class CourseTime extends Model{
	
	static{
		validatePresenceOf("course_number","time");
	}

}
