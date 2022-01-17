package com.uplus.msa.repository;

import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.uplus.msa.entity.CustomerEntity;

@SpringBootTest
public class CustomerRepositoryTest {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Test @Disabled
	public void customerTest() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setName("철수");
		customerEntity.setAddress("서울");
		
		CustomerEntity saveCustomerEntity = customerRepository.save(customerEntity);
		System.out.println("saveCustomerEntity" + saveCustomerEntity);
		
	}
	
	@Test @Disabled
	public void customerFindTest() {
		Optional<CustomerEntity> findCustomerEntity = customerRepository.findById(1L);
		System.out.println("findCustomerEntity" + findCustomerEntity);
		if(findCustomerEntity.isPresent()) {
			CustomerEntity customerEntity = findCustomerEntity.get();
			System.out.println("customer Id : " + customerEntity.getId());
			System.out.println("customer Name : " + customerEntity.getName());
			System.out.println("customer Address : " + customerEntity.getAddress());
		}
		System.out.println("setter 이후");
		CustomerEntity customerEntity = findCustomerEntity.get();
		customerEntity.setName("홍길동");
		customerEntity.setAddress("서울");
		CustomerEntity saveCustomerEntity = customerRepository.save(customerEntity);
		System.out.println("saveCustomerEntity" + saveCustomerEntity);
		
		Optional<CustomerEntity> findByName = customerRepository.findByName("김철수");
		if(findByName.isPresent()) {
			CustomerEntity customerEntity2 = findByName.get();
			System.out.println("findByName Id : " + customerEntity2.getId());
			System.out.println("findByName Name : " + customerEntity2.getName());
			System.out.println("findByName Address : " + customerEntity2.getAddress());
		}
		
	}
	
	@Test @Disabled
	public void customerFindAll() {
		List<CustomerEntity> findAll = customerRepository.findAll();
		
		// Lambda
		findAll.forEach(customerEntity -> System.out.println(customerEntity));
		
		// method reference
		findAll.forEach(System.out::println);
//		for(CustomerEntity customerEntity : findAll) {
//			System.out.println("customerEntity : " + customerEntity);
//		}
		
//		CustomerEntity customerFindOne = findAll.stream()
//				.filter(c -> c.getName().equals("김철수"))
//				.findFirst()
//				.orElseThrow(() -> new IllegalArgumentException());
//		
//		List<CustomerEntity> customerFindList = findAll.stream()
//				.filter(c -> c.getName().equals("가나다") || c.getName().equals("김철수"))
//				.collect(Collectors.toList());
//		
//		System.out.println("customerFindOne : " + customerFindOne);
//		System.out.println("customerFindList : " + customerFindList);
		
	}
	
	@Test @Disabled
	public void setterTest() { 
		Optional<CustomerEntity> customerFindById = customerRepository.findById(1L);
		CustomerEntity customerEntity = customerFindById.get();
		
		customerEntity.setName("길동");
		
		customerRepository.save(customerEntity);
		
	}
	
	@Test
	public void streamTest() {
		List<CustomerEntity> customerFilterList = customerRepository.findAll()
				.stream()
				.filter(c -> !c.getAddress().isEmpty())
				.collect(toList());
		
		customerFilterList.forEach(customerEntity -> System.out.println("customerEntity : " + customerEntity));
		
		List<String> customerFilterList2 = customerRepository.findAll()
				.stream()
				.map(c -> c.getName())
				.collect(toList());
		
		customerFilterList2.forEach(customerMapEntity -> System.out.println("customerMapEntity : " + customerMapEntity));
		
		List<CustomerEntity> collect = customerRepository.findAll().stream().filter(c -> c.getId() < 2).collect(toList());
		collect.forEach(c -> System.out.println(c));
		
	}
	
}
