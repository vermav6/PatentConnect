	package com.patentconnect.backend.db;

import java.util.ArrayList;
import java.util.List;
/**
 * A class representing a node in the patent database.
 * this class contains the patent, and any other patents that it cites
 * in the form of PatentID's
 * @author Yousef Al Hashemi
 */
public class Patent {
	private String patentID;
	private List<String> citations;
	
	/**
	 * A constructor for the Patent class that also takes citations
	 * @param patentID A String representing a PatentID
	 * @param citations A List of Strings representing the PatentIDs of all of the patents this patent cites
	 */
	public Patent(String patentID, List<String> citations) {
		this.patentID = patentID;
		this.citations = new ArrayList<>(citations);
	}
	
	/**
	 * A getter for the PatentID of this patent
	 * @return A String representing the PatentID of this patent
	 */
	public String getPatentID() {
		return this.patentID;
	}
	
	/**
	 * A getter for the Citations of this patent
	 * @return A List of Strings representing the PatentIDs that this Patent cites
	 */
	public List<String> getCitations(){
		return new ArrayList<>(this.citations);
	}
}
