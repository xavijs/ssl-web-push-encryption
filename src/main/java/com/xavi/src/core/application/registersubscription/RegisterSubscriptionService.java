package com.xavi.src.core.application.registersubscription;

import com.xavi.src.core.domain.SubscriptionRepository;
import com.xavi.src.core.domain.UserSubscriptionRepository;
import com.xavi.src.core.domain.entity.Subscription;
import com.xavi.src.core.domain.entity.SubscriptionId;
import com.xavi.src.core.domain.entity.UserId;
import com.xavi.src.core.domain.entity.UserSubscription;

import java.nio.file.Path;
import java.nio.file.Paths;

public class RegisterSubscriptionService {

  private SubscriptionRepository subscriptionRepository;
  private UserSubscriptionRepository userSubscriptionRepository;

  public RegisterSubscriptionService(
      SubscriptionRepository subscriptionRepository,
      UserSubscriptionRepository userSubscriptionRepository
  ) {
    this.subscriptionRepository = subscriptionRepository;
    this.userSubscriptionRepository = userSubscriptionRepository;
  }

  public void execute(RegisterSubscriptionRequest request) {

    SubscriptionId subscriptionId = getSubscriptionId(request.getEndpoint());

    if (userSubscriptionRepository.findBy(subscriptionId).isPresent()) {
      System.out.println("Subscription already registered. Skipping.");
      return;
    }

    userSubscriptionRepository.persist(
        new UserSubscription(
            new UserId(request.getUserId()),
            subscriptionId
        )
    );

    subscriptionRepository.persist(
        new Subscription(
            getSubscriptionId(request.getEndpoint()),
            request.getEndpoint(),
            request.getPublicKey(),
            request.getAuthKey()
        )
    );
  }

  private SubscriptionId getSubscriptionId(String endpoint) {
    Path urlPath = Paths.get(endpoint);
    return new SubscriptionId(urlPath.getName(urlPath.getNameCount() - 1).toString());
  }
}
