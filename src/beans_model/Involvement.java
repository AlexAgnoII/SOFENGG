package beans_model;

import java.time.Year;
import java.util.ArrayList;

public class Involvement {

	private int id;
	private int idNum;
	private String iName;
	private String position;
	private int acadYear;
	private int internal;
	private ArrayList<String> handler = new ArrayList<>();
	
	public Involvement(int id, int num, String name, String pos, int year, int internal){
		this.id = id;
		idNum = num;
		iName = name;
		position = pos;
		acadYear = year;
		this.internal = internal;
	}
	
	public Involvement(int num, String name, String pos, int year, int internal) {
		idNum = num;
		iName = name;
		position = pos;
		acadYear = year;
		this.internal = internal;
	}
	

	public Involvement(int int1, int int2, String string, String string2, Year of) {
		// TODO Auto-generated constructor stub
	}

	public Involvement() {
		// TODO Auto-generated constructor stub
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

	public int getInternal() {
		return internal;
	}
	public void setInternal(int internal) {
		this.internal = internal;
	}
	
	@Override
	public String toString() {
		return "Involvement [id=" + id + ", idNum=" + idNum + ", iName=" + iName + ", position=" + position
				+ ", acadYear=" + acadYear + "internal=" + internal + "]";
	}


	public ArrayList<String> getHandler() {
		return handler;
	}


	public void setHandler(ArrayList<String> handler) {
		this.handler = handler;
	}

	public int getAcadYear() {
		return acadYear;
	}

	public void setAcadYear(int acadYear) {
		this.acadYear = acadYear;
	}
	
	
}
