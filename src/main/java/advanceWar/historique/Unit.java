package advanceWar.historique;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import advanceWar.player.Player;
 @Entity
public class Unit implements Persistable<Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@JsonSerialize
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
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
	@Override
	@JsonSerialize
	public Integer getId() {
		
		return id;
	}
	@Override
	public String toString() {
		return " type : "+type.getType()+" Player : "+player.getName()+ " id="+id+" Pos="+pos+" PV="+ this.pv ;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	public void reducePv(int degat) {
		int result=this.pv-degat;
		if(result<0) {
			result=0;
		}
		
		this.pv=result;
		try {
			UnitFactory.getInstance().updateUnit(this);

		} catch (Exception e) {
			System.out.println("ERROR Unit");
		}
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (id == null || obj == null || getClass() != obj.getClass())
            return false;
        Unit that = (Unit) obj;
        return id.equals(that.id);
    }
    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }

	
	
	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return null==UnitFactory.getInstance().getUnitId(id);
	}
}
