package com.karolmalysa.insurancecenter.model.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "CompanyClient")
@Data
public class CompanyClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @Column(name = "surname", unique = false, nullable = false)
    private String surname;

    @Column(name = "dateOfBirth", unique = false, nullable = true)
    private LocalDate dateOfBirth;

    @Column(name = "PESEL", unique = true, nullable = false)
    private String pesel;

    @Column(name = "address", unique = false, nullable = false)
    private String address;

    @Column(name = "phone", unique = true, nullable = false)
    private String phone;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", unique = false, nullable = false)
    private UserRoles role;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(mappedBy = "companyClient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Motorcar> motorcarList;

}
