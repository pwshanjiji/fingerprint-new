package com.xml;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
//指定mapper的包路径，省去@Mapper注解
@MapperScan("com.xml.mapper")
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
}
