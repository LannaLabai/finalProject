package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import utils.ServiceType;

public class HotelAmenitiesView extends BasicViewTemplate {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                HotelAmenitiesView frame = new HotelAmenitiesView();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public HotelAmenitiesView() {
        super("Hotel Amenities");
    }

    public HotelAmenitiesView(JFrame nextFrame) {
        super("Hotel Amenities", nextFrame);
    }

    @Override
    public void initialize() {
        initializeDefault();

        contentPane.add(btnBack, BorderLayout.NORTH);

        lblTitle.setText("<html><h1>Hotel Amenities</h1></html>");
        lblSubtext.setText("This hotel offers a wide variety of services and amenities. Details listed in each of the following pages.");

        JPanel btnPanel = new JPanel(new GridLayout(0, 3, 5, 5)); // Adjusted layout: 0 rows, 3 columns, with 10px horizontal and vertical gaps
        mainPanel.add(btnPanel, BorderLayout.CENTER);
       
        for (ServiceType st : ServiceType.values()) {
            if (st != ServiceType.ENTERTAINMENT) {
                JButton btn = createServiceButton(st.toString());
                btnPanel.add(btn);
            }
        }
    }

    private JButton createServiceButton(String text) {
    	JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(50, 30)); // Adjusted size
        btn.setFont(new Font("Arial", Font.BOLD, 14)); // Reduced font size
        //btn.setBackground(new Color(255, 204, 102)); // Custom button color
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Add border
        btn.addActionListener(this);
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if (e.getSource() == btnBack) {
            nextFrame.setVisible(true);
            this.setVisible(false);
        } else if (e.getSource() instanceof JButton) {
            JButton sourceButton = (JButton) e.getSource();
            String buttonText = sourceButton.getText();
            openServiceView(buttonText);
        }
    }

    private void openServiceView(String service) {
        switch (ServiceType.valueOf(service)) {
            case DINING:
                new DiningView(this).setVisible(true);
                this.setVisible(false);
                break;
            case SPA:
                new SpaView(this).setVisible(true);
                this.setVisible(false);
                break;
            case POOL:
                new PoolView(this).setVisible(true);
                this.setVisible(false);
                break;
            case BABYSITTING:
                new BabysittingView(this).setVisible(true);
                this.setVisible(false);
                break;
            case GYMACTIVITIES:
                new GymActivitiesView(this).setVisible(true);
                this.setVisible(false);
                break;
            case TRIPS:
                new TripsView(this).setVisible(true);
                this.setVisible(false);
                break;
            case MANIPEDI:
                new ManicureAndPedicureView(this).setVisible(true);
                this.setVisible(false);
                break;
            default:
                break;
        }
    }
}

