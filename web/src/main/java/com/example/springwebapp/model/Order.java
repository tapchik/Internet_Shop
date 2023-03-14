package com.example.springwebapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    public long id;

    @Column(name = "timestamp")
    public OffsetDateTime timestamp;

    @Column(name = "city")
    public String city;

    public Order(long id, String order_city) {
        this.id = id;
        this.timestamp = OffsetDateTime.now();
        this.city = order_city;
    }
}
