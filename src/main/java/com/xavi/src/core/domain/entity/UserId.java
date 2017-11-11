package com.xavi.src.core.domain.entity;

public class UserId {

  private String value;

  public UserId(String value) {
    this.value = value;
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

    UserId userId = (UserId) o;

    return value != null ? value.equals(userId.value) : userId.value == null;
  }

  @Override
  public int hashCode() {
    return value != null ? value.hashCode() : 0;
  }
}
