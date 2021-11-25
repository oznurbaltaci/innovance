package com.oznurbaltaci.model.response;

import lombok.*;

import javax.validation.constraints.Min;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    private String currency;
    private Double amount;
}
