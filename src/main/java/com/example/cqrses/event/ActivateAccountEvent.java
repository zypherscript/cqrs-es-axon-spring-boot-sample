package com.example.cqrses.event;

import com.example.cqrses.enums.Status;

public class ActivateAccountEvent extends BaseEvent<String> {

  public final Status status;

  public ActivateAccountEvent(String id, Status status) {
    super(id);
    this.status = status;
  }
}