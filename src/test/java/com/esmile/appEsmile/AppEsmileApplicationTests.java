package com.esmile.appEsmile;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.application.properties")
class AppEsmileApplicationTests {

	@Test
	void contextLoads() {
	}

}
