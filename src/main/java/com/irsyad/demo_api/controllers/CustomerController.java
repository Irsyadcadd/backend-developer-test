package com.irsyad.demo_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.irsyad.demo_api.dto.CustomerDTO;
import com.irsyad.demo_api.models.entities.Customer;
import com.irsyad.demo_api.response.ApiResponse;
import com.irsyad.demo_api.response.CustomerResponse;
import com.irsyad.demo_api.services.CustomerService;




@RestController
@RequestMapping("/customer")
public class CustomerController {
    
    @Autowired
    CustomerService customerService;

    @GetMapping("/getalldetails")
    public ResponseEntity<ApiResponse<Page<CustomerDTO>>> findAll(
        @RequestParam(defaultValue = "0") int offset, 
        @RequestParam(defaultValue = "10") int pageSize
    ) {
        ApiResponse<Page<CustomerDTO>> apiResponse = new ApiResponse<>();
        try {
            Page<CustomerDTO> customersPagination = customerService.findAllCustomers(offset, pageSize);
            apiResponse.setData(customersPagination);
            apiResponse.setSuccess(true);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setSuccess(false);
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }


    @GetMapping("/getdetailById/{id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> getDetailById(@PathVariable Long id) {
        ApiResponse<CustomerResponse> apiResponse = new ApiResponse<>();
        try {
            CustomerDTO customer = customerService.findCustomerById(id);
            CustomerResponse customerResponse = new CustomerResponse(customer);
            apiResponse.setData(customerResponse);
            apiResponse.setSuccess(true);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            apiResponse.setMessage(e.toString());
            apiResponse.setSuccess(false);
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }
    
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(@RequestBody Customer customer) {
        ApiResponse<CustomerResponse> apiResponse = new ApiResponse<>();
        try {
            CustomerDTO createdCustomer = customerService.saveCustomer(customer);
            CustomerResponse customerResponse = new CustomerResponse(createdCustomer);
            apiResponse.setData(customerResponse);
            apiResponse.setSuccess(true);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setSuccess(false);
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> update(@PathVariable Long id, @RequestBody Customer customerDetails) {
        ApiResponse<CustomerResponse> apiResponse = new ApiResponse<>();

        try {
            CustomerDTO updatedCustomer = customerService.updateCustomer(id, customerDetails);
            CustomerResponse customerResponse = new CustomerResponse(updatedCustomer);
            apiResponse.setData(customerResponse);
            apiResponse.setSuccess(true);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setSuccess(false);
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCustomer(@PathVariable Long id) {
        ApiResponse<String> apiResponse = new ApiResponse<>();

        try {
            customerService.removeCustomer(id);
            apiResponse.setData("Customer with ID " + id + " deleted successfully");
            apiResponse.setSuccess(true);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e){
            apiResponse.setMessage(e.getMessage());
            apiResponse.setSuccess(false);
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }
    
}
