package com.irsyad.demo_api.models.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id", length=11, nullable=false)
    private long id;

    @Column(name="phone_number", length=15, nullable=false, unique=true)
    private String phoneNumber;

    @Column(name="email", length=50, nullable=false, unique=true)
    private String email;

    @Column(name="address", length=50, nullable=false)
    private String address;

    @OneToOne
    @JoinColumn(name = "account_number", referencedColumnName = "number", nullable = false, unique=true)
    private Account account;


    public Customer(String phoneNumber, String email, String address, Account account) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.account = account;
    }
}
