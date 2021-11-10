package cinema.hib.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shedule")
@Getter
public class Shedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @OneToOne
    private Hall hall;

    @Setter
    @OneToMany
    private List<Slot> slots;
}
