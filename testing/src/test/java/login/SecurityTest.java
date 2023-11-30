package login;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javafx.scene.control.TextField;

class SecurityTest {

	@Test
	void test1() {
		Security secure = new Security();
		String testPassword = "_password123_";
		assertEquals(secure.checkRequirements(testPassword), true);
	}

	@Test
	void test2() {
		Security secure = new Security();
		String testPassword = "_password123?";
		assertEquals(secure.checkRequirements(testPassword), false);
	}
}
