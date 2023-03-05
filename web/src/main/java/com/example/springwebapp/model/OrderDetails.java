package com.example.springwebapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
@NoArgsConstructor
@Getter
@Setter
public class OrderDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long id;

    @Column
    public long orderid;

    @Column
    public String productid;

    @Column
    public int qty;

    public OrderDetails(long orderid, String productid, int qty) {
        this.orderid = orderid;
        this.productid = productid;
        this.qty = qty;
    }
}
