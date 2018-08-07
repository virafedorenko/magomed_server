package app.repository.impl;

import app.entity.User;
import app.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private EntityManagerFactory entityManagerFactory;

    @Override
    public User findUserById(String id) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        return session.get(User.class, id);
    }

    @Override
    public User findUserByEmail(String email) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        Query query = session.createQuery("FROM User p where email=:email");
        query.setParameter("email", email);
        if (query.getResultList().size() != 0) {
            return (User) query.getResultList().get(0);
        }
        return null;
    }

    @Override
    public User add(User user) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        return (User) session.save(user);
    }
}
