package com.slowv.fruit.repository;

import com.slowv.fruit.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c from Category as c where c.status = :status")
    List<Category> findAllByStatus (@Param("status") int status);

}
