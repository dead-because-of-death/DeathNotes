package kojima.genius.deathnotes.repositories;

import kojima.genius.deathnotes.entities.Note;
import kojima.genius.deathnotes.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    @Override
    Optional<Note> findById(Long id);

    Optional<Note> findByUser(User user);
}
