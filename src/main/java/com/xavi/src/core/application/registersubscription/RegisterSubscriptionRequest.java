package com.xavi.src.core.application.registersubscription;

public class RegisterSubscriptionRequest {

  private String userId;
  private String endpoint;
  private String publicKey;
  private String authKey;

  public RegisterSubscriptionRequest(String userId, String endpoint, String publicKey, String authKey) {
    this.userId = userId;
    this.endpoint = endpoint;
    this.publicKey = publicKey;
    this.authKey = authKey;
  }

  public String getUserId() {
    return userId;
  }

  public String getEndpoint() {
    return endpoint;
  }

  public String getPublicKey() {
    return publicKey;
  }

  public String getAuthKey() {
    return authKey;
  }
}
