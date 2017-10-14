package beans_model;

import java.time.Year;

public class Involvement {

	private int id;
	private int idNum;
	private String iName;
	private String position;
	private Year acadYear;
	
	public Involvement(int id, int num, String name, String pos, Year year){
		this.id = id;
		idNum = num;
		iName = name;
		position = pos;
		acadYear = year;
	}
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdNum() {
		return idNum;
	}
	public void setIdNum(int idNum) {
		this.idNum = idNum;
	}
	public String getiName() {
		return iName;
	}
	public void setiName(String iName) {
		this.iName = iName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Year getAcadYear() {
		return acadYear;
	}
	public void setAcadYear(Year acadYear) {
		this.acadYear = acadYear;
	}
	
	@Override
	public String toString() {
		return "Involvement [id=" + id + ", idNum=" + idNum + ", iName=" + iName + ", position=" + position
				+ ", acadYear=" + acadYear + "]";
	}
	
	
}
