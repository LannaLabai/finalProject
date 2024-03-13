package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.SQLQueries;
import model.BookedRoomBooksService;
import model.Hotel;
import model.Service;
import model.Session;
import utils.ServiceType;

public class ManicureAndPedicureView extends BasicViewTemplate {

	private JComboBox<String> comboBoxDates;
	private JComboBox<String> comboBoxTime;
	private JCheckBox checkBoxMani;
	private JCheckBox checkBoxPedi;
	private JButton btnOrder;
	
	private final int numParticipants = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManicureAndPedicureView frame = new ManicureAndPedicureView();
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
	public ManicureAndPedicureView() {
		super("Manicure and Pedicure");
		
	}
	
	public ManicureAndPedicureView(JFrame nextFrame) {
		super("Manicure and Pedicure",nextFrame);
		
	}
	
	public void initialize() {
		
		initializeDefault();
		
		
	    lblTitle.setText("<html><h1>Manicure/Pedicure</h1></html>");
	    //mainPanel.add(lblTitle);

	    lblSubtext.setText("We provide manicure/pedicure treatments at this hotel. Either"
	    		+ " session costs 200 shekels for an hour long session. You can sign "
	    		+ "up below at any time convenient for you in the morning or the evening for either "
	    		+ "or both treatments.");
	    //mainPanel.add(lblSubtext);
	    
	    checkBoxMani = new JCheckBox("Manicure");
	    mainPanel.add(checkBoxMani);
	    
	    checkBoxPedi = new JCheckBox("Pedicure");
	    mainPanel.add(checkBoxPedi);
	    
	    JLabel lblSpaDate = new JLabel("Appointment date: ");
        LocalDate today = LocalDate.now();
        LocalDate[] availableDates = new LocalDate[7];
        String[] dateStrings = new String[7];
        for (int i = 0; i < 7; i++) {
        	availableDates[i] = today.plusDays(i);
            dateStrings[i] = availableDates[i].toString();
        }
        comboBoxDates = new JComboBox<>(dateStrings);
        mainPanel.add(lblSpaDate);
	    mainPanel.add(comboBoxDates);
	    
	    String[] hours = {"8:00", "9:00", "10:00", "11:00", "12:00", "18:00","19:00", "20:00", "21:00"};
	    JLabel lblStart = new JLabel("Appointment time: ");
	    comboBoxTime = new JComboBox<>(hours);
	    comboBoxTime.setPreferredSize(new Dimension(1, 5));/////////
	    mainPanel.add(lblStart);
	    mainPanel.add(comboBoxTime);
	    
	    btnOrder = new JButton("Order");
	    btnOrder.addActionListener(this);
	    
	    mainPanel.add(btnOrder);
	    
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		super.actionPerformed(e);
		if(e.getSource()==btnOrder) {
			Service manicure = null;
			Service pedicure = null;
			
			for(Service ser : Hotel.getInstance().getServiceByType(ServiceType.MANIPEDI)) {
				if(ser.getServiceName().equals("Manicure")) {
					if(checkBoxMani.isSelected()) {
						manicure = ser;
					}
				}
			}
			
			for(Service ser : Hotel.getInstance().getServiceByType(ServiceType.MANIPEDI)) {
				if(ser.getServiceName().equals("Pedicure")) {
					if(checkBoxPedi.isSelected()) {
						pedicure = ser;
					}
				}
				
			}
			
			String selectedDate = (String) comboBoxDates.getSelectedItem();
			LocalDate date = LocalDate.parse(selectedDate);

			String selectedTime = (String) comboBoxTime.getSelectedItem();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
			LocalTime time = LocalTime.parse(selectedTime, formatter);

			LocalDateTime dateTime = date.atTime(time);
			
			if(manicure!=null && pedicure==null) {
				newSession(manicure, dateTime);
			}
			if(pedicure!=null  && manicure==null) {
				newSession(pedicure, dateTime);
			}
			if(manicure!=null && pedicure!=null) {
				newSession(manicure, dateTime);
				time = LocalTime.parse(selectedTime, formatter).plusMinutes(45);
				dateTime = date.atTime(time);
				newSession(pedicure, dateTime);
			}
			
			
		}
		
	}
	
	public void newSession(Service service, LocalDateTime dateTime) {
		Session s = new Session(service.getServiceID(), dateTime,dateTime.plusMinutes(45), numParticipants);
		
		Hotel.getInstance().addSession(service, s);
		SQLQueries.insertDataIntoTblSession(s);
		SQLQueries.insertDataIntoTblBookedRoomBooksService(new BookedRoomBooksService(Hotel.getClientID(),Hotel.getRoomNumber(),SQLQueries.readLastSessionByClient()));
	}

}
