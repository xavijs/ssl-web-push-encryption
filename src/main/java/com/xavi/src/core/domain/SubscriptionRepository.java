package com.xavi.src.core.domain;

import com.xavi.src.core.domain.entity.Subscription;
import com.xavi.src.core.domain.entity.SubscriptionId;

import java.util.Optional;

public interface SubscriptionRepository {

  void persist(Subscription subscription);

  Optional<Subscription> findBy(SubscriptionId id);

}
