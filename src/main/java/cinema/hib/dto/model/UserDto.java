package cinema.hib.dto.model;

import cinema.hib.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {

    private String phoneNumber;

    private String name;

    private String password;

    private Role role;

}
