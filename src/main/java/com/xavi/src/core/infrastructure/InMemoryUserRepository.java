package com.xavi.src.core.infrastructure;

import com.xavi.src.core.domain.UserRepository;
import com.xavi.src.core.domain.entity.SubscribedUser;
import com.xavi.src.core.domain.entity.SubscriptionId;
import com.xavi.src.core.domain.entity.UserId;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryUserRepository implements UserRepository {

  private HashMap<UserId, SubscriptionId> userStorage = new HashMap<>();

  @Override
  public List<SubscribedUser> findAll() {
    return userStorage.keySet().stream().map(userId -> new SubscribedUser(userId, userStorage.get(userId))).collect(Collectors.toList());
  }

  @Override
  public Optional<SubscribedUser> findBy(UserId userId) {

    SubscriptionId subscriptionId = userStorage.get(userId);
    if (subscriptionId != null) {
      return Optional.of(new SubscribedUser(userId, subscriptionId));
    }
    return Optional.empty();
  }

  @Override
  public void persist(SubscribedUser subscribedUser) {
    userStorage.put(subscribedUser.getUserId(), subscribedUser.getSubscriptionId());
  }
}
