/*
package com.example.springwebapp.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springwebapp.model.NewProduct;

@Repository
@Transactional
public interface ShopRepository extends JpaRepository<NewProduct, Integer> {

    Page<NewProduct> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

    /*
    @Query("UPDATE Tutorial t SET t.published = :published WHERE t.id = :id")
    @Modifying
    public void updatePublishedStatus(Integer id, boolean published);
}
*/
