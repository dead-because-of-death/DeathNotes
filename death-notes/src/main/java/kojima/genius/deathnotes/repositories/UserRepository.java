package kojima.genius.deathnotes.repositories;

import kojima.genius.deathnotes.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String name);
}
