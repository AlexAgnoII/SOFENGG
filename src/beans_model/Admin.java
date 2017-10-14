package beans_model;

public class Admin {
	private int adminId;
	private String hashedPass;
	
	
	public Admin(int adminId, String hashedPass) {
		// TODO hashing
		super();
		this.adminId = adminId;
		this.hashedPass = hashedPass;
	}
	
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getHashedPass() {
		// TODO hashing
		return hashedPass;
	}
	public void setHashedPass(String hashedPass) {
		// TODO hashing
		this.hashedPass = hashedPass;
	}
	
	

}
