package kojima.genius.deathnotes.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Lob
    String text;
    @ManyToOne
    User user;
}
