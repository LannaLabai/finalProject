package view;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

import model.Hotel;
import utils.ServiceType;

public class PoolView extends BasicViewTemplate {


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PoolView frame = new PoolView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PoolView() {
		super("Pool");
	}
	
	public PoolView(JFrame nextFrame) {
		super("Pool",nextFrame);
		
	}
	@Override
	public void initialize() {
	    initializeDefault();
	    contentPane.setPreferredSize(new Dimension(800, 600)); // Adjust the size

	    // Create background label
	    ImageIcon backgroundImage = new ImageIcon("ImagesOfProject/backgroundImage.jpg");
	    JLabel backgroundLabel = new JLabel(backgroundImage);
	    backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());

	    // Create a transparent panel to hold the title and subtext
	    JPanel overlayPanel = new JPanel();
	    overlayPanel.setOpaque(false); // Make the panel transparent
	    overlayPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10)); // Stack components vertically with center alignment and 10px vertical gap
	    overlayPanel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());

	    // Add title
	    JLabel labelTitle = new JLabel("<html><h1>Pool <br></h1></html>");
	    labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label horizontally
	    overlayPanel.add(labelTitle);

	    // Add some vertical space between title and subtext
	    overlayPanel.add(Box.createRigidArea(new Dimension(0, 20)));

	    // Add subtext
	    JLabel lblSubtextSubtext = new JLabel("<html><p width=\"400\">" + Hotel.getInstance().getServiceByType(ServiceType.POOL).get(0).getServiceDesc() + "</p></html>");
	    lblSubtextSubtext.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label horizontally
	    overlayPanel.add(lblSubtextSubtext); // Add the subtext directly to the overlay panel
	    
	    // Create a layered pane to hold the background image and overlay panel
	    JLayeredPane layeredPane = new JLayeredPane();
	    layeredPane.setPreferredSize(new Dimension(800, 600)); // Adjusted size for more vertical space
	    layeredPane.add(backgroundLabel, new Integer(0)); // Add background label to the lowest layer
	    layeredPane.add(overlayPanel, new Integer(1)); // Add overlay panel to a higher layer

	    // Add the layered pane to the content pane
	    contentPane.add(layeredPane, BorderLayout.CENTER);
	}

}
