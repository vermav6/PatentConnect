package com.patentconnect.tools;

import java.awt.Color;

/**
 * Important Data Used Throughout PatentConnect
 * 
 * Contains Strings and other information/data that is used throughout
 * PatentConnect. This class was created to make PatentConnect
 * easily change-able and editable. A simple change here affects 
 * the entire software without breaking anything. For example, if we
 * want to change the Search Icon Image to something else, all we have 
 * to do is change the name right here, in this file. Since the data does
 * not change everything is public and constant. 
 */
public class InformationGlobal {
	
	/*
	 * These strings contain paths to the images used by PatentConnect
	 * We choose to make the Strings public, final, and static because
	 *   we don't anticipate that these paths will change. Even if they do,
	 *   it will be very easy to update them. PatentConnect was designed
	 *   for change.
	 */
	public static final String ICON_PATH = "src/resources/";
	public static final String PATENT_IMAGE_PATH = "tempAssets/";
	
	/*
	 * These strings are the names of the icons used in the UIs.
	 * We choose to structure our code this way because it allows us 
	 *   to easily swap out the images or change them. This helps 
	 *   with modularity and sustains design for change principles.
	 * This design decision makes testing and debugging much easier! 
	 *   I can personally vouch for this.
	 */
	public static final String SEARCH_ICON = "search.icon.png";
	public static final String LINK_ICON = "link.icon.png";
	public static final String NODE_ICON = "node.icon.png";
	
	/*
	 * Stores all the console messages in a String array. All other
	 * classes retrieve the value from here. This helps with debugging,
	 * testing, and increases cohesion while making PatentConnect
	 * more changeable and manageable. 
	 */
	public final static String[] STATUS_MSG_CONSOLE =
			new String[]{"Please Enter A Patent ID", 
						 "This Format Is Correct", 
						 "This Is Not A Valid Patent ID",
						 "This Is Not An Integer. Please Input A Whole Number",
						 "Something Went Terribly Wrong. Please Try Again"
						};
	
	/*
	 * Stores all the status label strings in a String array. All other
	 * classes retrieve the value from here. This helps with debugging,
	 * testing, and increases cohesion while making PatentConnect
	 * more changeable and manageable. 
	 */
	public final static String[] STATUS_MSG_USER_INTERFACE =
			new String[]{"Status: Waiting For User Input", 
						 "Status: Please stand by while we process your request...", 
						 "Status: Invalid patent number",
						 "Status: This is not a whole number. Please try again",
						 "Status: Unknown error encountered. Please try again"
						};
	
	// This is what the Patent thumbnail image name starts with
	public static final String PATENT_THUMBNAIL_START = "thumb_";
	
	// This is what the Patent image names end in
	public static final String PATENT_FILENAME_ENDING = "_0.png";
	
	// This is the ending extension for Portable Network Graphic files
	public static final String PNG = ".png";
	
	/*
	 * The following Strings are displayed on the Search Interface
	 * underneath the picture/images section. The message informs the 
	 * user if the patent has any pictures. If so, the user can click
	 * on them to open a full-sized image.
	 */
	private static final String NO_IMAGES = "";
	private static final String ONE_IMAGE = "This is the only image. Click on it to open it.";
	private static final String MORE_IMAGES = "Click on an image to open it OR Hover over it & Scroll to see more images.";
	
	/**
	 * Returns an appropriate message based on the number of images
	 * 
	 * Based on how many patent images are available, an appropriate message
	 * is returned, which tells the user if they can click on the thumb-nail
	 * and open a full sized image. 
	 * 
	 * @param numberOfImages The number of available images (Patent Images)
	 * @return A String (message) pertaining to image availability 
	 */
	public static String returnCaption(int numberOfImages) {
		
		if (numberOfImages == 0) { // If no patent thumb-nails exist
			return NO_IMAGES;
		} else if (numberOfImages == 1) { // Exactly 1 patent thumb-nail
			return ONE_IMAGE;
		} else { // More than 1 patent thumb-nail
			return MORE_IMAGES + " There Are " + numberOfImages + " Images In Total." ;
		}
	}	
	
	/**
	 * Returns the appropriate Status Label based on exit code of Parser. These will be
	 * displayed on the User Interfaces
	 * 
	 * @param exitCode Integer that determines what state the program is in and what string to return
	 * @return The appropriate String based on the exit code supplied
	 */
	public static String returnStatusLabel(int exitCode) {
		
		// Switch statement returns appropriate Status for Label, based on exit code
		switch(exitCode) {
			case 0: 
				return STATUS_MSG_USER_INTERFACE[0];
			case 1:
				return STATUS_MSG_USER_INTERFACE[1];
			case 2:
				return STATUS_MSG_USER_INTERFACE[2];
			case 3: 
				return STATUS_MSG_USER_INTERFACE[3];
			default:
				// All other cases returns the following String:
				return STATUS_MSG_USER_INTERFACE[4];
		}		
	}
	
	/**
	 * Returns the appropriate Color Label based on exit code of Parser
	 * 
	 * Gray  = User has not entered anything or the text field is empty
	 * Green = Input is in the correct format, and is a valid Patent ID
	 * Red   = Input is not an integer between 5 and 7 digits
	 * Black = Something went wrong and an exception has occurred
	 * 
	 * @param exitCode Integer that determines what state the program is in and what color to return for Label
	 * @return The appropriate Color for Label based on the exit code supplied
	 */
	public static Color returnLabelColor(int exitCode) {
		
		// Switch ladder decides which color to return based on supplied exit code
		switch(exitCode) {
			case 0: 
				return Color.GRAY;
			case 1:
				return Color.GREEN;
			case 2: // Since case 2 and case 3 are the same color, case 2 has no return statement and falls to case 3
			case 3: 
				return Color.RED;
			default: 
				// All other cases will return black as the color
				return Color.BLACK;
		}
	}
}
