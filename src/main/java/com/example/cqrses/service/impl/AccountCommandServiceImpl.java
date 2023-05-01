package com.example.cqrses.service.impl;

import com.example.cqrses.command.CreateAccountCommand;
import com.example.cqrses.command.DepositMoneyCommand;
import com.example.cqrses.command.WithdrawMoneyCommand;
import com.example.cqrses.dto.CreateAccountDTO;
import com.example.cqrses.dto.DepositMoneyDTO;
import com.example.cqrses.dto.WithdrawMoneyDTO;
import com.example.cqrses.service.AccountCommandService;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountCommandServiceImpl implements AccountCommandService {

  private final CommandGateway commandGateway;

  @Override
  public CompletableFuture<String> createAccount(CreateAccountDTO createAccountDTO) {
    return commandGateway.send(new CreateAccountCommand(UUID.randomUUID().toString(),
        createAccountDTO.getStartingBalance(), createAccountDTO.getCurrency()));
  }

  @Override
  public CompletableFuture<String> depositMoneyToAccount(String accountNumber,
      DepositMoneyDTO depositMoneyDTO) {
    return commandGateway.send(
        new DepositMoneyCommand(accountNumber, depositMoneyDTO.getCreditAmount(),
            depositMoneyDTO.getCurrency()));
  }

  @Override
  public CompletableFuture<String> withdrawMoneyFromAccount(String accountNumber,
      WithdrawMoneyDTO withdrawMoneyDTO) {
    return commandGateway.send(new WithdrawMoneyCommand(accountNumber, withdrawMoneyDTO.getAmount(),
        withdrawMoneyDTO.getCurrency()));
  }
}
