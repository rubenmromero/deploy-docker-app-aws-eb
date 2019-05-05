package com.schibsted.helloworld.framework.adapter;

import com.schibsted.helloworld.domain.model.User;
import com.schibsted.helloworld.domain.service.UserService;
import com.schibsted.helloworld.framework.controller.UserDTO;
import com.schibsted.helloworld.framework.mapper.UserMapper;

import java.util.List;

public class UserAdapter {

  private final UserService userService;
  private final UserMapper userMapper;


  public UserAdapter(UserService userService, UserMapper userMapper) {
    this.userService = userService;
    this.userMapper = userMapper;
  }

  public UserDTO getUser(long userId) {
    User user = userService.getUser(userId);

    return userMapper.toDTO(user);
  }


  public List<UserDTO> getUsersByType(int userType) {
    List<User> usersByType = userService.getUsersByType(userType);
    return userMapper.toListDTO(usersByType);
  }
}
