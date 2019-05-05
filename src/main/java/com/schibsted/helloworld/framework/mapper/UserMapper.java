package com.schibsted.helloworld.framework.mapper;

import com.schibsted.helloworld.domain.model.User;
import com.schibsted.helloworld.framework.controller.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

  public UserDTO toDTO(User user) {
    return UserDTO.builder()
        .withId(user.getId())
        .withName(user.getName())
        .withSurname(user.getSurname())
        .withType(user.getType())
        .build();
  }

  public List<UserDTO> toListDTO(List<User> usersByType) {
    return usersByType.stream().map(this::toDTO).collect(Collectors.toList());
  }
}
