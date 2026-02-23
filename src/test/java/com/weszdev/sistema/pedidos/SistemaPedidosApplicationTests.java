package com.weszdev.sistema.pedidos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(classes = {ServletWebServerFactoryAutoConfiguration.class},
		webEnvironment = RANDOM_PORT,
		properties = {"spring.cloud.config.enabled=false"})
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class SistemaPedidosApplicationTests {

	@Test
	void contextLoads() {
		assertTrue(true);
	}

}
