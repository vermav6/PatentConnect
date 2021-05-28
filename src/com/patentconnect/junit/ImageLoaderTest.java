package com.patentconnect.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import com.patentconnect.tools.InformationGlobal;
import com.patentconnect.tools.ImageLoader;

/**
 * JUnit Test Class For ImageLoader.java 
 * 
 * This tests the ImageLoader class in the 'com.patentconnect.tools'
 * package. ImageLoader is used to load Patent thumb-nails, full-sized
 * images and icons for the UI. Since the Patent images are cleared out
 * upon exiting the program, the icon images inside resources are tested.
 * Non-existent images are also tested; these are treated as edge cases.
 * 
 * @author Jatin Chowdhary
 */
public class ImageLoaderTest {
	
	/*
	 * Even though these two strings are not necessary, 
	 * they were added for code readability
	 */
	String pathToIconImages; // Points to the location of the Icon Images
	String pathToPatentImages; // Point to the 'tempAssets' folder
	
	/*
	 * Declaring Strings that will store the name of Icon 
	 * Images found in 'src/resources'. 
	 */
	String testEntityImageFile1;
	String testEntityImageFile2;
	String testEntityImageFile3;
	String testEntityImageFile4;
	String testEntityImageFile5;
	
	/*
	 * Declaring Strings that will store the name of non-existent
	 * images. The file path will be 'src/resources' and
	 * 'tempAssets/'
	 */
	String testNonExistentImageFile1;
	String testNonExistentImageFile2;
	String testNonExistentImageFile3;
	String testNonExistentImageFile4;
	String testNonExistentImageFile5;
	
	@Before
	public void setUp() throws Exception {
		
		// Location of all the Icon Images
		pathToIconImages = InformationGlobal.ICON_PATH;
		
		// Location of temporary Patent Image Assets
		pathToPatentImages = InformationGlobal.PATENT_IMAGE_PATH;
		
		/*
		 * These are the filenames of the Icon Images inside the
		 * 'resources' folder. 
		 */
		testEntityImageFile1 = "link.icon.original.png";
		testEntityImageFile2 = InformationGlobal.LINK_ICON;
		testEntityImageFile3 = InformationGlobal.NODE_ICON;
		testEntityImageFile4 = InformationGlobal.SEARCH_ICON;
		testEntityImageFile5 = "search.icon.original.png";
		
		/*
		 * These files do not exist anywhere in this application,
		 *   or in any of its folders.
		 * Thus, when ImageLoader tries to load them, a null value
		 *   will be returned. We consider this as an edge case.
		 */
		testNonExistentImageFile1 = "some.UML.diagram.png";
		testNonExistentImageFile2 = "patent.blueprint.png";
		testNonExistentImageFile3 = "alternate_icon.png";
		testNonExistentImageFile4 = "patent_thumbnail001.png";
		testNonExistentImageFile5 = "9123823_.png";
	}

	@Test
	public void test() {
		
		/*
		 * The following test cases are done on Icon Images in the
		 * 'resources' folder. Since these Images exist, ImageLoader
		 * should return an Object, and not a null value. If a null
		 * value is returned, then we know that something went awry.
		 */
		assertNotEquals(ImageLoader.returnImage(pathToIconImages, testEntityImageFile1), null);
		assertNotEquals(ImageLoader.returnImage(pathToIconImages, testEntityImageFile2), null);
		assertNotEquals(ImageLoader.returnImage(pathToIconImages, testEntityImageFile3), null);
		assertNotEquals(ImageLoader.returnImage(pathToIconImages, testEntityImageFile4), null);
		assertNotEquals(ImageLoader.returnImage(pathToIconImages, testEntityImageFile5), null);
		
		/*
		 * The following test cases are done on Images that DO NOT
		 *   exist in any folder or anywhere in PatentConnect's source
		 *   code. Thus, ImageLoader should return a null value, which
		 *   can be checked using Equality.
		 * The folder being searched is 'src/resources'
		 */
		assertEquals(ImageLoader.returnImage(pathToIconImages, testNonExistentImageFile1), null);
		assertEquals(ImageLoader.returnImage(pathToIconImages, testNonExistentImageFile2), null);
		assertEquals(ImageLoader.returnImage(pathToIconImages, testNonExistentImageFile3), null);
		assertEquals(ImageLoader.returnImage(pathToIconImages, testNonExistentImageFile4), null);
		assertEquals(ImageLoader.returnImage(pathToIconImages, testNonExistentImageFile5), null);
		
		/*
		 * Same as above, but the folder being searched is 'tempAssets'
		 */
		assertEquals(ImageLoader.returnImage(pathToPatentImages, testNonExistentImageFile1), null);
		assertEquals(ImageLoader.returnImage(pathToPatentImages, testNonExistentImageFile2), null);
		assertEquals(ImageLoader.returnImage(pathToPatentImages, testNonExistentImageFile3), null);
		assertEquals(ImageLoader.returnImage(pathToPatentImages, testNonExistentImageFile4), null);
		assertEquals(ImageLoader.returnImage(pathToPatentImages, testNonExistentImageFile5), null);
	}
}