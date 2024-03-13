package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.SQLQueries;
import model.BookedRoomBooksService;
import model.Hotel;
import model.PaidService;
import model.Service;
import model.Session;
import utils.ServiceType;

public class GymActivitiesView extends BasicViewTemplate {

	private JButton btnOrder;
	private JComboBox<String> comboBoxDates;
	private JComboBox<String> comboBoxTime;
	private JTextField txtNumParticipants;
	private JComboBox<String> comboBoxActivities;
	private JLabel lblCost;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GymActivitiesView frame = new GymActivitiesView();
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
	public GymActivitiesView() {
		super("Gym Activities");
	}
	
	public GymActivitiesView(JFrame nextFrame) {
		super("Gym Activities",nextFrame);
	}
	
	@Override
	public void initialize() {
		initializeDefault();
		
		contentPane.add(btnBack,BorderLayout.NORTH);
		
		JPanel orderGymActivity = new JPanel();
		orderGymActivity.setLayout(new BoxLayout(orderGymActivity, BoxLayout.Y_AXIS));
		mainPanel.add(orderGymActivity);
		
		lblCost = new JLabel("");
		
		lblTitle.setText("<html><h1>Gym Activities</h1></html>");
		String desc = "";
		
		String[] gymServices = new String[Hotel.getInstance().getServiceByType(ServiceType.GYMACTIVITIES).size()-1];
		
		int i = 0;
		for(Service s: Hotel.getInstance().getServiceByType(ServiceType.GYMACTIVITIES)) {
			if(s.getServiceName().equals("Gym")) {
				desc = s.getServiceDesc();
			}
			else {
				gymServices[i++] = s.getServiceName();
			}
			
			
		}
		lblSubtext.setText(desc);

		JLabel lblOrder = new JLabel("You can sign up for a gym activity by filling out the form below:");
		orderGymActivity.add(lblOrder);
		
		JLabel lblChooseService = new JLabel("Desired gym activity: ");
		comboBoxActivities = new JComboBox<>(gymServices);
		
		comboBoxActivities.addActionListener(this);
		
		JLabel lblActivityDate = new JLabel("Activity date: ");
        LocalDate today = LocalDate.now();
        LocalDate[] availableDates = new LocalDate[7];
        String[] dateStrings = new String[7];
        for (i = 0; i < 7; i++) {
        	availableDates[i] = today.plusDays(i);
            dateStrings[i] = availableDates[i].toString();
        }
        
        comboBoxDates = new JComboBox<>(dateStrings);
        
        String[] hours = {"6:00", "7:00","8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00",
                "19:00", "20:00", "21:00", "22:00"};
	    JLabel lblActivityTime = new JLabel("Training time: ");
	    comboBoxTime = new JComboBox<>(hours);
		
		JLabel lblNumParticipants = new JLabel("Number of Participants (maximum of 2): ");
		txtNumParticipants = new JTextField();
		
		btnOrder = new JButton("Sign Up");
		btnOrder.addActionListener(this);

		orderGymActivity.add(lblChooseService);
		orderGymActivity.add(comboBoxActivities);
		orderGymActivity.add(lblCost);
		orderGymActivity.add(lblActivityDate);
		orderGymActivity.add(comboBoxDates);
		orderGymActivity.add(lblActivityTime);
		orderGymActivity.add(comboBoxTime);
		orderGymActivity.add(lblNumParticipants);
		orderGymActivity.add(txtNumParticipants);
		orderGymActivity.add(btnOrder);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	    super.actionPerformed(e);
	    if (e.getSource() == btnOrder) {
	        Service service = null;
	        for (Service ser : Hotel.getInstance().getServiceByType(ServiceType.GYMACTIVITIES)) {
	            if (ser.getServiceName().equals(comboBoxActivities.getSelectedItem())) {
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
	        
	        int numParticipants = Integer.parseInt(txtNumParticipants.getText());
	        if (numParticipants > service.getMaxNumOfParticipants()) {
	            JOptionPane.showMessageDialog(this, "Number of participants exceeds maximum capacity.", "Error", JOptionPane.ERROR_MESSAGE);
	            return; // Exit method early
	        }
	        
	        Session s = new Session(service.getServiceID(), dateTime, dateTime.plusHours(1), numParticipants);
	        
	        boolean success = SQLQueries.insertDataIntoTblSession(s);
	        if (success) {
	            Hotel.getInstance().addSession(service, s);
	            SQLQueries.insertDataIntoTblBookedRoomBooksService(new BookedRoomBooksService(Hotel.getClientID(), Hotel.getRoomNumber(), SQLQueries.readLastSessionByClient()));
	            JOptionPane.showMessageDialog(this, "Order placed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            JOptionPane.showMessageDialog(this, "Failed to place order.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	    if (e.getSource() == btnBack) {
	        nextFrame.setVisible(true);
	        this.setVisible(false);
	    }
	    if(e.getSource()==comboBoxActivities) {
	    	if(comboBoxActivities.getSelectedItem()!=null) {
	    		Service service = null;
		        for (Service ser : Hotel.getInstance().getServiceByType(ServiceType.GYMACTIVITIES)) {
		            if (ser.getServiceName().equals(comboBoxActivities.getSelectedItem())) {
		                service = ser;
		            }
		        }
		        
		        if(service instanceof PaidService) {
		        	PaidService ps = (PaidService)service;
		        	lblCost.setText("Total price: " + String.valueOf(ps.getServiceCost()));
		        }
	    	}
	    }
	}


}
