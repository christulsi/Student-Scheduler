package model;

import org.javalite.activejdbc.Model;

public class CourseTime extends Model{
	//identifies table by presence of fields specified 
	static{
		validatePresenceOf("course_number","time");
	}

}
