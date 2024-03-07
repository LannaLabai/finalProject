package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import control.SQLQueries;
import model.*;
import utils.RequestType;

public class DonationsView extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JFrame nextFrame;
	private ArrayList<JRadioButton> donationProjectsRadio;
	private ButtonGroup buttonGroup;
	private JButton btnDonate;
	private JTextField txtSum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DonationsView frame = new DonationsView();
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
	public DonationsView() {
		super("Donations");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		initialize();
		setVisible(true);
	}
	
	public DonationsView(JFrame nextFrame) {
		super("Donations");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.nextFrame = nextFrame;
		initialize();
		setVisible(true);
	}
	
	public void initialize() {
		contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    contentPane.setLayout(new BorderLayout(0, 0));

	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
	    contentPane.add(scrollPane, BorderLayout.CENTER);

	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    scrollPane.setViewportView(panel);
	    setContentPane(contentPane);
	    
	    donationProjectsRadio = new ArrayList<>();

	    // Add title
	    JLabel lblTitle = new JLabel("<html><h1>Donations</h1></html>");
	    panel.add(lblTitle);
	    
	    JLabel lblDesc = new JLabel("Please consider donating to one of these charities. At this hotel, we are committed"
	    		+ " to not only providing a good stay for our guests, but also to make the local community a better "
	    		+ "place through donations. It would mean a lot if you would consider donating a small amount to "
	    		+ "one of the following charities:");
	    panel.add(lblDesc);
	    
	    buttonGroup = new ButtonGroup();
	    
	    for(DonationProject dp : Hotel.getInstance().getDonationProjects()) {
	    	JRadioButton radioButton = new JRadioButton(dp.getDonationProjectName());
	    	JLabel shortDesc = new JLabel(dp.getProjectDetails());
	    	buttonGroup.add(radioButton);
	    	donationProjectsRadio.add(radioButton);
	    	panel.add(radioButton);
	    	panel.add(shortDesc);
	    }
	    
	    txtSum = new JTextField(10);
	    panel.add(txtSum);
	    
	    btnDonate = new JButton("Donate");
	    btnDonate.addActionListener(this);
	    panel.add(btnDonate);
	    
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnDonate) {
			ButtonModel selectedButton = buttonGroup.getSelection();
		    if (selectedButton != null) {
		        for(int i=0; i<donationProjectsRadio.size(); i++) {
		        	if(selectedButton.equals(donationProjectsRadio.get(i).getModel()) && (txtSum.getText()!=null && txtSum.getText()!="")) {
		        		model.Donation d = new model.Donation(Hotel.getInstance().getDonationProjects().get(i).getDonationProjectID(),
		        				Hotel.getClientID(), Hotel.getRoomNumber(), Double.parseDouble(txtSum.getText()), LocalDateTime.now());
		        		
		        		SQLQueries.insertDataIntoTblDonation(d);
		        	}
		        }
		    }
		}
		
	}

}
