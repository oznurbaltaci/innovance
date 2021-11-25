package com.oznurbaltaci.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferAccountRequest {
   private String senderCurrency;
   private Double amount;
   private Long senderAccountId;
   private Long receiverAccountId;
}
