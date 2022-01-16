package org.example.dao;

import org.example.model.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<User> allUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();

    }

    @Override
    public void add(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.persist(user);
    }

    @Override
    public void delete(User user) {
        User merge = entityManager.merge(user);
        entityManager.remove(merge);
    }

    @Override
    public void editUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.update(user);
    }

    @Override
    public User getById(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(User.class, id);
    }
}
