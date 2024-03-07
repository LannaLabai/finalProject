package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.*;

public class HomeScreenView extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JFrame nextFrame;
	private JToolBar toolBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeScreenView frame = new HomeScreenView();
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
	public HomeScreenView() {
		
		super("Home Screen");
        initialize();
        setVisible(true);
	}

	public HomeScreenView(JFrame nextFrame) {
		
		super("Home Screen");
        initialize();
        this.nextFrame = nextFrame;
        setVisible(true);
	}
	
	public void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(500,300));
        this.setLayout(new BorderLayout());
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        /*
        JLabel backgroundImageLabel = new JLabel();
        ImageIcon backgroundImage = new ImageIcon("path/to/background/image.jpg");
        backgroundImageLabel.setIcon(backgroundImage);
        backgroundImageLabel.setBounds(-6, 87, 867, 446);
        contentPane.add(backgroundImageLabel);*/
        
        toolBar = new JToolBar();
        toolBar.setBounds(133, 471, 397, 51);
        String[] toolbarButtons = {"About Us", "Safety", "Your Room", "Room Service", "Hotel Amenities", 
                                   "Housekeeping", "Donate", "My Bill", "Alarm", "WiFi", "Request", "TV"};
        for (String buttonLabel : toolbarButtons) {
            JButton button = new JButton(buttonLabel);
            toolBar.add(button);
        }
        contentPane.add(toolBar, BorderLayout.NORTH);
        
        for (Component comp : toolBar.getComponents()) {
            if (comp instanceof JButton) { // Check if the component is a JButton
                JButton button = (JButton) comp; // Cast the component to JButton
                button.addActionListener(this); // Add the ActionListener to the button
            }
        }
        
        JPanel donatePanel = new JPanel(null);
        donatePanel.setBounds(-7, 301, 662, 138);
        contentPane.add(donatePanel, BorderLayout.CENTER);
        /*
        JLabel donateImageLabel = new JLabel();
        ImageIcon donateImage = new ImageIcon("path/to/donate/image.jpg");
        donateImageLabel.setIcon(donateImage);
        donateImageLabel.setBounds(0, 0, 270, 169);
        donatePanel.add(donateImageLabel);*/
        
        JLabel donateTextLabel = new JLabel("Join us in building a stronger community", JLabel.CENTER);
        donateTextLabel.setBounds(240, 50, 200, 50);
        donateTextLabel.setForeground(Color.WHITE);
        donateTextLabel.setFont(new Font("Arial", Font.BOLD, 15));
        donatePanel.add(donateTextLabel);
        
        JButton donateButton = new JButton("Donate now");
        donateButton.setBounds(0, 40, 100, 30);
        donateButton.setFont(new Font("Arial", Font.BOLD, 13));
        donatePanel.add(donateButton);
        
        JLabel donateDescriptionLabel = new JLabel("Your donation can make a difference today!", JLabel.CENTER);
        donateDescriptionLabel.setBounds(0, 2, 662, 50);
        donateDescriptionLabel.setFont(new Font("Arial", Font.BOLD, 25));
        donateDescriptionLabel.setForeground(Color.WHITE);
        donatePanel.add(donateDescriptionLabel);
        
        JLabel mainLabel = new JLabel("Label");
        mainLabel.setBounds(50, 0, 100, 30);
        contentPane.add(mainLabel, BorderLayout.CENTER);
        
        JLabel dateTimeLabel = new JLabel("Date");
        dateTimeLabel.setBounds(10, 0, 100, 30);
        contentPane.add(dateTimeLabel, BorderLayout.CENTER);
        
        JLabel logoLabel = new JLabel();
        //ImageIcon logoImage = new ImageIcon("path/to/logo/image.jpg");
        //logoLabel.setIcon(logoImage);
        logoLabel.setBounds(150, 0, 139, 144);
        contentPane.add(logoLabel, BorderLayout.NORTH);
        
        JLabel weatherLabel = new JLabel("Weather");
        weatherLabel.setBounds(10, 10, 100, 30);
        contentPane.add(weatherLabel, BorderLayout.NORTH);
        
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		System.out.println(command);
	    if (command != null) {
	        switch (command) {
	            case "About Us":
	            	new AboutUsView(this);
	                this.setVisible(false);
	                break;
	            case "Safety":
	                // Handle the "Safety" button click
	                System.out.println("Safety button clicked");
	                break;
	            case "Your Room":
	                // Handle the "Your Room" button click
	                System.out.println("Your Room button clicked");
	                break;
	            // Add cases for other buttons as needed
	            case "Room Service":
	                new RoomServiceView(this);
	                this.setVisible(false);
	                break;
	        }
	    }
		
	}

}
