package com.patentconnect.gui;

import com.patentconnect.backend.db.PatentDatabase;
import com.patentconnect.tools.ImageLoader;
import com.patentconnect.tools.InformationGlobal;
import com.patentconnect.tools.Node;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxICell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

/**
 * Defines a directed graph that visualizes the shortest citation connection path between two patents. The
 * nodes representing patents can be clicked on to reveal further information about them, and the nodes themselves
 * can be dragged-and-dropped around the interface's frame window.
 */
public class GraphInterface extends JFrame {

	private JPanel contentPane;
	
	/*
	 * These are the properties of the Graph Interface. They are public
	 * and constant values because they never change and other interfaces
	 * that call the GraphInterface must be able to properly set its size
	 */
	public static final int FRAME_WIDTH  = 1200;
	public static final int FRAME_HEIGHT = 600;
	public static final int CELL_HEIGHT  = 120;
	public static final int CELL_WIDTH   = 120;

	/**
	 * Defines and visualizes the directed graph that shows the shortest path
	 * between two nodes. 
	 */
	public GraphInterface(PatentDatabase patentDB, List<String> shortestPathBetweenPatents) {
		
		Image nodeIconImage = ImageLoader.returnImage(InformationGlobal.ICON_PATH, InformationGlobal.NODE_ICON);
		// Sets the icon of the window, only if it was loaded correctly
		if (nodeIconImage != null) setIconImage(nodeIconImage); // Sets icon to 'image object'
		
		setTitle("Shortest Path"); // Sets the title of the window of the Graph Interface
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// Closing graph doesn't close everything
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        setVisible(false);
		        dispose();
		    }
		});
		
		// Citation #1 Code inspired from https://stackoverflow.com/questions/44896929/how-to-show-graph-on-jframe		
		
		// Define the graph
		final mxGraph graph = new mxGraph();				
		Object parent = graph.getDefaultParent();
		
		// The previous node or the first node
		mxICell prev;
		
		// Creates a list of connected patent nodes
		List<Node> patentConnectingNodes = new ArrayList<Node>();
		
		// Iterating through list of strings that represent connected patents 
		for (String s : shortestPathBetweenPatents) {
			Node x = new Node(s);
			// Add patents to list of connected nodes
			patentConnectingNodes.add(x);
		}
		
		graph.getModel().beginUpdate();	
		
		// Set attributes prior to for loop
	    graph.setCellsEditable(true);   
	    graph.setCellsMovable(true); 
	    graph.setCellsSelectable(true);
		
		// Link successive nodes
		try {
			prev = (mxICell) graph.insertVertex(parent, null, patentConnectingNodes.get(0).getName(), 0, 0, CELL_WIDTH, CELL_HEIGHT, "shape=ellipse"); 

			for (int i = 1; i < patentConnectingNodes.size(); i++) {				
				// Next node after last
				mxICell next = (mxICell) graph.insertVertex(parent, null, patentConnectingNodes.get(i).getName(), 0, 0, CELL_WIDTH, CELL_HEIGHT, "shape=ellipse");			    
			    graph.insertEdge(parent, null, "", prev, next);
			    prev = next;	
			}
			
            // Alter attributes after for loop
		    graph.setCellsEditable(false);   		    
		    graph.setCellsMovable(true); 		    
		    graph.setCellsSelectable(true);
		    
		} finally {
		    graph.getModel().endUpdate();
		}		

		// Define the layout and disable adding user defined arrows
		mxIGraphLayout layout = new mxHierarchicalLayout(graph);		
		layout.execute(graph.getDefaultParent());
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		graphComponent.setConnectable(false);
		
		// Citation #2 This block of code inspired from https://stackoverflow.com/questions/23313003/center-mxgraph-in-jframe
	    double widthLayout = graphComponent.getLayoutAreaSize().getWidth();
	    double heightLayout = graphComponent.getLayoutAreaSize().getHeight();        
	    double width = graph.getGraphBounds().getWidth();
	    double height = graph.getGraphBounds().getHeight();	    
	    // Move the nodes away from their default position in the frame
	    graph.getModel().setGeometry(graph.getDefaultParent(), 
	            new mxGeometry(FRAME_WIDTH/2 - CELL_WIDTH/2, CELL_HEIGHT/2,
	                    widthLayout, heightLayout));
	    // ******** End citation #2 ********
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(graphComponent, BorderLayout.CENTER);
		// ******** End citation #1 ********
		  
		// Citation #3 https://github.com/jgraph/jgraphx/blob/master/examples/com/mxgraph/examples/swing/ClickHandler.java#L43
		graphComponent.getGraphControl().addMouseListener(new MouseAdapter() {
		    
			/**
			 * When you click on a patent node, trigger information interface for it in a new window
			 */
			public void mouseReleased(MouseEvent e) {
				// Get cell location
				Object cell = graphComponent.getCellAt(e.getX(), e.getY());
				
				if (cell != null) {   
					// Display the new information interface
					InformationInterface frame = new InformationInterface(patentDB, patentDB.getPatent(graph.getLabel(cell)));
					frame.setVisible(true);
					System.out.println("cell="+graph.getLabel(cell));
				}
			}
		});
		// ******** End citation #3 ********
	}
}
/*

  ROUGH CODE
  PLEASE IGNORE
 
	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {		

	    GraphInterface frame = new GraphInterface();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(frameWidth, frameHeight);
	    frame.setVisible(true);
	}
	
	System.out.println(shortestPathBetweenPatents.toString());
	System.out.println(patent.getPatentID())

	Add data points
	Node n1 = new Node("Starting patent");
	Node n2 = new Node("Connected Patent 1");
	Node n3 = new Node("Connected Patent 2");
	Node n4 = new Node("2838924");
	Node n5 = new Node("3858241");
	Node n6 = new Node("Last patent");
	
	ArrayList<Node> dataPoints = new ArrayList<>();
	dataPoints.add(n1);	
	dataPoints.add(n2);
	dataPoints.add(n3);
	dataPoints.add(n4);		
	dataPoints.add(n5);
	dataPoints.add(n6);		
 */