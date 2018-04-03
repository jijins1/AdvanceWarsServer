package advanceWar.player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.Fetch;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;

import advanceWar.partie.Partie;

@Entity // This tells Hibernate to make a table out of this class
public class Player extends ResourceSupport {
	@JsonProperty
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	private String token;
	
	@ManyToMany(fetch=FetchType.LAZY)
	private List<Partie> parties;
	
    private String name;

    
    
    public Player(@JsonProperty("name") String s) {
    	name=s;
    	parties=new ArrayList<>();
    }

    @JsonCreator
    public Player(@JsonProperty("name") String s,@JsonProperty("id") int id) {
    	
    }
	/**
	 * 
	 * @param p ajoute la partie p a la liste des partie en cours 
	 */
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
	public void suppPartie(Partie partie) {
		parties.remove(partie);
	}

	public void setToken(String token) {
		this.token=token;
	}

	public String getToken() {
		return token;
	}
	
	/**
	 * Notifie le joueur sur son dernier Device Connu
	 * @param texte le message à envoer au joueur
	 */
	public void notifyPlayer(String texte)  {
		if(this.token==null) {
			return;
		}
		Message message=Message.builder()
				.putData(this.name, texte)
				.setToken(token)
				.build();

		// registration token.
		String response;
		try {
			response = FirebaseMessaging.getInstance().sendAsync(message).get();
			// Response is a message ID string.
			System.out.println("Successfully sent message: " + response);

		
		} catch (InterruptedException | ExecutionException e) {
			System.out.println("ERROR notification");
			e.printStackTrace();
		}
		
	}
	

	
}

