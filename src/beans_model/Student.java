package beans_model;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;

//TODO Need to fix student class (Especially how we use it) for consistent codes on other classes (Like userservice)
public class Student {
	private int dbID; //the ID of user in the DB
	private int studentId, zip, age, nInvolvements;
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

	//This is used for updating date (Birthday).
	private java.sql.Date tempDate;
	

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
		this.course = course;
		this.hashedPass = hashedPass;
		this.civil = civil;
		this.citizen = citizen;
		this.gender = gender;
	}

	//This one is used for signup.
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
	
	
	public Student() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", celNo=" + celNo + ", telNo=" + telNo + ", birthday=" + birthday
				+ ", yEnrolled=" + yEnrolled + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", email=" + email + ", address=" + address + ", course=" + course + "]";
	}
	
	//Calculates the age of the user.
	public void calculateAge(Date date) {

		if (date != null) {	
			int currYear = Calendar.getInstance().get(Calendar.YEAR);
			int currMonth = Calendar.getInstance().get(Calendar.MONTH); //index 0 - 11
			int currDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
			String temp[] = date.toString().split("-"); //first index must be the year, second is month.
			int year = Integer.parseInt(temp[0]); //convert string to int.
			int month = Integer.parseInt(temp[1]) - 1; //convert string to int
			int day = Integer.parseInt(temp[2]); 
			int age = java.lang.Math.abs(currYear - year);
			
			System.out.println("Year: " + year);
			System.out.println("Curryear: " + currYear);
			System.out.println("day: " + day);
			System.out.println("Month: " + month);
			System.out.println("currMonth: " + currMonth);
			System.out.println("Age: " + age); //get age (ABS value)
			
			if(currMonth < month) {
				age -= 1;
			}
			
			else if(currDay < day) {
				age -=1;
			}
			
			if(age < 0) {
				age = 0;
			}
			
			System.out.println("Final age: " + age);
			this.age = age;
		}
		else this.age = 0;
		
	}
	
	public void setSQLDate(String date) {
		this.tempDate = java.sql.Date.valueOf(date);
	}
	
	public java.sql.Date getTempDate() {
		return tempDate;
	}
	
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getCelNo() {
		
		if (this.celNo == null)
			return "";
		
		return celNo;
	}
	public void setCelNo(String celNo) {
		this.celNo = celNo;
	}
	public String getTelNo() {
		
		if(this.telNo == null)
			return "";
		
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
		if(this.address == null)
			return "";
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
		if(this.civil == null)
			return "";
		return civil;
	}


	public void setCivil(String civil) {
		this.civil = civil;
	}


	public String getCitizen() {
		if(this.citizen == null)
			return "";
		return citizen;
	}


	public void setCitizen(String citizen) {
		this.citizen = citizen;
	}


	public String getGender() {
		if(this.gender == null)
			return "";
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
		if(this.city == null)
			return "";
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getProvince() {
		if(this.province == null)
			return "";
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public String getCountry() {
		if(this.country == null)
			return "";
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}

	public int getDbID() {
		return dbID;
	}

	public void setDbID(int dbID) {
		this.dbID = dbID;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setTempDate(java.sql.Date tempDate) {
		this.tempDate = tempDate;
	}

	public int getNInvolvements() {
		return nInvolvements;
	}

	public void setNInvolvements(int nInvolvements) {
		this.nInvolvements = nInvolvements;
	}
	
}
