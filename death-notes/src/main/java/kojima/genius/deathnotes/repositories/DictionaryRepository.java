package kojima.genius.deathnotes.repositories;

import kojima.genius.deathnotes.entities.Dictionary;
import kojima.genius.deathnotes.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {

    @Override
    Optional<Dictionary> findById(Long id);

    Optional<Dictionary> findByUser(User user);
}
