package com.schibsted.helloworld.framework.configuration;

import com.schibsted.helloworld.domain.service.UserService;
import com.schibsted.helloworld.domain.service.UserServiceImpl;
import com.schibsted.helloworld.framework.adapter.UserAdapter;
import com.schibsted.helloworld.framework.mapper.UserMapper;
import com.schibsted.helloworld.framework.repository.UserRepository;
import com.schibsted.helloworld.framework.repository.UserRepositoryImpl;
import com.schibsted.helloworld.framework.repository.UserRowMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class UserConfiguration {

  @Bean
  public UserService userService(UserRepository userRepository) {
    return new UserServiceImpl(userRepository);
  }

  @Bean
  public UserMapper userMapper() {
    return new UserMapper();
  }

  @Bean
  public UserRepository userRepository(NamedParameterJdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
    return new UserRepositoryImpl(jdbcTemplate, userRowMapper);
  }

  @Bean
  public UserRowMapper userRowMapper() {
    return new UserRowMapper();
  }

  @Bean
  public NamedParameterJdbcTemplate configureNamedJdbcTemplate(JdbcTemplate jdbcTemplate) {
    return new NamedParameterJdbcTemplate(jdbcTemplate);
  }

  @Bean
  public UserAdapter userAdapter(UserService userService, UserMapper userMapper) {
    return new UserAdapter(userService, userMapper);
  }
}
