package com.example.cqrses.controller;

import com.example.cqrses.entity.AccountDetailsQueryEntity;
import com.example.cqrses.service.AccountDetailsQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/account")
@Tag(name = "Bank Account Queries", description = "Bank Account Query Events Endpoint")
@RequiredArgsConstructor
public class AccountQueryController {

  private final AccountDetailsQueryService accountDetailsQueryService;

  @GetMapping("/{accountNumber}")
  public ResponseEntity<AccountDetailsQueryEntity> getAccountDetails(
      @PathVariable(value = "accountNumber") String accountNumber) {
    var result = accountDetailsQueryService.getAccountDetails(accountNumber);
    return result.map(
            accountDetailsQueryEntity -> new ResponseEntity<>(accountDetailsQueryEntity, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @GetMapping("/{accountNumber}/events")
  public List<Object> listEventsForAccount(
      @PathVariable(value = "accountNumber") String accountNumber) {
    return accountDetailsQueryService.listEventsForAccount(accountNumber);
  }
}
