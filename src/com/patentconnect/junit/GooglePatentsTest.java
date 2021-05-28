package com.patentconnect.junit;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.patentconnect.backend.googleAPI.GooglePatents;

/**
 * JUnit Test Class For GooglePatents
 * 
 * @author Jatin Chowdhary
 */
public class GooglePatentsTest { 
	
	// Beginning of the URL for the Google Patent web page
	private static final String PATENT_URL_START = "https://patents.google.com/patent/US";
	
	// Ending of the URL for the Google Patent web page
	private static final String PATENT_URL_END   = "/en";
	
	/*
	 * When Patent's do not have an abstract section, the following
	 *   String will be used for its description box
	 * Among our test cases, this is only for the following patents:
	 *   2, 6, 7, 8, 9, 10
	 */
	private static final String NO_DESCRIPTION_AVAILABLE = "No Description Available";
	
	/*
	 * Declares 11 Strings; they will store Patent IDs.
	 * These IDs are from our Patent data set. 
	 */
	String testPatentDB0;
	String testPatentDB1;
	String testPatentDB2;
	String testPatentDB3;
	String testPatentDB4;
	String testPatentDB5;
	String testPatentDB6;
	String testPatentDB7;
	String testPatentDB8;
	String testPatentDB9;
	String testPatentDB10;
	
	// Declares 11 GooglePatents Objects.
	GooglePatents testGooglePatent0;
	GooglePatents testGooglePatent1;
	GooglePatents testGooglePatent2;
	GooglePatents testGooglePatent3;
	GooglePatents testGooglePatent4;
	GooglePatents testGooglePatent5;
	GooglePatents testGooglePatent6;
	GooglePatents testGooglePatent7;
	GooglePatents testGooglePatent8;
	GooglePatents testGooglePatent9;
	GooglePatents testGooglePatent10;
	
	/*
	 * Declares Integers that will store the number of 
	 * pictures the patent has on its web page.
	 */
	int numberOfPatentPictures0;
	int numberOfPatentPictures1;
	int numberOfPatentPictures2;
	int numberOfPatentPictures3;
	int numberOfPatentPictures4;
	int numberOfPatentPictures5;
	int numberOfPatentPictures6;
	int numberOfPatentPictures7;
	int numberOfPatentPictures8;
	int numberOfPatentPictures9;
	int numberOfPatentPictures10;
	
	// Declares Strings that will store the Patent's name.
	String testPatentName0;
	String testPatentName1;
	String testPatentName2;
	String testPatentName3;
	String testPatentName4;
	String testPatentName5;
	String testPatentName6;
	String testPatentName7;
	String testPatentName8;
	String testPatentName9;
	String testPatentName10;
	
	// Declares Strings that will store the Patent's description.
	String testPatentDescription0;
	String testPatentDescription1;
	String testPatentDescription3;
	String testPatentDescription4;
	String testPatentDescription5;
	
	// Declares Strings that will store the Patent's URL.
	String testPatentURL0;
	String testPatentURL1;
	String testPatentURL2;
	String testPatentURL3;
	String testPatentURL4;
	String testPatentURL5;
	String testPatentURL6;
	String testPatentURL7;
	String testPatentURL8;
	String testPatentURL9;
	String testPatentURL10;
	
	// Declares Strings that will represent invalid patent IDs.
	String testInvalidPatent0;
	String testInvalidPatent1;
	String testInvalidPatent2;
	String testInvalidPatent3;
	String testInvalidPatent4;
	String testInvalidPatent5;
	String testInvalidPatent6;
	String testInvalidPatent7;
	String testInvalidPatent8;
	String testInvalidPatent9;
	String testInvalidPatent10;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		/*
		 * Initializes Patent IDs that will be used for testing.
		 * These IDs are from our Patent data set.
		 */
		testPatentDB0 = "3858242";
		testPatentDB1 = "4667814";
		testPatentDB2 = "2706117";
		testPatentDB3 = "7449629";
		testPatentDB4 = "3858241";
		testPatentDB5 = "3858436";
		testPatentDB6 = "1377626";
		testPatentDB7 = "3140130";
		testPatentDB8 = "3111777";
		testPatentDB9 = "835150";
		
