package cinema.hib.model;

import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "roles")
@Getter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.ORDINAL)
    private RoleType roleType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return roleType == role.roleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), roleType);
    }

    @Override
    public java.lang.String toString() {
        return "Role{" +
                "id=" + id +
                ", roleType=" + roleType +
                '}';
    }
}
