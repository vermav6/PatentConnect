package com.patentconnect;

import java.awt.EventQueue;
import java.io.IOException;

import com.patentconnect.backend.db.PatentDatabase;
import com.patentconnect.backend.db.PatentFileParsingReader;
import com.patentconnect.backend.global.PregenSettings;
import com.patentconnect.gui.SearchInterface;

/**
 * @author Yousef, Jatin, Steve, & Yiding (AND NOT VARUN)
 * @version 1.0
 */
public class Main {
	
	/*
	 * This variable is used to toggle Debugging mode. 
	 * Debugging mode tests a smaller version of the 
	 *     Patent Database. 
	 * When enabled, more information is printed to console.
	 */
	private static final boolean ARE_WE_DEBUGGING = false;
	
	/**
	 * The entry point for the program
	 * @param args (Unused)
	 * @throws IOException if for some reason the file passed into PatentFileParsingReader is not there. 
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		
		PatentDatabase patentDB;
		PatentFileParsingReader inputCitations;
		
		if (ARE_WE_DEBUGGING) {
			// Small Database (1000 lines) - Used For Debugging
		    inputCitations = new PatentFileParsingReader("src/com/patentconnect/data/small-cit-Patents.txt");
		    System.out.println("Small Debugging Database Loaded");
		} else {
			// Full Database - This takes quite some time on older PCs
		    inputCitations = new PatentFileParsingReader(PregenSettings.DATA_FILE_PATH);
		}
		
		// Null check on whether DB loaded properly
		if (inputCitations != null) {
			
			patentDB = new PatentDatabase(inputCitations);
			
			if (ARE_WE_DEBUGGING) {
				// Prints out the size of the data set
				System.out.println("The size of the dataset is :" + patentDB.size());
			}
			
			// Start PatentConnect Search Interface
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						SearchInterface frame = new SearchInterface(patentDB);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} 
		// If the data did not load correctly, print message:
		else System.out.println("ERROR: Database Did Not Load Correctly");
	}
}