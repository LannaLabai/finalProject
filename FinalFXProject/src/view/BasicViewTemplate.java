
package view;
import javax.swing.ImageIcon;
import view.BackgroundPanel;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
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
		// Create layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600)); // Adjusted size for more vertical space

        // Create background label
        ImageIcon backgroundImage = new ImageIcon("ImagesOfProject/backgroundImage.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
   
        contentPane = new JPanel();
        contentPane.setOpaque(false); 
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    contentPane.setLayout(new BorderLayout(0, 0));
	    

	    scrollPane = new JScrollPane();
	    scrollPane.setOpaque(false);
	    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    contentPane.add(scrollPane, BorderLayout.CENTER);

	    mainPanel = new JPanel();
	    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Stack components vertically
	    scrollPane.setViewportView(mainPanel);
	    setContentPane(contentPane);
	    
	    // Add back button to the top-left corner
	    btnBack = new JButton("Back");
	    btnBack.addActionListener(this);
	    JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Panel for back button
	   // btnPanel.setOpaque(false); // Make it transparent
	    btnPanel.add(btnBack); // Add back button to panel
	    contentPane.add(btnPanel, BorderLayout.NORTH);
	    
	    // Add title
	    lblTitle = new JLabel();
	    mainPanel.add(lblTitle);

	    lblSubtext = new JLabel();
	    mainPanel.add(lblSubtext);
	    
	    mainPanel.setMaximumSize(new Dimension(800,600));
	 
	}
	
	public void initialize(){
		
	}
	
	public void setBackgroundImage(String imagePath) {
	    BackgroundPanel backgroundPanel = new BackgroundPanel(imagePath);

	    scrollPane = new JScrollPane();
	    scrollPane.setOpaque(false);
	    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    backgroundPanel.add(scrollPane, BorderLayout.CENTER);

	    mainPanel = new JPanel();
	    mainPanel.setOpaque(false);
	    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	    scrollPane.setViewportView(mainPanel);

	    setContentPane(backgroundPanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnBack) {
			nextFrame.setVisible(true);
            this.setVisible(false);
		}
		
	}

}