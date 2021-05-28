package com.patentconnect.tools;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Helper Class Used To Load Images
 * 
 * @author Jatin Chowdhary
 */
public class ImageLoader {
	
	/** 
	 * Loads image from a path
	 * 
 	 * Looks for a specific image in any folder you give it and if 
 	 * found, it will load it into an Image object and then return it 
 	 * to the caller. If image was not found null will be returned. 
	 * 
	 * @param imagePath The path of the image; where it is located
	 * @param fileName The name of the file/image
	 * @return The image object or null if it could not load image
	 * @throws IOException If the image could not be loaded
	 */
	public static Image returnImage(String imagePath, String fileName) {
		
		// Create a new image object and set it to null
		Image image = null;
		
		try {
			// Try to load the icon path
		    File pathToFile = new File(imagePath.concat(fileName));
		    // For testing purposes: //System.out.println(imagePath.concat(fileName));
		    // Store icon into image object
		    image = ImageIO.read(pathToFile);
		} catch (IOException ex) {
			//System.out.println("Could not load " + fileName);
			image = null; // Kind of redundant, but just in case
		    //ex.printStackTrace(); // For debugging purposes
		}
		// Return loaded image or null
		return image;
	}
}