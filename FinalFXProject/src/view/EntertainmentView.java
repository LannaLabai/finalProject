package view;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

import model.Hotel;
import model.Service;
import utils.ServiceType;


public class EntertainmentView extends BasicViewTemplate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<JPanel> showPanels;
	private ArrayList<JButton> buttons;
	/** 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EntertainmentView frame = new EntertainmentView();
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
	public EntertainmentView() {
		super("Entertainment");
	}
	
	public EntertainmentView(JFrame nextFrame) {
		super("Entertainment",nextFrame);
	}
	
	@Override
	public void initialize() {
		initializeDefault();
		
		JPanel topPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        topPanel.setBackground(Color.WHITE);
        
        lblTitle.setText("Entertainment");
        lblSubtext.setText("Our hotel provides entertainment from time to time including shows. Additionally"
        		+ " children also have a playroom that they can go to.");

        // Middle Panel
        JPanel middlePanel = new JPanel(new BorderLayout());
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        //middlePanel.setBackground(Color.RED);

        JLabel lblPlayroom = new JLabel("Playroom: our hotel provides a playroom for the kids operating between 8:00 AM to"
        		+ " 10:00 PM. Parents may drop them off as they wish, however, please mind not to leave children "
        		+ "under the age of 5 alone and to always stay within close proximity.");
        lblPlayroom.setHorizontalAlignment(SwingConstants.LEFT);
        lblPlayroom.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        middlePanel.add(lblPlayroom, BorderLayout.CENTER);

        JLabel offersLabel = new JLabel("Shows:");
        offersLabel.setHorizontalAlignment(SwingConstants.LEFT);
        offersLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        middlePanel.add(offersLabel, BorderLayout.CENTER);

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new GridLayout(1, 3));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);


        for(Service s : Hotel.getInstance().getServiceByType(ServiceType.ENTERTAINMENT)) {
        	JButton btnEnt = createButton(s.getSerivceName());
        	buttons.add(btnEnt);
        	bottomPanel.add(btnEnt);
        	
        	JPanel showEnt = new JPanel();
        	showPanels.add(showEnt);
        	
        	
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

}
