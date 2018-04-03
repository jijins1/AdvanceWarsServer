package advanceWar.historique;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnitTypeFactory   {
	static final int ID_INF=1;
	static final int ATK_INF=5;
	static final int PV_INF=10;
	static final int PORT_INF=2;
	static final int MOUV_INF=2;
	static final String TYPE_INF="inf";

	@Autowired
	private  UnitTypeRepository unitTypeRepository;
	
	public  UnitType GetUnitInf() {
		System.out.println(unitTypeRepository);
		 return unitTypeRepository.findById(ID_INF).orElse( this.CreateUnitInf());
		
	}
	private  UnitType CreateUnitInf() {
		 
		 UnitType inf=new UnitType(ATK_INF, PV_INF,ID_INF,PORT_INF,MOUV_INF,TYPE_INF);
		 unitTypeRepository.save(inf);
		 return inf;
	}
	
	public static UnitTypeFactory getInstance() {
        if (null == instance) { // Premier appel
            instance = new UnitTypeFactory();
        }
        return instance;
    }
	
	
    private UnitTypeFactory() {
    	this.instance=this;

	}
   
    

	private static UnitTypeFactory instance;

}
