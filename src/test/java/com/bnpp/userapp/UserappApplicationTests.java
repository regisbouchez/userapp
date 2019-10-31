package com.bnpp.userapp;

import com.bnpp.userapp.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserappApplicationTests {

	@Test
	void contextLoads() {
		User user = new User(null,"toto", "titi");

	}

}
