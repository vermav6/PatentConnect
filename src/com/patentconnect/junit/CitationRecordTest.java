package com.patentconnect.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.patentconnect.backend.db.CitationRecord;

/**
 * JUnit Test Class For CitationRecord.java
 * 
 * @author Jatin Chowdhary
 */
public class CitationRecordTest {
	
	// Declares Strings that will represent sample Patents
	String testPatent0000001;
	String testPatent0000002;
	String testPatent0000003;
	String testPatent0000004;
	String testPatent0000005;
	String testPatent0000006;
	String testPatent0000007;
	String testPatent0000008;
	String testPatent0000009;
	String testPatent0000010;
	String testPatent0000011;
	String testPatent0000012;
	String testPatent0000013;
	String testPatent0000014;
	String testPatent0000015;
	
	// Declares the CitationRecord Objects, which will use the Strings above
	CitationRecord testDirectedCitation01;
	CitationRecord testDirectedCitation02;
	CitationRecord testDirectedCitation03;
	CitationRecord testDirectedCitation04;
	CitationRecord testDirectedCitation05;
	CitationRecord testDirectedCitation06;
	CitationRecord testDirectedCitation07;
	CitationRecord testDirectedCitation08;
	CitationRecord testDirectedCitation09;
	CitationRecord testDirectedCitation10;
	CitationRecord testDirectedCitation11;
	CitationRecord testDirectedCitation12;
	CitationRecord testDirectedCitation13;
	CitationRecord testDirectedCitation14;
	CitationRecord testDirectedCitation15;
	CitationRecord testDirectedCitation16;
	CitationRecord testDirectedCitation17;
	CitationRecord testDirectedCitation18;
	CitationRecord testDirectedCitation19;
	CitationRecord testDirectedCitation20;
	CitationRecord testDirectedCitation21;
	CitationRecord testDirectedCitation22;
	CitationRecord testDirectedCitation23;
	CitationRecord testDirectedCitation24;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		// Instantiates String with sample Patent IDs
		testPatent0000001 = "0000001";
		testPatent0000002 = "0000002";
		testPatent0000003 = "0000003";
		testPatent0000004 = "0000004";
		testPatent0000005 = "0000005";
		testPatent0000006 = "0000006";
		testPatent0000007 = "0000007";
		testPatent0000008 = "0000008";
		testPatent0000009 = "0000009";
		testPatent0000010 = "0000010";
		testPatent0000011 = "0000011";
		testPatent0000012 = "0000012";
		testPatent0000013 = "0000013";
		testPatent0000014 = "0000014";
		testPatent0000015 = "0000015";
		
