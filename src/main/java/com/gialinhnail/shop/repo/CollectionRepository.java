package com.gialinhnail.shop.repo;

import com.gialinhnail.shop.enity.Collection;
import com.gialinhnail.shop.enity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {
    Optional<Collection> findFirstByStatusAndOrderByCreatedAtDesc(int status);
}
