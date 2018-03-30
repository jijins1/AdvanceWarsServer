package advanceWar.action;

import java.util.List;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import advanceWar.historique.Position;
import advanceWar.historique.Unit;
import advanceWar.historique.UnitFactory;

@Entity
public class ActionMove extends Action {
	@JsonProperty
	private Position dest;

	public ActionMove() {
		super(null);
	}
	
	public ActionMove( Unit unit,Position dest) {
		super(unit);
		this.dest = dest;
	}

	@JsonCreator
	public ActionMove(@JsonProperty("unitId") int unit,@JsonProperty("dest")Position dest) {
		super(UnitFactory.getInstance().getUnitId(unit));
		this.dest = dest;
	}
	
	public Position getDest() {
		return dest;
	}

	public void setDest(Position dest) {
		this.dest = dest;
	}
	/**
	 * 
	 */
	@Override
	public void apply(List<Unit> units) {
		// TODO Auto-generated method stub
		this.getUnit().setPos(dest);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Action Move "+this.getUnit().toString()+" dest "+this.dest;
	}
	
	
}
