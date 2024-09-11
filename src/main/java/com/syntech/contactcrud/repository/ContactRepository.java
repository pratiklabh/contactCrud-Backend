package com.syntech.contactcrud.repository;
import com.syntech.contactcrud.model.Contact;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Stateless
public class ContactRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public ContactRepository() {
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void save(Contact contact) {
        getEntityManager().persist(contact);
    }

    @Transactional
    public Contact findById(Long id) {
        return getEntityManager().find(Contact.class, id);
    }

    @Transactional
    public void update(Contact contact) {
        getEntityManager().merge(contact);
    }

    @Transactional
    public void delete(Long id) {
        Contact contact = findById(id);
        if (contact != null) {
            getEntityManager().remove(contact);
        }
    }

    public List<Contact> findAll() {
        return getEntityManager()
                .createQuery("SELECT c FROM Contact c", Contact.class)
                .getResultList();
    }
}