package com.uplus.msa.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerRepositoryLambdaTest {
	
	@Test @Disabled
	public void innerclass() {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("innerclass test");
				
			}
		});
		
		t1.start();
		
	}
	
	@Test
	public void lambda() {
		new Thread(() -> {
			System.out.println("lambda test");
		}).start();
	}
}
