package com.example.cqrses.config;

import com.example.cqrses.aggregate.BankAccountAggregate;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AxonConfig {

  private final EventStore eventStore;

  @Bean
  public EventSourcingRepository<BankAccountAggregate> accountAggregateRepository() {
    return EventSourcingRepository.builder(BankAccountAggregate.class)
        .eventStore(eventStore)
        .build();
  }
}