package advanceWar.historique;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PostUpdate;
import javax.persistence.Version;

import org.hibernate.annotations.Tuplizer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import advanceWar.player.Player;
 @Entity
public class Unit {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@JsonSerialize
	@ManyToOne(cascade=CascadeType.ALL)
	private Player player;
	
	@JsonSerialize
	@OneToOne
	private UnitType type;
	@JsonSerialize
	private Position pos;
	
	private int atk;
	private int port;
	public int getPv() {
		return pv;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}
	
	private int pv;
	private int mouv;
	
	@JsonCreator
	public Unit(@JsonProperty("type") UnitType type, @JsonProperty("pos") Position pos,@JsonProperty("player") Player player) {
		super();
		this.type = type;
		this.pos = pos;
		this.player=player;
		this.setAtk(type.getAtk());
		this.setMouv(type.getMouv());
		this.setPort(type.getPort());
		this.pv=type.getPv();
	}
	
	
	public Unit() {
		
	}
	
	public void damage(int atk) {
		this.pv=pv-atk;
		UnitFactory.getInstance().updateUnit(this);
		
	}
	public void mouv(int mouv) {
		 
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getMouv() {
		return mouv;
	}

	public void setMouv(int mouv) {
		this.mouv = mouv;
	}
	public void setPos(Position pos) {
		this.pos = pos;
	}
	@JsonSerialize
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return " type : "+type.getType()+" Player : "+player.getName()+ " id="+id+" Pos="+pos ;
	}
	
}
