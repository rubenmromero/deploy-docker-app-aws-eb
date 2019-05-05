package com.schibsted.helloworld.framework.controller;

import com.schibsted.helloworld.domain.service.UserNotFoundException;
import com.schibsted.helloworld.framework.adapter.UserAdapter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {

  private final UserAdapter userAdapter;

  @Inject
  public UserController(UserAdapter userAdapter) {
    this.userAdapter = userAdapter;
  }

  @RequestMapping(value="/users/{userId}", method = RequestMethod.GET)
  public UserDTO getUsers(@PathVariable long userId) {
    return userAdapter.getUser(userId);
  }

  @RequestMapping(value="/users", method = RequestMethod.GET)
  public List<UserDTO> getUsersByType(@RequestParam (name = "userType", required = true) int userType) {
    return userAdapter.getUsersByType(userType);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<String> handleError(UserNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

}
