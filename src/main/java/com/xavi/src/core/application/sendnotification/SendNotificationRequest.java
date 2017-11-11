package com.xavi.src.core.application.sendnotification;

public class SendNotificationRequest {

  private String userId;
  private String message;

  public SendNotificationRequest(String userId, String message) {
    this.userId = userId;
    this.message = message;
  }

  public String getUserId() {
    return userId;
  }

  public String getMessage() {
    return message;
  }
}
