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
}
