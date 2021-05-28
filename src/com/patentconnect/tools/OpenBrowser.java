// PLEASE IGNORE THIS CLASS. PLEASE READ THE FOLLOWING COMMENT:
/* 
 * This class was not created by the PatentConnect Team. It was
 *   created by the developers at RoseIndia.Net. The full code can
 *   be viewed over here:
 *   https://www.roseindia.net/tutorial/java/swing/openBrowser.html
 * 
 * We give full credit to the the original developers of this class.
 * Unfortunately, there names are not listed on the website. Nonetheless,
 *   we recognize and thank the developers at RoseIndia.net.
 * 
 * Please ignore this class for marking purposes. This code was not 
 *   created by the PatentConnect Team. This code mostly affects the
 *   UI portion of PatentConnect. It opens up Web Pages. It has nothing 
 *   to do with the Searching, Sorting, Graphing, or Parsing aspects of 
 *   PatentConnect.
 *   
 * Furthermore, since this class was not created by us, we are not going
 *   to create test cases for it. We will assume that this class works
 *   as intended.
 *   
 * Once again, we give full credit to RoseIndia.net for creating
 *   this class. Thank you!
 *
 * Sincerely,
 * PatentConnect
 */
package com.patentconnect.tools;

import javax.swing.*;

public class OpenBrowser {

	/**
	 * A method to open the default browser for the system.
	 * @param url the url to open in the browser.
	 */
	public static void openURL(String url) {
		String osName = System.getProperty("os.name");
		try {
			if (osName.startsWith("Windows"))
				Runtime.getRuntime().exec(
						"rundll32 url.dll,FileProtocolHandler " + url);
			else {
				String[] browsers = { "firefox", "opera", "konqueror",
						"epiphany", "mozilla", "netscape" };
				String browser = null;
				for (int count = 0; count < browsers.length && browser == null; count++)
					if (Runtime.getRuntime().exec(
							new String[] { "which", browsers[count] })
							.waitFor() == 0)
						browser = browsers[count];
				Runtime.getRuntime().exec(new String[] { browser, url });
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error in opening browser"
					+ ":\n" + e.getLocalizedMessage());
		}
	}
	
//	public static void main(String[] args) {
//		openURL("https://gitlab.cas.mcmaster.ca/patentconnect/patentconnect");
//	}
}