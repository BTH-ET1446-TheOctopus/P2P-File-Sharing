package test;

import static org.junit.Assert.*;

public class Test {

	@org.junit.Test
	public void test() {
		int sum = 0;
		sum = 2 + 3;
		assertEquals(5, sum);
	}
	/*
	@org.junit.Test
	public void fail() {
		int sum = 0;
		sum = 2 + 3;
		assertEquals(6, sum);
	}
	*/
}
