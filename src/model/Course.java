package model;

import org.javalite.activejdbc.Model;

public class Course extends Model {
	
	static{
		validatePresenceOf("course_number","course_name");
	}
}