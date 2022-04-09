package com.slowv.fruit.service.impl;

import com.slowv.fruit.domain.Collection;
import com.slowv.fruit.repository.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl {
    @Autowired
    CollectionRepository collectionRepository;
    public Collection save(Collection collection){
        return collectionRepository.save(collection);
    }

    public Page<Collection> collections(int page, int size){
        return collectionRepository.findAll(PageRequest.of(page, size));
    }

    public List<Collection> findAllNoPage(){
        return collectionRepository.findAll();
    }

    public void saveAll(List<Collection> collections){
        collectionRepository.saveAll(collections);
    }

    public Collection findOneByCreatedAtDesc(){
        return collectionRepository.findTopByOrderByCreatedAtDesc();
    }
}
