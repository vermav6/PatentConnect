package com.patentconnect.gui;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JTextArea;

import com.patentconnect.tools.ImageLoader;
import com.patentconnect.tools.OpenBrowser;
import com.patentconnect.tools.InformationGlobal;
import com.patentconnect.tools.Parser;
import com.patentconnect.tools.TextPrompt;
import com.patentconnect.backend.db.Patent;
import com.patentconnect.backend.db.PatentDatabase;
import com.patentconnect.backend.googleAPI.GooglePatents;

public class InformationInterface extends JFrame {

	// Declaring JSwing objects for the UI
	private JPanel contentPane;
	private JTextField textFieldSecondPatent;
	private JLabel lblPictureInstructions;
	private JLabel lblPicture = new JLabel("Loading..");
	
	// Boolean value to track thumbnails across methods
	private boolean didThumbnailLoad = false;
	
	// Immutable width of the window 
	private static final int FRAME_WIDTH = 1024;
	
	// Immutable height of the window
	private static final int FRAME_HEIGHT = 780 ;
	
	/*
	 * Universal dimension for the JSeparators
	 * We use to have them but then removed them due to spacing issues related
	 *   to GridBag Layout. If we ever add them back, this is the dynamically
	 *   calculated dimension that we found to work best
	 */
	//private static final Dimension SEPARATOR_DIMENSION = new Dimension(FRAME_WIDTH - 10, 10);
		
	// Stores the URL for the patent on Google Patents
	private String patentURL = null;
	
	/*
	 * These variables are responsible for updating and 
	 * managing the Patent Image information. This information
	 * is used to load Patent Thumbnails and Full-Sized Images.
	 */
	private int currentImageNumber = 0;
	private Image patentThumbnailImage;
	private File fullSizedImage;
	private int numImages = 0;
	
	/*
	 * Makes the border invisible for the scroll-able text Areas.
	 * Borders are invisible because they do not look appealing and 
	 *   make the UI look rough around the edges. Thus, we think it is
	 *   best to make them invisible
	 */
	private static final boolean BORDER_IS_INVISIBLE = true;

