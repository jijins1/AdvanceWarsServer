package advanceWar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import advanceWar.historique.Unit;
import advanceWar.player.Player;

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
	/**
	 * @return retourne une Map avec pour clé un Player et comme valeur le nombre de joueur vivant qu'il possede dans l'état
	 */
	public Map<Player, Integer> unitByPlayer(){
		
		Map<Player, Integer> map=new HashMap<>();
		
		for (Unit unit : units) {
			Player player=unit.getPlayer();
			map.putIfAbsent(player, 0);
			if (unit.getPv()>0) {
				System.out.println(unit);
				map.put(player,map.get(player)+1);
			}
		}
		return map;
	}
}
