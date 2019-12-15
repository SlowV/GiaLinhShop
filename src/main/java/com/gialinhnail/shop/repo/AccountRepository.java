package com.gialinhnail.shop.repo;

import com.gialinhnail.shop.enity.Account;
import com.gialinhnail.shop.enity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
}
