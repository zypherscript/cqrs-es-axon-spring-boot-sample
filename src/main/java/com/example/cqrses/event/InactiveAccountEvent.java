package com.example.cqrses.event;

public class InactiveAccountEvent extends BaseEvent<String> {

  public String errorMsg;

  public InactiveAccountEvent(String id, String errorMsg) {
    super(id);
    this.errorMsg = errorMsg;

  }
}
