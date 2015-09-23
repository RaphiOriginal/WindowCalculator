package ch.raphi.jomoscalculator.test.calculation;

import static org.junit.Assert.*;
import org.junit.Test;

import ch.raphi.jomoscalculator.calculation.Calculator;

/**
 * @author raphaelbrunner
 *
 */
public class TestCalculation {
	/**
	 * testing with values from a hand drawn sketch by the customer
	 */
	@Test
	public void calculationTest(){
		Calculator classUnderTest = new Calculator();
		final double expectedResult = 40;
		final double expectedResult2 = 112;
		final double calculatedResult = classUnderTest.calculateX(3.5, 9, 72, 103, 0.5, 65, 2);
		final double calculatedResult2 = classUnderTest.calculateY(3.5, 9, 73, calculatedResult);
		
		assertEquals(expectedResult, calculatedResult, 3.0);
		assertEquals(expectedResult2, calculatedResult2, 3.0);
	}
	/**
	 * testing with values from a hand drawn sketch by author
	 */
	@Test
	public void calculation2Test(){
		Calculator classUnderTest = new Calculator();
		final double expectedResult = 60;
		final double expectedResult2 = 116;
		final double calculatedResult = classUnderTest.calculateX(4, 16, 58, 104, 0, 60, 0);
		final double calculatedResult2 = classUnderTest.calculateY(4, 16, 58, calculatedResult);
		assertEquals(expectedResult, calculatedResult, 3.0);
		assertEquals(expectedResult2, calculatedResult2, 3.0);
	}
}
