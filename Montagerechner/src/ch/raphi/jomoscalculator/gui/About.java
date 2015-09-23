package ch.raphi.jomoscalculator.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * This Class shows information from the author and the helpers
 * @author raphaelbrunner
 *
 */
public class About extends JFrame{

	private static final long serialVersionUID = -8402733346232415529L;


	/**
	 * constructor for the information window
	 */
	public About(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("About");
		
		JLabel content = new JLabel();
		content.setHorizontalAlignment(JLabel.CENTER);
		
		//TODO create About content
		content.setText("Version 1.0");
		
		add(content);
		
		pack();
		setVisible(true);
	}

}
