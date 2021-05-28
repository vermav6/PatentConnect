package com.patentconnect.junit;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.patentconnect.tools.Parser;

/**
 * JUnit Test Class For Parser.java
 * 
 * Tests a wide variety of strings that range from empty strings
 * to long numbers to strings containing ASCII characters, random
 * Unicode characters, floating point numbers, and everything in
 * between. Also, it covers a plethora of edge cases.
 * 
 * @author Jatin Chowdhary
 */
public class ParserTest {
	
	/*
	 * Declares the Strings that will be tested. The String
	 * names are descriptive and self-explanatory. Rationale is
	 * explained below. 
	 */
	String testEmptyString;
	String testEmptyCharacters;
	String testOneDigitNumber;
	String testTwoDigitNumber;
	String testThreeDigitNumber;
	String testFourDigitNumber;
	String testFiveDigitNumber;
	String testSixDigitNumber;
	String testSevenDigitNumber;
	String testEightDigitNumber;
	String testNineDigitNumber;
	String testTenDigitNumber;
	String testTwentyDigitNumber;
	String testThirtyDigitNumber;
	String testFloat1;
	String testFloat2;
	String testFloat3;
	String testBadFloat;
	String testSingleCharacter;
	String testShortWord;
	String testMediumWord;
	String testLongWord;
	String testUpperCaseWord;
	String testWordsAndNumbers;
	String testSpacesAndNumbers;
	String testSpacesAndLetters;
	String testPunctuationCharacters;
	String testQuotationMarksNumbers;
	String testQuotationMarksLetters;
	String testJavaUnicode1;
	String testJavaUnicode2;
	String testJavaUnicode3;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		/*
		 * Values are assigned to the testStrings (Initialization)
		 * The name of the variable corresponds to the value
		 *   assigned to it
		 */
		
		// Test the empty string in case user does not supply input
		testEmptyString = "";  
		
		// Test sequence of empty characters
		testEmptyCharacters = "     "; 
		
		// Test a single digit number
		testOneDigitNumber = "1"; 
		
		// Test two digit number
		testTwoDigitNumber = "22"; 
		
		// Test three digit number
		testThreeDigitNumber = "123";  
		
		// Test four digit number
		testFourDigitNumber = "4242"; 
		
		// Test five digit number
		testFiveDigitNumber = "55555";
		
		// Test six digit number
		testSixDigitNumber = "666666";
		
		// Test seven digit number
		testSevenDigitNumber = "1234567";
		
		// Test eight digit number
		testEightDigitNumber = "87654321";
		
		// Test nine digit number
		testNineDigitNumber = "999666333";
		
		// Test ten digit number
		testTenDigitNumber = "0123456789";
		
		// Test 20 digit number
		testTwentyDigitNumber = "00112233445566778899";
		
		// Test 30 digit number
		testThirtyDigitNumber = "999897969492908886848280787674";
		
		// Test a small float value
		testFloat1 = "123.456";
		
		// Test a large float value
		testFloat2 = "42000.69696";
		
		// Test float value ending in .0 // Edge case
		testFloat3 = "484376.0";
		
		// Test malformed float with multiple decimals
		testBadFloat = "123.456.789";
		
		// Test a single character
		testSingleCharacter = "X";
		
		// Test short string of 3 letters
		testShortWord = "wow";
		
		// Test medium sized string of 6 letters
		testMediumWord = "patent";
		
		// Test long strong of 10 letters 
		testLongWord = "Dictionary";
		
		// Test uppercase word
		testUpperCaseWord = "ANOTHER ONE";
		
		// Test string of characters and numbers
		testWordsAndNumbers = "99BottlesOfBeer";
		
		// Test numbers with spaces in between
		testSpacesAndNumbers = "1 2 3 4 5";
		
		// Test letters with spaces in between
		testSpacesAndLetters = "q w e r t y";
		
		// Test punctuation characters like brackets and slashes
		testPunctuationCharacters = "[]';/.,\\";
		
		// Test string with quotes and numbers
		testQuotationMarksNumbers = "1\"46\"789";
		
		// Test string with quotes and letters
		testQuotationMarksLetters = "a\"bod\"fun";
		
