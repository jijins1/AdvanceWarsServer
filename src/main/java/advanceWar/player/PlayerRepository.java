package advanceWar.player;


import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import advanceWar.player.Player;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface PlayerRepository extends CrudRepository<Player, Long> {
    Optional<Player> findByName(String name);
    Optional<Player> findById(int Id);

    


}
