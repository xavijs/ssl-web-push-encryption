package com.xavi.src.framework.http;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendNewMessageDto {

  @JsonProperty("message")
  private String message;

  @JsonProperty("userId")
  private String userId;

  public SendNewMessageDto(
      @JsonProperty("message") String message,
      @JsonProperty("userId") String userId
  ) {
    this.message = message;
    this.userId = userId;
  }

  public String getMessage() {
    return message;
  }

  public String getUserId() {
    return userId;
  }
}
