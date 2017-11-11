package com.xavi.src.core.domain;

import com.xavi.src.core.domain.entity.SubscribedUser;
import com.xavi.src.core.domain.entity.UserId;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

  List<SubscribedUser> findAll();

  Optional<SubscribedUser> findBy(UserId userId);

  void persist(SubscribedUser subscribedUser);
}
