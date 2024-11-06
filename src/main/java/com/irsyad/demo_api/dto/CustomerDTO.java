package com.irsyad.demo_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private long id;
    private String phoneNumber;
    private String email;
    private String address;
    private AccountDTO accountDTO;
}
