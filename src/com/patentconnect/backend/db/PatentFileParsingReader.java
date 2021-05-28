package com.patentconnect.backend.db;

import com.patentconnect.backend.global.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class does not inherit the Reader abstract class, or any subclass thereof <b>
 * This class does borrow some ideas thereof though, in particular its constructors.
 * @author Yiding Li
 */
public class PatentFileParsingReader {

    private Scanner encapsulatedScanner;
    private final String filepath;

    /**
     * A constructor for the PatentFileParsingReader that takes the file's path as a String
     * @param filepath A string representing the path of the file of patents to read
     * @throws FileNotFoundException If the file path does not lead to an existing file
     */
    public PatentFileParsingReader (String filepath) throws FileNotFoundException {
        this.filepath = filepath;
        this.encapsulatedScanner = makeScannerFromPath(filepath);
    }

    /** 
     * A method to check if the reader has a next line
     * @return true if the reader has another line, false otherwise
     */
    public boolean hasNextLine() {
        return this.encapsulatedScanner.hasNextLine();
    }

    /**
     * A method to get the next line of the file in the form of a CitationRecord
     * @return A CitationRecord containing the citation of the next line
     */
    public CitationRecord nextLine() {
        return (lineToCitationRecord(this.encapsulatedScanner.nextLine()));
    }

    /**
     * A method to create a citation record from a String representing the line containing two patents
     * The line is in the form of:
     * <PatentID from> <PatentID to>
     * @param line A String representing the line containing a citation in the form shown above
     * @return A CitationRecord storing the citation given in the line
     */
    private static CitationRecord lineToCitationRecord(String line) {
        String[] twoFold = line.trim().split(PregenSettings.CITATION_SEPARATOR);
        
        return new CitationRecord(twoFold[0], twoFold[1]);
    }

    /**
     * A method to create a scanner given a file's path
     * @param filepath A string representing the path to a file of PatentID citations
     * @return A Scanner for the file
     * @throws FileNotFoundException If the given file path does not lead to a file that exists
     */
    private static Scanner makeScannerFromPath (String filepath) throws FileNotFoundException {
        File file = new File(filepath); 
        Scanner sc;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException fnfe) {
            throw new FileNotFoundException ("Unresolved: File not found at ".concat(filepath));
        }
        return sc;
    }
    
    
    /**
     * A method to create a scanner given a File object
     * @param givenFile A File object representing the path to a file of PatentID citations
     * @return A Scanner for the file
     * @throws FileNotFoundException If the given file path does not lead to a file that exists
     */
    private static Scanner makeScannerFromFile (File givenFile) throws FileNotFoundException {
        Scanner sc;
        try {
            sc = new Scanner(givenFile);
        } catch (FileNotFoundException fnfe) {
            throw new FileNotFoundException ("Unresolved: File not found at ".concat(givenFile.getPath()));
        }
        return sc;
    }

}