package com.xavi.src.framework.http;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubscriptionDto {

  @JsonProperty("endpoint")
  private String endpoint;

  @JsonProperty("expirationTime")
  private String expirationTime;

  @JsonProperty("keys")
  private Keys keys;

  public SubscriptionDto(
      @JsonProperty("endpoint") String endpoint,
      @JsonProperty("expirationTime") String expirationTime,
      @JsonProperty("keys") Keys keys) {
    this.endpoint = endpoint;
    this.expirationTime = expirationTime;
    this.keys = keys;
  }

  public static class Keys {

    @JsonProperty("p256dh")
    private String p256dh;

    @JsonProperty("auth")
    private String auth;

    public Keys(
        @JsonProperty("p256dh") String p256dh, @JsonProperty("auth") String auth) {
      this.p256dh = p256dh;
      this.auth = auth;
    }

    @JsonProperty("p256dh")
    public String getP256dh() {
      return p256dh;
    }

    @JsonProperty("auth")
    public String getAuth() {
      return auth;
    }

    @Override
    public String toString() {
      return "Keys{" +
             "p256dh='" + p256dh + '\'' +
             ", auth='" + auth + '\'' +
             '}';
    }
  }

  @JsonProperty("endpoint")
  public String getEndpoint() {
    return endpoint;
  }

  @JsonProperty("expirationTime")
  public String getExpirationTime() {
    return expirationTime;
  }

  @JsonProperty("keys")
  public Keys getKeys() {
    return keys;
  }

  @Override
  public String toString() {
    return "SubscriptionDto{" +
           "endpoint='" + endpoint + '\'' +
           ", expirationTime='" + expirationTime + '\'' +
           ", keys=" + keys +
           '}';
  }
}
