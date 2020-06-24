package model.service;

import model.dto.User;

public interface IUser {
    boolean register(String username, String password);
    boolean login(User user);
}
