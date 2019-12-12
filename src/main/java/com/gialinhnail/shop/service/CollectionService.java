package com.gialinhnail.shop.service;

import com.gialinhnail.shop.enity.Category;
import com.gialinhnail.shop.enity.Collection;
import com.gialinhnail.shop.repo.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CollectionService {
    @Autowired
    CollectionRepository collectionRepository;
    public Collection save(Collection collection){
        return collectionRepository.save(collection);
    }

    public Page<Collection> collections(int page){
        int size = 2; //default page size is 10
        return collectionRepository.findAll(PageRequest.of(page, size));
    }
}
