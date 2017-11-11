package com.xavi.src.framework.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xavi.src.core.application.listsubscribedusers.ListSubscribedUsersService;
import com.xavi.src.core.application.registersubscription.RegisterSubscriptionService;
import com.xavi.src.core.application.sendnotification.SendNotificationService;
import com.xavi.src.core.domain.SubscriptionRepository;
import com.xavi.src.core.domain.UserSubscriptionRepository;
import com.xavi.src.core.infrastructure.InMemorySubscriptionRepository;
import com.xavi.src.core.infrastructure.InMemoryUserSubscriptionRepository;
import com.xavi.src.framework.controller.PushNotificationsController;
import nl.martijndwars.webpush.PushService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

  private static final String PRIVATE_KEY = "PRIVATE_SERVER_KEY";

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    objectMapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
    return objectMapper;
  }

  @Bean
  public SubscriptionRepository inmemorySubscriptionRepository() {
    return new InMemorySubscriptionRepository();
  }

  @Bean
  public UserSubscriptionRepository userSubscriptionRepository() {
    return new InMemoryUserSubscriptionRepository();
  }

  @Bean
  public PushNotificationsController pushNotificationsController(
      RegisterSubscriptionService registerSubscriptionService,
      SendNotificationService sendNotificationService
  ) {
    return new PushNotificationsController(registerSubscriptionService, sendNotificationService);
  }

  @Bean
  public RegisterSubscriptionService registerSubscriptionService(
      SubscriptionRepository subscriptionRepository,
      UserSubscriptionRepository userSubscriptionRepository
  ) {
    return new RegisterSubscriptionService(subscriptionRepository, userSubscriptionRepository);
  }

  @Bean
  public SendNotificationService sendNotificationService(
      PushService pushService,
      SubscriptionRepository subscriptionRepository,
      UserSubscriptionRepository userSubscriptionRepository
  ) {
    return new SendNotificationService(pushService, subscriptionRepository, userSubscriptionRepository);
  }

  @Bean
  public ListSubscribedUsersService listSubscribedUsersService(
      UserSubscriptionRepository userSubscriptionRepository
  ) {
    return new ListSubscribedUsersService(userSubscriptionRepository);
  }

  @Bean
  public PushService pushService() {
    return new PushService(PRIVATE_KEY);
  }
}
