package com.syntech.contactcrud.repository;

import com.syntech.contactcrud.model.Number;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Stateless
public class NumberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public NumberRepository() {
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void save(Number number) {
        getEntityManager().persist(number);
    }

    @Transactional
    public Number findById(Long id) {
        return getEntityManager().find(Number.class, id);
    }

    @Transactional
    public void update(Number number) {
        getEntityManager().merge(number);
    }

    @Transactional
    public void delete(Long id) {
        Number number = findById(id);
        if (number != null) {
            getEntityManager().remove(number);
        }
    }

    public List<Number> findAll() {
        return getEntityManager()
                .createQuery("SELECT c FROM Number c", Number.class)
                .getResultList();
    }
}