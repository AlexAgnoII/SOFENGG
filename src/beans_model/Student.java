package beans_model;

import java.time.Year;
import java.util.Date;

public class Student {
	
	private int studentId,
				celNo,
				telNo;
	private Date birthday;
	private Year yEnrolled;
	private String firstName,
				   middleName,
				   lastName,
				   email,
				   address,
				   course,
				   hashedPass;
	
	public Student(int studentId, int celNo, int telNo, Date birthday, Year yEnrolled, String firstName,
			String middleName, String lastName, String email, String address, String course, String hashedPass) {
		super();
		this.studentId = studentId;
		this.celNo = celNo;
		this.telNo = telNo;
		this.birthday = birthday;
		this.yEnrolled = yEnrolled;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.course = course;
		
		// TODO hashing
		this.hashedPass = hashedPass;
	}
	
	
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", celNo=" + celNo + ", telNo=" + telNo + ", birthday=" + birthday
				+ ", yEnrolled=" + yEnrolled + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", email=" + email + ", address=" + address + ", course=" + course + "]";
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getCelNo() {
		return celNo;
	}
	public void setCelNo(int celNo) {
		this.celNo = celNo;
	}
	public int getTelNo() {
		return telNo;
	}
	public void setTelNo(int telNo) {
		this.telNo = telNo;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Year getyEnrolled() {
		return yEnrolled;
	}
	public void setyEnrolled(Year yEnrolled) {
		this.yEnrolled = yEnrolled;
	}
	public String getFirstName() {
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
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getHashedPass() {
		// TODO unhashed pass
		return hashedPass;
	}
	public void setHashedPass(String hashedPass) {
		// TODO hashing
		this.hashedPass = hashedPass;
	}

}
