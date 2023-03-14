package com.example.springwebapp.repository;

import com.example.springwebapp.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

    List<OrderDetails> findByproductid(String productid);

}