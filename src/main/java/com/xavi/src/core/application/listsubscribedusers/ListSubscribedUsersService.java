package com.xavi.src.core.application.listsubscribedusers;

import com.xavi.src.core.domain.UserRepository;
import com.xavi.src.core.domain.entity.UserId;
import com.xavi.src.core.domain.entity.SubscribedUser;

import java.util.stream.Collectors;

public class ListSubscribedUsersService {

  private UserRepository userRepository;

  public ListSubscribedUsersService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public ListSubscribedUsersResponse execute() {
    return new ListSubscribedUsersResponse(
        userRepository.findAll()
            .stream()
            .map(SubscribedUser::getUserId)
            .map(UserId::getValue)
            .collect(Collectors.toList())
    );
  }
}
