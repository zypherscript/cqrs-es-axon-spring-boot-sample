package com.example.cqrses.manager;

import com.example.cqrses.aggregate.BankAccountAggregate;
import com.example.cqrses.entity.AccountDetailsQueryEntity;
import com.example.cqrses.event.BaseEvent;
import com.example.cqrses.repository.AccountDetailsRepository;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountQueryManager {

  @Autowired
  private AccountDetailsRepository accountDetailsRepository;

  @Autowired
  private EventSourcingRepository<BankAccountAggregate> accountAggregateRepository;

  @SuppressWarnings("rawtypes")
  @EventSourcingHandler
  void on(BaseEvent event) {
    var accountAggregate = accountAggregateRepository.load(event.id.toString())
        .getWrappedAggregate()
        .getAggregateRoot();
    var accountDetailsQueryEntity = buildQueryAccount(accountAggregate);
    accountDetailsRepository.save(accountDetailsQueryEntity);
  }

  private AccountDetailsQueryEntity buildQueryAccount(BankAccountAggregate accountAggregate) {
    var id = accountAggregate.getId();
    var accountQueryEntityOptional = accountDetailsRepository.findById(id);
    AccountDetailsQueryEntity accountQueryEntity = accountQueryEntityOptional.orElseGet(
        AccountDetailsQueryEntity::new);

    accountQueryEntity.setId(id);
    accountQueryEntity.setBalance(accountAggregate.getBalance());
    accountQueryEntity.setCurrency(accountAggregate.getCurrency());
    accountQueryEntity.setStatus(accountAggregate.getStatus());

    return accountQueryEntity;
  }
}
