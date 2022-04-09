package com.slowv.fruit.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Email
    private String email;
    private String phoneNumber;
    private String addressDetail;
    @ManyToOne
    private Account account;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;

    public Customer() {
    }

    public Customer(String name, @Email String email, String phoneNumber, String addressDetail) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.addressDetail = addressDetail;
    }

}
