package com.patentconnect.backend.googleAPI;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;	
import java.net.URL;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.patentconnect.tools.InformationGlobal;

/**
 * A class to fetch Patent data from google Patents
 * @author Yousef Al Hashemi
 */
public class GooglePatents {
	
	private final static int MAX_PICTURES = 3;
	private final static String FOLDER_PATH = InformationGlobal.PATENT_IMAGE_PATH;
	
	private String patentID;
	private String urlString;
	private String name;
	private String description;
	private String[] picURLs;
	private String[] thumbURLs;
	private boolean picsReady;
	
	private final Document patentPage;
	
	/**
	 * A constructor for the GooglePatents class
	 * @param patentID a valid patent in the patent database
	 * @throws IOException if for some reason the patent either is invalid, or there is an internet problem
	 */
	public GooglePatents(String patentID) throws IOException{
		this.patentID = patentID;
		picsReady = false;
		urlString = GooglePatents.getURLFromPatentID(patentID);
		patentPage = Jsoup.connect(urlString).get();
		//get the name, it is stored in the title in the form "PatentID - name - Google Patents"
		//the ' ' after the '-' is added just in case a title contains a '-' midway through it.
		name = patentPage.selectFirst("title").text().split("- ")[1].trim();
		if (name.length() == 0) {
			name = "No name available";
		}
		
		/*
		 * get the picture elements, and then the URL of each picture
		 * up to MAX_PICTURES
		 */
		
		//get the thumbnails for the pictures
		Elements Pics = patentPage.select("img");
		thumbURLs = new String[Math.min(Pics.size(), MAX_PICTURES)];
		for (int i = 0; i < thumbURLs.length; i++) {
			thumbURLs[i] = Pics.get(i).attr("src");
		}
		
		//get the actual pictures
		Pics = patentPage.select("li[itemprop=images] meta[itemprop=full]");
		picURLs = new String[Math.min(Pics.size(), MAX_PICTURES)];
		for (int i = 0; i < picURLs.length; i++) {
			picURLs[i] = Pics.get(i).attr("content");
		}
		
		//get the description (called the abstract), if it exists
		Element tempDescription = patentPage.selectFirst("abstract div.abstract");
		if (tempDescription == null) {
			description = "No Description Available";
		}else {
			description = tempDescription.text().trim();
		}
		
	}
	
	/**
	 * A getter for the PatentID
	 * @return the patentID stored in this object
	 */
	public String getPatentID() {
		return patentID;
	}
	
	/**
	 * A getter for the name of the patent found on Google Patents
	 * @return A string representing the patentID stored in this object
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * A getter for a brief description of the patent found on Google Patents
	 * If a description is unavailable, the description will be:
	 * No description available
	 * @return A string representing the Description stored in this object
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * A getter for the URL of the google patents page for this patentID 
	 * @return A string representing the URL stored in this object
	 */
	public String getURL() {
		return urlString;
	}
	
	/**
	 * A method to download the pictures associated with this class.
	 * The pictures will be stored in tempAssets, and they will be of the form:
	 * <PatentID>_<PictureNumber>.png, for example:
	 * 1324234_0.png (zero indexed)
	 * And thumbnails will be stored in tempAssets as well, they will be of the form:
	 * thumb_<PatentID>_<PictureNumber>.png, for example:
	 * thumb_1324234_0.png (zero indexed)
	 * If pictures are already downloaded, this method will just return the number downloaded.
	 * @return an int, the number of pictures downloaded
	 * @throws IOException if something goes wrong with the download.
	 */
	public int downLoadPictures() throws IOException {
		//pics are already downloaded
		if (picsReady) {
			return picURLs.length;
		}
		
		//download the actual pictures
		for (int i = 0; i < picURLs.length; i++) {
			URL pURL = new URL(picURLs[i]);
			InputStream in = pURL.openStream();
			OutputStream out = new BufferedOutputStream(new FileOutputStream(FOLDER_PATH + patentID + "_" + i +".png"));
			for (int b; (b = in.read()) != -1;) {
				out.write(b);
			}
			out.close();
			in.close();
		}
		
		//download the thumbnails
		for (int i = 0; i < thumbURLs.length; i++) {
			URL pURL = new URL(thumbURLs[i]);
			InputStream in = pURL.openStream();
			OutputStream out = new BufferedOutputStream(new FileOutputStream(FOLDER_PATH + "thumb_" + patentID + "_" + i +".png"));
			for (int b; (b = in.read()) != -1;) {
				out.write(b);
			}
			out.close();
			in.close();
		}
		picsReady =  picURLs.length != 0;
		return picURLs.length;
	}
	
	/**
	 * 
	 * @return True if the pictures are downloaded, false otherwise
	 */
	public boolean arePicturesAvailable() {
		return picsReady;
	}
	
	/**
	 * A utility method to get the url associated with a given patent ID 	
	 * @param patentID the patent ID to find the page for 
	 * @return A string representing the URL of the given patent ID
	 */
	public static String getURLFromPatentID(String patentID) {
		return "https://patents.google.com/patent/US" + patentID + "/en";
	}
	
	/**
	 * A utility method to check if a given patent exists on Google Patents
	 * @param patentID the patentID to check
	 * @return true if the Patent exists, false otherwise
	 */
	public static boolean isValidPatent(String patentID) {
		try {
			Document patentPage = Jsoup.connect(GooglePatents.getURLFromPatentID(patentID)).get();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Method to delete all downloaded files after destruction
	 * @param patentID id of pictures to delete
	 * @param numPics number of pictures downloaded
	 */
	public static void deletePictures(String patentID, int numPics) {
		//Delete all of the temporary Assets
		for (int i = 0; i < numPics; i++) {
			File picFile = new File(FOLDER_PATH + patentID + "_" + i +".png");
			File thumbFile = new File(FOLDER_PATH +"thumb_" + patentID + "_" + i +".png");
			
			//added checks just in case a bad input is given
			if (picFile.exists()) {
				picFile.delete();
			}
			if (thumbFile.exists()) {
				thumbFile.delete();
			}
		}
	}
}