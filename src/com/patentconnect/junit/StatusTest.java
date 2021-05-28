package com.patentconnect.junit;

import static org.junit.Assert.*;
import java.awt.Color;
import org.junit.Before;
import org.junit.Test;
import com.patentconnect.tools.InformationGlobal;
import com.patentconnect.tools.Parser;

/**
 * JUnit Test Class For Status.java 
 * 
 * This JUnit class tests two functions in 'Status.java', which
 * determine the Status Message and Label Color. To improve code
 * reliability, a wide variety of strings are tested; from 
 * empty strings to long numbers to strings containing ASCII 
 * characters, random Unicode characters, floating point numbers, 
 * and everything in between. All possible exit codes and Status
 * messages are tested.
 * 
 * The Status message relies on the Parser. The exit code from
 * the Parser determines the status message. Hence, this JUnit
 * test class is very similar to 'ParserTest.java', and explains
 * why most of the test cases are identical.
 * 
 * @author Jatin Chowdhary
 */
public class StatusTest {
	
	/*
	 * Declares Strings that will store the Status Message
	 * The names are non-descriptive because the messages
	 *   can be easily changed inside 'InformtionGlobal.java',
	 *   and in case we want to add or remove Messages.
	 */
	String statusMessage0;
	String statusMessage1;
	String statusMessage2;
	String statusMessage3;
	String statusMessage4;

	/*
	 * Declares the Strings that will be tested. The String
	 * names are descriptive and self-explanatory. Rationale is
	 * explained in the 'setUp' method.
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
	
	@Before
	public void setUp() {
		
		/*
		 * Assigns the correct Status Message String from 'InformationGlobal.java'.
		 * The number at the end of the variable name corresponds to the Status
		 *   Message that is assigned to it.
		 */
		statusMessage0 = InformationGlobal.STATUS_MSG_USER_INTERFACE[0];
		statusMessage1 = InformationGlobal.STATUS_MSG_USER_INTERFACE[1];
		statusMessage2 = InformationGlobal.STATUS_MSG_USER_INTERFACE[2];
		statusMessage3 = InformationGlobal.STATUS_MSG_USER_INTERFACE[3];
		statusMessage4 = InformationGlobal.STATUS_MSG_USER_INTERFACE[4];
		
