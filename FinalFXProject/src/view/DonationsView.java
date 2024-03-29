package view;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import control.SQLQueries;
import model.Donation;
import model.DonationProject;
import model.Hotel;

public class DonationsView extends JFrame implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Color GRAY = null;
	private JFrame nextFrame;
    private JPanel contentPane;
    private ArrayList<JRadioButton> donationProjectsRadio;
    private ButtonGroup buttonGroup;
    private JButton btnDonate;
    private JTextField txtSum;
    private ArrayList<JLabel> texts;
    private JButton btnBack;

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

    public DonationsView() {
        super("Donations");
        setBounds(50, 50, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initialize();
        setVisible(true);
    }
    
    public DonationsView(JFrame nextFrame) {
        super("Donations");
        setBounds(50, 50, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.nextFrame = nextFrame;
        initialize();
        setVisible(true);
    }

    public void initialize() {
        BackgroundPanel contentPanel = new BackgroundPanel("ImagesOfProject/backgroundImage.jpg");
        contentPane = contentPanel; // Set contentPane to BackgroundPanel instance
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10)); // Increased padding
        contentPane.setLayout(new BorderLayout());

        texts = new ArrayList<>();

        btnBack = new JButton("Back");
		btnBack.addActionListener(this);
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Panel for back button
	    btnPanel.setOpaque(false); // Make it transparent
	    btnPanel.add(btnBack); // Add back button to panel

	    contentPane.add(btnPanel, BorderLayout.NORTH);
		//contentPane.add(btnBack, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets.top = 10;
        gbc.insets.bottom = 10;
        contentPane.add(panel, BorderLayout.CENTER);

        donationProjectsRadio = new ArrayList<>();

        JLabel lblTitle = new JLabel("Donations");
        
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24)); // Larger font size and bold
        panel.add(lblTitle, gbc);
        gbc.gridy++;

        JPanel containerPanel = new JPanel();
        containerPanel.setOpaque(false);
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS)); // Vertical box layout
        gbc.gridy++;
        panel.add(containerPanel, gbc);
        buttonGroup = new ButtonGroup();

        for (DonationProject dp : Hotel.getInstance().getDonationProjects()) {
            JPanel pairPanel = createDonationProjectPanel(dp);
            containerPanel.add(pairPanel);
            containerPanel.add(Box.createVerticalStrut(15)); // Spacing between panels
        }

        JLabel lblSum = new JLabel("Sum to Donate: ");
        lblSum.setBackground(GRAY);
        txtSum = new JTextField(10);
        txtSum.setPreferredSize(new Dimension(150, 30)); // Set preferred size
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(lblSum, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(txtSum, gbc);
        btnDonate = new JButton("Donate");
        btnDonate.addActionListener(this);
        btnDonate.setFont(new Font("Arial", Font.BOLD, 16)); // Larger font size and bold
        btnDonate.setBackground(Color.black); // Change background color
        btnDonate.setForeground(Color.WHITE); // Change text color
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER; // Set anchor to center
        panel.add(btnDonate, gbc); // Add button with modified constraints

        // Add spacing at the bottom
        gbc.weighty = 1;
        panel.add(Box.createVerticalStrut(10), gbc);

        setContentPane(contentPane);
    }

    private JPanel createDonationProjectPanel(DonationProject dp) {
        JPanel pairPanel = new BlurContentPanel(); // Using custom panel for blur effect
        pairPanel.setLayout(new BorderLayout()); // Set layout to BorderLayout
        pairPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Border for visualization

        JRadioButton radioButton = new JRadioButton(dp.getDonationProjectName());
        radioButton.setFont(new Font("Arial", Font.BOLD, 16)); // Larger font size
        buttonGroup.add(radioButton);
        donationProjectsRadio.add(radioButton);
        pairPanel.add(radioButton, BorderLayout.NORTH);

        JLabel shortDesc = new JLabel("<html><p style='width: 450px;'>" + dp.getProjectDetails() + "</p></html>");
        shortDesc.setFont(new Font("Arial", Font.PLAIN, 14)); // Smaller font size
        pairPanel.add(shortDesc, BorderLayout.CENTER);

        return pairPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            nextFrame.setVisible(true);
            this.setVisible(false);
        }
        if (e.getSource() == btnDonate) {
            DonationProject selectedProject = null;
            for (JRadioButton radioButton : donationProjectsRadio) {
                if (radioButton.isSelected()) {
                    for (DonationProject dp : Hotel.getInstance().getDonationProjects()) {
                        if (dp.getDonationProjectName().equals(radioButton.getText())) {
                            selectedProject = dp;
                            break; // Exit loop once found
                        }
                    }
                }
            }
            if (selectedProject != null) {
                Donation d = new Donation(selectedProject.getDonationProjectID(), Hotel.getClientID(), Hotel.getRoomNumber(),
                        Double.parseDouble(txtSum.getText()), LocalDateTime.now());

                boolean success = SQLQueries.insertDataIntoTblDonation(d);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Donation made successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to make donation.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
