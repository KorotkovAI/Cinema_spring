package cinema.hib.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@Getter
@EqualsAndHashCode
@ToString
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Ticket() {
        this.createdAt = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Setter
    private User userId;

    @ManyToOne
    @Setter
    @JoinColumn(name = "films_name", referencedColumnName = "name")
    private Film filmName;

    @ManyToOne
    @Setter
    @JoinColumn(name = "halls_name", referencedColumnName = "name")
    private Hall hallName;

    @ManyToOne
    @Setter
    @JoinColumn(name = "seats_id", referencedColumnName = "id")
    private Seat seatId;

    @ManyToOne
    @Setter
    @JoinColumn(name = "slots_id", referencedColumnName = "id")
    private Slot slot;

    @Setter
    @Column(name = "isUsedTicket", nullable = false)
    private Boolean isUsedTicket = false;
}
