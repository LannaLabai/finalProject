package view;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

import model.Hotel;
import model.Service;
import utils.ServiceType;

public class TripsView extends BasicViewTemplate {
	
	private ArrayList<JCheckBox> tripsCB;
	private ArrayList<Service> trips;

	private JButton btnOrder;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TripsView frame = new TripsView();
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
	public TripsView() {
		super("Trips");
		
	}
	
	public TripsView(JFrame nextFrame) {
		super("Trips",nextFrame);
	}
	
	@Override
	public void initialize() {
		initializeDefault();
		
		lblTitle.setText("<html><h1>Trips</h1></html>");
		lblSubtext.setText("This hotel offers several tours to Tel Aviv, Haifa, and the Northern region of Israel. "
				+ "The closest dates are written below:");
		
		trips = Hotel.getInstance().getServiceByType(ServiceType.TRIPS);
		tripsCB = new ArrayList<>();
		
		for(Service s : trips) {
			JPanel tripPanel = new JPanel();
			tripPanel.setLayout(new BoxLayout(tripPanel,BoxLayout.Y_AXIS));
			JCheckBox checkBox = new JCheckBox(s.getServiceName());
			//JLabel lblCost = new JLabel(Hotel.getSe);
			tripPanel.add(checkBox);
			tripPanel.add(new JLabel(s.getServiceDesc()));
			mainPanel.add(tripPanel);
			
		}
		
		btnOrder = new JButton("Book");
	    btnOrder.addActionListener(this);
		mainPanel.add(btnOrder);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		
	}

}