		/*
		 * This Patent is an edge case. This is because it is very
		 *   old and contains almost no information, other than a
		 *   name; it has no picture, description, citation, etc.
		 * We found it important to test this Patent because of its
		 *   sparse details.
		 */
		testPatentDB10 = "2072303";
		
		// Instantiates GooglePatents Objects using Patent IDs.
		testGooglePatent0  = new GooglePatents(testPatentDB0);
		testGooglePatent1  = new GooglePatents(testPatentDB1);
		testGooglePatent2  = new GooglePatents(testPatentDB2);
		testGooglePatent3  = new GooglePatents(testPatentDB3);
		testGooglePatent4  = new GooglePatents(testPatentDB4);
		testGooglePatent5  = new GooglePatents(testPatentDB5);
		testGooglePatent6  = new GooglePatents(testPatentDB6);
		testGooglePatent7  = new GooglePatents(testPatentDB7);
		testGooglePatent8  = new GooglePatents(testPatentDB8);
		testGooglePatent9  = new GooglePatents(testPatentDB9);
		testGooglePatent10 = new GooglePatents(testPatentDB10);
	
		/*
		 * Stores the number of pictures the Patent has on its
		 *   Google Patents web page, as an Integer. In addition, 
		 *   the thumb-nails and full-sized pictures are downloaded 
		 *   and stored in 'tempAssets'.
		 * This will be used to directly test the 'arePicturesAvailable'
		 *   function, and indirectly test the 'downLoadPictures'
		 *   function. 
		 */
		numberOfPatentPictures0  = testGooglePatent0.downLoadPictures();
		numberOfPatentPictures1  = testGooglePatent1.downLoadPictures();
		numberOfPatentPictures2  = testGooglePatent2.downLoadPictures();
		numberOfPatentPictures3  = testGooglePatent3.downLoadPictures();
		numberOfPatentPictures4  = testGooglePatent4.downLoadPictures();
		numberOfPatentPictures5  = testGooglePatent5.downLoadPictures();
		numberOfPatentPictures6  = testGooglePatent6.downLoadPictures();
		numberOfPatentPictures7  = testGooglePatent7.downLoadPictures();
		numberOfPatentPictures8  = testGooglePatent8.downLoadPictures();
		numberOfPatentPictures9  = testGooglePatent9.downLoadPictures();
		numberOfPatentPictures10 = testGooglePatent10.downLoadPictures();
		
		/*
		 * Initializes Patent names. The names are hard-coded and
		 * will be used to test the 'getName' function.
		 */
		testPatentName0  = "Hand gun bullet proof face shield";
		testPatentName1  = "Oxygen absorbent packet";
		testPatentName2  = "Playing card shuffling device";
		testPatentName3  = "Solar panel including a low moisture vapor transmission rate adhesive composition";
		testPatentName4  = "Shock absorbent collar for armor plate";
		testPatentName5  = "Vehicle brake leak testing system and method";
		testPatentName6  = "Folding door";
		testPatentName7  = "Keyed segmented race rings and improved method of making same";
		testPatentName8  = "Harvester";
		testPatentName9  = "Whip-handle.";
		testPatentName10 = "Artificial threads, bands, tubes, and the like for surgical and other purposes";
	
		/*
		 * Initializes Patent descriptions. The descriptions are 
		 * hard-coded and will be used to test the 'getDescription' 
		 * function.
		 */
		testPatentDescription0 = "A protective face shield which consists of layers of high impact resilient plastic maintained in spaced relationship"
		    + " to each other by a metal frame so that a dead air space is provided between the layers incorporating"
		    + " various combinations of wall thickness and spacings employed to adapt the face guard to a specific application."
		    + " The face shield is pivotally mounted in a unique manner on a rigid helmet and provides protection from .38 caliber"
		    + " and smaller bullets fired from hand guns at close range.";
	
