package view;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

public class TripsView extends BasicViewTemplate {
	
	private ArrayList<JCheckBox> trips;

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
				+ "Please contact the lobby for information on pricing as it varies from season to season. "
				+ "The closest dates are written below:");
		
		trips = new ArrayList<>();
		//initialize trips list
		//for()
		
		btnOrder = new JButton("Order");
	    btnOrder.addActionListener(this);
		mainPanel.add(btnOrder);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
