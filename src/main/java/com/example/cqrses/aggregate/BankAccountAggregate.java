package com.example.cqrses.aggregate;

import com.example.cqrses.command.CreateAccountCommand;
import com.example.cqrses.command.DepositMoneyCommand;
import com.example.cqrses.command.WithdrawMoneyCommand;
import com.example.cqrses.enums.Status;
import com.example.cqrses.event.AccountCreatedEvent;
import com.example.cqrses.event.ActivateAccountEvent;
import com.example.cqrses.event.DepositMoneyEvent;
import com.example.cqrses.event.HoldAccountEvent;
import com.example.cqrses.event.InactiveAccountEvent;
import com.example.cqrses.event.WithdrawMoneyEvent;
import lombok.Getter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Getter
public class BankAccountAggregate {

  @AggregateIdentifier
  private String id;

  private double balance;

  private String currency;

  private Status status;

  private String errorMsg;

  public BankAccountAggregate() {
  }

  @CommandHandler
  public BankAccountAggregate(CreateAccountCommand createAccountCommand) {
    AggregateLifecycle.apply(
        new AccountCreatedEvent(createAccountCommand.id, createAccountCommand.accountBalance,
            createAccountCommand.currency));
  }

  @EventSourcingHandler
  protected void on(AccountCreatedEvent accountCreatedEvent) {
    this.id = accountCreatedEvent.id;
    this.balance = accountCreatedEvent.balance;
    this.currency = accountCreatedEvent.currency;
    this.status = Status.CREATED;
    AggregateLifecycle.apply(new ActivateAccountEvent(this.id, Status.ACTIVATED));

  }

  @CommandHandler
  protected void on(DepositMoneyCommand depositMoneyCommand) {
    AggregateLifecycle.apply(
        new DepositMoneyEvent(depositMoneyCommand.id, depositMoneyCommand.amount,
            depositMoneyCommand.currency));
  }

  @EventSourcingHandler
  protected void on(DepositMoneyEvent depositMoneyEvent) {

    if (this.balance < 0 && (this.balance + depositMoneyEvent.creditAmount) >= 0) {
      if (this.status != Status.ACTIVATED) {
        AggregateLifecycle.apply(new ActivateAccountEvent(depositMoneyEvent.id, Status.ACTIVATED));

      }
    }

    this.balance += depositMoneyEvent.creditAmount;
  }

  @CommandHandler
  protected void on(WithdrawMoneyCommand withdrawMoneyCommand) {
    AggregateLifecycle.apply(
        new WithdrawMoneyEvent(withdrawMoneyCommand.id, withdrawMoneyCommand.amount,
            withdrawMoneyCommand.currency));
  }

  @EventSourcingHandler
  protected void on(WithdrawMoneyEvent withdrawMoneyEvent) {
    if (this.status != Status.ACTIVATED) {
      AggregateLifecycle.apply(new InactiveAccountEvent(withdrawMoneyEvent.id, "inactive account"));
      return;
    }

    if (this.balance >= 0 && (this.balance - withdrawMoneyEvent.debitAmount) < 0) {
      AggregateLifecycle.apply(
          new HoldAccountEvent(withdrawMoneyEvent.id, Status.HOLD, "insufficient balance"));
    } else {
      this.balance -= withdrawMoneyEvent.debitAmount;
    }

  }

  @EventSourcingHandler
  protected void on(ActivateAccountEvent activateAccountEvent) {
    this.status = activateAccountEvent.status;
  }

  @EventSourcingHandler
  protected void on(HoldAccountEvent holdAccountEvent) {
    this.status = holdAccountEvent.status;
    this.errorMsg = holdAccountEvent.errorMsg;
  }

  @EventSourcingHandler
  protected void on(InactiveAccountEvent inactiveAccountEvent) {
    this.errorMsg = inactiveAccountEvent.errorMsg;
  }
}
