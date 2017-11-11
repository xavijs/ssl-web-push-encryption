package com.xavi.src.core.domain.entity;

public class SubscriptionId {

  private String value;

  public SubscriptionId(String value) {
    this.value = value;

    if (value.length() < 10) {
      throw new IllegalArgumentException("Invalid SubscriptionId");
    }
  }

  public String getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    SubscriptionId that = (SubscriptionId) o;

    return value != null ? value.equals(that.value) : that.value == null;
  }

  @Override
  public int hashCode() {
    return value != null ? value.hashCode() : 0;
  }
}
