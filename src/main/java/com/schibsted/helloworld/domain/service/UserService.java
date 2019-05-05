package com.schibsted.helloworld.domain.service;

import com.schibsted.helloworld.domain.model.User;

import java.util.List;

public interface UserService {

  User getUser(long userId);

  List<User> getUsersByType(int type);
}
