package com.irsyad.demo_api.response;

import com.irsyad.demo_api.dto.CustomerDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PagingCustomerResponse {
    private Iterable<CustomerDTO> listCustomers;
}
