package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import model.Hotel;

public class BasicViewTemplate extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JButton btnBack;
	protected JPanel contentPane;
	protected JFrame nextFrame;
	protected JScrollPane scrollPane;
	protected JLabel lblTitle;
	protected JLabel lblSubtext;
	protected JPanel mainPanel;

	public BasicViewTemplate(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 800, 600);
		initialize();
		setVisible(true);
	}
	
	public BasicViewTemplate(String title,JFrame nextFrame) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 800, 600);
		this.nextFrame = nextFrame;
		initialize();
		setVisible(true);
	}
	
	public void initializeDefault() {
		contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    contentPane.setLayout(new BorderLayout(0, 0));

	    scrollPane = new JScrollPane();
	    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    contentPane.add(scrollPane, BorderLayout.CENTER);

	    mainPanel = new JPanel();
	    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Stack components vertically
	    //mainPanel.setLayout(new BorderLayout());
	    scrollPane.setViewportView(mainPanel);
	    setContentPane(contentPane);
	    
		btnBack = new JButton("Back");
		btnBack.addActionListener(this);
		mainPanel.add(btnBack);

	    // Add title
	    lblTitle = new JLabel();
	    mainPanel.add(lblTitle);

	    lblSubtext = new JLabel();
	    mainPanel.add(lblSubtext);
	    
	    mainPanel.setMaximumSize(new Dimension(800,600));
	}
	
	public void initialize(){
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnBack) {
			nextFrame.setVisible(true);
            this.setVisible(false);
		}
		
	}

}
