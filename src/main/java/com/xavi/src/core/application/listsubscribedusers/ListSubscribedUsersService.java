package com.xavi.src.core.application.listsubscribedusers;

import com.xavi.src.core.domain.UserSubscriptionRepository;
import com.xavi.src.core.domain.entity.UserId;
import com.xavi.src.core.domain.entity.UserSubscription;

import java.util.stream.Collectors;

public class ListSubscribedUsersService {

  private UserSubscriptionRepository userSubscriptionRepository;

  public ListSubscribedUsersService(UserSubscriptionRepository userSubscriptionRepository) {
    this.userSubscriptionRepository = userSubscriptionRepository;
  }

  public ListSubscribedUsersResponse execute() {
    return new ListSubscribedUsersResponse(
        userSubscriptionRepository.findAll()
            .stream()
            .map(UserSubscription::getUserId)
            .map(UserId::getValue)
            .collect(Collectors.toList())
    );
  }
}
