package com.bigdata.hdinsight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.bigdata")
public class HdinsightApplication {

	public static void main(String[] args) {
		SpringApplication.run(HdinsightApplication.class, args);
	}

}
