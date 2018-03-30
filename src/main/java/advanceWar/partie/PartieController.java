package advanceWar.partie;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import advanceWar.State;
import advanceWar.historique.Historique;
import advanceWar.historique.HistoriqueRepository;
import advanceWar.historique.Position;
import advanceWar.historique.Tour;
import advanceWar.historique.Unit;
import advanceWar.historique.UnitRepository;
import advanceWar.historique.UnitType;
import advanceWar.historique.UnitTypeFactory;
import advanceWar.player.Player;
import advanceWar.player.PlayerNotFoundException;
import advanceWar.player.PlayerRepository;

@Controller
@RequestMapping(path="/partie") // This means URL's start with /demo (after Application path)

public class PartieController {
	@Autowired
	private PartieRepository partieRepository;
	@Autowired
	private PlayerRepository playerRepository;
	@Autowired 
	private HistoriqueRepository historiqueRepository;
	@Autowired 
	private UnitRepository unitRepository;
	
	/**
	 * Genere une partie. Si p1 et p2 on deja une partie en cours lance une exception 
	 * @param p1 
	 * @param p2
	 * @return retourne la partie crée 
	 * @throws AlreadyInGame 
	 * @exception alreadyInGame
	 */
	@PostMapping(path="") // Map ONLY POST Requests
	public @ResponseBody ResponseEntity addNewPartie (@RequestParam String p1,@RequestParam String p2
			) throws AlreadyInGame {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		
		Player play1=playerRepository.findByName(p1).orElseThrow(() ->new PlayerNotFoundException(p1));
		Player play2=playerRepository.findByName(p2).orElseThrow(() ->new PlayerNotFoundException(p2));
		if (!play1.getNoRelation(play2)) {
			throw new AlreadyInGame();
		}
		UnitType type = UnitTypeFactory.getInstance().GetUnitInf();
		Position pos =new Position(3,3);
		Position pos2 =new Position(4,3);
		Unit unit=new Unit(type,pos,play1);
		Unit unit2=new Unit(type,pos2,play2);
		List<Unit> units = new ArrayList<>();
		units.add(unit);
		units.add(unit2);
		Historique historique = new Historique(units);

		Partie partie=new Partie(play1, play2,historique);
		

		partieRepository.save(partie);
        partie.add(linkTo(methodOn(PartieController.class).GetPartieWithId(partie.getIdPartie())).withSelfRel());
        
        return new ResponseEntity<Partie>(partie, HttpStatus.OK);
		
	}
	
	@PutMapping(path="/{partieId}/tour")
	public @ResponseBody ResponseEntity PutTurn(@RequestBody Tour tour,@PathVariable int partieId) {
		Partie partie=this.validatePartie(partieId);
		partie.nextTurn(tour);
		//TODO verifier si le joeurs qui publie est le bon 
		
		//TODO afficher en implementé
		return new ResponseEntity<String>("",HttpStatus.NOT_IMPLEMENTED);
		
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Partie> getAllUsers() {
		// This returns a JSON or XML with the users
		return partieRepository.findAll();
	}
	
	
	@GetMapping(value="/{partieId}")
	public @ResponseBody ResponseEntity GetPartieWithId(@PathVariable int partieId) {
		
		return new ResponseEntity<Partie>(this.partieRepository.findById(partieId).get(),HttpStatus.OK);
	}
	/**
	 * TODO 
	 * @param partieId est l'id de la partie 
	 */
	@GetMapping(value="/{partieId}/historique")
	public @ResponseBody ResponseEntity putTurn(@PathVariable int partieId) {
		this.validatePartie(partieId);
		return new ResponseEntity<Partie>(this.validatePartie(partieId),HttpStatus.OK);
	}
	
	/**
	 * Valide si l'id d'une partie est valide
	 * @param Id id de la partie 
	 * @return la partie  
	 */
	private Partie validatePartie(int Id) {
		return this.partieRepository.findById(Id).orElseThrow(
				() -> new PartieNotFoundException(Id));
	}
	
	@GetMapping(value="/{partieId}/state")
	public @ResponseBody ResponseEntity getState(@PathVariable int partieId) {
		this.validatePartie(partieId);
		return new ResponseEntity<State>(this.partieRepository.findById(partieId).get().getHistorique().GetCurrent(),HttpStatus.OK);
	}
	
	
	
	
	
	
	
}
