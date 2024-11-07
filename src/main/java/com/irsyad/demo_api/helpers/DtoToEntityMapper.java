package com.irsyad.demo_api.helpers;

import com.irsyad.demo_api.dto.AccountDTO;
import com.irsyad.demo_api.dto.CustomerDTO;
import com.irsyad.demo_api.models.entities.Account;
import com.irsyad.demo_api.models.entities.Customer;

public class DtoToEntityMapper {
    public static Account convertToEntityAccount(AccountDTO accountDto) {
        Account account = new Account();
        account.setNumber(accountDto.getNumber());
        account.setType(accountDto.getType());
        account.setName(accountDto.getName());
        account.setBalance(accountDto.getBalance());
        return account;
    }

    public static Customer convertToEntityCustomer(CustomerDTO customerDto) {
        Customer customer = new Customer();
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setEmail(customerDto.getEmail());
        customer.setAddress(customerDto.getAddress());
        customer.setAccount(convertToEntityAccount(customerDto.getAccount()));
        return customer;
    }
}