		testPatentDescription1 = "In an oxygen absorbent packet, an oxygen absorbent containing moisture is received in the cup like plastic container"
		    + " and an air-permeable layer made of paper is adhered to the opening section of the container."
			+ " A substantially air-impermeable layer is formed on the outer surface of the air-permeable layer,"
			+ " whereby the oxygen absorbent absorbs oxygen through the peripheral side edge of the air-permeable layer.";
	
		testPatentDescription3 = "A solar panel including a photovoltaic material layer; a backing panel; and an adhesive layer disposed between and"
			+ " adhering together the photovoltaic material layer and the backing panel, in which the adhesive layer comprises"
			+ " an adhesive composition, the adhesive composition comprising a low MVTR polymer or copolymer and a silane-modified polymer or copolymer."
			+ " In another embodiment, the solar panel includes module wire openings which are filled by an adhesive composition comprising a low MVTR polymer"
			+ " or copolymer and a silane-modified polymer or copolymer. A method of making the solar panel is provided.";
	
		testPatentDescription4 = "A shock absorbent collar for a protective torso armor plate for human beings made of expanded plastic material."
			+ " The expanded plastic is crushable and, therefore, impact absorbing. The collar protects the neck, chin, and face"
			+ " or other portions of the head of the wearer of the armor plate in case of sudden deceleration of the body of the wearer of the armor plate,"
			+ " which would shift upwardly in such event and in the absence of the collar would strike"
			+ " the neck or chin or other parts of the head of the wearer with damaging force.";
	
		testPatentDescription5 = "A system for leak testing a closed vehicle brake system by actuating pistons in a master cylinder."
			+ " A fluid motor in a separate pneumatic plumbing circuit is automatically sequentially actuated extending"
			+ " a plunger engaging the pistons to pressurize the brake fluid in various degress as prescribed in a predetermined test procedure."
			+ " A solid state electrical control circuit automatically sequences valves in the pneumatic fluid circuit providing"
			+ " desired brake fluid pressures during the leak test. The control circuit includes a digital converter applying a voltage"
			+ " determined by the position of the master cylinder piston at the beginning of a test time period which is continuously"
			+ " used as a reference in opposiion to a position responsive voltage regulated by the linear potentiometer during the test."
			+ " The control circuit can include a selector switch to operate one of a plurality of separate pneumatic plumbing circuits,"
			+ " each being suited for a particular master cylinder design. In this manner, fluid leakage is accurately and immediately"
			+ " sensed and a fault signal is energized and the test aborted when unacceptable leakage occurs.";
		
		/*
		 * Initializes Patent URLs. The URLs are hard-coded and
		 * will be used to test the 'getURL' function.
		 */
		testPatentURL0  = PATENT_URL_START + testPatentDB0  + PATENT_URL_END; 
		testPatentURL1  = PATENT_URL_START + testPatentDB1  + PATENT_URL_END; 
		testPatentURL2  = PATENT_URL_START + testPatentDB2  + PATENT_URL_END; 
		testPatentURL3  = PATENT_URL_START + testPatentDB3  + PATENT_URL_END; 
		testPatentURL4  = PATENT_URL_START + testPatentDB4  + PATENT_URL_END; 
		testPatentURL5  = PATENT_URL_START + testPatentDB5  + PATENT_URL_END; 
		testPatentURL6  = PATENT_URL_START + testPatentDB6  + PATENT_URL_END; 
		testPatentURL7  = PATENT_URL_START + testPatentDB7  + PATENT_URL_END; 
		testPatentURL8  = PATENT_URL_START + testPatentDB8  + PATENT_URL_END; 
		testPatentURL9  = PATENT_URL_START + testPatentDB9  + PATENT_URL_END; 
		testPatentURL10 = PATENT_URL_START + testPatentDB10 + PATENT_URL_END; 
		
