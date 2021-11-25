package com.oznurbaltaci.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", updatable = false, nullable = false)
    private Long accountId;
    @Column(name = "currency", length = 3)
    private String currency;
    @Column(name = "amount")
    private Double amount;

}
