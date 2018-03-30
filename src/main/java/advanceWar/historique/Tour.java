package advanceWar.historique;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mysql.fabric.xmlrpc.base.Array;

import advanceWar.action.Action;
import advanceWar.player.Player;
@Entity
public class   Tour {
	@OneToOne
	private Player player;
	private int n;
	@JsonProperty
	@OneToMany
	private List<Action> actions;
	
	@JsonProperty
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id ;
	
	
	public Tour(Player player, int n) {
		super();
		this.player = player;
		this.n = n;
		this.actions=new ArrayList<Action>();
	}
	public Tour() {
		super();
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public void apply(List<Unit> units) {
		for (Action act : actions) {
			
			act.apply(units);
		}
	}
	/**
	 * ajoute une actions au tour
	 * @param act action a ajouter 
	 */
	public void addAction(Action act) {
		actions.add(act);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return player.toString()+n+actions.toString();
	}
	
}
