package com.xavi.src.core.domain.entity;

public class UserSubscription {
  private UserId userId;
  private SubscriptionId subscriptionId;

  public UserSubscription(UserId userId, SubscriptionId subscriptionId) {
    this.userId = userId;
    this.subscriptionId = subscriptionId;
  }

  public UserId getUserId() {
    return userId;
  }

  public SubscriptionId getSubscriptionId() {
    return subscriptionId;
  }
}
