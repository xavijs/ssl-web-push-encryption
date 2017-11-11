package com.xavi.src.core.infrastructure;

import com.xavi.src.core.domain.entity.Subscription;
import com.xavi.src.core.domain.entity.SubscriptionId;
import com.xavi.src.core.domain.SubscriptionRepository;

import java.util.HashMap;
import java.util.Optional;

public class InMemorySubscriptionRepository implements SubscriptionRepository {

  private HashMap<String, Subscription> storage = new HashMap<>();

  @Override
  public void persist(Subscription subscription) {
    storage.put(subscription.getId().getValue(), subscription);
  }

  @Override
  public Optional<Subscription> findBy(SubscriptionId id) {
    return Optional.ofNullable(storage.get(id.getValue()));
  }
}
