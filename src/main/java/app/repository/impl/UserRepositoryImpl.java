package app.repository.impl;

import app.entity.User;
import app.repository.UserRepository;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findUserById(String id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public User findUserByEmail(String email) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM User p where email=:email");
        query.setParameter("email", email);
        if (query.getResultList().size() != 0) {
            return (User) query.getResultList().get(0);
        }
        return null;
    }

    @Override
    public User add(User user) {
        return (User) sessionFactory.getCurrentSession().save(user);
    }
}
