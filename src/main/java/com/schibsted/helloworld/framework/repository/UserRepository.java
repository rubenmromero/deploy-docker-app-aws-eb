package com.schibsted.helloworld.framework.repository;

import com.schibsted.helloworld.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

  Optional<User> getUser(long userId);

  List<User> getUsersByType(int type);
}
