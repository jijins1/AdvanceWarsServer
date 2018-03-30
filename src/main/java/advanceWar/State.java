package advanceWar;

import java.util.List;

import advanceWar.historique.Unit;

public class State {
	private List<Unit> units;
	public State(List<Unit> units) {
		this.units=units;
	}
	public List<Unit> getUnits() {
		return units;
	}
	public void setUnits(List<Unit> units) {
		this.units = units;
	}
	
}
