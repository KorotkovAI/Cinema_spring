package cinema.hib.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "genres")
@Getter
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "The film genre cannot be empty")
    @Column(nullable = false, unique = true)
    @Setter
    @Size(min = 3, max = 50)
    private String name;

    @ManyToMany
    @Setter
    private List<Film> films;
}
