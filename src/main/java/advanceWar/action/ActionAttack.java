package advanceWar.action;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import advanceWar.historique.Unit;
import advanceWar.historique.UnitFactory;

@Entity
public class ActionAttack extends Action{
	@OneToOne(cascade=CascadeType.ALL)
	private Unit ennemies;
	private Integer degat;
	/**
	 * Constructeur pour le bean
	 */
	public ActionAttack() {
		super();
	}
	/**
	 * Constructeur de l'action d'attaque 
	 * @param unit Lanceur de l'attaque 
	 * @param ennemies Unité endommagée 
	 * @param degat Valeur des degats 
	 */
	public ActionAttack(Unit unit ,Unit ennemies, Integer degat) {
		super(unit);
		this.ennemies = ennemies;
		this.degat = degat;
	}
	
	@JsonCreator
	public ActionAttack(@JsonProperty("unitId") int unitId,@JsonProperty("unitIdEnnemie")int unitIdEnnemie,@JsonProperty("degat") Integer degat ) {
		UnitFactory uf = UnitFactory.getInstance();
		this.setUnit(uf.getUnitId(unitId));
		this.setEnnemies(uf.getUnitId(unitIdEnnemie));
		this.degat=degat;
	}
	public Unit getEnnemies() {
		return ennemies;
	}
	public void setEnnemies(Unit ennemies) {
		this.ennemies = ennemies;
	}
	public Integer getDegat() {
		return degat;
	}
	public void setDegat(Integer degat) {
		this.degat = degat;
	}
	
	@Override
	public void apply(List<Unit> units) {
		// TODO Auto-generated method stub
		
	}
}
