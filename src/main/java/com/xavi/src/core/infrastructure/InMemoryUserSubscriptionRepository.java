package com.xavi.src.core.infrastructure;

import com.xavi.src.core.domain.UserSubscriptionRepository;
import com.xavi.src.core.domain.entity.SubscriptionId;
import com.xavi.src.core.domain.entity.UserId;
import com.xavi.src.core.domain.entity.UserSubscription;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryUserSubscriptionRepository implements UserSubscriptionRepository {

  private HashMap<SubscriptionId, UserId> subscriptionStorage = new HashMap<>();
  private HashMap<UserId, SubscriptionId> userStorage = new HashMap<>();

  @Override
  public List<UserSubscription> findAll() {
    return userStorage.keySet().stream().map(userId -> new UserSubscription(userId, userStorage.get(userId))).collect(Collectors.toList());
  }

  @Override
  public Optional<UserSubscription> findBy(UserId userId) {

    SubscriptionId subscriptionId = userStorage.get(userId);
    if (subscriptionId != null) {
      return Optional.of(new UserSubscription(userId, subscriptionId));
    }
    return Optional.empty();
  }

  @Override
  public Optional<UserSubscription> findBy(SubscriptionId subscriptionId) {

    UserId userId = subscriptionStorage.get(subscriptionId);
    if (userId != null) {
      return Optional.of(new UserSubscription(userId, subscriptionId));
    }
    return Optional.empty();
  }

  @Override
  public void persist(UserSubscription userSubscription) {
    subscriptionStorage.put(userSubscription.getSubscriptionId(), userSubscription.getUserId());
    userStorage.put(userSubscription.getUserId(), userSubscription.getSubscriptionId());
  }
}
