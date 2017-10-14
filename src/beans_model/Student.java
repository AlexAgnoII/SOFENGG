package beans_model;

import java.time.Year;
import java.util.Date;

public class Student {
	
	private int studentId;
	private Date birthday;
	private Year yEnrolled;
	private String firstName,
				   middleName,
				   lastName,
				   telNo,
				   celNo,
				   email,
				   address,
				   course,
				   hashedPass;
	
	public Student(int studentId, 
			String lastname,
			String firstname,
			String middlename,
			String mobNum,
			String telNum,
			String username,
			String password
			       ) {
		this.studentId = studentId;
		this.celNo = mobNum;
		this.telNo = telNum;
		this.firstName = firstname;
		this.middleName = middlename;
		this.lastName = lastname;
		this.email = username;
		this.address = null;
		this.course = null;
		this.yEnrolled = null;
		this.birthday = null;
		
		// TODO hashing
		this.hashedPass = password;
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
	public String getCelNo() {
		return celNo;
	}
	public void setCelNo(String celNo) {
		this.celNo = celNo;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
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
