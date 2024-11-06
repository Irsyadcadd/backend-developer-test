package com.irsyad.demo_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irsyad.demo_api.dto.AccountDTO;
import com.irsyad.demo_api.helpers.Mapper;
import com.irsyad.demo_api.models.entities.Account;
import com.irsyad.demo_api.models.repository.AccountRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;

    public AccountDTO saveAccount(Account account) {
        AccountDTO accountDTO = Mapper.convertToAccountDTO(accountRepository.save(account));
        return accountDTO;
    }

    public AccountDTO updateAcount(String number, Account accountDetails) {
        Account account = accountRepository.findAccountByNumber(number);
        account.setName(accountDetails.getName());
        account.setType(accountDetails.getType());
        account.setBalance(accountDetails.getBalance());
        AccountDTO accountDTO = Mapper.convertToAccountDTO(accountRepository.save(account));
        return accountDTO;
    }

    public AccountDTO findAccountByNumber(String number) {
        AccountDTO accountDTO = Mapper.convertToAccountDTO(accountRepository.save(
            accountRepository.findAccountByNumber(number)
            ));
        return accountDTO;
    }

    public void deleteAccount(String number) {
        accountRepository.deleteAccountByNumber(number);
    }
}
