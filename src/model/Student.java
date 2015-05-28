package model;

import org.javalite.activejdbc.Model;

public class Student extends Model{
	//check attributes for right table
	static{
		validatePresenceOf("overall_gpa","major_gpa","other_gpa");
	}
}
