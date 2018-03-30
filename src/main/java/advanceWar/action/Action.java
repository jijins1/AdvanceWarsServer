package advanceWar.action;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import advanceWar.historique.Unit;

@Entity
@Inheritance
public abstract class Action {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id ;
	@JsonCreator
	public Action(@JsonProperty("unit") Unit unit) {
		super();
		this.unit = unit;
	}
	public Action() {
		super();
	}

	@JsonProperty
	@OneToOne(cascade=CascadeType.ALL)
	private Unit unit;

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	public Integer getId() {
		return id;
	}
	
	public abstract void apply(List<Unit> units); 

}
