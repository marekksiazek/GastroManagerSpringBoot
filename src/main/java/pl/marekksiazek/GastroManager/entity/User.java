package pl.marekksiazek.GastroManager.entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Table(name="users")
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank
    @Column(name="user_id")
    private Long userId;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "surname")
    private String surname;

    @NotBlank
    @Email
    @Column(name = "email")
    private String email;

    @NotBlank
    @Column(name = "user_pwd")
    private String userPwd;

    @NotBlank
    @Column(name = "company_name")
    private String companyName;

    @NotBlank
    @Column(name = "role")
    private String role;

    @Column(name = "is_active")
    private int isActive;

    @NotBlank
    @Column(name = "company_id")
    private Long companyId;


    public void setUserPwd(String plainTextPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.userPwd = "{bcrypt}" + passwordEncoder.encode(plainTextPassword);
    }

    public void setRole(String role) {
        this.role = "ROLE_" + role.toUpperCase();
    }

    public Long getId() {
        return userId;
    }
}
