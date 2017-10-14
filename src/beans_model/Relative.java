package beans_model;

public class Relative {
	private int relativeId,
				studentId;
	
	private String firstName,
				   middleName,
				   lastName,
				   type,
				   occupation;

	
	
	public Relative(int relativeId, int studentId, String firstName, String middleName, String lastName, String type,
			String occupation) {
		super();
		this.relativeId = relativeId;
		this.studentId = studentId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.type = type;
		this.occupation = occupation;
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



	@Override
	public String toString() {
		return "Relative [relativeId=" + relativeId + ", studentId=" + studentId + ", firstName=" + firstName
				+ ", middleName=" + middleName + ", lastName=" + lastName + ", type=" + type + ", occupation="
				+ occupation + "]";
	}

	
}
