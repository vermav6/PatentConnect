package com.patentconnect.tools;

import com.mxgraph.model.mxICell;

/**
 * Node ADT used by GraphInterface to create Nodes
 * 
 * @author Steve Gonder
 */
public class Node {
	
	private String name;
	private mxICell next;
	
	public Node(String data) {
		name = data;
	}
	
	public String getName() {
		return name;
	}
}
