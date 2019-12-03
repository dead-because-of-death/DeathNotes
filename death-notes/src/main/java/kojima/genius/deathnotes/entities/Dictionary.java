package kojima.genius.deathnotes.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Dictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @OneToMany(cascade = CascadeType.ALL)
    List<Pair> definitions;

    @ManyToOne
    User user;
}