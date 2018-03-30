package advanceWar.historique;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import advanceWar.player.Player;

public interface UnitTypeRepository extends CrudRepository<UnitType, Long>{
	Optional<UnitType> findById(int Id);

}