		/*
		 * Initializes invalid Patent IDs. These IDs are invalid, 
		 *   and the Patents do not exist on Google Patents.
		 * This will be used to test the 'isValidPatent' function.
		 */
		testInvalidPatent0  = null;
		testInvalidPatent1  = "420420.0";
		testInvalidPatent2  = "696969696969";
		testInvalidPatent3  = "0.42";
		testInvalidPatent4  = "bullet_patent";
		testInvalidPatent5  = "343554ABC";
		testInvalidPatent6  = "       ";
		testInvalidPatent7  = "8 5 2 1 4 5 6 9 8";
		testInvalidPatent8  = "$5598745";
		testInvalidPatent9  = "@9541254";
		testInvalidPatent10 = "kevlar_vest";
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {

		/*
		 * Deletes Patent thumb-nails and full-sized pictures in 
		 * 'tempAssets'- at least the ones that were created by this 
		 * JUnit Test class
		 */
		GooglePatents.deletePictures(testPatentDB0, numberOfPatentPictures0);
	    GooglePatents.deletePictures(testPatentDB1, numberOfPatentPictures1);
	    GooglePatents.deletePictures(testPatentDB2, numberOfPatentPictures2);
	    GooglePatents.deletePictures(testPatentDB3, numberOfPatentPictures3);
	    GooglePatents.deletePictures(testPatentDB4, numberOfPatentPictures4);
	    GooglePatents.deletePictures(testPatentDB5, numberOfPatentPictures5);
	    GooglePatents.deletePictures(testPatentDB6, numberOfPatentPictures6);
	    GooglePatents.deletePictures(testPatentDB7, numberOfPatentPictures7);
	    GooglePatents.deletePictures(testPatentDB8, numberOfPatentPictures8);
	    GooglePatents.deletePictures(testPatentDB9, numberOfPatentPictures9);
	    GooglePatents.deletePictures(testPatentDB10, numberOfPatentPictures10);
	}

	/**
	 * Test method for {@link com.patentconnect.backend.googleAPI.GooglePatents#getPatentID()}.
	 */
	@Test
	public void testGetPatentID() {
		//fail("Not yet implemented");
		
		/*
		 * Tests if the Patent ID fetched from Google Patents is equal
		 * to the hard-coded Patent ID defined above.
		 */
		assertEquals(testPatentDB0, testGooglePatent0.getPatentID());
		assertEquals(testPatentDB1, testGooglePatent1.getPatentID());
		assertEquals(testPatentDB2, testGooglePatent2.getPatentID());
		assertEquals(testPatentDB3, testGooglePatent3.getPatentID());
		assertEquals(testPatentDB4, testGooglePatent4.getPatentID());
		assertEquals(testPatentDB5, testGooglePatent5.getPatentID());
		assertEquals(testPatentDB6, testGooglePatent6.getPatentID());
		assertEquals(testPatentDB7, testGooglePatent7.getPatentID());
		assertEquals(testPatentDB8, testGooglePatent8.getPatentID());
		assertEquals(testPatentDB9, testGooglePatent9.getPatentID());
		assertEquals(testPatentDB10, testGooglePatent10.getPatentID());
	}

	/**
	 * Test method for {@link com.patentconnect.backend.googleAPI.GooglePatents#getName()}.
	 */
	@Test
	public void testGetName() {
		//fail("Not yet implemented");
		
		/*
		 * Tests if the Patent name fetched from Google Patents is
		 * equal to the hard-coded Patent name defined above.
		 */
		assertEquals(testPatentName0, testGooglePatent0.getName());
		assertEquals(testPatentName1, testGooglePatent1.getName());
		assertEquals(testPatentName2, testGooglePatent2.getName());
		assertEquals(testPatentName3, testGooglePatent3.getName());
		assertEquals(testPatentName4, testGooglePatent4.getName());
		assertEquals(testPatentName5, testGooglePatent5.getName());
		assertEquals(testPatentName6, testGooglePatent6.getName());
		assertEquals(testPatentName7, testGooglePatent7.getName());
		assertEquals(testPatentName8, testGooglePatent8.getName());
		assertEquals(testPatentName9, testGooglePatent9.getName());
		assertEquals(testPatentName10, testGooglePatent10.getName());
	}

