package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Numbers;

class TestEnums {

	Numbers[] numbs = {
		    Numbers.Empty,
		    Numbers.One,
		    Numbers.Two,
		    Numbers.Three,
		    Numbers.Four,
		    Numbers.Five,
		    Numbers.Six,
		    Numbers.Seven,
		    Numbers.Eight,
		    Numbers.Nine
		};

	
	
	@Test
	void testToString() {
		String[] strings = {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		for(int i = 0; i < 10; i++) {
			assertEquals(numbs[i].toString(), (strings[i]));
		}
	}
	
	@Test
	void testFromInt() {
		for(int i = 0; i < 10; i++) {
			assertEquals(numbs[i].toInteger(), i);
		}
	}
	@Test
	void toNumTest() {
		for(int i = 0; i < 10; i++) {
			assertEquals(Numbers.fromInteger(i), numbs[i]);
		}	
	}
	
}
