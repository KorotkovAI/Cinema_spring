package cinema.hib.dto.model;

import cinema.hib.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {

    @Min(0)
    private long id;

    @NotBlank(message = "Number phone cannot be empty")
    @Pattern(regexp = "^\\+?3?8?(0\\d{9})$", message = "Phone number must have prefix +380 and have 9 numbers after that")
    private String phoneNumber;

    @NotBlank(message = "The user Name cannot be empty")
    @Pattern(regexp = "^[A-Za-z_-]{3,20}$", message = "User Name must contain from 3 to 20 latin letters, dash and space")
    private String name;

    @Pattern.List({
            @Pattern(regexp = "(?=.*[0-9]).+", message = "Password must contain one digit."),
            @Pattern(regexp = "(?=.*[a-z]).+", message = "Password must contain one lowercase letter."),
            @Pattern(regexp = "(?=.*[A-Z]).+", message = "Password must contain one uppercase letter."),
            @Pattern(regexp = "(?=.*[@#$%^&+=]).+", message = "Password must contain one special character."),
            @Pattern(regexp = "(?=\\S+$).+", message = "Password must contain no whitespace.")
    })
    private String password;

    private Role role;

}
