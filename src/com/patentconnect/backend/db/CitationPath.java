package com.patentconnect.backend.db;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.patentconnect.backend.global.PregenSettings;

/**
 * A class to work with paths between patents. The path is created upon construction of the object,
 * And can be accessed by calling the getPath method.
 * If a path does not exist, getPath will return null. 
 * @author Yousef Al Hashemi
 */
public class CitationPath {
	
	private String start;
	private String end;
	private ArrayList<String> finalPath;
	
	/**
	 * A constructor for a citation path, this method will create a path between two Patents.
	 * @param db the database holding the patents
	 * @param start the start patentID
	 * @param end the ending patentID
	 */
	public CitationPath(PatentDatabase db, String start, String end) {
		if (!(db.contains(start) && db.contains(end))){
			return;
		}
		/*
		 * Uses Breadth First Search to find the shortest path between 
		 *   the start and end patent.
		 * The path can go either way, even though this is a digraph, and 
		 *   it will keep track of the direction of each.
		 */
		this.start = start;
		this.end = end;
		Map<String, String> path = new HashMap<>();
		Set<String> marked = new HashSet<>();
		Queue<String> toVisit = new ArrayDeque<>();
		Map<String, Integer> depth = new HashMap<>();
		
		depth.put(start, 0);
		marked.add(start);
		toVisit.add(start);
		
		while (!toVisit.isEmpty()) {
			String currPatent = toVisit.remove();
			//don't go past a certain depth
			if (depth.get(currPatent) == PregenSettings.MAX_DEPTH) {
				continue;
			}
			//go through all citations to and from the current patent
			for (String cit : db.getPatent(currPatent).getCitations()) {
				
				
				if (!marked.contains(cit)) {
					toVisit.add(cit);
					marked.add(cit);
					path.put(cit, currPatent);
					depth.put(cit, depth.get(currPatent) + 1);
					//If we have found the patent we are looking for, no need to go further.
					if(cit.equals(end)) {
						toVisit.clear();
						break;
					}
				}
			}	
		}
		
		//now construct the path if it exists
		if (marked.contains(end)) {
			finalPath = new ArrayList<>();
			String currPatent = end;
			while(currPatent != start) {
				finalPath.add(0, currPatent);
				currPatent = path.get(currPatent);
			}
			finalPath.add(0,currPatent);
		}else {
			finalPath = null;
		}
		
		
	}
	
	/**
	 * A getter for the path created by this class
	 * @return An ArrayList of Strings, the path from the given start node to the end node.
	 */
	public ArrayList<String> getPath(){
		return this.finalPath;
	}
}
