package view;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import control.SQLQueries;
import model.Hotel;
import model.Request;
import utils.RequestType;

public class HouseKeepingView extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JFrame nextFrame;
	private JButton btnOrder;
	private ArrayList<JCheckBox> checkboxes;
	private JCheckBox otherCheckBox;
	private JPanel cleaningPanel;
	private JTextArea txtAreaOtherRequest;
	private ArrayList<JRadioButton> radioButtons;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HouseKeepingView frame = new HouseKeepingView();
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
	public HouseKeepingView() {
		super("House-keeping");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		initialize();
		setVisible(true);
	}
	
	public HouseKeepingView(JFrame nextFrame) {
		super("House-keeping");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		initialize();
		this.nextFrame = nextFrame;
		setVisible(true);
	}
	
	
	public void initialize() {
	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    contentPane.setLayout(new BorderLayout(0, 0));

	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    contentPane.add(scrollPane, BorderLayout.CENTER);
	    
	    btnBack = new JButton("Back");
	    btnBack.addActionListener(this);
	    contentPane.add(btnBack,BorderLayout.NORTH);

	    JPanel mainPanel = new JPanel();
	    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Stack components vertically
	    scrollPane.setViewportView(mainPanel);
	    setContentPane(contentPane);

	    // Add title
	    JLabel lblTitle = new JLabel("<html><h1>House-Keeping</h1></html>");
	    mainPanel.add(lblTitle);

	    JLabel lblSubtext = new JLabel("Please consider reusing towels for environmental purposes.");
	    mainPanel.add(lblSubtext);

	    // Cleaning section
	    cleaningPanel = new JPanel();
	    cleaningPanel.setBorder(BorderFactory.createTitledBorder("Cleaning"));
	    cleaningPanel.setLayout(new BoxLayout(cleaningPanel, BoxLayout.Y_AXIS)); // Stack components vertically

	    JLabel lblCleaning = new JLabel("Would you like us to clean today?");
	    cleaningPanel.add(lblCleaning);

	    JRadioButton radioButtonYes = new JRadioButton(RequestType.CLEAN_ROOM.toString());
	    JRadioButton radioButtonNo = new JRadioButton(RequestType.DO_NOT_DISTURB.toString());

	    ButtonGroup buttonGroup = new ButtonGroup();
	    buttonGroup.add(radioButtonYes);
	    buttonGroup.add(radioButtonNo);

	    cleaningPanel.add(radioButtonYes);
	    cleaningPanel.add(radioButtonNo);
	    
	    radioButtons = new ArrayList<>();
	    radioButtons.add(radioButtonYes);
	    radioButtons.add(radioButtonNo);

	    JLabel lblAdditionalRequests = new JLabel("Additional Requests:");
	    cleaningPanel.add(lblAdditionalRequests);
	    
	    checkboxes = new ArrayList<JCheckBox>();

	    for (RequestType r : RequestType.values()) {
	    	if(!r.equals(RequestType.CLEAN_ROOM) && !r.equals(RequestType.DO_NOT_DISTURB) && !r.equals(RequestType.COLLECT_LAUNDRY)) {
	    		if(!r.equals(RequestType.OTHER)) {
	    			JCheckBox checkBoxAddRequests = new JCheckBox(r.toString());
			        checkboxes.add(checkBoxAddRequests);
			        cleaningPanel.add(checkBoxAddRequests);
	    		}
		        if(r.equals(RequestType.OTHER)) {
		        	otherCheckBox = new JCheckBox(r.toString());
			        checkboxes.add(otherCheckBox);
			        cleaningPanel.add(otherCheckBox);
			        otherCheckBox.addActionListener(this);
			        
		        }
	    	}
	    }

	    mainPanel.add(cleaningPanel);

	    // Laundry section
	    JPanel laundryPanel = new JPanel();
	    laundryPanel.setBorder(BorderFactory.createTitledBorder("Laundry Services"));
	    laundryPanel.setLayout(new BoxLayout(laundryPanel, BoxLayout.Y_AXIS)); // Stack components vertically

	    JLabel lblLaundryDesc = new JLabel("<html>We have laundry services in the hotel on the 0th floor if you are interested in doing your own laundry.<br>"
	            + "In addition, here you will able to order laundry collection services, provided that it does not exceed the size<br>"
	            + "of the room's basket.<br>"
	            + "Laundry will be collected after cleaning and will be returned to the room by the end of the day.<br>"
	            + "Please place your dirty clothes in the laundry basket and leave the basket next to the door.<br>"
	            + "There will be an additional payment for the laundry collection request of 10 shekels that will be added to your bill.</html>");
	    laundryPanel.add(lblLaundryDesc);

	    JCheckBox checkboxLaundry = new JCheckBox(RequestType.COLLECT_LAUNDRY.toString());
	    checkboxes.add(checkboxLaundry);
	    laundryPanel.add(checkboxLaundry);
	    
	    btnOrder = new JButton("Order");
	    btnOrder.addActionListener(this);

	    mainPanel.add(laundryPanel);
	    
	    mainPanel.add(btnOrder);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    // Handle button clicks
	    if (e.getSource() == btnOrder) {
	        // Iterate over checkboxes
	        for (JCheckBox cb : checkboxes) {
	            if (cb.isSelected()) {
	                String content = null;
	                if (cb.getText().equals("Other")) {
	                    content = txtAreaOtherRequest.getText();
	                }

	                RequestType rt = null;
	                for (RequestType rti : RequestType.values()) {
	                    if (rti.toString().equals(cb.getText())) {
	                        rt = rti;
	                    }
	                }

	                Request r = new Request(Hotel.getRoomNumber(), Hotel.getClientID(), LocalDateTime.now(), rt, content);

	                boolean success = SQLQueries.insertDataIntoTblRequest(r);

	                // Show a dialog based on the success of the insertion
	                if (success) {
	                    JOptionPane.showMessageDialog(this, "Request sent successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
	                } else {
	                    JOptionPane.showMessageDialog(this, "Failed to send request.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        }
	    }

	    // Handle "Other" checkbox selection
	    if (e.getSource() == otherCheckBox) {
	        if (otherCheckBox.isSelected()) {
	            JPanel textAreaPanel = new JPanel(); // Create a panel for the JTextArea
	            txtAreaOtherRequest = new JTextArea(3, 10); // Rows, Columns
	            txtAreaOtherRequest.setLineWrap(true);
	            txtAreaOtherRequest.setWrapStyleWord(true);
	            textAreaPanel.add(txtAreaOtherRequest); // Add JTextArea to the panel
	            cleaningPanel.add(textAreaPanel, BorderLayout.SOUTH);
	        } else {
	            cleaningPanel.remove(txtAreaOtherRequest);
	        }
	        // Revalidate and repaint the panel
	        cleaningPanel.revalidate();
	        cleaningPanel.repaint();
	    }

	    // Handle radio button selection
	    for (JRadioButton radioButton : radioButtons) {
	        if (radioButton.isSelected()) {
	            RequestType rt = null;
	            for (RequestType rti : RequestType.values()) {
	                if (rti.toString().equals(radioButton.getText())) {
	                    rt = rti;
	                }
	            }

	            Request r = new Request(Hotel.getRoomNumber(), Hotel.getClientID(), LocalDateTime.now(), rt, null);

	            boolean success = SQLQueries.insertDataIntoTblRequest(r);

	            // Show a dialog based on the success of the insertion
	            if (success) {
	                JOptionPane.showMessageDialog(this, "Request sent successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
	            } else {
	                JOptionPane.showMessageDialog(this, "Failed to send request.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    }

	    // Handle back button click
	    if (e.getSource() == btnBack) {
	        nextFrame.setVisible(true);
	        this.setVisible(false);
	    }
	}

	
	

}
