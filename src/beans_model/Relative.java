package beans_model;

import java.sql.Date;

public class Relative {
	private int relativeId,
				studentId;
	
	private String name,
				   type,
				   occupation;
	
	private Date birthday;

	private java.sql.Date tempDate;
	
	
	public Relative(int relativeId, int studentId, String name, String type,
			String occupation, Date birthday) {
		super();
		this.relativeId = relativeId;
		this.studentId = studentId;
		this.name = name;
		this.type = type;
		this.occupation = occupation;
		this.birthday = birthday;
	}



	public int getRelativeId() {
		return relativeId;
	}



	public void setRelativeId(int relativeId) {
		this.relativeId = relativeId;
	}



	public int getStudentId() {
		return studentId;
	}



	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}



	/*public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getMiddleName() {
		return middleName;
	}



	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}*/



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getOccupation() {
		return occupation;
	}



	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	
	public Date getBirthday() {
		return birthday;
	}
	

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Relative() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "Relative [relativeId=" + relativeId + ", studentId=" + studentId + ", name=" + name
				+ ",  type=" + type + ", occupation="
				+ occupation + ", birthday=" + birthday + "]";
	}
	
	public void setSQLDate(String date) {
		this.tempDate = java.sql.Date.valueOf(date);
	}
	
	public java.sql.Date getTempDate() {
		return tempDate;
	}
	
	public void setTempDate(java.sql.Date tempDate) {
		this.tempDate = tempDate;
	}

	
}
