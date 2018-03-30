package advanceWar.historique;


import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
public class UnitType{
	private int atk=5;
	private int pv=10;
	private int port=1;
	private int mouv=5;


	@Id
	private int id=1;
	private  String type="inf";
	 
	
	public UnitType() {
		
	}
	@JsonCreator
	public UnitType(@JsonProperty("atk") int atk,
			@JsonProperty("pv") int pv,
			@JsonProperty("id") int id,
			@JsonProperty("port") int port,
			@JsonProperty("mouv") int mouv,
			@JsonProperty("type") String type) {
		
		this.atk = atk;
		this.pv = pv;
		this.type=type;
		this.id=id;
		this.setPort(port);
		this.setMouv(mouv);
	}
	
	
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public int getPv() {
		return pv;
	}
	public void setPv(int pv) {
		this.pv = pv;
	}
	public String getType() {
		return type ;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getMouv() {
		return mouv;
	}
	public void setMouv(int mouv) {
		this.mouv = mouv;
	}
	
	
	
}
