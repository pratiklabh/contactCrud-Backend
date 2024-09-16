package com.syntech.contactcrud.repository;

import com.syntech.contactcrud.model.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Stateless
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public UserRepository() {
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void save(User user) {
        getEntityManager().persist(user);
    }

    @Transactional
    public User findById(Long id) {
        return getEntityManager().find(User.class, id);
    }

    @Transactional
    public void update(User user) {
        getEntityManager().merge(user);
    }

    @Transactional
    public void delete(Long id) {
        User user = findById(id);
        if (user != null) {
            getEntityManager().remove(user);
        }
    }

    public List<User> findAll() {
        return getEntityManager()
                .createQuery("SELECT c FROM User c", User.class)
                .getResultList();
    }
}