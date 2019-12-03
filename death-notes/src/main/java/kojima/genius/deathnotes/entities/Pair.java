package kojima.genius.deathnotes.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Pair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Lob
    String key;

    @Lob
    String value;
}