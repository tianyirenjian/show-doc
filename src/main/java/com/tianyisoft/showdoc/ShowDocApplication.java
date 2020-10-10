package com.tianyisoft.showdoc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tianyisoft.showdoc.mapper")
public class ShowDocApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShowDocApplication.class, args);
	}

}
