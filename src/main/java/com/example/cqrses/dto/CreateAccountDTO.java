package com.example.cqrses.dto;

import lombok.Data;

@Data
public class CreateAccountDTO {

  private double startingBalance;

  private String currency;
}
