package advanceWar.partie;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import advanceWar.historique.Historique;
import advanceWar.historique.Tour;
import advanceWar.player.Player;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

@Entity
public  class  Partie extends ResourceSupport {
	@JsonProperty
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id ;
	@JsonIgnore
	public Integer getIdPartie() {
		return id;
	}
	@ManyToMany(mappedBy="parties")
	private List<Player> players;
	@OneToOne
	private Player current;
	
	
	@JsonIgnore
    @OneToOne(cascade=CascadeType.ALL)
     private Historique historique;
    
    
    
	/**
	 * 
	 * @param players the player of the partie 
	 * this metode will setup reference in the player
	 * 
	 */
	protected void setPlayer(List<Player> players) {
		this.players=players;
		for (Player player : players) {
			player.addPartie(this);
		}
	
		
	}
	/**
	 * add 1 turn to the partie
	 */
	
	
	protected void setCurrent(Player player) {
		this.current=player;
	}

	public  Player getCurrent() {
		return this.current;
	};
	
	
	
	public List<Player> getPlayers() {
		return players;
		//return null;
	}
	
	
	
	public Partie(Player p1, Player p2,Historique historique) {
	
		List<Player> players =new ArrayList<>();
		players.add(p1);
		players.add(p2);
		this.historique=historique;
		this.historique.setPartie(this);
		setPlayer(players);
		setCurrent(p1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Change le joueur courant et applique les actions du tour en cours.
	 * @param tour a appliquer 
	 */
	public void nextTurn(Tour tour) {
		// TODO Auto-generated method stub
		if (getPlayers().get(0)==getCurrent()) {
			setCurrent(getPlayers().get(1));
		}else {
			setCurrent(getPlayers().get(0));
			
		}
		historique.actualise(tour);

		//TODO lancer une notification
	}
	Partie(){}
	@JsonIgnore
	public Historique getHistorique() {
		return this.historique;
	}
	
}
