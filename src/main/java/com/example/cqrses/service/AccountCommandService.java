package com.example.cqrses.service;

import com.example.cqrses.dto.CreateAccountDTO;
import com.example.cqrses.dto.DepositMoneyDTO;
import com.example.cqrses.dto.WithdrawMoneyDTO;
import java.util.concurrent.CompletableFuture;

public interface AccountCommandService {

  CompletableFuture<String> createAccount(CreateAccountDTO createAccountDTO);

  CompletableFuture<String> depositMoneyToAccount(String accountNumber,
      DepositMoneyDTO depositMoneyDTO);

  CompletableFuture<String> withdrawMoneyFromAccount(String accountNumber,
      WithdrawMoneyDTO withdrawMoneyDTO);
}
