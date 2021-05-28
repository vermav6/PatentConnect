package com.patentconnect.backend.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A that stores and provides methods to interact with the patent citation database
 * @author Yousef Al Hashemi
 */
public class PatentDatabase {
    
    private HashMap<String, List<String>> patentMap;

    /**
     * A constructor for the PatentDatabase that creates it from a PatentFileParsingReader
     * @param reader A PatentFileParsingReader for a database of citations
     */
    public PatentDatabase(PatentFileParsingReader reader) {
        patentMap = new HashMap<>();
        while (reader.hasNextLine()) {
        	this.addCitation(reader.nextLine());
        }
    }
    
    /**
     * A method to get a Patent object from a PatentID
     * @param patentID A String representing the patentID of the Patent to retrieve 
     * @return A Patent Object of the given PatentID
     */
    public Patent getPatent(String patentID) {
    	if (this.patentMap.containsKey(patentID)) {
    		return new Patent(patentID, MergeSort.sort(patentMap.get(patentID)));
    	}
    	return null;
 
    }
    
    
    /**
     * A method to check if a given patent is in the database
     * @param patentID The PatentID of a patent to check 
     * @return true if the patent is in the database, false otherwise
     */
    public boolean contains(String patentID) {
    	return this.patentMap.containsKey(patentID);
    }
    
    /**
     * A method to get the size of the database
     * @return an int representing the number of patents in the database
     */
    public int size() {
        return this.patentMap.size();
    }

    /**
     * A method to get the shortest path between two patents
     * @param start The PatentID to start the shortest path
     * @param end The PatentID to end the shortest path at
     * @return A CitationPath object that contains the path between the two objects
     */
    public CitationPath shortestPath(String start, String end) {
    	return new CitationPath(this, start, end);
    }
    
    
    /**
     * A method to add a citation to the database
     * @param cit A CitationRecord to add to the database
     * @return true if the citation was successfully added, false if it was already in the database
     */
    private boolean addCitation(CitationRecord cit) {
    	return this.addCitation(cit.getFrom(), cit.getTo());
    }
    
    /**
     * A method to add a citation to the database
     * @param from A String of the PatentID of the citing patent
     * @param to A String of the PatentID of the cited patent
     * @return true if the citation was successfully added, false if it was already in the database
     */
    private boolean addCitation(String from, String to) {
    	this.addPatent(from);
    	this.addPatent(to);
    	if (!this.patentMap.get(from).contains(to)) {
    		this.patentMap.get(from).add(to);
    		return true;
    	}
    	return false;
    }
    
    /**
     * A method to get a Patent object from a PatentID
     * @param patentID A String representing the patentID of the Patent to retrieve 
     * @return A Patent Object of the given PatentID
     */
    private boolean addPatent(String patentID) {
    	if (!this.patentMap.containsKey(patentID)) {
    		this.patentMap.put(patentID, new ArrayList<String>());
    		return true;
    	}
    	return false;
    }

}