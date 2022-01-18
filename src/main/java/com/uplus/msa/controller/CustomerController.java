package com.uplus.msa.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uplus.msa.dto.CustomerDTO;
import com.uplus.msa.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

	private final CustomerService service;

	@GetMapping("/{id}")
	//http://localhost:8087/customers/1
	public CustomerDTO getCustomer(@PathVariable Long id) throws Exception {
		return service.getCustomerById(id);
	}
	
	@GetMapping("/re/{id}")
	public ResponseEntity<?> getCustomerRE(@PathVariable Long id) throws Exception {
		return service.getCustomerByIdRE(id);
	}
	
	@GetMapping
	public List<CustomerDTO> getAllCustomers() throws Exception {
		return service.getAllCustomers();
	}
	
	@GetMapping("/ids")
	public List<CustomerDTO> getAllCustomerById(@RequestParam("id") List<Long> ids) throws Exception {
		return service.getCustomerByIdList(ids);
	}
	
	
	@PostMapping
	public Long createCustomer(@RequestBody CustomerDTO customerDTO) throws Exception {
		return service.createCustomer(customerDTO);
	}
	
	@GetMapping("/page")
	public List<CustomerDTO> getCustomersPaing(Pageable pageable) throws Exception {
		return service.getCustomersPaing(pageable);
	}
}