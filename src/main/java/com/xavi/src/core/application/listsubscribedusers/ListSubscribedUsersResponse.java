package com.xavi.src.core.application.listsubscribedusers;

import java.util.List;

public class ListSubscribedUsersResponse {
  List<String> users;

  public ListSubscribedUsersResponse(List<String> users) {
    this.users = users;
  }

  public List<String> getUsers() {
    return users;
  }
}
