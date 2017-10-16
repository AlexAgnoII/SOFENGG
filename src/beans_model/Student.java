package beans_model;

import java.time.Year;
import java.util.Date;

public class Student {
	
	private int studentId, zip;
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
				   college,
				   hashedPass,
				   civil,
				   citizen,
				   gender,
				   city,
				   province,
				   country;
	

	public Student(int studentId, 
			       Date birthday, 
			       Year yEnrolled, 
				   String firstName, 
				   String middleName, 
				   String lastName,
				   String telNo, 
				   String celNo, 
				   String email, 
				   String address, 
				   //String college,
				   String course, 
				   String hashedPass, 
				   String civil,
				   String citizen, 
				   String gender) {
		super();
		this.studentId = studentId;
		this.birthday = birthday;
		this.yEnrolled = yEnrolled;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.telNo = telNo;
		this.celNo = celNo;
		this.email = email;
		this.address = address;
		//this.college = college;
		this.course = course;
		this.hashedPass = hashedPass;
		this.civil = civil;
		this.citizen = citizen;
		this.gender = gender;
	}


	public Student(int studentId, 
			String lastname,
			String firstname,
			String middlename,
			String username,
			String password,
			String college,
			String course) {
		this.studentId = studentId;
		this.celNo = null;
		this.telNo = null;
		this.firstName = firstname;
		this.middleName = middlename;
		this.lastName = lastname;
		this.email = username;
		this.address = null;
		this.course = course;
		this.yEnrolled = null;
		this.birthday = null;
		this.college = college;
		
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


	public String getCivil() {
		return civil;
	}


	public void setCivil(String civil) {
		this.civil = civil;
	}


	public String getCitizen() {
		return citizen;
	}


	public void setCitizen(String citizen) {
		this.citizen = citizen;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getCollege() {
		return college;
	}


	public void setCollege(String college) {
		this.college = college;
	}


	public int getZip() {
		return zip;
	}


	public void setZip(int zip) {
		this.zip = zip;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}
	
	

}
