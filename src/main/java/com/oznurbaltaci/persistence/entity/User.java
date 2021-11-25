package com.oznurbaltaci.persistence.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false, nullable = false)
    private Long userId;
    @Column(name = "identity_number", length = 11)
    private Long identityNumber;
    @Column(name = "name", length = 30)
    private String name;
    @Column(name = "surname", length = 30)
    private String surname;
    @Column(name = "email", length = 30)
    private String email;
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Account> accounts;

}
