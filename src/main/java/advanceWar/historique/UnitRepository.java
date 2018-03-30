package advanceWar.historique;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


public interface UnitRepository extends CrudRepository<Unit, Long>  {
	Optional<Unit> findById(int Id);
}
