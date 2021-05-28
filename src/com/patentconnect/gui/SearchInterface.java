package com.patentconnect.gui;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import com.patentconnect.backend.db.PatentDatabase;
import com.patentconnect.tools.ImageLoader;
import com.patentconnect.tools.OpenBrowser;
import com.patentconnect.tools.InformationGlobal;
import com.patentconnect.tools.Parser;
import com.patentconnect.tools.TextPrompt;

import com.jgoodies.forms.factories.DefaultComponentFactory;

public class SearchInterface extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblStatus;
	private static final String PATENT_CONNECT_GITLAB_URL = "https://gitlab.cas.mcmaster.ca/patentconnect/patentconnect";
	
	// Dimensions of the Search Interface window
	private static final int HEIGHT = 180;
	private static final int WIDTH = 370;
	
	/**
	 * Create the frame.
	 */
	public SearchInterface(PatentDatabase patentDB) {
		
		/*
		 * Properties of the window; characteristics such as size, dimension, border, etc.
		 * The absolute size of the window is 370 x 180 (width x height). This is immutable.
		 */
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		// Closing search doesn't close everything
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        setVisible(false);
		        dispose();
		    }
		});
		
		setBounds(100, 100, WIDTH, HEIGHT);
		setTitle("Patent Search");
		setResizable(false);
		
		// Loads image of the search icon
		Image searchIconImage = ImageLoader.returnImage(InformationGlobal.ICON_PATH, InformationGlobal.SEARCH_ICON); 
		if (searchIconImage != null) setIconImage(searchIconImage); // Sets icon to 'image object'
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);
		
		/*
		 * This block of code contains the settings for the PatentConnect Label
		 * at the top of the program. It sets the font and color and size of the label,
		 * as well as position/coordinates.
		 */
		JLabel lblPatentconnect = DefaultComponentFactory.getInstance().createLabel("PatentConnect");
		lblPatentconnect.setFont(new Font("Lucida Grande", Font.PLAIN, 37));
		
		/*
		 * Click Listener For The PatentConnect Label
		 * When the label is clicked, it will open a dialog box that explains
		 *   what is PatentConnect and then gives an option to open up the
		 *   GitLab webpage. However, only authorized users can view it.
		 */
		lblPatentconnect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//System.out.println("Mouse Click On PatentConnect Label Detected");
				
				// Stores the result of the JOptionPane result/exit status into int
				int informationPanelReturnCode = JOptionPane.showOptionDialog(contentPane, 
						
						// This is the main message of the dialog box
				        "PatentConnect is a multi-faceted patent landscaping application\n"   + 
				        "that enables its users and clients to efficiently and effectively\n" +
				        "search for patent citations and relations.\n\n" + 
				        
				        "Do you want to visit the GitLab page for more information?", 
				        
				        // This is the title of the dialog box
				        "PatentConnect Information", 
				        
				        // This is the button options // Options are binary
				        JOptionPane.OK_OPTION, 
				        JOptionPane.INFORMATION_MESSAGE, 
				        null,
				        
				        // Changes the button texts to the following
				        new String[]{"Absolutely, I'm Curious!", "I'd Rather Play Around With It"},
				        "default");
				
				// If 'user' presses 'yes', then:  
				if (informationPanelReturnCode == 0) {
					
					//System.out.println("Fork Us On GitLab!");
					JOptionPane.showMessageDialog(contentPane, "Note: You need to be signed into Gitlab\n" + 
															   "in order to see the code.\n\n" + 
															   "P.S.: Remember to fork us!");
					// Open up GitLab repo
					OpenBrowser.openURL(PATENT_CONNECT_GITLAB_URL);
				} else {
					//System.out.println("We Hope You Like It!");
					JOptionPane.showMessageDialog(contentPane, "Thank you for using PatentConnect.\n" + 
															   "We hope you like it as much as we do!\n");
				}
			}
		});
		
		/*
		 * GridBag Layout Settings For PatentConnect Label
		 */
		GridBagConstraints gbc_lblPatentconnect = new GridBagConstraints();
		gbc_lblPatentconnect.gridwidth = 12;
		gbc_lblPatentconnect.insets = new Insets(0, 0, 5, 5);
		gbc_lblPatentconnect.gridx = 0;
		gbc_lblPatentconnect.gridy = 0;
		contentPane.add(lblPatentconnect, gbc_lblPatentconnect);
		
		/*
		 * Properties of the "Status" Label. This label indicates what the program is doing and
		 * if the user input is valid or not. An appropriate message is displayed. For example:
		 * if the user inputs a string with non-numeric values, it will indicate that the input 
		 * does not adhere to standards.
		 */
		textField = new JTextField(10);
		TextPrompt jTextField = new TextPrompt("Enter Patent ID ", textField);
		jTextField.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		jTextField.setForeground(Color.GRAY);
		jTextField.changeAlpha(0.5f);
		jTextField.changeStyle(Font.ITALIC);
		jTextField.setHorizontalTextPosition(SwingConstants.LEFT);
		
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 8;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		/*
		 * Properties of the TextField. The TextField is where the user enters in
		 * the patent ID they would like to look up. The properties, such as size, of
		 * the text field are below.
		 */
		
		// Sets icon to loaded Image 'image'
		if (searchIconImage != null) jTextField.setIcon(new ImageIcon(searchIconImage));

		/*
		 * Properties and behaviour of the "Search" button that is located below
		 * the TextField and PatentConnect Label. The properties indicate things like
		 * size and position. The behaviour is what the program will do when the button
		 * is clicked. When clicked, it will parse user input and process it. It will
		 * check to see if it fits the criteria for the patent ID.
		 */
		
		/*
		 * Properties & Functionality For Search Button; btnSearch
		 */
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnSearch.setMargin(new Insets(2,2,2,2));
		
		// Functionality of Search Button (What happens when button is clicked)
		btnSearch.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				// Get text from TextField and store into String
				String parseField = textField.getText();
				
				// Store exit code from Parser into an int
				byte exitCode = Parser.parseExitCode(parseField);
				
				// Switch Ladder for exit code
				switch(exitCode) {
					case 0: // If String is empty
						//System.out.println(" | Exit Code: 0");
						lblStatus.setForeground(InformationGlobal.returnLabelColor(exitCode)); // Change Status label to Gray
						lblStatus.setText(InformationGlobal.returnStatusLabel(exitCode));
						break;
					case 1: // If String is the correct format
						//System.out.println(" | Exit Code: 1");
						if (patentDB.contains(parseField)) {
							lblStatus.setForeground(InformationGlobal.returnLabelColor(exitCode)); // Change Status label to Green
							lblStatus.setText(InformationGlobal.returnStatusLabel(exitCode));
							// Create An Information Interface Window
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									try {
										// Print for debugging dataset and backend
										//System.out.println(patentDB.contains(parseField));
										//System.out.println(patentDB.getPatent(parseField).getPatentID());
										InformationInterface frame = new InformationInterface(patentDB, patentDB.getPatent(parseField));
										frame.setVisible(true);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							}); 
						} else {
							lblStatus.setForeground(Color.BLUE); // Change Status label to Blue
							lblStatus.setText("Patent ID Not Found In Database");
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
		 * GridBag Layout Settings For Search Button
		 */
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.gridwidth = 8;
		gbc_btnSearch.gridx = 1;
		gbc_btnSearch.gridy = 2;
		contentPane.add(btnSearch, gbc_btnSearch);
		
		/*
		 * Properties & GridBag Layout Settings For Status Label
		 */
		lblStatus = DefaultComponentFactory.getInstance().createLabel("Status: Waiting For User Input");
		lblStatus.setForeground(Color.GRAY);
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.gridwidth = 6;
		gbc_lblStatus.insets = new Insets(0, 0, 0, 5);
		gbc_lblStatus.gridx = 1;
		gbc_lblStatus.gridy = 3;
		contentPane.add(lblStatus, gbc_lblStatus);
	}
}

/*
 * 	ROUGH CODE
 *  PLEASE IGNORE THIS
// empty string --> u didnt enter in anything
// non-numerics are not allowed
// try to use integer.parseInt
// correct string --> is all numbers and of length 5 - 7

 * 1. check if empty string 2. try integer.parseint a. if works
 * then check length => length must be in between 5 to 7 b. if
 * no work then spit error =>
 * ("only numerics are allowed of the format XXXXXXX")
 
// Color red = new Color(255,0,0);
// Color green = new Color(0,255,0);
// Color gray
// long parseString;
// System.out.println(textField.getText());
// System.out.println("#####");
// System.out.println(textField.toString());
 * 
 */