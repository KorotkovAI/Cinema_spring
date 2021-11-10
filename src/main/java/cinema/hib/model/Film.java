package cinema.hib.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "films")
@Getter
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "The film Name cannot be empty")
    @Column(nullable = false, unique = true)
    @Setter
    @Size(min = 3, max = 200)
    private String name;

    @NotBlank(message = "The film duration cannot be empty")
    @NotNull
    @Setter
    private int duration;

    @NotBlank(message = "The film rate cannot be empty")
    @NotNull
    @Setter
    private double rate;

    @Enumerated(EnumType.ORDINAL)
    private AgeLimit ageLimit;

    @NotBlank(message = "The film description cannot be empty")
    @NotNull
    @Setter
    private String description;

    @ManyToMany
    @Setter
    @JoinColumn(name = "genres_name", referencedColumnName = "name")
    private List<Genre> genres;

    @OneToMany
    @Setter
    @JoinColumn(name = "slot_id", referencedColumnName = "id")
    private List<Slot> slots;
}
