package com.uplus.msa.runner;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class DatabaseRunner implements ApplicationRunner {
	@Autowired
	private DataSource dataSource;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Connection connection = dataSource.getConnection();
		DatabaseMetaData metaData = connection.getMetaData();
		log.debug("debug==> DB URL " + metaData.getURL());
		log.debug("debug==> dataSource " + dataSource.getClass().getName());
		log.debug("debug==> User name " + metaData.getUserName());
		
	}
	
}
