package com.example.cqrses.controller;

import com.example.cqrses.dto.CreateAccountDTO;
import com.example.cqrses.dto.DepositMoneyDTO;
import com.example.cqrses.dto.WithdrawMoneyDTO;
import com.example.cqrses.service.AccountCommandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/account")
@Tag(name = "Banking Account Commands", description = "Banking Account Command Related Endpoints")
@RequiredArgsConstructor
public class AccountCommandController {

  private final AccountCommandService accountCommandService;

  @PostMapping(value = "/create")
  public CompletableFuture<String> createAccount(@RequestBody CreateAccountDTO createAccountDTO) {
    return accountCommandService.createAccount(createAccountDTO);
  }

  @PutMapping(value = "/credits/{accountNumber}")
  public CompletableFuture<String> creditMoneyToAccount(
      @PathVariable(value = "accountNumber") String accountNumber,
      @RequestBody DepositMoneyDTO depositMoneyDTO) {
    return accountCommandService.depositMoneyToAccount(accountNumber, depositMoneyDTO);
  }

  @PutMapping(value = "/debits/{accountNumber}")
  public CompletableFuture<String> debitMoneyFromAccount(
      @PathVariable(value = "accountNumber") String accountNumber,
      @RequestBody WithdrawMoneyDTO withdrawMoneyDTO) {
    return accountCommandService.withdrawMoneyFromAccount(accountNumber, withdrawMoneyDTO);
  }
}
