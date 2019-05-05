package com.schibsted.helloworld.framework.repository;

import com.schibsted.helloworld.domain.model.User;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

  private final NamedParameterJdbcTemplate jdbcTemplate;
  private final UserRowMapper userRowMapper;


  public UserRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
    this.jdbcTemplate = jdbcTemplate;
    this.userRowMapper = userRowMapper;
  }


  @Override
  public Optional<User> getUser(long userId) {
   String sql = "SELECT u_id, u_name, u_surname, u_user_type FROM users WHERE u_id=:id";

    Map<String, Object> parameters = new HashMap<>();
    parameters.put("id", userId);
    List<User> users = jdbcTemplate.query(sql, parameters, userRowMapper);
    return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
  }

  @Override
  public List<User> getUsersByType(int type) {
    String sql = "SELECT u_id, u_name, u_surname, u_user_type FROM users WHERE u_user_type=:userType ORDER BY u_created_at limit 100";

    Map<String, Object> parameters = new HashMap<>();
    parameters.put("userType", type);

    return jdbcTemplate.query(sql, parameters, userRowMapper);
  }



}
