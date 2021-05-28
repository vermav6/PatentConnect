package com.patentconnect.backend.global;

/**
 * A class for constants used throughout the pregen process
 */
public class PregenSettings {
	
    // Assumptions
    public static final int MAX_LINE_MEMBERS = 2;
    
    // Configurable Variables
    public static final String CITATION_SEPARATOR = "\\s+";
    public static final String DATA_FILE_PATH="src/com/patentconnect/data/cit-Patents.txt";
    public static final int MAX_DEPTH = 100;
}