	/**
	 * Test method for {@link com.patentconnect.backend.googleAPI.GooglePatents#getDescription()}.
	 */
	@Test
	public void testGetDescription() {
		//fail("Not yet implemented");
		
		/*
		 * The following Patents have a description on their
		 * Google Patents web page. The description retrieved is
		 * checked with the hard-coded description.
		 */
		assertEquals(testPatentDescription0, testGooglePatent0.getDescription());
		assertEquals(testPatentDescription1, testGooglePatent1.getDescription());
		assertEquals(testPatentDescription3, testGooglePatent3.getDescription());
		assertEquals(testPatentDescription4, testGooglePatent4.getDescription());
		assertEquals(testPatentDescription5, testGooglePatent5.getDescription());
		
		/*
		 * The following Patents have no description on their 
		 * Google Patents web page. When this occurs, the 
		 * description is set to "No Description Available".
		 */
		assertEquals(NO_DESCRIPTION_AVAILABLE, testGooglePatent2.getDescription());
		assertEquals(NO_DESCRIPTION_AVAILABLE, testGooglePatent6.getDescription());
		assertEquals(NO_DESCRIPTION_AVAILABLE, testGooglePatent7.getDescription());
		assertEquals(NO_DESCRIPTION_AVAILABLE, testGooglePatent8.getDescription());
		assertEquals(NO_DESCRIPTION_AVAILABLE, testGooglePatent9.getDescription());
		assertEquals(NO_DESCRIPTION_AVAILABLE, testGooglePatent10.getDescription());
	}

	/**
	 * Test method for {@link com.patentconnect.backend.googleAPI.GooglePatents#getURL()}.
	 */
	@Test
	public void testGetURL() {
		//fail("Not yet implemented");
		
		// Tests the 'getURL' function for each Patent.
		assertEquals(testPatentURL0, testGooglePatent0.getURL());
		assertEquals(testPatentURL1, testGooglePatent1.getURL());
		assertEquals(testPatentURL2, testGooglePatent2.getURL());
		assertEquals(testPatentURL3, testGooglePatent3.getURL());
		assertEquals(testPatentURL4, testGooglePatent4.getURL());
		assertEquals(testPatentURL5, testGooglePatent5.getURL());
		assertEquals(testPatentURL6, testGooglePatent6.getURL());
		assertEquals(testPatentURL7, testGooglePatent7.getURL());
		assertEquals(testPatentURL8, testGooglePatent8.getURL());
		assertEquals(testPatentURL9, testGooglePatent9.getURL());
		assertEquals(testPatentURL10, testGooglePatent10.getURL());
	}

	/**
	 * Test method for {@link com.patentconnect.backend.googleAPI.GooglePatents#arePicturesAvailable()}.
	 * @throws IOException 
	 */
	@Test
	public void testArePicturesAvailable() throws IOException {
		//fail("Not yet implemented");
		
		// Tests if the Patent's pictures are available in 'tempAssets'.
		assertEquals(testGooglePatent0.arePicturesAvailable(), true);
		assertEquals(testGooglePatent1.arePicturesAvailable(), true);
		assertEquals(testGooglePatent2.arePicturesAvailable(), true);
		assertEquals(testGooglePatent3.arePicturesAvailable(), true);
		assertEquals(testGooglePatent4.arePicturesAvailable(), true);
		assertEquals(testGooglePatent5.arePicturesAvailable(), true);
		assertEquals(testGooglePatent6.arePicturesAvailable(), true);
		assertEquals(testGooglePatent7.arePicturesAvailable(), true);
		assertEquals(testGooglePatent8.arePicturesAvailable(), true);
		assertEquals(testGooglePatent9.arePicturesAvailable(), true);
		assertEquals(testGooglePatent10.arePicturesAvailable(), false);
	}

