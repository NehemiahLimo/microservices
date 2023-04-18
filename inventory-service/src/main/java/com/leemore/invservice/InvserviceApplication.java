package com.leemore.invservice;

import com.leemore.invservice.model.Inventory;
import com.leemore.invservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
@EnableDiscoveryClient
public class InvserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvserviceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository repository){
		return args -> {
			Inventory inventory= new Inventory();
			inventory.setSkuCode("iphone_13");
			inventory.setQuantity(100);

			Inventory inventory1= new Inventory();
			inventory1.setSkuCode("iphone_13_red");
			inventory1.setQuantity(0);

			repository.save(inventory);
			repository.save(inventory1);



		};
	}
}
