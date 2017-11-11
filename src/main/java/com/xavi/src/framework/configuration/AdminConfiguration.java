package com.xavi.src.framework.configuration;

import com.xavi.src.core.application.listsubscribedusers.ListSubscribedUsersService;
import com.xavi.src.core.application.sendnotification.SendNotificationService;
import com.xavi.src.framework.controller.AdminController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminConfiguration {

  @Bean
  public AdminController adminController(
      ListSubscribedUsersService listSubscribedUsersService,
      SendNotificationService sendNotificationService
  ) {
    return new AdminController(listSubscribedUsersService, sendNotificationService);
  }
}
