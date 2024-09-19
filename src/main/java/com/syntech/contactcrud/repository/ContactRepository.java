package com.syntech.contactcrud.repository;

import com.syntech.contactcrud.model.Contact;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

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

    public int countEntity(Map<String, FilterMeta> filters) {
        
        String jpql = "SELECT COUNT(c) FROM Contact c"; 
        Long count = getEntityManager().createQuery(jpql, Long.class).getSingleResult();

        return count.intValue();
    }

    public List<Contact> getEntity(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
    String jpql = "SELECT c FROM Contact c";
    // Add filtering and sorting logic if needed
    
    TypedQuery<Contact> query = getEntityManager().createQuery(jpql, Contact.class);
    query.setFirstResult(first);
    query.setMaxResults(pageSize);

    return query.getResultList();
}


}
