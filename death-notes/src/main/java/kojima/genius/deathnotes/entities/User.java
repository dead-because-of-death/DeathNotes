package kojima.genius.deathnotes.entities;

import kojima.genius.deathnotes.entities.Note;
import kojima.genius.deathnotes.entities.Role;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usr")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 5, max = 16, message = "must be [5,16] symbols")
    @Pattern(regexp = "[a-z,A-Z,0-9,_]+" , message = "must contain only letters, underscores and digits")
    private String username;

    @Size(min = 5, max = 16, message = "must be [5,16] symbols")
    @Pattern(regexp = "[a-z,A-Z,0-9,_]+" , message = "must contain only letters, underscores and digits")
    private String password;
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Note> notes;
}
