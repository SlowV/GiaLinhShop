package com.slowv.fruit.repository;

import com.slowv.fruit.domain.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {
    Collection findTopByOrderByCreatedAtDesc();
}
