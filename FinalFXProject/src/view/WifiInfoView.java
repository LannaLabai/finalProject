package view;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

import model.Hotel;

public class WifiInfoView extends BasicViewTemplate {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WifiInfoView frame = new WifiInfoView();
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
	public WifiInfoView() {
		super("Wifi Information");
	}
	
	public WifiInfoView(JFrame nextFrame) {
		super("Wifi Information",nextFrame);
	}
	
	@Override
	public void initialize() {
		initializeDefault();
		
		lblTitle.setText("<html><h1>Wifi Information</h1></html>");
		lblSubtext.setText("");
		
		JLabel lblUsername = new JLabel("Username: "+Hotel.getWifiUsername());
		JLabel lblPassword = new JLabel("Password: "+Hotel.getWifipassword());
		
		mainPanel.add(lblUsername);
		mainPanel.add(lblPassword);
		
	}

}
