package authservice.entity;

import lombok.Data;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "account")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @ElementCollection
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "account_id"))
    @Column(name = "role")
    private Set<String> roles;
}
