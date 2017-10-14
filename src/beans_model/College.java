package beans_model;

public class College {
	private int collegeId;
	private String cName;
	
	
	public College(int collegeId, String cName) {
		super();
		this.collegeId = collegeId;
		this.cName = cName;
	}


	public int getCollegeId() {
		return collegeId;
	}


	public void setCollegeId(int collegeId) {
		this.collegeId = collegeId;
	}


	public String getcName() {
		return cName;
	}


	public void setcName(String cName) {
		this.cName = cName;
	}


	@Override
	public String toString() {
		return "College [collegeId=" + collegeId + ", cName=" + cName + "]";
	}
	
}
