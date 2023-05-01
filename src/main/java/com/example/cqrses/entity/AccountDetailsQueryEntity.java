package com.example.cqrses.entity;

import com.example.cqrses.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class AccountDetailsQueryEntity {

  @Id
  private String id;

  private double balance;

  private String currency;

  private Status status;

  @Override
  public String toString() {
    return "AccountDetailsQueryEntity{" +
        "id='" + id + '\'' +
        ", balance =" + balance +
        ", currency='" + currency + '\'' +
        ", status='" + status + '\'' +
        '}';
  }
}
