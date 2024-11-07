package com.irsyad.demo_api.helpers;

import com.irsyad.demo_api.dto.AccountDTO;
import com.irsyad.demo_api.dto.CustomerDTO;
import com.irsyad.demo_api.models.entities.Account;
import com.irsyad.demo_api.models.entities.Customer;

public class EntityToDtoMapper {
    public static AccountDTO convertToAccountDTO(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.setNumber(account.getNumber());
        dto.setType(account.getType());
        dto.setName(account.getName());
        dto.setBalance(account.getBalance());
        return dto;
    }

    public static CustomerDTO convertToCustomerDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setEmail(customer.getEmail());
        dto.setAddress(customer.getAddress());
        dto.setAccount(convertToAccountDTO(customer.getAccount()));
        return dto;
    }
}
