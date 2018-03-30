package advanceWar.testObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import advanceWar.State;
import advanceWar.action.Action;
import advanceWar.action.ActionAttack;
import advanceWar.action.ActionMove;
import advanceWar.action.ActionRepository;
import advanceWar.action.ActionRepositoryAttack;
import advanceWar.action.ActionRepositoryMove;
import advanceWar.historique.Position;
import advanceWar.historique.Tour;
import advanceWar.historique.Unit;
import advanceWar.historique.UnitFactory;
import advanceWar.historique.UnitType;
import advanceWar.historique.UnitTypeFactory;
import advanceWar.partie.Partie;
import advanceWar.partie.PartieRepository;
import advanceWar.player.Player;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/test") // This
public class TestController {
	@Autowired
	ActionRepositoryAttack attack;
	@Autowired
	ActionRepositoryMove move;
	@Autowired
	PartieRepository partie;
	
	
	
	
	@GetMapping(path="/unit")
	public @ResponseBody Unit testUnit() {
		// This returns a JSON or XML with the users
		Position pos =new Position(1,1);
		UnitType type = UnitTypeFactory.getInstance().GetUnitInf();
		Unit unit=new Unit(type,pos,null);
		return unit;
	}
	
	
	
	@GetMapping(path="/Action")
	public @ResponseBody Action testActionMove() {
		// This returns a JSON or XML with the users
		Position pos =new Position(1,1);
		Position pos2 =new Position(1,2);
		UnitType type = UnitTypeFactory.getInstance().GetUnitInf();
		Unit unit=new Unit(type,pos,null);
		Unit unit2=new Unit(type,pos,null);
		ActionAttack action=new ActionAttack(unit, unit2, 5);
		attack.save(action);
		return action;
	}
	
	@GetMapping(path="/State")
	public @ResponseBody State testState()
	{
		Partie p = partie.findById(1).orElse(null);
		return p.getHistorique().GetCurrent();
	}
	
	@GetMapping(path="/Tour")
	public @ResponseBody Tour testTurn()
	{
		Player p=new  Player("Ruokki1");
		Tour t =new Tour(p, 1);
		Unit u= new Unit(UnitTypeFactory.getInstance().GetUnitInf(), new Position(1, 1), p);
		
		Action act=new ActionAttack(u,u,10);
		t.addAction(act);
		return t;
	}
	@PutMapping(path="/Tour")
	public @ResponseBody String testTurnPut(@RequestBody Tour turn) {
		System.out.println(turn);
		turn.apply(null);//Pas besoin de list avec une action Move
		System.out.println(turn);
		return "Test";
	}
	
}
