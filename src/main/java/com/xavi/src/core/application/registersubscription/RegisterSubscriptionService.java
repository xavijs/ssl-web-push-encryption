package com.xavi.src.core.application.registersubscription;

import com.xavi.src.core.domain.SubscriptionRepository;
import com.xavi.src.core.domain.UserRepository;
import com.xavi.src.core.domain.entity.Subscription;
import com.xavi.src.core.domain.entity.SubscriptionId;
import com.xavi.src.core.domain.entity.UserId;
import com.xavi.src.core.domain.entity.SubscribedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;

public class RegisterSubscriptionService {

  private final Logger LOGGER = LoggerFactory.getLogger(RegisterSubscriptionService.class);
  private SubscriptionRepository subscriptionRepository;
  private UserRepository userRepository;

  public RegisterSubscriptionService(
      SubscriptionRepository subscriptionRepository,
      UserRepository userRepository
  ) {
    this.subscriptionRepository = subscriptionRepository;
    this.userRepository = userRepository;
  }

  public void execute(RegisterSubscriptionRequest request) {

    SubscriptionId subscriptionId = getSubscriptionId(request.getEndpoint());

    userRepository.persist(
        new SubscribedUser(
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

    LOGGER.info("Stored subscription for user {}", request.getUserId());
  }

  private SubscriptionId getSubscriptionId(String endpoint) {
    Path urlPath = Paths.get(endpoint);
    return new SubscriptionId(urlPath.getName(urlPath.getNameCount() - 1).toString());
  }
}
