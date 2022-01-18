package com.uplus.msa.service;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uplus.msa.dto.CustomerDTO;
import com.uplus.msa.entity.CustomerEntity;
import com.uplus.msa.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerRepository repository;
	
	//constructor injection
//	public CustomerServiceImpl(CustomerRepository repository) {
//		this.repository = repository;
//	}

	@Override
	public List<CustomerDTO> getAllCustomers() throws Exception {
		
		return null;
	}

	@Override
	public CustomerDTO getCustomerById(Long id) throws Exception {
		CustomerEntity customer = repository.findById(id).orElseGet(() -> new CustomerEntity());
		//1.직접매핑하기
		CustomerDTO customerDTO = CustomerDTO.builder()
					.id(customer.getId())
					.name(customer.getName())
					.address(customer.getAddress())
					.build();
		return customerDTO;
	}

	
}
