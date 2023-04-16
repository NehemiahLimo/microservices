package com.leemore.invservice;

import com.leemore.invservice.model.Inventory;
import com.leemore.invservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InvserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvserviceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository repository){
		return args -> {
			Inventory inventory= new Inventory();
			inventory.setSkuCode("skucode-233-34");
			inventory.setQuantity(3);

			Inventory inventory1= new Inventory();
			inventory1.setSkuCode("skucode_32ew");
			inventory1.setQuantity(0);

			repository.save(inventory);
			repository.save(inventory1);



		};
	}
}
