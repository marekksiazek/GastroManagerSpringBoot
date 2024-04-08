package pl.marekksiazek.GastroManager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Table(name="company")
@Entity
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @NotBlank
    private Long id;

    @NotBlank
    @Column(name = "nip")
    private String nip;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Email
    @Column(name = "email")
    private String email;

    @NotBlank
    @Column(name = "street")
    private String street;

    @NotBlank
    @Column(name = "home_number")
    private String homeNumber;

    @Column(name = "local_number")
    private int localNumber;

    @NotBlank
    @Column(name = "city")
    private String city;

    @NotBlank
    @Column(name = "country")
    private String country;

    @NotBlank
    @Column(name = "password")
    private String password;

    @Column(name = "is_deleted")
    private int isDeleted;
}
