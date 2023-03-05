package com.example.springwebapp.repository;

import com.example.springwebapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCity(String city);


    /* public default boolean makeOrder(String city) {
        try {

        }
        catch (Exception ex) {
            return false;
        }
        return true;
    } */
}
