package dev.marvin.customermanager;

import dev.marvin.customermanager.customer.entity.Customer;
import dev.marvin.customermanager.customer.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerManagerApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(CustomerRepository customerRepository){
		return args -> {
			Customer customer = new Customer("Marvin", "marvin@gmail.com", "0792000000");
			customerRepository.save(customer);
		};
	}
}
