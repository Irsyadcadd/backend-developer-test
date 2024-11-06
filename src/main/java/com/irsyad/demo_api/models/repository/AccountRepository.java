package com.irsyad.demo_api.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irsyad.demo_api.models.entities.Account;

public interface AccountRepository extends JpaRepository<Account, String>{
    
    Account findAccountByNumber(String number);

    void deleteAccountByNumber(String number);
}
