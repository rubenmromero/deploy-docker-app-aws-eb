package com.schibsted.helloworld.domain.service;

import com.schibsted.helloworld.domain.model.User;
import com.schibsted.helloworld.framework.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User getUser(long userId) {
    Optional<User> optionalUser = userRepository.getUser(userId);

    return optionalUser.orElseThrow(() -> new UserNotFoundException(userId));
  }

  @Override
  public List<User> getUsersByType(int type) {
   return userRepository.getUsersByType(type);
  }
}
