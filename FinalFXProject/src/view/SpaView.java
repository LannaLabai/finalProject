package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

import control.SQLQueries;
import model.BookedRoomBooksService;
import model.Hotel;
import model.PaidService;
import model.Service;
import model.Session;
import utils.ServiceType;

public class SpaView extends BasicViewTemplate {
	
	private JButton btnOrder;
	
	private JComboBox<String> comboBoxDates;
	private JComboBox<String> comboBoxTime;
	private JTextField txtNumParticipants;
	private JComboBox<String> comboBoxSpa;
	private JLabel spaPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpaView frame = new SpaView();
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
	public SpaView() {
		super("Spa");
	}
	
	public SpaView(JFrame nextFrame) {
		super("Spa",nextFrame);
	}
	
	@Override
	public void initialize() {
	    initializeDefault();
	    
	    // Set mainPanel layout to BorderLayout
	    mainPanel.setLayout(new BorderLayout());
	    
	    JPanel orderSpaPanel = new JPanel(new GridBagLayout());
	    JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Set layout to FlowLayout with center alignment
	    titlePanel.setOpaque(false);
	    lblTitle.setText("<html><h1>Spa<br></h1></html>");
	    String desc = "";
	    
	    String[] spaServices = new String[Hotel.getInstance().getServiceByType(ServiceType.SPA).size()-3];
	    
	    int i = 0;
	    for(Service s: Hotel.getInstance().getServiceByType(ServiceType.SPA)) {
	        if(s.getServiceName().equals("Spa")) {
	            desc = s.getServiceDesc();
	        }
	        if(!s.getServiceName().equals("Spa") && !s.getServiceName().equals("Jacuzzi") && !s.getServiceName().equals("Sauna")){
	            spaServices[i++] = s.getServiceName();
	        }
	    }
	    lblSubtext.setText(desc);
	    
	 // Add components to titlePanel
	    titlePanel.add(lblTitle);
	    titlePanel.add(new JSeparator(SwingConstants.HORIZONTAL));
	    titlePanel.add(lblSubtext);
	   

	    JLabel lblOrder = new JLabel("You can order a spa session by filling out the form below:");
	    
	    JLabel lblChooseService = new JLabel("Desired spa service: ");
	    comboBoxSpa = new JComboBox<>(spaServices);
	    comboBoxSpa.addActionListener(this);
	    
	    spaPrice = new JLabel("");
	    
	    JLabel lblSpaDate = new JLabel("Spa date: ");
	    LocalDate today = LocalDate.now();
	    LocalDate[] availableDates = new LocalDate[7];
	    String[] dateStrings = new String[7];
	    for (i = 0; i < 7; i++) {
	        availableDates[i] = today.plusDays(i);
	        dateStrings[i] = availableDates[i].toString();
	    }
	    
	    comboBoxDates = new JComboBox<>(dateStrings);
	    
	    String[] hours = {"8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00",
	            "19:00", "20:00", "21:00", "22:00"};
	    JLabel lblSpaTime = new JLabel("Spa time: ");
	    comboBoxTime = new JComboBox<>(hours);
	    
	    JLabel lblNumParticipants = new JLabel("Number of Participants (maximum of 2): ");
	    txtNumParticipants = new JTextField();
	    txtNumParticipants.setPreferredSize(new Dimension(150, txtNumParticipants.getPreferredSize().height)); // Set preferred width
	 
	    btnOrder = new JButton("Order");
	    btnOrder.addActionListener(this);
	    
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.insets = new Insets(5, 5, 5, 5); // Add some padding
	    
	    // Add components to orderSpaPanel with appropriate constraints
	    orderSpaPanel.add(lblOrder, gbc);
	    gbc.gridy++;
	    orderSpaPanel.add(lblChooseService, gbc);
	    gbc.gridy++;
	    orderSpaPanel.add(comboBoxSpa, gbc);
	    gbc.gridy++;
	    orderSpaPanel.add(spaPrice, gbc);
	    gbc.gridy++;
	    orderSpaPanel.add(lblSpaDate, gbc);
	    gbc.gridy++;
	    orderSpaPanel.add(comboBoxDates, gbc);
	    gbc.gridy++;
	    orderSpaPanel.add(lblSpaTime, gbc);
	    gbc.gridy++;
	    orderSpaPanel.add(comboBoxTime, gbc);
	    gbc.gridy++;
	    orderSpaPanel.add(lblNumParticipants, gbc);
	    gbc.gridy++;
	    orderSpaPanel.add(txtNumParticipants, gbc);
	    gbc.gridy++;
	    gbc.gridwidth = 2; // span two columns for the button
	    orderSpaPanel.add(btnOrder, gbc);
	    
	 // Add titlePanel to mainPanel with BorderLayout.NORTH
	    mainPanel.add(titlePanel, BorderLayout.NORTH);
	    // Add orderSpaPanel to mainPanel with BorderLayout.CENTER
	    mainPanel.add(orderSpaPanel, BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	    super.actionPerformed(e);
	    if (e.getSource() == btnOrder) {
	        Service service = null;
	        for (Service ser : Hotel.getInstance().getServiceByType(ServiceType.SPA)) {
	            if (ser.getServiceName().equals(comboBoxSpa.getSelectedItem())) {
	                service = ser;
	                break; // Exit loop once found
	            }
	        }

	        String selectedDate = (String) comboBoxDates.getSelectedItem();
	        LocalDate date = LocalDate.parse(selectedDate);

	        String selectedTime = (String) comboBoxTime.getSelectedItem();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
	        LocalTime time = LocalTime.parse(selectedTime, formatter);

	        LocalDateTime dateTime = date.atTime(time);

	        if (service != null) {
	            if (Integer.parseInt(txtNumParticipants.getText()) > service.getMaxNumOfParticipants()) {
	                JOptionPane.showMessageDialog(this, "Number of participants exceeds maximum limit.", "Error", JOptionPane.ERROR_MESSAGE);
	                return; // Stop further processing
	            }

	            Session s = new Session(service.getServiceID(), dateTime, dateTime.plusHours(1), Integer.parseInt(txtNumParticipants.getText()));

	            boolean success = SQLQueries.insertDataIntoTblSession(s);
	            if (success) {
	                Hotel.getInstance().addSession(service, s);
	                SQLQueries.insertDataIntoTblBookedRoomBooksService(new BookedRoomBooksService(Hotel.getClientID(), Hotel.getRoomNumber(), SQLQueries.readLastSessionByClient()));
	                JOptionPane.showMessageDialog(this, "Session booked successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
	            } else {
	                JOptionPane.showMessageDialog(this, "Failed to book session.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        } else {
	            JOptionPane.showMessageDialog(this, "Please select a service.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	    if(e.getSource()==comboBoxSpa) {
	    	if(comboBoxSpa.getSelectedItem()!=null) {
	    		Service service = null;
		        for (Service ser : Hotel.getInstance().getServiceByType(ServiceType.SPA)) {
		            if (ser.getServiceName().equals(comboBoxSpa.getSelectedItem())) {
		                service = ser;
		            }
		        }
		        
		        if(service instanceof PaidService) {
		        	PaidService ps = (PaidService)service;
		        	spaPrice.setText("Total price: " + String.valueOf(ps.getServiceCost()));
		        }
	    	}
	    }
	}


}