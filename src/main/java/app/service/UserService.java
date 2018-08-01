package app.service;

import app.entity.User;

public interface UserService {
    User register(User user);

    User findUserByEmail(String email);

    User findUserById(String id);

}
