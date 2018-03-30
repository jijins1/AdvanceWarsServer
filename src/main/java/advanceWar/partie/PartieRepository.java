package advanceWar.partie;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface PartieRepository extends CrudRepository<Partie, Long> {
    Optional<Partie> findById(int Id);
}
