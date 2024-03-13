package view;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

import utils.ServiceType;

public class HotelAmenitiesView extends BasicViewTemplate {

	private ArrayList<JButton> buttons;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HotelAmenitiesView frame = new HotelAmenitiesView();
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
	public HotelAmenitiesView()  {
		super("Hotel Amenities");
	}
	
	public HotelAmenitiesView(JFrame nextFrame) {
		super("Hotel Amenities",nextFrame);
		
	}
	
	@Override
	public void initialize() {
		initializeDefault();
		
		contentPane.add(btnBack,BorderLayout.NORTH);
		
		lblTitle.setText("<html><h1>Hotel Amenities</h1></html>");
		lblSubtext.setText("This hotel offers a wide variety of services and amenities. Details listed in each of the following pages:");
		
		JPanel btnPanel = new JPanel(new GridLayout(3,3));
		mainPanel.add(btnPanel);
		
		for(ServiceType st: ServiceType.values()) {
			if(st!=ServiceType.ENTERTAINMENT) {
				JButton btn = new JButton(st.toString());
				btnPanel.add(btn);
				btn.addActionListener(this);
			}
		}
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		JButton sourceButton = (JButton) e.getSource();
	    String buttonText = sourceButton.getText();
	    
	    if (buttonText.equals(ServiceType.DINING.toString())) {
	    	new DiningView(this);
            this.setVisible(false);
	    } else if (buttonText.equals(ServiceType.SPA.toString())) {
	    	new SpaView(this);
            this.setVisible(false);
	    } else if (buttonText.equals(ServiceType.POOL.toString())) {
	    	new PoolView(this);
            this.setVisible(false);
	    }else if (buttonText.equals(ServiceType.BABYSITTING.toString())) {
	    	new BabysittingView(this);
            this.setVisible(false);
	    } else if (buttonText.equals(ServiceType.GYMACTIVITIES.toString())) {
	    	new GymActivitiesView(this);
            this.setVisible(false);
	    }else if (buttonText.equals(ServiceType.TRIPS.toString())) {
	    	new TripsView(this);
            this.setVisible(false);
	    } else if (buttonText.equals(ServiceType.MANIPEDI.toString())) {
	    	new ManicureAndPedicureView(this);
            this.setVisible(false);
	    }
	    if(e.getSource()==btnBack) {
			nextFrame.setVisible(true);
            this.setVisible(false);
		}
	}

}
