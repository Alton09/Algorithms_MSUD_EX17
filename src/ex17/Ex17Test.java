package ex17;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Ex17Test {
	final static String PROJECT_DIR = System.getProperty("user.dir");
	
	@Test
	public void test2OptimalValue() {
		// Test Data file path
		final String[] filePath = {PROJECT_DIR + "\\DataFiles\\Ex17\\Ex17TestQuestionData.txt"};
		
		// Test
		assertEquals(159.0 ,Ex17.testDriver(filePath), .01);
	}
	
	@Test
	public void exercise9Value() {
		// Test Data file path
		final String[] filePath = {PROJECT_DIR + "\\DataFiles\\Ex17\\Ex17Ex9Data.txt"};
		
		// Test
		assertEquals(274.0 ,Ex17.testDriver(filePath), .01);
	}
	
	@Test
	public void sample1Value() {
		// Test Data file path
		final String[] filePath = {PROJECT_DIR + "\\DataFiles\\Ex17\\Sample1.txt"};
		
		// Test
		assertEquals(300.0 ,Ex17.testDriver(filePath), .01);
	}
	
	@Test
	public void a4Value() {
		// Test Data file path
		final String[] filePath = {PROJECT_DIR + "\\DataFiles\\Ex17\\a4.txt"};
		
		// Test
		assertEquals(224.0 ,Ex17.testDriver(filePath), .01);
	}
}