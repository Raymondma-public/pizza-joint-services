package com.pizzajoint.ordergenerationservice;

import com.pizzajoint.ordergenerationservice.services.OrderPersistenceService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class OrderGenerationServiceApplicationTests {

	@MockBean
	OrderPersistenceService orderPersistenceService;

	@Test
	void contextLoads() {
	}

}
