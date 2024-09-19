package com.syntech.contactcrud.model;

import com.syntech.contactcrud.repository.ContactRepository;
import java.util.List;
import java.util.Map;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

public class ContactLazyDataModel extends LazyDataModel<Contact>{

    private ContactRepository contactRepo;

    public ContactLazyDataModel(ContactRepository contactRepo) {
        this.contactRepo = contactRepo;
    }
    
    @Override
    public int count(Map<String, FilterMeta> filterBy) {
        return contactRepo.countEntity(filterBy);
    }

    @Override
    public List<Contact> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
    
        return contactRepo.getEntity(first, pageSize, sortBy, filterBy);
    }

}
