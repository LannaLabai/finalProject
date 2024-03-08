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

    private static final Color Green = null;
	private static final Color GRAY = null;
	private JPanel contentPane;
    private ArrayList<JRadioButton> donationProjectsRadio;
    private ButtonGroup buttonGroup;
    private JButton btnDonate;
    private JTextField txtSum;
    private ArrayList<JLabel> texts;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500); // Increased width and height for better layout
        initialize();
        setVisible(true);
    }

    public void initialize() {
        BackgroundPanel Imageofpage = new BackgroundPanel("src/view/images2/backgroundImage.jpg");
        contentPane = new JPanel();
        contentPane.add(Imageofpage);
        contentPane.setBackground(Color.BLUE);
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10)); // Increased padding
        contentPane.setLayout(new BorderLayout());

        texts=new ArrayList<>();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets.top = 10;
        gbc.insets.bottom = 10;
        scrollPane.setViewportView(panel);
        setContentPane(contentPane);

        donationProjectsRadio = new ArrayList<>();

        JLabel lblTitle = new JLabel("Donations");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24)); // Larger font size and bold
        panel.add(lblTitle, gbc);
        gbc.gridy++;

        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS)); // Vertical box layout
        gbc.gridy++;
        panel.add(containerPanel, gbc);
        buttonGroup = new ButtonGroup();

        for (DonationProject dp : Hotel.getInstance().getDonationProjects()) {
            JPanel pairPanel = new JPanel(new BorderLayout()); // Panel for each pair
            pairPanel.setLayout(new BorderLayout()); // Set layout to BorderLayout
            pairPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Border for visualization
            pairPanel.setBackground(Color.GRAY);

            JRadioButton radioButton = new JRadioButton(dp.getDonationProjectName());
            radioButton.setFont(new Font("Arial", Font.BOLD, 16)); // Larger font size
            radioButton.setBackground(GRAY);
            JLabel shortDesc = new JLabel("<html><p style='width: 450px;'>" + dp.getProjectDetails() + "</p></html>");
            shortDesc.setPreferredSize(new Dimension(Short.MAX_VALUE, shortDesc.getPreferredSize().height));
            //shortDesc.setVerticalAlignment(SwingConstants.TOP);
            shortDesc.setFont(new Font("Arial", Font.PLAIN, 14)); // Smaller font size
            texts.add(shortDesc);

            pairPanel.add(radioButton, BorderLayout.NORTH);
            pairPanel.add(shortDesc, BorderLayout.CENTER);

            buttonGroup.add(radioButton);
            donationProjectsRadio.add(radioButton);
            containerPanel.add(pairPanel);
            containerPanel.add(Box.createVerticalStrut(15)); // Spacing between panels
            
            // Set preferred size for each panel
            pairPanel.setPreferredSize(new Dimension(600, pairPanel.getPreferredSize().height));
            pairPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, pairPanel.getPreferredSize().height));
        }
        
        txtSum = new JTextField(10);
        txtSum.setPreferredSize(new Dimension(150, 30)); // Set preferred size
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER; 
        panel.add(txtSum, gbc);
        btnDonate = new JButton("Donate");
        btnDonate.addActionListener(this);
        btnDonate.setFont(new Font("Arial", Font.BOLD, 16)); // Larger font size and bold
        btnDonate.setBackground(Color.blue); // Change background color
        btnDonate.setForeground(Color.WHITE); // Change text color
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER; // Set anchor to center
        panel.add(btnDonate, gbc);
        panel.add(btnDonate, gbc); // Add button with modified constraints
        

        // Add spacing at the bottom
        gbc.weighty = 1;
        panel.add(Box.createVerticalStrut(10), gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDonate) {
            ButtonModel selectedButton = buttonGroup.getSelection();
            if (selectedButton != null) {
                for (int i = 0; i < donationProjectsRadio.size(); i++) {
                    if (selectedButton.equals(donationProjectsRadio.get(i).getModel()) && (!txtSum.getText().isEmpty())) {
                        Donation d = new Donation(Hotel.getInstance().getDonationProjects().get(i).getDonationProjectID(),
                                Hotel.getClientID(), Hotel.getRoomNumber(), Double.parseDouble(txtSum.getText()), LocalDateTime.now());

                        SQLQueries.insertDataIntoTblDonation(d);
                    }
                }
            }
        }
    }
}