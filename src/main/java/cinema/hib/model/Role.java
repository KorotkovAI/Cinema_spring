package cinema.hib.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Getter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.ORDINAL)
    private RoleType roleType;

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Role role = (Role) object;
        return roleType.equals(role.roleType);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), roleType);
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Role{" +
                "id=" + id +
                ", roleType=" + roleType +
                '}';
    }
}