	/**
	 * Test method for {@link com.patentconnect.backend.googleAPI.GooglePatents#getURLFromPatentID(java.lang.String)}.
	 */
	@Test
	public void testGetURLFromPatentID() {
		//fail("Not yet implemented");
		
		/*
		 * Creates a Google Patents link from the Patent ID and compares it 
		 * with the URL fetched from the GooglePatents Object.
		 */
		assertEquals(GooglePatents.getURLFromPatentID(testPatentDB0), testGooglePatent0.getURL());
		assertEquals(GooglePatents.getURLFromPatentID(testPatentDB1), testGooglePatent1.getURL());
		assertEquals(GooglePatents.getURLFromPatentID(testPatentDB2), testGooglePatent2.getURL());
		assertEquals(GooglePatents.getURLFromPatentID(testPatentDB3), testGooglePatent3.getURL());
		assertEquals(GooglePatents.getURLFromPatentID(testPatentDB4), testGooglePatent4.getURL());
		assertEquals(GooglePatents.getURLFromPatentID(testPatentDB5), testGooglePatent5.getURL());
		assertEquals(GooglePatents.getURLFromPatentID(testPatentDB6), testGooglePatent6.getURL());
		assertEquals(GooglePatents.getURLFromPatentID(testPatentDB7), testGooglePatent7.getURL());
		assertEquals(GooglePatents.getURLFromPatentID(testPatentDB8), testGooglePatent8.getURL());
		assertEquals(GooglePatents.getURLFromPatentID(testPatentDB9), testGooglePatent9.getURL());
		assertEquals(GooglePatents.getURLFromPatentID(testPatentDB10), testGooglePatent10.getURL());
	}

	/**
	 * Test method for {@link com.patentconnect.backend.googleAPI.GooglePatents#isValidPatent(java.lang.String)}.
	 */
	@Test
	public void testIsValidPatent() {
		//fail("Not yet implemented");
		
		/*
		 * The following tests are done on valid Patent IDs. Hence, 
		 * it should always return true. The Patents IDs are from
		 * our data set.
		 */
		assertTrue(GooglePatents.isValidPatent(testPatentDB0));
		assertTrue(GooglePatents.isValidPatent(testPatentDB1));
		assertTrue(GooglePatents.isValidPatent(testPatentDB2));
		assertTrue(GooglePatents.isValidPatent(testPatentDB3));
		assertTrue(GooglePatents.isValidPatent(testPatentDB4));
		assertTrue(GooglePatents.isValidPatent(testPatentDB5));
		assertTrue(GooglePatents.isValidPatent(testPatentDB6));
		assertTrue(GooglePatents.isValidPatent(testPatentDB7));
		assertTrue(GooglePatents.isValidPatent(testPatentDB8));
		assertTrue(GooglePatents.isValidPatent(testPatentDB9));
		assertTrue(GooglePatents.isValidPatent(testPatentDB10));
		
		/*
		 * The following tests are done on invalid Patent IDs, and
		 * it should always return false - unless the invalid Patent
		 * ID gets registered on Google Patents.
		 */
		assertFalse(GooglePatents.isValidPatent(testInvalidPatent0));
		assertFalse(GooglePatents.isValidPatent(testInvalidPatent1));
		assertFalse(GooglePatents.isValidPatent(testInvalidPatent2));
		assertFalse(GooglePatents.isValidPatent(testInvalidPatent3));
		assertFalse(GooglePatents.isValidPatent(testInvalidPatent4));
		assertFalse(GooglePatents.isValidPatent(testInvalidPatent5));
		assertFalse(GooglePatents.isValidPatent(testInvalidPatent6));
		assertFalse(GooglePatents.isValidPatent(testInvalidPatent7));
		assertFalse(GooglePatents.isValidPatent(testInvalidPatent8));
		assertFalse(GooglePatents.isValidPatent(testInvalidPatent9));
		assertFalse(GooglePatents.isValidPatent(testInvalidPatent10));
	}
}