		// Test unicode characters
		testJavaUnicode1 = "3\u2202 + 1\u2202 + 3\u2202";
		
		// Completely different characters; compared to letters and numbers
		testJavaUnicode2 = "\u1202\u1200\u1102\u1402\u2271\u0821";
		
		// Tests unicode characters, but these characters are valid numbers
		testJavaUnicode3 = "\u0032\u0033\u0034\u0035\u0036\u0037";
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		// Not Required
	}

	/**
	 * Test method for {@link com.patentconnect.tools.Parser#parseExitCode(java.lang.String)}.
	 */
	@Test
	public void testParseExitCode() {
		// fail("Not yet implemented");
				
		/*
		 * Exit code is 0 because empty string contains nothing and equals ""
		 * We consider the empty string to be a possible edge case, thus
		 *   we are testing it.
		 */
		assertEquals(Parser.parseExitCode(testEmptyString), 0);
 
		/*
		 * Exit code is 3 because the Parser sees this as a string of 
		 *   characters that are non-numeric.
		 * This test case was added on the basis of the empty string test case
		 */
		assertEquals(Parser.parseExitCode(testEmptyCharacters), 3);
		
		/*
		 * NUMERIC TESTS
		 * 
		 * For all numeric test cases, I have added a length tester to make sure
		 *   that the Parser does not accept Strings with a length less than 5 or
		 *   greater than 7. I added this extra test because the Parser checks the 
		 *   size of numeric strings. 
		 * Since size is a criteria, including it in the test cases helps with 
		 *   code reliability and robust-ability. 
		 */
				
		/*
		 * Exit code is 2 because the Parser does not accept strings
		 *   that are less than 5 characters. Even though this string is
		 *   numeric, it does not satisfy the size criteria.
		 * Length of string returns 1
		 */
		assertEquals(testOneDigitNumber.length(), 1); 
		assertEquals(Parser.parseExitCode(testOneDigitNumber), 2); 
		
		/*
		 * Exit code is 2 because the numeric value is two digits, 
		 *   and the Parser does not accept Strings with a length less
		 *   than 5 or greater than 7.
		 * Length of string returns 2
		 */
		assertEquals(testTwoDigitNumber.length(), 2); 
		assertEquals(Parser.parseExitCode(testTwoDigitNumber), 2);
		
		/*
		 * Exit code is 2 because numeric value is 3 digits
		 * Length of string returns 3
		 */
		assertEquals(testThreeDigitNumber.length(), 3); 
		assertEquals(Parser.parseExitCode(testThreeDigitNumber), 2);
		
		/*
		 * Exit code is 2 because numeric value is 4 digits
		 * Length of string returns 4
		 */
		assertEquals(testFourDigitNumber.length(), 4);
		assertEquals(Parser.parseExitCode(testFourDigitNumber), 2);
		
		/*
		 * Exit code is 1 because the string only consists of 
		 *   5 numbers. This format is accepted by the Parser.
		 * Length of string returns 5
		 */
		assertEquals(testFiveDigitNumber.length(), 5);
		assertEquals(Parser.parseExitCode(testFiveDigitNumber), 1);
		
		/*
		 * Exit code is 1 because the string only consists of 
		 *   6 numbers. This format is accepted by the Parser.
		 * Length of string returns 6
		 */
		assertEquals(testSixDigitNumber.length(), 6);
		assertEquals(Parser.parseExitCode(testSixDigitNumber), 1);
		
		/*
		 * Exit code is 1 because the string only consists of 
		 *   7 numbers. This format is accepted by the Parser.
		 * Length of string returns 7
		 */
		assertEquals(testSevenDigitNumber.length(), 7);
		assertEquals(Parser.parseExitCode(testSevenDigitNumber), 1);
		
		/*
		 * Exit code is 2 because the string consists of 8 numbers, which 
		 *   is greater than 7; the maximum
		 * Length of string returns 8
		 */
		assertEquals(testEightDigitNumber.length(), 8);
		assertEquals(Parser.parseExitCode(testEightDigitNumber), 2);
		
		/*
		 * Exit code is 2 because the string consists of 9 numbers, which
		 *   is greater than 7; the maximum
		 * Length of string is 9
		 */
		assertEquals(testNineDigitNumber.length(), 9);
		assertEquals(Parser.parseExitCode(testNineDigitNumber), 2);
		
		/*
		 * Exit code is 2 because the string is too big and does
		 *   not match the Patent ID data, even though the String
		 *   only contains numbers.
		 * Length of string is 10
		 */
		assertEquals(testTenDigitNumber.length(), 10);
		assertEquals(Parser.parseExitCode(testTenDigitNumber), 2);
		
		/*
		 * Exit code is 2 because the string is too big and does
		 *   not match the Patent ID data, even though the String
		 *   only contains numbers.
		 * Length of string is 20
		 */
		assertEquals(testTwentyDigitNumber.length(), 20);
		assertEquals(Parser.parseExitCode(testTwentyDigitNumber), 2);
		
		/*
		 * Exit code is 2 because the string is too big and does
		 *   not match the Patent ID data, even though the String
		 *   only contains numbers.
		 * Length of string is 30
		 */
		assertEquals(testThirtyDigitNumber.length(), 30);
		assertEquals(Parser.parseExitCode(testTwentyDigitNumber), 2);
		
		/*
		 * FLOATING POINT TESTS
		 * 
		 * The following tests are done on floating point numbers.
		 * I added these test cases because when working with Integers,
		 *   it is good practice to handle cases with floating point values.
		 * For instance, 1 and 1.0 are the same number, numerically, but are
		 *   seen differently by the computer. Hence, it is a great idea to 
		 *   test floating point numbers.
		 * The length was not tested, because floats are not accepted by the
		 *   Parser, because Patent IDs are integers.
		 */
		
		/*
		 * Exit code is 3 because the Parser sees this as a non-integer,
		 *   and any non-integer is a bad String - for the Parser.
		 */
		assertEquals(Parser.parseExitCode(testFloat1), 3);


		/*
		 * Exit code is 3 because the Parser sees this as a non-integer,
		 *   and any non-integer is a bad String - for the Parser.
		 */
		assertEquals(Parser.parseExitCode(testFloat2), 3);
		
		/*
		 * Exit code is 3 because the Parser sees this as a non-integer,
		 *   and any non-integer is a bad String. Even though this number
		 *   is the same as its Integer counterpart, 484376, they are 
		 *   not equivalent.
		 * We consider this to be an edge case.
		 */
		assertEquals(Parser.parseExitCode(testFloat3), 3);
		
		/*
		 * Exit code is 3 because the Parser sees this as a String, not
		 *   a floating point value, or anything else. Since it is a String,
		 *   an exit code of 3 is generated and returned by the Parser.
		 */
		assertEquals(Parser.parseExitCode(testBadFloat), 3);
		
		/*
		 * TEST CASES FOR STRINGS
		 * 
		 * The following test cases cover strings of all kinds and sizes;
		 *   small strings, big strings, strings with spaces, numbers, etc. 
		 *   are all covered. We even test Unicodes, which are considered
		 *   edge cases. Punctuation symbols are also tested.
		 *   
		 * The exit code for all of the strings below are 3, because none
		 *   of them are Integers. All kinds of characters are tested, including
		 *   symbols. Strings with letters and numbers are also tested, and they
		 *   return an exit code of 3.
		 *   
		 * However, the last test is a special case. See below for more info.
		 */
		assertEquals(Parser.parseExitCode(testSingleCharacter), 3);
		assertEquals(Parser.parseExitCode(testShortWord), 3);
		assertEquals(Parser.parseExitCode(testMediumWord), 3);
		assertEquals(Parser.parseExitCode(testLongWord), 3);
		assertEquals(Parser.parseExitCode(testUpperCaseWord), 3);
		assertEquals(Parser.parseExitCode(testWordsAndNumbers), 3);
		assertEquals(Parser.parseExitCode(testSpacesAndNumbers), 3);
		assertEquals(Parser.parseExitCode(testSpacesAndLetters), 3);
		assertEquals(Parser.parseExitCode(testPunctuationCharacters), 3);
		assertEquals(Parser.parseExitCode(testQuotationMarksNumbers), 3);
		assertEquals(Parser.parseExitCode(testJavaUnicode1), 3);
		assertEquals(Parser.parseExitCode(testJavaUnicode2), 3);
		
		/*
		 * The Unicode characters in this String pertain to numbers. At a first glance,
		 *   one would assume that this is a String and fails to be parsed into a number.
		 * However, the unicodes are converted into numbers and then parsed. Thus,
		 *   the String is the correct format, size and is valid. This is an edge case.
		 * An exit code if 1 is generated and returned. 
		 */
		assertEquals(Parser.parseExitCode(testJavaUnicode3), 1);
		
		/*
		 * The following test cases are for Exit Code 4 (EC4). This is a special case of 
		 *   the Parser. Exit Code 4 is defined to be a 'Hail Mary'. In other words, 
		 *   once all other options are exhausted and nothing is left, Exit Code 4 
		 *   is returned. EC4 was added to improve code robust-ability and reliability.
		 *   It is only returned when something goes horribly wrong. Normally, EC4
		 *   is not to be returned. However, it is not dead code. The chance of EC4
		 *   occurring is infinitesimally small, but still possible. EC4 is an edge case.
		 *     
		 * Since EC4 is almost never returned, we test EC4 by comparing the return
		 *   status of all strings against it. In other words, we make sure that strings that
		 *   behave in an expected way, do not behave in an unexpected way. For example,
		 *   if a String, X, always returns 2, then we check to see if 2 is not equal to 4.
		 *   We repeat this for many many test cases. If a test case fails, then we know that
		 *   EC4 was generated and something went horribly wrong. 
		 * 
		 * All testStrings were used to test EC4. This is because EC4 is an extreme edge case.
		 *   We don't know what or how it will be generated, just that it is possible. Therefore,
		 *   we thought it was best to test everything. 
		 */
		assertNotEquals(Parser.parseExitCode(testEmptyString), 4);
		assertNotEquals(Parser.parseExitCode(testEmptyCharacters), 4);
		assertNotEquals(Parser.parseExitCode(testOneDigitNumber), 4);
		assertNotEquals(Parser.parseExitCode(testTwoDigitNumber), 4);
		assertNotEquals(Parser.parseExitCode(testThreeDigitNumber), 4);
		assertNotEquals(Parser.parseExitCode(testFourDigitNumber), 4);
		assertNotEquals(Parser.parseExitCode(testFiveDigitNumber), 4);
		assertNotEquals(Parser.parseExitCode(testSixDigitNumber), 4);
		assertNotEquals(Parser.parseExitCode(testSevenDigitNumber), 4);
		assertNotEquals(Parser.parseExitCode(testEightDigitNumber), 4);
		assertNotEquals(Parser.parseExitCode(testNineDigitNumber), 4);
		assertNotEquals(Parser.parseExitCode(testTenDigitNumber), 4);
		assertNotEquals(Parser.parseExitCode(testTwentyDigitNumber), 4);
		assertNotEquals(Parser.parseExitCode(testThirtyDigitNumber), 4);
		assertNotEquals(Parser.parseExitCode(testFloat1), 4);
		assertNotEquals(Parser.parseExitCode(testFloat2), 4);
		assertNotEquals(Parser.parseExitCode(testFloat3), 4);
		assertNotEquals(Parser.parseExitCode(testSingleCharacter), 4);
		assertNotEquals(Parser.parseExitCode(testShortWord), 4);
		assertNotEquals(Parser.parseExitCode(testMediumWord), 4);
		assertNotEquals(Parser.parseExitCode(testLongWord), 4);
		assertNotEquals(Parser.parseExitCode(testUpperCaseWord), 4);
		assertNotEquals(Parser.parseExitCode(testWordsAndNumbers), 4);
		assertNotEquals(Parser.parseExitCode(testSpacesAndNumbers), 4);
		assertNotEquals(Parser.parseExitCode(testSpacesAndLetters), 4);
		assertNotEquals(Parser.parseExitCode(testPunctuationCharacters), 4);
		assertNotEquals(Parser.parseExitCode(testQuotationMarksNumbers), 4);
		assertNotEquals(Parser.parseExitCode(testJavaUnicode1), 4);
		assertNotEquals(Parser.parseExitCode(testJavaUnicode2), 4);
	}
}
