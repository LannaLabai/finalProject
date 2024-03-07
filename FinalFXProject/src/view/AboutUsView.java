package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import control.SQLQueries;
import model.*;

import java.awt.*;

public class AboutUsView extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JFrame nextFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutUsView frame = new AboutUsView();
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
	public AboutUsView() {
		super("About Us");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		initialize();
		setVisible(true);
	}
	
	public AboutUsView(JFrame nextFrame) {
		super("About Us");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		this.nextFrame= nextFrame;
		initialize();
		setVisible(true);
	}
	
	
	public void initialize() {
	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    contentPane.setLayout(new BorderLayout(0, 0));

	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
	    contentPane.add(scrollPane, BorderLayout.CENTER);

	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    scrollPane.setViewportView(panel);
	    setContentPane(contentPane);

	    // Add title
	    JLabel lblTitle = new JLabel("<html><h1>Welcome to Domus Bat Galim</h1></html>");
	    panel.add(lblTitle);

	    // Add introductory text
	    JTextArea txtIntro = new JTextArea();
	    txtIntro.setEditable(false);
	    txtIntro.setLineWrap(true);
	    txtIntro.setWrapStyleWord(true);
	    txtIntro.setText("We hope that you have a pleasant stay here at Domus Bat Galim. In case you may need anything, please do not hesitate to reach out to us with the contact information below or to send a request through the Request page.");
	    panel.add(txtIntro);

	    // Add hotel information
	    JTextArea txtHotelInfo = new JTextArea();
	    txtHotelInfo.setEditable(false);
	    txtHotelInfo.setLineWrap(true);
	    txtHotelInfo.setWrapStyleWord(true);
	    txtHotelInfo.setText("Hotel Information:\n" +
	            "Hotel name: " + Hotel.getHotelName() + "\n" +
	            "Hotel phone number: " + Hotel.getHotelPhoneNumber() + "\n" +
	            "Hotel address: " + Hotel.getHotelAddress() + "\n" +
	            "Hotel email: " + Hotel.getHotelEmail());
	    panel.add(txtHotelInfo);

	    // Add more about rooms title
	    JLabel lblRooms = new JLabel("<html><h2>More About Our Rooms:</h2></html>");
	    panel.add(lblRooms);

	    // Add room type descriptions
	    for (RoomType rt : Hotel.getInstance().getRoomTypes()) {
	        JTextArea txtRoomTypeDesc = new JTextArea();
	        txtRoomTypeDesc.setEditable(false);
	        txtRoomTypeDesc.setLineWrap(true);
	        txtRoomTypeDesc.setWrapStyleWord(true);
	        String roomTypeDesc = rt.getRoomType() + "\n";
	        if (rt.getRoomSize() != null && !rt.getRoomSize().isEmpty()) {
	            roomTypeDesc += rt.getRoomSize() + "\n";
	        }
	        roomTypeDesc += rt.getMaxNumOfGuests() + " guests\n";
	        roomTypeDesc += rt.getRoomTypeDesc() + "\n";
	        if (rt.getKeyFeatures() != null && !rt.getKeyFeatures().isEmpty()) {
	            roomTypeDesc += "Key features: " + rt.getKeyFeatures() + "\n";
	        }
	        roomTypeDesc += "Beds included: " + rt.getBedsDesc() + "\n";
	        txtRoomTypeDesc.setText(roomTypeDesc);
	        panel.add(txtRoomTypeDesc);
	    }
	    
	    pack();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
