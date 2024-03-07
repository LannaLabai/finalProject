package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import javafx.scene.control.ComboBox;

public class BabysittingScreen extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JFrame nextFrame;
	private JButton btnOrder;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BabysittingScreen frame = new BabysittingScreen();
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
	public BabysittingScreen() {
		super("Babysitting");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		initialize();
		setVisible(true);
	}
	
	public BabysittingScreen(JFrame nextFrame) {
		super("Babysitting");
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

	    JPanel mainPanel = new JPanel();
	    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Stack components vertically
	    scrollPane.setViewportView(mainPanel);
	    setContentPane(contentPane);

	    // Add title
	    JLabel lblTitle = new JLabel("<html><h1>Babysitting</h1></html>");
	    mainPanel.add(lblTitle);

	    JLabel lblSubtext = new JLabel("We provide babysitting services from 8:00 AM until 10:00 PM. We only ask that"
	    		+ " you tell us from which hours you would like to order the baysitting services and you will "
	    		+ "be able to drop your children off in the hotel playroom. You can only order the service on the same day"
	    		+ ", and not in advance. Babysitting services are 80 shekels an hour "
	    		+ "for each child.");
	    mainPanel.add(lblSubtext);
	    
	    String[] hours = {"8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00",
                "19:00", "20:00", "21:00", "22:00"};
	    JLabel lblStart = new JLabel("Drop-off hour: ");
	    JComboBox<String> comboBoxStart = new JComboBox<>(hours);
	    comboBoxStart.setPreferredSize(new Dimension(1, 5));/////////
	    
	    JLabel lblEnd = new JLabel("Pick-up hour: ");
	    JComboBox<String> comboBoxEnd = new JComboBox<>(hours);
	    comboBoxStart.setPreferredSize(new Dimension(1, 5));////////
	    
	    btnOrder = new JButton("Order");
	    btnOrder.addActionListener(this);
	    
	    mainPanel.add(lblStart);
	    mainPanel.add(comboBoxStart);
	    mainPanel.add(lblEnd);
	    mainPanel.add(comboBoxEnd);
	    mainPanel.add(btnOrder);
	    
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnOrder) {
			
		}
		
	}

}