package com.irsyad.demo_api.models.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
@Data 
@NoArgsConstructor 
@AllArgsConstructor
public class Account implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="number", length=50, nullable = false)
    private String number;

    @Column(name="type", length=50, nullable = false)
    private String type;

    @Column(name="name", length=50, nullable = false)
    private String name;

    @Column(name="balance", nullable = false)
    private double balance;

}
