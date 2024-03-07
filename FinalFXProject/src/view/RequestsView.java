package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import control.SQLQueries;
import model.Hotel;
import model.Request;
import utils.RequestType;

public class RequestsView extends JFrame implements ActionListener {

	private JFrame nextFrame;
	private JPanel contentPane;
	private JButton btnSend;
	private JTextArea txtArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RequestsView frame = new RequestsView();
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
	public RequestsView() {
		super("Requests");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		initialize();
		setVisible(true);
	}
	
	public RequestsView(JFrame nextFrame) {
		super("Requests");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.nextFrame=nextFrame;
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

	    // Add title
	    JLabel lblTitle = new JLabel("<html><h1>Requests</h1></html>");
	    panel.add(lblTitle);
	    
	    JLabel lblDesc = new JLabel("If you have any additional requests, do not hesitate to send them through here.\n"
	    		+ "If your requests are about housekeeping/laundry, any hotel services or room service, we ask that you "
	    		+ "leave those requests in their respective pages.");
	    panel.add(lblDesc);
	    
	    JLabel lblQuestion = new JLabel("What may we help you with?");
	    panel.add(lblQuestion);
	    
	    JPanel textAreaPanel = new JPanel(new BorderLayout());
	    txtArea = new JTextArea(3,10);
	    //txtArea.setLineWrap(true);
	    //txtArea.setWrapStyleWord(true);
	    textAreaPanel.add(txtArea, BorderLayout.CENTER); // Add the JTextArea to the center of the panel

	    panel.add(textAreaPanel);
	    
	    btnSend = new JButton("Send Request");
	    btnSend.addActionListener(this); 
	    panel.add(btnSend);
	    
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnSend) {
			Request request = new Request(Hotel.getRoomNumber(), Hotel.getClientID(),LocalDateTime.now(), 
					RequestType.OTHER,txtArea.getText());
			System.out.println(request);
			SQLQueries.insertDataIntoTblRequest(request);
			//add success message
		}
		
	}

}
