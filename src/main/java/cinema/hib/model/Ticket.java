package cinema.hib.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Ticket() {
        this.createdAt = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "films_name", referencedColumnName = "name")
    private Film filmName;

    @ManyToOne
    @JoinColumn(name = "halls_name", referencedColumnName = "name")
    private Hall hallName;

    @ManyToOne
    @JoinColumn(name = "seats_id", referencedColumnName = "id")
    private Seat seatId;

    @ManyToOne
    @JoinColumn(name = "slots_id", referencedColumnName = "id")
    private Slot slot;

    @Setter
    @Column(name = "isUsedTicket", nullable = false)
    private Boolean isUsedTicket = false;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Ticket ticket = (Ticket) object;
        return createdAt.equals(ticket.createdAt) && userId.equals(ticket.userId) && filmName.equals(ticket.filmName) && hallName.equals(ticket.hallName) && seatId.equals(ticket.seatId) && slot.equals(ticket.slot) && isUsedTicket.equals(ticket.isUsedTicket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), createdAt, userId, filmName, hallName, seatId, slot, isUsedTicket);
    }

    @Override
    public java.lang.String toString() {
        return "Ticket{" +
                "createdAt=" + createdAt +
                ", userId=" + userId +
                ", filmName=" + filmName +
                ", hallName=" + hallName +
                ", seatId=" + seatId +
                ", slot=" + slot +
                ", isUsedTicket=" + isUsedTicket +
                '}';
    }
}
