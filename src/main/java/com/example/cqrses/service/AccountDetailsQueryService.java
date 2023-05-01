package com.example.cqrses.service;

import com.example.cqrses.entity.AccountDetailsQueryEntity;
import java.util.List;
import java.util.Optional;

public interface AccountDetailsQueryService {

  List<Object> listEventsForAccount(String accountNumber);

  Optional<AccountDetailsQueryEntity> getAccountDetails(String accountNumber);
}
