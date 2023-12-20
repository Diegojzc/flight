package com.tokioschool.flightapp.flight.entities;
import com.tokioschool.flightapp.core.repository.TsidGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @TsidGenerator
    private String id;
    @CreationTimestamp
    private LocalDateTime created;
    private String name;
    private String surname;
    private String email;
    private String password;
    private LocalDateTime lastLogin;

    @ManyToMany
    @JoinTable(name ="users_with_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name= "role_id"))
    private Set<RoleEntity> roles;

}
