package com.uplus.msa.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.uplus.msa.dto.CustomerDTO;

public interface CustomerService {
	//전체조회
	public List<CustomerDTO> getAllCustomers() throws Exception;
	
	//Customer 하나 조회
	public CustomerDTO getCustomerById(Long id) throws Exception;
	
	Long createCustomer(CustomerDTO customerDTO) throws Exception;
	
	ResponseEntity<?> getCustomerByIdRE(Long id) throws Exception;

	List<CustomerDTO> getCustomerByIdList(List<Long> ids) throws Exception;
	
	List<CustomerDTO> getCustomersPaing(Pageable pageable) throws Exception;
	
	ResponseEntity<?> updateCustomer(CustomerDTO customerDTO) throws Exception;
	
}
