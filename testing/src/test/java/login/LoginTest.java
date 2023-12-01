package login;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LoginTest {

	@Test
	void testUsernameSuccess() {
		Login newUser = new Login();
		newUser.setName("testUsername");
		newUser.setPassword("testPassword");
		assertEquals(newUser.getName(), "testUsername");
	}

	@Test
	void testPasswordSuccess() {
		Login newUser = new Login();
		newUser.setName("testUsername");
		newUser.setPassword("testPassword");
		assertEquals(newUser.getPassword(), "testPassword");
	}
	
	@Test
	void testPasswordFail() {
		Login newUser = new Login();
		newUser.setName("testUsername");
		newUser.setPassword("testPassword");
		assertEquals(newUser.getPassword(), "testQwerty");
	}
}
