package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class ManicureAndPedicureView extends JFrame implements ActionListener {

	private JPanel contentPane;
	
	private JFrame nextFrame;
	private JComboBox<String> comboBoxStart;
	private JCheckBox checkBoxMani;
	private JCheckBox checkBoxPedi;
	private JButton btnOrder;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		initialize();
		setVisible(true);
	}
	
	public ManicureAndPedicureView(JFrame nextFrame) {
		super("Manicure and Pedicure");
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
	    JLabel lblTitle = new JLabel("<html><h1>Manicure/Pedicure</h1></html>");
	    mainPanel.add(lblTitle);

	    JLabel lblSubtext = new JLabel("We provide manicure/pedicure treatments at this hotel. Either"
	    		+ " session costs 200 shekels for an hour long session. You can sign "
	    		+ "up below at any time convenient for you in the morning or the evening for either "
	    		+ "or both treatments.");
	    mainPanel.add(lblSubtext);
	    
	    checkBoxMani = new JCheckBox("Manicure");
	    mainPanel.add(checkBoxMani);
	    
	    checkBoxPedi = new JCheckBox("Pedicure");
	    mainPanel.add(checkBoxPedi);
	    
	    String[] hours = {"8:00", "9:00", "10:00", "11:00", "12:00", "18:00","19:00", "20:00", "21:00"};
	    JLabel lblStart = new JLabel("Session time: ");
	    comboBoxStart = new JComboBox<>(hours);
	    comboBoxStart.setPreferredSize(new Dimension(1, 5));/////////
	    mainPanel.add(lblStart);
	    mainPanel.add(comboBoxStart);
	    
	    btnOrder = new JButton("Order");
	    btnOrder.addActionListener(this);
	    
	    mainPanel.add(btnOrder);
	    
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnOrder) {
			//save session + booked
		}
		
	}

}
