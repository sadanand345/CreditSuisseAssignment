package com.creditsuisseassignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.creditsuisseassignment.service.LogsCheck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class CreditsuisseassignmentApplication  implements CommandLineRunner{
	private static final Logger LOGGER = LoggerFactory.getLogger(CreditsuisseassignmentApplication.class);

	@Autowired
	private LogsCheck logsCheck;
	
	public static void main(String[] args) {
		SpringApplication.run(CreditsuisseassignmentApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("Starting execution of analyzeLogs()");
		logsCheck.analyzeLogs(args);
		LOGGER.info("Finished execution of analyzeLogs()");
	}
}
