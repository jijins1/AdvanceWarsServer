package advanceWar.historique;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnitFactory {
	@Autowired
	private  UnitRepository unitRepository ;
	public static UnitFactory getInstance() {
		 if (null == instance) { // Premier appel
	            instance = new UnitFactory();
	        }
	     return instance;
	}
	
	private UnitFactory() {
    	this.instance=this;

	}
	private static UnitFactory instance;
	public void updateUnit(Unit unit) {
		unitRepository.save(unit);
	}
	
	public Unit getUnitId(int id) {
		return unitRepository.findById(id).get();
	}

}
