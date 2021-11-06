package cinema.hib.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@EqualsAndHashCode
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Number phone cannot be empty")
    @Setter
    @Column(nullable = false, unique = true)
    @Pattern(regexp = "^\\+?3?8?(0\\d{9})$", message = "Phone number must have prefix +380 and have 9 numbers after that")
    private String phoneNumber;

    @NotBlank(message = "The user Name cannot be empty")
    @NotNull
    @Setter
    @Pattern(regexp = "^[A-Za-z_-]{3,20}$", message = "User Name must contain from 3 to 20 latin letters, dash and space")
    private String name;

    @NotNull
    @Pattern.List({
            @Pattern(regexp = "(?=.*[0-9]).+", message = "Password must contain one digit."),
            @Pattern(regexp = "(?=.*[a-z]).+", message = "Password must contain one lowercase letter."),
            @Pattern(regexp = "(?=.*[A-Z]).+", message = "Password must contain one uppercase letter."),
            @Pattern(regexp = "(?=.*[@#$%^&+=]).+", message = "Password must contain one special character."),
            @Pattern(regexp = "(?=\\S+$).+", message = "Password must contain no whitespace.")
    })
    @Setter
    @Column
    private String password;

    @ManyToOne
    @Setter
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @OneToMany
    @Setter
    private List<Ticket> tickets;

}
