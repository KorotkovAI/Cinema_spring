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
@Table(name = "halls")
@Getter
@EqualsAndHashCode
@ToString
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @NotBlank(message = "Name of the hall cannot be empty")
    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 25)
    private String name;

    @OneToMany
    @Setter
    @JoinColumn(name = "seat_id", referencedColumnName = "id")
    private List <Seat> seats;
}
