package com.example.cqrses.service.impl;

import com.example.cqrses.entity.AccountDetailsQueryEntity;
import com.example.cqrses.repository.AccountDetailsRepository;
import com.example.cqrses.service.AccountDetailsQueryService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountDetailsQueryServiceImpl implements AccountDetailsQueryService {

  private final EventStore eventStore;
  private final AccountDetailsRepository accountDetailsRepository;

  @Override
  public List<Object> listEventsForAccount(String accountNumber) {
    return eventStore.readEvents(accountNumber).asStream()
        .map(Message::getPayload)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<AccountDetailsQueryEntity> getAccountDetails(String accountNumber) {
    return accountDetailsRepository.findById(accountNumber);
  }
}
