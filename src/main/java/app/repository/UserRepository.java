package app.repository;

import app.entity.User;

public interface UserRepository {
    User findUserById(String id);

    User findUserByEmail(String email);

    User save(User user);
}
