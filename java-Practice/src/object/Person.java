package object;

import java.sql.Timestamp;

public class Person {

	private String name;
	private Timestamp attendTime;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getAttendTime() {
		return attendTime;
	}
	public void setAttendTime(Timestamp attendTime) {
		this.attendTime = attendTime;
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", attendTime=" + attendTime + "]";
	}
}