	/**
	 * Create the frame and modify its default properties and settings
	 */
	public InformationInterface(PatentDatabase patentDB, Patent patent) {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Patent Information Page");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		// Loads image of the link icon
		Image linkIconImage = ImageLoader.returnImage(InformationGlobal.ICON_PATH, InformationGlobal.LINK_ICON);
		// If image loaded then set the frame's icon image to it
		if (linkIconImage != null) setIconImage(linkIconImage);
		
		// GridBag Layout Settings for the JFrame/JPanel
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{71, 48, 0, 66, 0, 0, 0, 0, 56, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		/*
		 * Create variables to hold Patent Information that is fetched by Google Patents API
		 * (i.e. Name, Description, Number of Images, etc.)
		 */
		String patentName = "Patent Has No Name";
		String patentDescription = "Patent Has No Description";
		//String patentURL = null;
		List patentCitations = patent.getCitations(); // Stores citations as a list
		//System.out.println(citations.toString()); // Prints list of patent citations to console 
		                                            // Used for debugging
		
		// Using GooglePatents API to fetch Patent Information using ID
		try {
			GooglePatents api = new GooglePatents(patent.getPatentID());
			patentName = api.getName();
			patentDescription = api.getDescription();
			patentURL = api.getURL();
			numImages = api.downLoadPictures();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(contentPane, "Could Not Load Patent. Please Try Again");
			e1.printStackTrace();
			//System.out.println("Could Not Find Patent");
		}
		
		// Testing Google Patent API Built By Yousef
		//System.out.println(patentName);
		//System.out.println(patentDescription);
		//System.out.println(patentURL);
		
		// Variable that determines if Patent has pictures
		boolean hasPictures = (numImages != 0);
		
		/*
		 * Properties For The Patent Name Text Area
		 * (i.e. Background color, Font, Line wrap, Editable)
		 */
		JTextArea nameTextArea = new JTextArea(patentName);
		nameTextArea.setBackground(UIManager.getColor("Button.background"));
		nameTextArea.setFont(new Font("Arial Black", Font.BOLD, 40));
		nameTextArea.setLineWrap(true);
		nameTextArea.setWrapStyleWord(true);
		nameTextArea.setEditable(false);	
		
		/*
		 * Mouse Listener For Patent Name Text Area
		 * When the Patent Name Text Area is clicked, a message box pops up
		 */
		nameTextArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Message box for Patent Name Text Area, when clicked
				JOptionPane.showMessageDialog(contentPane, "This is the name of the Patent, as per the Google API");
			}
		});
		/*
		 * GridBag Layout Settings For Patent Name Text Area
		 */
		GridBagConstraints gbc_nameTextArea = new GridBagConstraints();
		gbc_nameTextArea.insets = new Insets(0, 0, 5, 0);
		gbc_nameTextArea.fill = GridBagConstraints.BOTH;
		gbc_nameTextArea.gridx = 0;
		gbc_nameTextArea.gridy = 1;
		contentPane.add(nameTextArea, gbc_nameTextArea);
		
		/*
		 * Scroll Bar For Patent Name Text Area
		 * When the patent name is to long, the scroll bar will you to
		 *   scroll down to see the rest of the name
		 */
		JScrollPane scrollPanePatentName = new JScrollPane(nameTextArea);
		// Sets the border of the patent name text area to invisible
		if (BORDER_IS_INVISIBLE) scrollPanePatentName.setBorder(null);
		
		/*
		 * GridBag Layout Settings For Patent Name
		 */
		GridBagConstraints gbc_scrollPanePatentName = new GridBagConstraints();
		gbc_scrollPanePatentName.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPanePatentName.fill = GridBagConstraints.BOTH;
		gbc_scrollPanePatentName.gridx = 0;
		gbc_scrollPanePatentName.gridy = 0;
		contentPane.add(scrollPanePatentName, gbc_scrollPanePatentName);
		
		/*
		 * Properties for the Patent Number JLabel
		 */
		JLabel lblPatentNumber = new JLabel("(#" + patent.getPatentID() + ")"); 
		lblPatentNumber.setFont(new Font("Arial", Font.PLAIN, 30));
		
		// Mouse Click Listener For The Patent Number Label
		lblPatentNumber.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// Opens up a dialog box explaining the label
				int patentNumberDialogReturnCode = 
						
						JOptionPane.showOptionDialog(contentPane, 
								
						// This is the main message of the dialog box
						"This is the PatentID found in the Stanford Patent Citation Network.\n" + 
						"Would you like me to open up the Patent Information Page on Google API?",
							
						// This is the title of the dialog box
						null, 
							
						// This is the button options // Options are binary
						JOptionPane.OK_OPTION, 
						JOptionPane.INFORMATION_MESSAGE, 
						null,
							
						// Changes the button texts to the following
						new String[]{"Yes, Please!", "No, Thank You"},
						"default"
						);	
				
				// If user wants to see patent on Google Patent and URL is NOT null, then:
				if (patentNumberDialogReturnCode == 0 && patentURL != null) {
					// Open patent on Google Patents
					OpenBrowser.openURL(patentURL);
				} else if (patentURL == null){
					// If patent ID is null, which means it didn't load properly, then display message:
					JOptionPane.showMessageDialog(contentPane, "I'm Sorry I Could Not Find That Patent On Google");
				} 
			}
		});
		
		/*
		 * GridBag Layout Settings For Patent Number Label
		 */
		GridBagConstraints gbc_lblPatentNumber = new GridBagConstraints();
		gbc_lblPatentNumber.anchor = GridBagConstraints.WEST;
		gbc_lblPatentNumber.insets = new Insets(0, 0, 5, 0);
		gbc_lblPatentNumber.gridx = 0;
		gbc_lblPatentNumber.gridy = 1;
		contentPane.add(lblPatentNumber, gbc_lblPatentNumber);
		
		/*
		 * Physical Properties For The Description Header Label
		 * &
		 * GridBag Layout Settings
		 */
		JLabel lblDescriptionHeader = new JLabel("Description");
		lblDescriptionHeader.setFont(new Font("Arial Black", Font.PLAIN, 40));
		GridBagConstraints gbc_lblDescriptionHeader = new GridBagConstraints();
		gbc_lblDescriptionHeader.anchor = GridBagConstraints.WEST;
		gbc_lblDescriptionHeader.insets = new Insets(0, 0, 5, 0);
		gbc_lblDescriptionHeader.gridx = 0;
		gbc_lblDescriptionHeader.gridy = 2;
		contentPane.add(lblDescriptionHeader, gbc_lblDescriptionHeader);
		
		/*
		 * Properties for the Patent Description Text Area. Settings are
		 * assigned/created
		 */
		JTextArea descriptionTextArea = new JTextArea();
		descriptionTextArea.setEditable(false);
		descriptionTextArea.setLineWrap(true);
		descriptionTextArea.setWrapStyleWord(true);
		descriptionTextArea.setText(patentDescription);
		descriptionTextArea.setFont(new Font("Arial Black", Font.PLAIN, 20));
		descriptionTextArea.setBackground(UIManager.getColor("Button.background"));
		
		/*
		 * GridBag Layout Settings For Patent Description Text Area 
		 */
		GridBagConstraints gbc_descriptionTextArea = new GridBagConstraints();
		gbc_descriptionTextArea.insets = new Insets(0, 0, 5, 0);
		gbc_descriptionTextArea.fill = GridBagConstraints.BOTH;
		gbc_descriptionTextArea.gridx = 0;
		gbc_descriptionTextArea.gridy = 5;
		contentPane.add(descriptionTextArea, gbc_descriptionTextArea);
		
		/*
		 * Scroll pane for the patent description text area
		 * When the patent description is to big, a scroll bar is added so the user
		 *   can scroll through the text area and see the entire description. The
		 *   abstract/description fetched from Google Patents is often times lengthy
		 */
		JScrollPane scrollPanePatentDescription = new JScrollPane(descriptionTextArea);
		if (BORDER_IS_INVISIBLE) scrollPanePatentDescription.setBorder(null); // Removes border around the text area
		
		/*
		 * GridBag Layout Settings For Scroll Pane
		 */
		GridBagConstraints gbc_scrollPanePatentDescription = new GridBagConstraints();
		gbc_scrollPanePatentDescription.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPanePatentDescription.fill = GridBagConstraints.BOTH;
		gbc_scrollPanePatentDescription.gridx = 0;
		gbc_scrollPanePatentDescription.gridy = 3;
		contentPane.add(scrollPanePatentDescription, gbc_scrollPanePatentDescription);
		
		// Creates JLabel For Pictures Header 
		JLabel lblPictures = new JLabel("Pictures");
		
		/*
		 * Mouse Listener For Pictures Header
		 * When clicked it notifies the user that the full-sized pictures
		 *   below can be opened in their default image viewer
		 */
		lblPictures.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Opens a message dialog box
				JOptionPane.showMessageDialog(contentPane, "Click on the thumbnail below to open the full-sized image");
			}
		});
		
		/*
		 * Properties For The Picture Header Label
		 * &
		 * Its GridBag Layout Settings
		 */
		lblPictures.setFont(new Font("Arial Black", Font.PLAIN, 40));
		GridBagConstraints gbc_lblPictures = new GridBagConstraints();
		gbc_lblPictures.anchor = GridBagConstraints.WEST;
		gbc_lblPictures.insets = new Insets(0, 0, 5, 0);
		gbc_lblPictures.gridx = 0;
		gbc_lblPictures.gridy = 4;
		contentPane.add(lblPictures, gbc_lblPictures);
		
		/*
		 * JLabel With Instructions On How To Use Image Display
		 * Graphical properties and layout settings are below
		 */
		lblPictureInstructions = new JLabel("Instructions");
		lblPictureInstructions.setFont(new Font("Arial", Font.ITALIC, 10));
		GridBagConstraints gbc_lblPictureInstructions = new GridBagConstraints();
		gbc_lblPictureInstructions.anchor = GridBagConstraints.WEST;
		gbc_lblPictureInstructions.insets = new Insets(0, 0, 5, 0);
		gbc_lblPictureInstructions.gridx = 0;
		gbc_lblPictureInstructions.gridy = 6;
		contentPane.add(lblPictureInstructions, gbc_lblPictureInstructions);
		
		
		// If the patent has pictures (retrieved from Google Patents)
		if (hasPictures) {
			
			// Clear Picture Label
			lblPicture.setText("");
			
			// Load first patent thumbnail -- the first image; image #0
			patentThumbnailImage = ImageLoader.returnImage(InformationGlobal.PATENT_IMAGE_PATH, 
					                                             InformationGlobal.PATENT_THUMBNAIL_START + 
					                                             patent.getPatentID() + 
					                                             "_" + currentImageNumber +
					                                             InformationGlobal.PNG);
			/*
			 * Check to see if it loaded properly by doing a nullcheck, and
			 *   if successful then set it as the picture and set boolean value
			 *   to true
			 * If it fails then change label to 'Could Not Display' and change
			 *   boolean to false
			 */
			if (patentThumbnailImage != null) {
				lblPicture.setIcon(new ImageIcon(patentThumbnailImage));
				didThumbnailLoad = true;
				// Since the pictures loaded successfully, then show instructions on how to use
				lblPictureInstructions.setText(InformationGlobal.returnCaption(numImages));
			} else {
				lblPicture.setFont(new Font("Arial Black", Font.PLAIN, 30));
				lblPicture.setText("Could Not Load Picture"); // Sets Label Text
				didThumbnailLoad = false; // Redundant but added for safe measure
			}
			// Gets the path to the file size patent image and assigns it
			fullSizedImage = new File(InformationGlobal.PATENT_IMAGE_PATH + 
                                      patent.getPatentID() + 
                                      InformationGlobal.PATENT_FILENAME_ENDING);
		} else {
			// Graphical Properties Of Picture Label
			lblPicture.setFont(new Font("Arial Black", Font.PLAIN, 30));
			lblPicture.setText("Picture Not Available"); // Sets Label Text
			lblPictureInstructions.setVisible(false);
		}
		
		lblPicture.addMouseWheelListener(new MouseWheelListener() {
			/*
			 * Mouse Wheel Listener For Picture Label
			 * When the user hovers over the Picture Label and scrolls,
			 *   the pictures will cycle through and the user can click 
			 *   on them
			 */
			public void mouseWheelMoved(MouseWheelEvent e) {
				
			    if (didThumbnailLoad) {
			    	/*
			    	 * The following 'if-else' code cycles through the number of 
			    	 * thumbnails image that pertain to the patent. This is done
			    	 * to make sure that the wrong thumbnail is not opened, which 
			    	 * will cause an Exception. Each time the user moves the mouse
			    	 * wheel, the image counter increases by one
			    	 */
			    	if (currentImageNumber == (numImages - 1)) {
			    		// If the currentImageNumber equals the maximum number of images minus one
			    		currentImageNumber = 0; // Reset image counter to 0
					} else { // Otherwise, increment image counter by 1
						currentImageNumber++;
					}
			    	
			    	// Load the correct patent thumbnail that corresponds to the image counter
					patentThumbnailImage = ImageLoader.returnImage(InformationGlobal.PATENT_IMAGE_PATH,
							                                       InformationGlobal.PATENT_THUMBNAIL_START + patent.getPatentID() + 
							                                       "_" + currentImageNumber + 
							                                       InformationGlobal.PNG);
					// If image could be loaded
					if (patentThumbnailImage != null) {
						lblPicture.setIcon(new ImageIcon(patentThumbnailImage)); // Set image
						didThumbnailLoad = true; // Change boolean value
					} else { // If Image could not be loaded
						lblPicture.setFont(new Font("Arial Black", Font.PLAIN, 30));
						lblPicture.setText("Could Not Load Picture"); // Sets Label Text to alert user
						didThumbnailLoad = false; // Redundant but added for safe measure
					}
					
					// Assign the respective full size image that pertains to the thumbnail 
					fullSizedImage = new File(InformationGlobal.PATENT_IMAGE_PATH + 
                                              patent.getPatentID() + 
                                              "_" + currentImageNumber + 
                                              InformationGlobal.PNG);
			    }		
			}
		});
		
		/*
		 * Mouse Click Listener For The Picture Label
		 * When the user clicks the picture, the full-sized image will
		 *   open on their computer using the default image browser
		 */
		lblPicture.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * If the full sized image exists, then it will be opened
				 * in the users's default image browser. If it does not
				 * exist, then the user will get a dialog box, indicating
				 * it. Also, the thumbnail has to load for the full-sized
				 * image to be accessible. 
				 */
				if (didThumbnailLoad) { // If the thumbnails loaded 
				    if (fullSizedImage != null) { // If full sized image loaded:
				    	// Get default image opening application
				    	Desktop defaultApplication = Desktop.getDesktop(); 
					    try {
					    	// Try to open image
							defaultApplication.open(fullSizedImage);
						} catch (IOException e1) {
							e1.printStackTrace(); // Print stackTrace
							// If the user does not have a method of opening the image, show message:
							JOptionPane.showMessageDialog(contentPane, "There Was A Problem Opening The Image" + 
							                                           "\nPlease Check Your Default Image Opener");
						}
				    } else {
				    	// If image does not exist then show message: 
				    	JOptionPane.showMessageDialog(contentPane, "We're Sorry, But Full-Sized Image Does Not Exist");
				    }
				}
			}
			
		});
		getContentPane().add(lblPicture);
		
		/*
		 * GridBag Layout Settings For Picture Label
		 */
		GridBagConstraints gbc_picLabel = new GridBagConstraints();
		gbc_picLabel.anchor = GridBagConstraints.WEST;
		gbc_picLabel.insets = new Insets(0, 0, 5, 0);
		gbc_picLabel.gridx = 0;
		gbc_picLabel.gridy = 5;
		contentPane.add(lblPicture, gbc_picLabel);
		
		// Properties For The Patent Citation Title 
		JLabel lblPatentCitationTitle = new JLabel("Patent Citations");
		lblPatentCitationTitle.setFont(new Font("Arial Black", Font.PLAIN, 40));
		
		// Mouse (Click) Listener For The Patent Citation Title
		lblPatentCitationTitle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Displays an explanatory message when Patent Citation Label is clicked
				JOptionPane.showMessageDialog(contentPane, "All of these patents, below, are cited by Patent #" + patent.getPatentID());
			}
		});
		
		/*
		 * GridBag Layout Settings For Patent Citation Title
		 */
		GridBagConstraints gbc_lblPatentCitationTitle = new GridBagConstraints();
		gbc_lblPatentCitationTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblPatentCitationTitle.anchor = GridBagConstraints.WEST;
		gbc_lblPatentCitationTitle.gridx = 0;
		gbc_lblPatentCitationTitle.gridy = 7;
		contentPane.add(lblPatentCitationTitle, gbc_lblPatentCitationTitle);
		
		/*
		 * Properties For The Citation Text Area
		 * &
		 * Its GridBag Layout Settings
		 */
		JTextArea citationsTextArea = new JTextArea();
		citationsTextArea.setFont(new Font("Arial Black", Font.PLAIN, 30));
		citationsTextArea.setWrapStyleWord(true);
		citationsTextArea.setLineWrap(true);
		citationsTextArea.setBackground(UIManager.getColor("Button.background"));
		GridBagConstraints gbc_citationsTextArea = new GridBagConstraints();
		gbc_citationsTextArea.insets = new Insets(0, 0, 5, 0);
		gbc_citationsTextArea.fill = GridBagConstraints.BOTH;
		gbc_citationsTextArea.gridx = 0;
		gbc_citationsTextArea.gridy = 14;
		contentPane.add(citationsTextArea, gbc_citationsTextArea);
		
		/*
		 * Scroll pane for patent citation label
		 * When there are many patent citations, a scroll bar is 
		 *   added so the user can scroll through them all
		 * Scroll pane's GridBag Layout Settings are located below
		 */
		JScrollPane scrollPaneCitations = new JScrollPane(citationsTextArea);
		// Only shows vertical scroll bar if needed. We removed it because it doesn't look consistent
		// scrollPaneCitations.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		if (BORDER_IS_INVISIBLE) scrollPaneCitations.setBorder(null); // Sets text area border to null 
		GridBagConstraints gbc_scrollPaneCitations = new GridBagConstraints();
		gbc_scrollPaneCitations.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPaneCitations.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneCitations.gridx = 0;
		gbc_scrollPaneCitations.gridy = 8;
		contentPane.add(scrollPaneCitations, gbc_scrollPaneCitations);
		
		// Checks if citation list is empty and change citation label accordingly
		if (patentCitations.isEmpty()) {
			// Empty citation list = No Citations
			citationsTextArea.setText("No Citations Found");
		} else {
			// Otherwise, convert to String and set it to citation label
			citationsTextArea.setText(patentCitations.toString());
		}
		
		/*
		 * Sets Properties & GridBag Layout Settings For The
		 * Text Field Where User Enters Second Patent ID
		 */
		textFieldSecondPatent = new JTextField(10);
		textFieldSecondPatent.setForeground(Color.BLACK);
		textFieldSecondPatent.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_textFieldSecondPatent = new GridBagConstraints();
		gbc_textFieldSecondPatent.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSecondPatent.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldSecondPatent.gridx = 0;
		gbc_textFieldSecondPatent.gridy = 9;
		contentPane.add(textFieldSecondPatent, gbc_textFieldSecondPatent);
		textFieldSecondPatent.setColumns(10);
		
		/*
		 * TextPrompt Settings For Patent ID Text Field
		 * Creates ghost text for the Patent ID text field that goes away
		 *   when the user focuses on the Patent ID text field. When the user
		 *   clicks away and the text field is empty, the ghost text return and 
		 *   prompts user to "Enter... ID"
		 */
		TextPrompt jTextField = new TextPrompt("Enter Patent ID ", textFieldSecondPatent);
		jTextField.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		jTextField.setForeground(Color.GRAY);
		jTextField.changeAlpha(0.5f);
		jTextField.changeStyle(Font.ITALIC);
		jTextField.setHorizontalTextPosition(SwingConstants.LEFT);

		// If loaded icon image is not null, then add it to the Text Field
		if (linkIconImage != null) jTextField.setIcon(new ImageIcon(linkIconImage));
		
		/*
		 * Physical Properties & GridBag Layout Settings For
		 *   Status JLabel
		 * This label provides feedback to the user when they enter
		 *   in a Patent ID and click 'Connect'
		 */
		JLabel lblStatus = new JLabel("Status: Please Enter A Patent ID & Click \"Connect Patents\" To See (Shortest) Path");
		lblStatus.setForeground(Color.GRAY);
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.insets = new Insets(0, 0, 5, 0);
		gbc_lblStatus.anchor = GridBagConstraints.WEST;
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 11;
		contentPane.add(lblStatus, gbc_lblStatus);
		
		// Properties For The 'Connect' Button
		JButton btnConnectPatents = new JButton("Connect Patents");
		btnConnectPatents.setFont(new Font("Arial", Font.PLAIN, 25));
		btnConnectPatents.setMargin(new Insets(4,4,4,4));
		
		// Event Action Listener For The Connect Button
		btnConnectPatents.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
		
				// Stores user input into a String
				String parseInput = textFieldSecondPatent.getText();
				
				// Parses user input using custom Parser and stores result as exit code
				byte exitCode = Parser.parseExitCode(parseInput);
				
				// Switch Ladder based on exit code
				switch(exitCode) {
					case 0: // If String is empty
						//System.out.println(" | Exit Code: 0");
						lblStatus.setForeground(InformationGlobal.returnLabelColor(exitCode)); // Change Status label to Gray
						lblStatus.setText(InformationGlobal.returnStatusLabel(exitCode));
						break;
					case 1: // If String is of correct format
						//System.out.println(" | Exit Code: 1");
						
						// Checking if patent is in the DB
						if (patentDB.contains(parseInput)) {
				
							lblStatus.setForeground(InformationGlobal.returnLabelColor(exitCode)); // Change Status label to Green
							lblStatus.setText(InformationGlobal.returnStatusLabel(exitCode));
							
							/*
							 * Get shortest path between two patents from the backend
							 * Returns result as a List of Strings
							 */
							List<String> shortestPathBetweenPatents = patentDB.shortestPath(patent.getPatentID(), textFieldSecondPatent.getText()).getPath();
							//System.out.println(shortestPathBetweenPatents);
					        
							// If shortest path is not null (i.e. If path exists):
					        if (shortestPathBetweenPatents != null) {
					        	// Fire up the graph interface show the connection between two patents
					        	GraphInterface frame = new GraphInterface(patentDB, shortestPathBetweenPatents);
							    //frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
							    frame.setSize(GraphInterface.FRAME_WIDTH, GraphInterface.FRAME_HEIGHT);
							    frame.setVisible(true);
					        } else { 
					        	// If there is no path between the patents then display message:
					        	lblStatus.setText("There is no path. Please enter another patent");
					        	JOptionPane.showMessageDialog(contentPane, "There is no path between #" + 
					                                                        patent.getPatentID() + 
					                                                       " and #" + parseInput);		
					        }
						} else {
							lblStatus.setForeground(Color.BLUE); // Change Status label to Blue
							lblStatus.setText("PatentID Not Found In Database");
						}
						break;
					case 2: // If String is not the correct length
						//System.out.println(" | Exit Code: 2");
						lblStatus.setForeground(InformationGlobal.returnLabelColor(exitCode)); // Change Status label to Gray
						lblStatus.setText(InformationGlobal.returnStatusLabel(exitCode));
						break;
					case 3: // If String could not be converted into BigInteger
						//System.out.println(" | Exit Code: 3");
						lblStatus.setForeground(InformationGlobal.returnLabelColor(exitCode)); // Change Status label to Red 
						lblStatus.setText(InformationGlobal.returnStatusLabel(exitCode));
						break;
					case 4: // Last case for Parser
						//System.out.print(" | Exit Code : 4");
						lblStatus.setForeground(InformationGlobal.returnLabelColor(exitCode)); // Change Status label to Black
						lblStatus.setText(InformationGlobal.returnStatusLabel(exitCode));
						break;
					default: // In case all other cases are not met
						System.out.print("ERROR: Something Went Terribly Wrong. Please Restart PatentConnect.");
						lblStatus.setForeground(InformationGlobal.returnLabelColor(exitCode)); // Change Status label to Black
						lblStatus.setText(InformationGlobal.returnStatusLabel(exitCode));
				}
			}
		});
		
		/*
		 * GridBag Layout Settings For 'Connect' Button
		 */
		GridBagConstraints gbc_btnConnectPatents = new GridBagConstraints();
		gbc_btnConnectPatents.insets = new Insets(0, 0, 5, 0);
		gbc_btnConnectPatents.fill = GridBagConstraints.BOTH;
		gbc_btnConnectPatents.gridx = 0;
		gbc_btnConnectPatents.gridy = 10;
		contentPane.add(btnConnectPatents, gbc_btnConnectPatents);
		
		/*
		 * On exit delete all image files in 'tempAssets'
		 */
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        GooglePatents.deletePictures(patent.getPatentID(), numImages);
		        setVisible(false);
		        dispose();
		    }
		});
	}
}

/*

    ROUGH CODE
    PLEASE IGNORE

List<String> tempPath = new ArrayList<String>() {{ 
    add("3858280"); 
    add("3858282");
    add("3858283");
    add("3858284");
    add("3858285");
    add("3858286");
    add("3858287");
}}; 

//GraphInterface graphs = new GraphInterface(patentDB);
//GraphInterface graph = new GraphInterface(patentDB);
//graph.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//graph.setSize(1200, 600);
//graph.setVisible(true);
//String x = new String("me");
//GraphInterface.main(x);


try {
	thumbnailImage = ImageIO.read(new File(GlobalStrings.PATENT_IMAGE_PATH + "thumb_" + patent.getPatentID() + "_0.png"));
	noPictureLoaded = false;
} catch (IOException e1) {
	//e1.printStackTrace();
	noPictureLoaded = true;
	thumbnailImage = null; // Redundant but added for safe measure
}

 */