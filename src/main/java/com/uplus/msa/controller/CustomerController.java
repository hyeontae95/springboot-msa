package com.uplus.msa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public CustomerDTO getCustomer(@PathVariable Long id) throws Exception {
		return service.getCustomerById(id);
	}
}
