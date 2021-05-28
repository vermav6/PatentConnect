package com.patentconnect.backend.db;

/**
 * A class for dealing with directed citations
 * @author Yiding Li
 *
 */
public class CitationRecord {
    
    private final String from;
    private final String to;

    /** A constructor for the CitationRecord
     * @param from a String representing the PatentID that makes the citation
     * @param to a String representing the PatentID that is cited
     */
    public CitationRecord(String from, String to) {
        this.from = from;
        this.to = to;
    }

    /**
     * A getter for the PatentID that makes the citation
     * @return A String, the citing PatentID
     */
    public String getFrom() {
        return this.from;
    }
    /**
     * A getter for the PatentID that is being cited
     * @return A String, the cited PatentID
     */
    public String getTo() {
        return this.to;
    }
}