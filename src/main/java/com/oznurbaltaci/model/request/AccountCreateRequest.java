package com.oznurbaltaci.model.request;


import lombok.*;

import javax.validation.constraints.Min;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreateRequest {
    private String currency;
    private Double amount = 0d;



}
