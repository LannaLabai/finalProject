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
import model.Service;
import model.Session;
import utils.ServiceType;

public class SpaView extends BasicViewTemplate {
	
	private JButton btnOrder;
	
	private JComboBox<String> comboBoxDates;
	private JComboBox<String> comboBoxTime;
	private JTextField txtNumParticipants;
	private JComboBox<String> comboBoxSpa;

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
		
		JPanel orderSpaPanel = new JPanel();
		orderSpaPanel.setLayout(new BoxLayout(orderSpaPanel, BoxLayout.Y_AXIS));
		mainPanel.add(orderSpaPanel);
		
		lblTitle.setText("<html><h1>Spa</h1></html>");
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

		JLabel lblOrder = new JLabel("You can order a spa session by filling out the form below:");
		orderSpaPanel.add(lblOrder);
		
		JLabel lblChooseService = new JLabel("Desired spa service: ");
		comboBoxSpa = new JComboBox<>(spaServices);
		
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
		
		btnOrder = new JButton("Order");
		btnOrder.addActionListener(this);

		orderSpaPanel.add(lblChooseService);
		orderSpaPanel.add(comboBoxSpa);
		orderSpaPanel.add(lblSpaDate);
		orderSpaPanel.add(comboBoxDates);
		orderSpaPanel.add(lblSpaTime);
		orderSpaPanel.add(comboBoxTime);
		orderSpaPanel.add(lblNumParticipants);
		orderSpaPanel.add(txtNumParticipants);
		orderSpaPanel.add(btnOrder);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getSource()==btnOrder) {
			Service service = null;
			for(Service ser : Hotel.getInstance().getServiceByType(ServiceType.SPA)) {
				if(ser.getServiceName().equals(comboBoxSpa.getSelectedItem())) {
					service = ser;
				}
			}
			
			String selectedDate = (String) comboBoxDates.getSelectedItem();
			LocalDate date = LocalDate.parse(selectedDate);

			String selectedTime = (String) comboBoxTime.getSelectedItem();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
			LocalTime time = LocalTime.parse(selectedTime, formatter);

			LocalDateTime dateTime = date.atTime(time);
			
			if(Integer.parseInt(txtNumParticipants.getText())>service.getMaxNumOfParticipants()) {
				//throw new exception
			}
			
			Session s = new Session(service.getServiceID(), dateTime, dateTime.plusHours(1),Integer.parseInt(txtNumParticipants.getText()));
			
			Hotel.getInstance().addSession(service, s);
			SQLQueries.insertDataIntoTblSession(s);
			SQLQueries.insertDataIntoTblBookedRoomBooksService(new BookedRoomBooksService(Hotel.getClientID(),Hotel.getRoomNumber(),SQLQueries.readLastSessionByClient()));
		}
		
	}

}