package com.uplus.msa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.uplus.msa.dto.CustomerDTO;
import com.uplus.msa.entity.CustomerEntity;
import com.uplus.msa.repository.CustomerRepository;
import com.uplus.msa.util.AppUtils;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerRepository customerRepository;
	private final ModelMapper modelMapper;
	
	//constructor injection
//	public CustomerServiceImpl(CustomerRepository repository) {
//		this.repository = repository;
//	}

	@Override
	public List<CustomerDTO> getAllCustomers() throws Exception {
		List<CustomerEntity> customerList = customerRepository.findAll();
		//1. 직접매핑하기
//		List<CustomerDTO> dtoList = new ArrayList<>();
//		for (Customer customer : customerList) {
//			CustomerDTO customerDTO = CustomerDTO.builder()
//				.id(customer.getId())
//				.name(customer.getName())
//				.address(customer.getAddress())
//			.build();
//			dtoList.add(customerDTO);
//		}
		
		//2.Stream과 BeanUtils 사용하여 매핑하기
/*		
		List<CustomerDTO> dtoList = customerList.stream() //Stream<Customer>
					//.map(cust -> AppUtils.entityToDto(cust)) //Stream<CustomerDTO>
					.map(AppUtils::entityToDto)
					.collect(Collectors.toList());
*/					
		//3.Stream과 ModelMapper 사용하여 매핑하기
		List<CustomerDTO> dtoList = customerList.stream() //Stream<Customer>
					.map(cust -> modelMapper.map(cust, CustomerDTO.class)) //Stream<CustomerDTO>
					.collect(Collectors.toList());
		return dtoList;
	}

	@Override
	public CustomerDTO getCustomerById(Long id) throws Exception {
		CustomerEntity customerEntity = customerRepository.findById(id).orElseGet(() -> new CustomerEntity());
		//1.직접매핑하기
//		CustomerDTO customerDTO = CustomerDTO.builder()
//					.id(customer.getId())
//					.name(customer.getName())
//					.address(customer.getAddress())
//					.build();		
		
		//2.BeanUtils의 copyProperties() 사용
//		CustomerDTO customerDTO = new CustomerDTO();
//		BeanUtils.copyProperties(customer, customerDTO);
		
//		CustomerDTO customerDTO = AppUtils.entityToDto(customer);
		
		//3. ModelMapper(외부라이브러리)의 map() 사용
		CustomerDTO customerDTO = modelMapper.map(customerEntity, CustomerDTO.class);
		
		return customerDTO;
	}

	@Transactional
	@Override
	public Long createCustomer(CustomerDTO customerDTO) throws Exception {
		//CustomerDTO -> Customer 매핑
		CustomerEntity customerEntity = modelMapper.map(customerDTO, CustomerEntity.class);
		CustomerEntity savedCustomerEntity = customerRepository.save(customerEntity);
		return savedCustomerEntity.getId();
	}

	@Override
	public ResponseEntity<?> getCustomerByIdRE(Long id) throws Exception {
		Optional<CustomerEntity> findById = customerRepository.findById(id);
		if(!findById.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		CustomerEntity customerEntity = findById.get();
		CustomerDTO customerDTO = modelMapper.map(customerEntity, CustomerDTO.class);
		return ResponseEntity.ok(customerDTO);
	}
	
	@Override
	public List<CustomerDTO> getCustomerByIdList(List<Long> ids) throws Exception {
		return customerRepository.findByIdIn(ids) //List<Customer>
						.stream()	//Stream<Customer>
						.map(cust -> modelMapper.map(cust, CustomerDTO.class)) //Stream<CustomerDTO>
						.collect(Collectors.toList()); //List<CustomerDTO>						
	}
	
	@Override
	public List<CustomerDTO> getCustomersPaing(Pageable pageable) throws Exception {
		Page<CustomerEntity> customerPage = customerRepository.findAll(pageable);
		return customerPage.get() //Stream<Customer>
						   .map(cust -> modelMapper.map(cust, CustomerDTO.class)) //Stream<CustomerDTO>
						   .collect(Collectors.toList());						   
	}

	@Override
	public ResponseEntity<?> updateCustomer(CustomerDTO customerDTO) throws Exception {
		Optional<CustomerEntity> customerID = customerRepository.findById(customerDTO.getId());
		if(!customerID.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customerDTO.getId() + " customer not found");
		}
		
		CustomerEntity customerEntity = customerID.get();
		
		if(!StringUtils.isEmpty(customerDTO.getName())) {
			customerEntity.setName(customerDTO.getName());
		}
		if(!StringUtils.isEmpty(customerDTO.getAddress())) {
			customerEntity.setAddress(customerDTO.getAddress());
		}
		
		CustomerEntity updatedCustomer = customerRepository.save(customerEntity);
		
		CustomerDTO customer = modelMapper.map(updatedCustomer, CustomerDTO.class);
		
		return ResponseEntity.ok(customer);
	}
	
}