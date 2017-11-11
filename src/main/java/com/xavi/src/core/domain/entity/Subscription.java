package com.xavi.src.core.domain.entity;

public class Subscription {

  private SubscriptionId id;
  private String endpoint;
  private String publicKey;
  private String authKey;

  public Subscription(SubscriptionId id, String endpoint, String publicKey, String authKey) {
    this.id = id;
    this.endpoint = endpoint;
    this.publicKey = publicKey;
    this.authKey = authKey;
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

  public SubscriptionId getId() {
    return id;
  }
}
