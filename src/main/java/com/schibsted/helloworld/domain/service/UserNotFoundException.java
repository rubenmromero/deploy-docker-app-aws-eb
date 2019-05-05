package com.schibsted.helloworld.domain.service;



public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(long userId) {
    super("User not found:" + userId);
  }
}
