package com.xavi.src.core.application.sendnotification;

import com.xavi.src.core.domain.SubscriptionRepository;
import com.xavi.src.core.domain.UserRepository;
import com.xavi.src.core.domain.entity.SubscribedUser;
import com.xavi.src.core.domain.entity.Subscription;
import com.xavi.src.core.domain.entity.UserId;
import com.xavi.src.core.domain.exception.SubscriptionNotFoundException;
import com.xavi.src.core.domain.exception.UserNotFoundException;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.ExecutionException;

public class SendNotificationService {

  private PushService pushService;
  private SubscriptionRepository subscriptionRepository;
  private UserRepository userRepository;
  private final Logger LOGGER = LoggerFactory.getLogger(SendNotificationService.class);


  public SendNotificationService(
      PushService pushService,
      SubscriptionRepository subscriptionRepository,
      UserRepository userRepository
  ) {
    this.pushService = pushService;
    this.subscriptionRepository = subscriptionRepository;
    this.userRepository = userRepository;
  }

  public void execute(SendNotificationRequest request) {

    LOGGER.info("Sending new message to {}", request.getUserId());

    SubscribedUser subscribedUser =
        userRepository.findBy(new UserId(request.getUserId())).orElseThrow(UserNotFoundException::new);

    Subscription subscription =
        subscriptionRepository.findBy(subscribedUser.getSubscriptionId()).orElseThrow(SubscriptionNotFoundException::new);


    try {
      Notification notification = new Notification(
          subscription.getEndpoint(),
          subscription.getPublicKey(),
          subscription.getAuthKey(),
          request.getMessage()
      );

      pushService.send(notification);
    } catch (GeneralSecurityException | IOException | JoseException | ExecutionException | InterruptedException e) {
      throw new RuntimeException(e);
    }

    LOGGER.info("Message {} successfully sent to {}", request.getMessage(), request.getUserId());
  }
}
