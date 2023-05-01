package com.example.cqrses.event;

import com.example.cqrses.enums.Status;

public class HoldAccountEvent extends BaseEvent<String> {

  public final Status status;
  public String errorMsg;

  public HoldAccountEvent(String id, Status status, String errorMsg) {
    super(id);
    this.status = status;
    this.errorMsg = errorMsg;
  }
}
