package com.example.cqrses.dto;

import lombok.Data;

@Data
public class WithdrawMoneyDTO {

  private double amount;

  private String currency;
}
