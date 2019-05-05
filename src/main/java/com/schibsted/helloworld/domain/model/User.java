package com.schibsted.helloworld.domain.model;

public class User {
  private long id;
  private String name;
  private String surname;
  private int type;

  private User(Builder builder) {
    this.id = builder.id;
    this.name = builder.name;
    this.surname = builder.surname;
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

  public static Builder builder() {
    return new Builder();
  }


  public static final class Builder {
    private long id;
    private String name;
    private String surname;
    private int type;

    private Builder() {
    }

    public static Builder anUser() {
      return new Builder();
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

    public User build() {
      return new User(this);
    }
  }
}
