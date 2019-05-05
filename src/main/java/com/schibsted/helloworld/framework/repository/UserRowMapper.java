package com.schibsted.helloworld.framework.repository;

import com.schibsted.helloworld.domain.model.User;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

  @Override
  public User mapRow(ResultSet rs, int rowNum) throws SQLException {
    return User.builder()
        .withId(rs.getLong("u_id"))
        .withName(rs.getString("u_name"))
        .withSurname(rs.getString("u_surname"))
        .withType(rs.getInt("u_user_type"))
        .build();
  }
}