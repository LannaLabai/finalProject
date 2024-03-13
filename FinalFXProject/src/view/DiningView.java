package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;

import control.SQLQueries;
import model.BookedRoomBooksService;
import model.Hotel;
import model.Service;
import model.Session;
import utils.ServiceType;

public class DiningView extends BasicViewTemplate {

	private ArrayList<JPanel> diningPanels;
	private ArrayList<JButton> buttons;
	private JPanel diningInfoPanel;
	private JButton btnReservation;
	private JComboBox<String> comboBoxDates;
	private JComboBox<String> comboBoxTime;
	private JTextField txtNumPeople;
	private ArrayList<Service> services;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiningView frame = new DiningView();
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
	public DiningView() {
		super("Dining");
	}
	
	public DiningView(JFrame nextFrame) {
		super("Dining",nextFrame);
	}
	
	@Override
	public void initialize() {
		initializeDefault();
		buttons = new ArrayList<>();
		diningPanels = new ArrayList<>();

		// Create top panel
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(Color.WHITE);
		mainPanel.add(topPanel);
		
		contentPane.add(btnBack,BorderLayout.NORTH);

		// Create title and subtext labels
		lblTitle.setText("Dining");
		lblSubtext.setText("Our hotel provides a number of dining options for our guests. If you wish to order from "
		        + "any of them to your room, you can do so from the 'Room Service' page in this app. For any further "
		        + "questions about these options and any dietary restrictions, please contact the lobby or simply "
		        + "send a request through the 'Request' page.");
		topPanel.add(lblTitle, BorderLayout.NORTH);
		topPanel.add(lblSubtext, BorderLayout.CENTER);

		// Create bottom panel for buttons
		JPanel bottomPanel = new JPanel(new GridLayout(1, 3));
		mainPanel.add(bottomPanel);

		// Populate bottom panel with buttons
		for (Service s : Hotel.getInstance().getServiceByType(ServiceType.DINING)) {
		    JButton btn = createButton(s.getServiceName());
		    buttons.add(btn);
		    bottomPanel.add(btn);
		    btn.addActionListener(this);
		}

		// Create panel for dining information
		diningInfoPanel = new JPanel(new GridLayout(0, 1));
		mainPanel.add(diningInfoPanel);
		
		services = new ArrayList<Service>();

		// Populate dining information panel with dining panels
		for (Service s : Hotel.getInstance().getServiceByType(ServiceType.DINING)) {
		    JPanel diningPane = new JPanel();
		    diningPane.setLayout(new BoxLayout(diningPane, BoxLayout.Y_AXIS));
		    String info = s.getServiceName() + " \n" + s.getServiceDesc() + "\n";
		    services.add(s);
		    if (s.getServiceName().equals("Restaurant")) {
		        info += "The maximum number of people you can reserve for is " + s.getMaxNumOfParticipants() + " people";
		        
		        JLabel lblReservationDate = new JLabel("Reservation date: ");
		        LocalDate today = LocalDate.now();
		        LocalDate[] availableDates = new LocalDate[7];
		        String[] dateStrings = new String[7];
		        for (int i = 0; i < 7; i++) {
		        	availableDates[i] = today.plusDays(i);
		            dateStrings[i] = availableDates[i].toString();
		        }
		        
		        comboBoxDates = new JComboBox<>(dateStrings);
		        
		        String[] hours = {"8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00",
		                "19:00", "20:00", "21:00", "22:00"};
			    JLabel lblReservationTime = new JLabel("Reservation time: ");
			    comboBoxTime = new JComboBox<>(hours);
			    
			    JLabel lblNumPeople = new JLabel("Number of guests: ");
			    txtNumPeople = new JTextField();
		        
		        btnReservation = new JButton("Make Reservation");
		        btnReservation.addActionListener(this);
		        
		        diningPane.add(new JLabel(info));
		        diningPane.add(lblReservationDate);
		        diningPane.add(comboBoxDates);
		        diningPane.add(lblReservationTime);
		        diningPane.add(comboBoxTime);
		        diningPane.add(lblNumPeople);
		        diningPane.add(txtNumPeople);
		        diningPane.add(btnReservation);
		    }
		    else {
		    	diningPane.add(new JLabel(info));
		    }
		    
		    diningPanels.add(diningPane);
		    //diningInfoPanel.add(diningPane);
		}

		

	}
	
	private JButton createButton(String text) {//, String imagePath
        JButton button = new JButton(text);
        //button.setIcon(new ImageIcon(getClass().getResource(imagePath)));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        return button;
    }
	

	@Override
	public void actionPerformed(ActionEvent e) {
	    super.actionPerformed(e);
	    if (e.getSource() == btnReservation) {
	        Service service = null;
	        for (Service ser : Hotel.getInstance().getServiceByType(ServiceType.DINING)) {
	            if (ser.getServiceName().equals("Restaurant")) {
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
	        
	        if( txtNumPeople.getText() == "") {
	        	JOptionPane.showMessageDialog(this, "Please enter the number of guests.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	        
	        int numPeople = Integer.parseInt(txtNumPeople.getText());
	        if (numPeople > service.getMaxNumOfParticipants() || txtNumPeople.getText() == "") {
	            JOptionPane.showMessageDialog(this, "Number of people exceeds maximum capacity.", "Error", JOptionPane.ERROR_MESSAGE);
	            return; // Exit method early
	        }
	        
	        Session s = new Session(service.getServiceID(), dateTime, dateTime.plusHours(2), numPeople);
	        
	        boolean success = SQLQueries.insertDataIntoTblSession(s);
	        if (success) {
	            Hotel.getInstance().addSession(service, s);
	            SQLQueries.insertDataIntoTblBookedRoomBooksService(new BookedRoomBooksService(Hotel.getClientID(), Hotel.getRoomNumber(), SQLQueries.readLastSessionByClient()));
	            JOptionPane.showMessageDialog(this, "Reservation made successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            JOptionPane.showMessageDialog(this, "Failed to make reservation.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    } else {
	        for (int i = 0; i < buttons.size(); i++) {
	            if (e.getSource() == buttons.get(i)) {
	                for (JPanel p : diningPanels) {
	                    diningInfoPanel.remove(p);
	                }
	                diningInfoPanel.add(diningPanels.get(i), BorderLayout.CENTER);
	                break; // Exit loop once the corresponding panel is found
	            }
	        }
	        mainPanel.revalidate();
	        mainPanel.repaint();
	    }
	    if(e.getSource()==btnBack) {
			nextFrame.setVisible(true);
            this.setVisible(false);
		}
	}


}
