package advanceWar.historique;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import advanceWar.State;
import advanceWar.partie.Partie;
@Entity
public class Historique implements Serializable {
	@OneToMany
	private List<Tour> tour;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Unit> units;
	@Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@JsonIgnore
	@OneToOne
    private Partie partie;
	@JsonIgnore
	public Partie getPartie() {
		return partie;
	}
	
	public void setPartie(Partie partie) {
		this.partie=partie;
	}
	
	public Historique() {
		super();
		tour=new ArrayList<Tour>() ;
		this.units=new ArrayList<Unit>();
	}
	
	public Historique(List<Unit> units ) {
		super();
		tour=new ArrayList<Tour>() ;
		this.units=units;
		
	}

	public List<Tour> getTour() {
		return tour;
	}

	
	private void addTourToHistory(Tour ntour) {
		tour.add(ntour);
	}
	public void actualise(Tour ntour) {
		addTourToHistory(ntour);
		ntour.apply(units);
		
	}
	public State GetCurrent()
	{
		return new State(units);
	}	
	
	
	
}
