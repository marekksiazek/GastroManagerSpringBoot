package pl.marekksiazek.GastroManager.entity;

import jakarta.persistence.*;
import lombok.*;


@Table(name="company")
@Entity
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "nip")
    private String nip;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "street")
    private String street;

    @Column(name = "homenumber")
    private String homeNumber;

    @Column(name = "localnumber")
    private int localNumber;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "password")
    private String password;
}
