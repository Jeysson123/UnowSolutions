package com.unowsolutions.vehicles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan({"com.unowsolutions.common","com.unowsolutions.vehicles.controllers"})
@EnableSwagger2
public class VehiclesApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehiclesApplication.class, args);
	}

}
