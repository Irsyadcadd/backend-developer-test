package com.irsyad.demo_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.irsyad.demo_api.dto.CustomerDTO;
import com.irsyad.demo_api.helpers.Mapper;
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

    public CustomerDTO saveCustomer(Customer customer) {
        accountRepository.save(customer.getAccount());
        CustomerDTO customerDTO = Mapper.convertToCustomerDTO(customerRepository.save(customer));
        return customerDTO;
    }

    public CustomerDTO findCustomerById(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        CustomerDTO customerDTO = Mapper.convertToCustomerDTO(customer);
        return customerDTO;
    }

    public Page<CustomerDTO> findAllCustomers(int offset, int pageSize) {
        Page<Customer> page = customerRepository.findAll(PageRequest.of(offset, pageSize));
        Page<CustomerDTO> customerDTOPage = page.map(customer -> Mapper.convertToCustomerDTO(customer));
        return customerDTOPage;
    }

    public CustomerDTO updateCustomer(long id, Customer customerDetails) {
        Account account = accountRepository.save(customerDetails.getAccount());
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setEmail(customerDetails.getEmail());
        customer.setPhoneNumber(customerDetails.getPhoneNumber());
        customer.setAddress(customerDetails.getAddress());
        customer.setAccount(account);

        CustomerDTO customerDTO = Mapper.convertToCustomerDTO(customerRepository.save(customer));
        return customerDTO;
    }

    public void removeCustomer(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        accountRepository.deleteAccountByNumber(customer.getAccount().getNumber());
        customerRepository.deleteById(id);
    }
}
