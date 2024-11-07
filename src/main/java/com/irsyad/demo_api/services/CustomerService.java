package com.irsyad.demo_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.irsyad.demo_api.dto.AccountDTO;
import com.irsyad.demo_api.dto.CustomerDTO;
import com.irsyad.demo_api.helpers.DtoToEntityMapper;
import com.irsyad.demo_api.helpers.EntityToDtoMapper;
import com.irsyad.demo_api.models.entities.Account;
import com.irsyad.demo_api.models.entities.Customer;
import com.irsyad.demo_api.models.repository.AccountRepository;
import com.irsyad.demo_api.models.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    public CustomerDTO saveCustomer(CustomerDTO customerDto) {
        Account account = DtoToEntityMapper.convertToEntityAccount(customerDto.getAccount());
        accountRepository.save(account);

        Customer customer = DtoToEntityMapper.convertToEntityCustomer(customerDto);
        CustomerDTO customerDTO = EntityToDtoMapper.convertToCustomerDTO(customerRepository.save(customer));

        return customerDTO;
    }

    public CustomerDTO findCustomerById(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        CustomerDTO customerDTO = EntityToDtoMapper.convertToCustomerDTO(customer);
        return customerDTO;
    }

    public Page<CustomerDTO> findAllCustomers(int offset, int pageSize) {
        Page<Customer> page = customerRepository.findAll(PageRequest.of(offset, pageSize));
        Page<CustomerDTO> customerDTOPage = page.map(customer -> EntityToDtoMapper.convertToCustomerDTO(customer));
        return customerDTOPage;
    }

    public CustomerDTO updateCustomer(long id, CustomerDTO customerDto) {
        AccountDTO accountDto = customerDto.getAccount();
        
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setEmail(customerDto.getEmail());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setAddress(customerDto.getAddress());
        
        Account account = customer.getAccount();
        account.setName(accountDto.getName());
        account.setType(accountDto.getType());
        account.setBalance(accountDto.getBalance());

        account = accountRepository.save(account);
        customer.setAccount(account);

        customer = customerRepository.save(customer);

        CustomerDTO customerDTO = EntityToDtoMapper.convertToCustomerDTO(customer);
        return customerDTO;
    }

    public void removeCustomer(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        String accountNumber = customer.getAccount().getNumber();
        customerRepository.deleteById(id);
        accountRepository.deleteAccountByNumber(accountNumber);
    }
}
