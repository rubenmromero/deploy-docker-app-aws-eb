package com.schibsted.helloworld.framework.controller;

import com.schibsted.helloworld.domain.model.User;

public class UserDTO {
  private long id;
  private String name;
  private String surname;
  private int type;

  private UserDTO(Builder builder) {
    this.name = builder.name;
    this.surname = builder.surname;
    this.id = builder.id;
    this.type = builder.type;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  public int getType() {
    return type;
  }

  public static UserDTO.Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private long id;
    private String name;
    private String surname;
    private int type;

    private Builder() {
    }

    public Builder withId(long id) {
      this.id = id;
      return this;
    }

    public Builder withName(String name) {
      this.name = name;
      return this;
    }

    public Builder withSurname(String surname) {
      this.surname = surname;
      return this;
    }

    public Builder withType(int type) {
      this.type = type;
      return this;
    }

    public UserDTO build() {
      return new UserDTO(this);
    }
  }
}
