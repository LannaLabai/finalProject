package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Hotel;
import model.Service;
import utils.ServiceType;

public class DiningView extends BasicViewTemplate {

	private ArrayList<JPanel> diningPanels;
	private ArrayList<JButton> buttons;
	private JPanel diningInfoPanel;
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
		    JButton btn = createButton(s.getSerivceName());
		    buttons.add(btn);
		    bottomPanel.add(btn);
		    btn.addActionListener(this);
		}

		// Create panel for dining information
		diningInfoPanel = new JPanel(new GridLayout(0, 1));
		mainPanel.add(diningInfoPanel);

		// Populate dining information panel with dining panels
		for (Service s : Hotel.getInstance().getServiceByType(ServiceType.DINING)) {
		    JPanel diningPane = new JPanel();
		    String info = s.getSerivceName() + " \n" + s.getServiceDesc() + "\n";
		    if (s.getSerivceName().equals("Restaurant")) {
		        info += "The maximum number of people you can reserve for is " + s.getMaxNumOfParticipants() + " people";
		    }
		    diningPane.add(new JLabel(info));
		    diningPanels.add(diningPane);
		    //diningInfoPanel.add(diningPane);
		}

		diningInfoPanel.setMaximumSize(new Dimension(800,600));
		bottomPanel.setMaximumSize(new Dimension(800,600));
		topPanel.setMaximumSize(new Dimension(800,600));

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
		for (int i = 0; i < buttons.size(); i++) {
            if (e.getSource() == buttons.get(i)) {
                for (JPanel p : diningPanels) {
                	diningInfoPanel.remove(p);
                }
                diningInfoPanel.add(diningPanels.get(i),BorderLayout.CENTER);
                break; // Exit loop once the corresponding panel is found
            }
        }
        mainPanel.revalidate();
        mainPanel.repaint();
		
	}


}
