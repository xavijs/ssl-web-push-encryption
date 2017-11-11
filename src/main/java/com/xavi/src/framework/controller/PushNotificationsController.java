package com.xavi.src.framework.controller;

import com.xavi.src.core.application.registersubscription.RegisterSubscriptionRequest;
import com.xavi.src.core.application.registersubscription.RegisterSubscriptionService;
import com.xavi.src.core.application.sendnotification.SendNotificationRequest;
import com.xavi.src.core.application.sendnotification.SendNotificationService;
import com.xavi.src.core.domain.exception.UserNotFoundException;
import com.xavi.src.framework.http.SendNewMessageDto;
import com.xavi.src.framework.http.SubscriptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@CrossOrigin
public class PushNotificationsController {

  private RegisterSubscriptionService registerSubscriptionService;
  private SendNotificationService sendNotificationService;

  public PushNotificationsController(
      RegisterSubscriptionService registerSubscriptionService,
      SendNotificationService sendNotificationService
  ) {
    this.registerSubscriptionService = registerSubscriptionService;
    this.sendNotificationService = sendNotificationService;
  }

  @RequestMapping(value = "/subscription/{userId}", method = RequestMethod.PUT)
  public ResponseEntity registerNewSubscription(
      @PathVariable("userId") String userId,
      @RequestBody SubscriptionDto subscription
  ) {

    registerSubscriptionService.execute(
        new RegisterSubscriptionRequest(
            userId,
            subscription.getEndpoint(),
            subscription.getKeys().getP256dh(),
            subscription.getKeys().getAuth()
        )
    );

    return ResponseEntity.ok().build();
  }

  @RequestMapping(value = "/message", method = RequestMethod.POST)
  public ResponseEntity sendNewMessage(
      @RequestBody SendNewMessageDto sendNewMessageDto
  ) {

    try {
      sendNotificationService.execute(
          new SendNotificationRequest(sendNewMessageDto.getUserId(), sendNewMessageDto.getMessage())
      );
    } catch (UserNotFoundException exception) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
    } catch (RuntimeException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno");
    }

    return ResponseEntity.ok("Notificacion enviada correctamente");
  }
}
