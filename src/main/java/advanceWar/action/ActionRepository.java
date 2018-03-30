package advanceWar.action;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@NoRepositoryBean
public interface ActionRepository<T extends Action > extends CrudRepository<T, Long> {
    
}

