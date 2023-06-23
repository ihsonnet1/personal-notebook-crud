package com.hubartech.personalnotebookcrud;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Personal Notebook Backend",
				description = "A Spring-Boot REST API Documentation of Personal Notebook CRUD",
				version = "1.0.1",
				contact = @Contact(
						name = "Injamamul Haque Sonet",
						email = "injamamul@swordfishtech.co.uk"
				)
		)
)
public class PersonalNotebookCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalNotebookCrudApplication.class, args);
	}

}