		/*
		 * Values are assigned to the testStrings (Initialization). 
		 * The name of the variable corresponds to the value
		 *   assigned to it.
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
	
	@Test
	public void testReturnStatusLabel() {
				
		/*
		 * Empty String causes Parser to return an exit code of 0, 
		 * which causes 'returnStatusLabel' to return the first 
		 * Status Message String.
		 */
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testEmptyString)), statusMessage0);
	
		/*
		 * Sequence of characters causes Parser to return an exit
		 * code of 3, which causes 'returnStatusLabel' to return the
		 * fourth Status Message String.
		 */
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testEmptyCharacters)), statusMessage3);
		
		/*
		 * Numbers between 1 and 4 (inclusive) digits result in an exit 
		 * code of 2, which causes 'returnStatusLabel' to return the 
		 * third Status Message String.
		 */
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testOneDigitNumber)), statusMessage2);
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testTwoDigitNumber)), statusMessage2);
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testThreeDigitNumber)), statusMessage2);
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testFourDigitNumber)), statusMessage2);
		
		/*
		 * Numbers between 5 and 7 (inclusive) digits result in an exit
		 * code of 1, which causes 'returnStatusLabel' to return the 
		 * second Status Message String.
		 */
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testFiveDigitNumber)), statusMessage1);
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testSixDigitNumber)), statusMessage1);
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testSevenDigitNumber)), statusMessage1);
		
		/*
		 * Numbers greater than or equal to 8 digits result in an
		 * exit code of 2, which returns the second Status Message
		 * String.
		 */
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testEightDigitNumber)), statusMessage2);
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testNineDigitNumber)), statusMessage2);
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testTenDigitNumber)), statusMessage2);
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testTwentyDigitNumber)), statusMessage2);
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testThirtyDigitNumber)), statusMessage2);
		
		/*
		 * Floats and other non-integers result in an exit code of
		 *   3, which returns the fourth Status Message String.
		 * Malformed floats also return the fourth Status Message.
		 */
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testFloat1)), statusMessage3);
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testFloat2)), statusMessage3);
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testFloat3)), statusMessage3);
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testBadFloat)), statusMessage3);

		/*
		 * Strings that are not Integers/whole numbers result in
		 * an exit code of 3, which returns the fourth Status Message
		 * String. 
		 */
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testSingleCharacter)), statusMessage3);
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testShortWord)), statusMessage3);
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testMediumWord)), statusMessage3);
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testLongWord)), statusMessage3);
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testUpperCaseWord)), statusMessage3);
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testWordsAndNumbers)), statusMessage3);
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testSpacesAndNumbers)), statusMessage3);
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testSpacesAndLetters)), statusMessage3);
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testPunctuationCharacters)), statusMessage3);
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testQuotationMarksNumbers)), statusMessage3);
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testJavaUnicode1)), statusMessage3);
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testJavaUnicode2)), statusMessage3);
		
		/*
		 * Unicode characters that represent numbers are valid inputs. 
		 * Hence, this testString is accepted by the Parser and returns 
		 * the second Status Message String.
		 */
		assertEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testJavaUnicode3)), statusMessage1);
		
		/*
		 * Every single testString is used to test the fifth Status 
		 * Message String. This is because this String is an edge case,
		 * and should not be returned unless something goes terribly 
		 * wrong. Since it is (almost) never returned, we test it by
		 * making sure that it is not generated by our testStrings.
		 */
		assertNotEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testEmptyString)), statusMessage4);
		assertNotEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testEmptyCharacters)), statusMessage4);
		assertNotEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testOneDigitNumber)), statusMessage4);
		assertNotEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testTwoDigitNumber)), statusMessage4);
		assertNotEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testThreeDigitNumber)), statusMessage4);
		assertNotEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testFourDigitNumber)), statusMessage4);
		assertNotEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testFiveDigitNumber)), statusMessage4);
		assertNotEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testSixDigitNumber)), statusMessage4);
		assertNotEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testSevenDigitNumber)), statusMessage4);
		assertNotEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testEightDigitNumber)), statusMessage4);
		assertNotEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testNineDigitNumber)), statusMessage4);
		assertNotEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testTenDigitNumber)), statusMessage4);
		assertNotEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testTwentyDigitNumber)), statusMessage4);
		assertNotEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testThirtyDigitNumber)), statusMessage4);
		assertNotEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testFloat1)), statusMessage4);
		assertNotEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testFloat2)), statusMessage4);
		assertNotEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testFloat3)), statusMessage4);
		assertNotEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testSingleCharacter)), statusMessage4);
		assertNotEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testShortWord)), statusMessage4);
		assertNotEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testMediumWord)), statusMessage4);
		assertNotEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testLongWord)), statusMessage4);
		assertNotEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testUpperCaseWord)), statusMessage4);
		assertNotEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testWordsAndNumbers)), statusMessage4);
		assertNotEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testSpacesAndNumbers)), statusMessage4);
		assertNotEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testSpacesAndLetters)), statusMessage4);
		assertNotEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testPunctuationCharacters)), statusMessage4);
		assertNotEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testQuotationMarksNumbers)), statusMessage4);
		assertNotEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testJavaUnicode1)), statusMessage4);
		assertNotEquals(InformationGlobal.returnStatusLabel(Parser.parseExitCode(testJavaUnicode2)), statusMessage4);
	}
	
	@Test
	public void testReturnLabelColor() {
		
		/*
		 * Empty text field (empty string) changes the color of
		 * the Label to Gray.
		 */
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testEmptyString)), Color.GRAY);
	
		/*
		 * A sequence of characters causes Parser to return an exit
		 * code of 3, which changes the Label color to Red.
		 */
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testEmptyCharacters)), Color.RED);
		
		/*
		 * Integers between 1 and 4 (inclusive) digits are invalid 
		 * Patent IDs, and change the color of the Label to Red.
		 */
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testOneDigitNumber)), Color.RED);
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testTwoDigitNumber)), Color.RED);
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testThreeDigitNumber)), Color.RED);
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testFourDigitNumber)), Color.RED);
		
		/*
		 * Integers between 5 and 7 (inclusive) digits are valid
		 * Patent IDs and change the color of the Label to Red.
		 */
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testFiveDigitNumber)), Color.GREEN);
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testSixDigitNumber)), Color.GREEN);
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testSevenDigitNumber)), Color.GREEN);
		
		/*
		 * Integers greater than or equal to 8 digits are invalid 
		 * Patent IDs, and change the color of the Label to Red.
		 */
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testEightDigitNumber)), Color.RED);
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testNineDigitNumber)), Color.RED);
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testTenDigitNumber)), Color.RED);
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testTwentyDigitNumber)), Color.RED);
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testThirtyDigitNumber)), Color.RED);
		
        /*
         * Floating point numbers are invalid Patent IDs and change
         * the color of the Label to Red.
         */
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testFloat1)), Color.RED);
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testFloat2)), Color.RED);
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testFloat3)), Color.RED);
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testBadFloat)), Color.RED);

        /*
         * Strings consisting of non-numeric characters are invalid
         *   by default and change the color of the Label to Red.
         * Letters, symbols, punctuation, and similar characters are
         *   not accepted by the Parser.
         */
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testSingleCharacter)), Color.RED);
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testShortWord)), Color.RED);
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testMediumWord)), Color.RED);
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testLongWord)), Color.RED);
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testUpperCaseWord)), Color.RED);
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testWordsAndNumbers)), Color.RED);
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testSpacesAndNumbers)), Color.RED);
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testSpacesAndLetters)), Color.RED);
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testPunctuationCharacters)), Color.RED);
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testQuotationMarksNumbers)), Color.RED);
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testJavaUnicode1)), Color.RED);
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testJavaUnicode2)), Color.RED);

		/*
		 * Java Unicode Characters are converted into numbers.
		 * Hence, the testString below is accepted as valid and
		 *   changes the color of the Label to Green.
		 */
		assertEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testJavaUnicode3)), Color.GREEN);
		
        /*
         * The Label's color should not change to Black under any 
         *   (normal) circumstance. If it does change to black, then
         *   something has gone awry. 
         * The only way to test this is to make sure that the Label's
         *   color is never changed to Black, by testing it against all
         *   the testStrings.
         */
		assertNotEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testEmptyString)), Color.BLACK);
		assertNotEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testEmptyCharacters)), Color.BLACK);
		assertNotEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testOneDigitNumber)), Color.BLACK);
		assertNotEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testTwoDigitNumber)), Color.BLACK);
		assertNotEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testThreeDigitNumber)), Color.BLACK);
		assertNotEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testFourDigitNumber)), Color.BLACK);
		assertNotEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testFiveDigitNumber)), Color.BLACK);
		assertNotEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testSixDigitNumber)), Color.BLACK);
		assertNotEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testSevenDigitNumber)), Color.BLACK);
		assertNotEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testEightDigitNumber)), Color.BLACK);
		assertNotEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testNineDigitNumber)), Color.BLACK);
		assertNotEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testTenDigitNumber)), Color.BLACK);
		assertNotEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testTwentyDigitNumber)), Color.BLACK);
		assertNotEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testThirtyDigitNumber)), Color.BLACK);
		assertNotEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testFloat1)), Color.BLACK);
		assertNotEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testFloat2)), Color.BLACK);
		assertNotEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testFloat3)), Color.BLACK);
		assertNotEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testSingleCharacter)), Color.BLACK);
		assertNotEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testShortWord)), Color.BLACK);
		assertNotEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testMediumWord)), Color.BLACK);
		assertNotEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testLongWord)), Color.BLACK);
		assertNotEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testUpperCaseWord)), Color.BLACK);
		assertNotEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testWordsAndNumbers)), Color.BLACK);
		assertNotEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testSpacesAndNumbers)), Color.BLACK);
		assertNotEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testSpacesAndLetters)), Color.BLACK);
		assertNotEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testPunctuationCharacters)), Color.BLACK);
		assertNotEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testQuotationMarksNumbers)), Color.BLACK);
		assertNotEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testJavaUnicode1)), Color.BLACK);
		assertNotEquals(InformationGlobal.returnLabelColor(Parser.parseExitCode(testJavaUnicode2)), Color.BLACK);
	}
}