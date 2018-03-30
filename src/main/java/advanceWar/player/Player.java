package advanceWar.player;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import advanceWar.partie.Partie;

@Entity // This tells Hibernate to make a table out of this class
public class Player extends ResourceSupport {
	@JsonProperty
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	
	
	@ManyToMany
	private List<Partie> parties;
	
    private String name;

    
    
    public Player(@JsonProperty("name") String s) {
    	name=s;
    	parties=new ArrayList<>();
    }

    @JsonCreator
    public Player(@JsonProperty("name") String s,@JsonProperty("id") int id) {
    	
    }
	
    public void addPartie(Partie p) {
    	parties.add(p);
    }
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	@JsonIgnore
	  public List<Partie> getParties() {
	 
		return parties;
	}
	@JsonIgnore
	public int getIdPartie() {
		return id;
	}
	/**
	 * retourne vrai si player n'as pas de partie en cours avec this
	 * @param player joueur à verifier 
	 * @return bool vrai si pas de relation entre les players
	 */
	public boolean getNoRelation(Player player) {
		boolean bool=false;
		for (Partie partie : parties) {
			if(partie.getPlayers().get(0).equals(player) || partie.getPlayers().get(1).equals(player)) {
				bool=true;
			}
		}
		return !bool;
	}
	Player(){}
	/**
	 * @return true si l'objet est un player avec le meme id
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof Player) {
			
			Player pl=(Player) obj;
			return pl.id==this.id;
		}
		return false;
		
	}

	
}

