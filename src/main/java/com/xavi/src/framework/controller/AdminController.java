package com.xavi.src.framework.controller;

import com.xavi.src.core.application.listsubscribedusers.ListSubscribedUsersService;
import com.xavi.src.core.application.sendnotification.SendNotificationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AdminController {

  private ListSubscribedUsersService listSubscribedUsersService;
  private SendNotificationService sendNotificationService;

  public AdminController(
      ListSubscribedUsersService listSubscribedUsersService,
      SendNotificationService sendNotificationService
  ) {
    this.listSubscribedUsersService = listSubscribedUsersService;
    this.sendNotificationService = sendNotificationService;
  }

  @RequestMapping("/admin")
  public String admin(
      Model model
  ) {

    List<String> users = listSubscribedUsersService.execute().getUsers();

    model.addAttribute("users", users);

    return "adminpanel";
  }
}
