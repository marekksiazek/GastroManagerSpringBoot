package pl.marekksiazek.GastroManager.entity;

import jakarta.persistence.*;

import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;




@Table(name="users")
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "user_pwd")
    private String userPwd;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "role")
    private String role;

    @Column(name = "company_id")
    private Long companyId;

    // Secure password hashing with Spring Security
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
