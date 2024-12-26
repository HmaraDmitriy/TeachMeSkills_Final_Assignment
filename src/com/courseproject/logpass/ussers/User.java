package com.courseproject.logpass.ussers;

import java.util.Objects;

public class User {

    private String login;
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.courseproject.logpass.ussers.User user = (com.courseproject.logpass.ussers.User) o;
        return Objects.equals(login, user.login) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        String maskedPassword = "*".repeat(password.length());
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + maskedPassword + '\'' +
                '}';
    }
}