		// Instantiates the CitationRecord object
		testDirectedCitation01 = new CitationRecord(testPatent0000001, testPatent0000002);
		testDirectedCitation02 = new CitationRecord(testPatent0000002, testPatent0000005);
		testDirectedCitation03 = new CitationRecord(testPatent0000005, testPatent0000007);
		testDirectedCitation04 = new CitationRecord(testPatent0000005, testPatent0000006);
		testDirectedCitation05 = new CitationRecord(testPatent0000006, null);
		testDirectedCitation06 = new CitationRecord(testPatent0000001, testPatent0000003);
		testDirectedCitation07 = new CitationRecord(testPatent0000003, testPatent0000007);
		testDirectedCitation08 = new CitationRecord(testPatent0000007, testPatent0000014);
		testDirectedCitation09 = new CitationRecord(testPatent0000014, null);
		testDirectedCitation10 = new CitationRecord(testPatent0000007, testPatent0000009);
		testDirectedCitation11 = new CitationRecord(testPatent0000009, null);
		testDirectedCitation12 = new CitationRecord(testPatent0000003, testPatent0000008);
		testDirectedCitation13 = new CitationRecord(testPatent0000008, testPatent0000009);
		testDirectedCitation14 = new CitationRecord(testPatent0000001, testPatent0000004);
		testDirectedCitation15 = new CitationRecord(testPatent0000004, testPatent0000010);
		testDirectedCitation16 = new CitationRecord(testPatent0000010, testPatent0000011);
		testDirectedCitation17 = new CitationRecord(testPatent0000004, testPatent0000011);
		testDirectedCitation18 = new CitationRecord(testPatent0000011, testPatent0000013);
		testDirectedCitation19 = new CitationRecord(testPatent0000004, testPatent0000012);
		testDirectedCitation20 = new CitationRecord(testPatent0000012, testPatent0000011);
		testDirectedCitation21 = new CitationRecord(testPatent0000012, testPatent0000013);
		testDirectedCitation22 = new CitationRecord(testPatent0000013, null);
		testDirectedCitation23 = new CitationRecord(testPatent0000001, testPatent0000015);
		testDirectedCitation24 = new CitationRecord(testPatent0000015, null);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		// Not Required/Needed
	}

	/**
	 * Test method for {@link com.patentconnect.backend.db.CitationRecord#getFrom()}.
	 */
	@Test
	public void testGetFrom() {
		//fail("Not yet implemented");
		
		// Tests the Patent that cites other Patents
		assertEquals(testDirectedCitation01.getFrom(), testPatent0000001);
		assertEquals(testDirectedCitation02.getFrom(), testPatent0000002);
		assertEquals(testDirectedCitation03.getFrom(), testPatent0000005);
		assertEquals(testDirectedCitation04.getFrom(), testPatent0000005);
		assertEquals(testDirectedCitation05.getFrom(), testPatent0000006);
		assertEquals(testDirectedCitation06.getFrom(), testPatent0000001);
		assertEquals(testDirectedCitation07.getFrom(), testPatent0000003);
		assertEquals(testDirectedCitation08.getFrom(), testPatent0000007);
		assertEquals(testDirectedCitation09.getFrom(), testPatent0000014);
		assertEquals(testDirectedCitation10.getFrom(), testPatent0000007);
		assertEquals(testDirectedCitation11.getFrom(), testPatent0000009);
		assertEquals(testDirectedCitation12.getFrom(), testPatent0000003);
		assertEquals(testDirectedCitation13.getFrom(), testPatent0000008);
		assertEquals(testDirectedCitation14.getFrom(), testPatent0000001);
		assertEquals(testDirectedCitation15.getFrom(), testPatent0000004);
		assertEquals(testDirectedCitation16.getFrom(), testPatent0000010);
		assertEquals(testDirectedCitation17.getFrom(), testPatent0000004);
		assertEquals(testDirectedCitation18.getFrom(), testPatent0000011);
		assertEquals(testDirectedCitation19.getFrom(), testPatent0000004);
		assertEquals(testDirectedCitation20.getFrom(), testPatent0000012);
		assertEquals(testDirectedCitation21.getFrom(), testPatent0000012);
		assertEquals(testDirectedCitation22.getFrom(), testPatent0000013);
		assertEquals(testDirectedCitation23.getFrom(), testPatent0000001);
		assertEquals(testDirectedCitation24.getFrom(), testPatent0000015);
	}

	/**
	 * Test method for {@link com.patentconnect.backend.db.CitationRecord#getTo()}.
	 */
	@Test
	public void testGetTo() {
		//fail("Not yet implemented");
		
		// Tests the Patent that gets cited
		assertEquals(testDirectedCitation01.getTo(), testPatent0000002);
		assertEquals(testDirectedCitation02.getTo(), testPatent0000005);
		assertEquals(testDirectedCitation03.getTo(), testPatent0000007);
		assertEquals(testDirectedCitation04.getTo(), testPatent0000006);
		assertEquals(testDirectedCitation05.getTo(), null);
		assertEquals(testDirectedCitation06.getTo(), testPatent0000003);
		assertEquals(testDirectedCitation07.getTo(), testPatent0000007);
		assertEquals(testDirectedCitation08.getTo(), testPatent0000014);
		assertEquals(testDirectedCitation09.getTo(), null);
		assertEquals(testDirectedCitation10.getTo(), testPatent0000009);
		assertEquals(testDirectedCitation11.getTo(), null);
		assertEquals(testDirectedCitation12.getTo(), testPatent0000008);
		assertEquals(testDirectedCitation13.getTo(), testPatent0000009);
		assertEquals(testDirectedCitation14.getTo(), testPatent0000004);
		assertEquals(testDirectedCitation15.getTo(), testPatent0000010);
		assertEquals(testDirectedCitation16.getTo(), testPatent0000011);
		assertEquals(testDirectedCitation17.getTo(), testPatent0000011);
		assertEquals(testDirectedCitation18.getTo(), testPatent0000013);
		assertEquals(testDirectedCitation19.getTo(), testPatent0000012);
		assertEquals(testDirectedCitation20.getTo(), testPatent0000011);
		assertEquals(testDirectedCitation21.getTo(), testPatent0000013);
		assertEquals(testDirectedCitation22.getTo(), null);
		assertEquals(testDirectedCitation23.getTo(), testPatent0000015);
		assertEquals(testDirectedCitation24.getTo(), null);
	}
}