package com.xavi.src.core.domain;

import com.xavi.src.core.domain.entity.SubscriptionId;
import com.xavi.src.core.domain.entity.UserId;
import com.xavi.src.core.domain.entity.UserSubscription;

import java.util.List;
import java.util.Optional;

public interface UserSubscriptionRepository {

  List<UserSubscription> findAll();

  Optional<UserSubscription> findBy(UserId userId);

  Optional<UserSubscription> findBy(SubscriptionId subscriptionId);

  void persist(UserSubscription userSubscription);
}
