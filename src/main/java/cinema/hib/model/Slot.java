package cinema.hib.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "slots")
@Getter
@EqualsAndHashCode
@ToString
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @NotNull(message = "The date of film cannot be empty")
    @Setter
    private LocalDate dateOfFilm;

    @NotBlank
    @NotNull(message = "The time start of film cannot be empty")
    @Setter
    private LocalTime startTime;

    @NotBlank
    @NotNull(message = "The end time of film cannot be empty")
    @Setter
    private LocalTime endTime;

    @ManyToOne
    @Setter
    @JoinColumn(name = "films_name", referencedColumnName = "name")
    private Film filmName;
}
