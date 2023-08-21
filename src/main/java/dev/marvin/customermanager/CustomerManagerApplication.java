package dev.marvin.customermanager;

import com.github.javafaker.Faker;
import dev.marvin.customermanager.customer.model.Customer;
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

			Faker faker = new Faker();

			var firstName = faker.name().firstName();
			var lastName = faker.name().lastName();
			var fullName = firstName + " " + lastName;
			var email = "%s%s@test.com".formatted(firstName.toLowerCase(), lastName.toLowerCase());
			var mobile = faker.phoneNumber().cellPhone();


			Customer customer = new Customer(fullName, email, mobile);
			customerRepository.save(customer);
		};
	}
}
