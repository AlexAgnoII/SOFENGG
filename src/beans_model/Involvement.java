package beans_model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "involvement")
public class Involvement {

	@Id
	@Column(name="involvementId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private int idNum;
	@Column
	private String iName;
	@Column
	private String position;
	@Column
	private Year acadYear;
}
