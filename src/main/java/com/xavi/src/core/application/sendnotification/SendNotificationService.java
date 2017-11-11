package com.xavi.src.core.application.sendnotification;

import com.xavi.src.core.domain.SubscriptionRepository;
import com.xavi.src.core.domain.UserSubscriptionRepository;
import com.xavi.src.core.domain.entity.Subscription;
import com.xavi.src.core.domain.entity.UserId;
import com.xavi.src.core.domain.entity.UserSubscription;
import com.xavi.src.core.domain.exception.SubscriptionNotFoundException;
import com.xavi.src.core.domain.exception.UserNotFoundException;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import org.jose4j.lang.JoseException;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.ExecutionException;

public class SendNotificationService {

  private PushService pushService;
  private SubscriptionRepository subscriptionRepository;
  private UserSubscriptionRepository userSubscriptionRepository;

  public SendNotificationService(
      PushService pushService,
      SubscriptionRepository subscriptionRepository,
      UserSubscriptionRepository userSubscriptionRepository
  ) {
    this.pushService = pushService;
    this.subscriptionRepository = subscriptionRepository;
    this.userSubscriptionRepository = userSubscriptionRepository;
  }

  public void execute(SendNotificationRequest request) {

    UserSubscription userSubscription =
        userSubscriptionRepository.findBy(new UserId(request.getUserId())).orElseThrow(UserNotFoundException::new);

    Subscription subscription =
        subscriptionRepository.findBy(userSubscription.getSubscriptionId()).orElseThrow(SubscriptionNotFoundException::new);


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
  }
}
