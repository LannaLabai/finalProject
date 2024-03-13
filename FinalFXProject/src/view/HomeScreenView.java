package view;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;

public class HomeScreenView extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JScrollPane menuScrollPane;
    private JPanel menuPanel;
    private JLabel backgroundImageLabel;
    private Clock clock;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HomeScreenView frame = new HomeScreenView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public HomeScreenView() {
        super("Home Screen");
        initialize();
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Open the JFrame in maximized state
    }

    public void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

     // Create the content pane with custom background painting
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("ImagesOfProject/backgroundImage.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        // Create and add the header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setOpaque(true);
        int headerHeight = 100;
        headerPanel.setPreferredSize(new Dimension(getWidth(), headerHeight));
        contentPane.add(headerPanel, BorderLayout.NORTH);

        // Add the clock to the left side of the header panel
        clock = new Clock();
        headerPanel.add(clock, BorderLayout.WEST);
        
     // Create a label for the logo
        JLabel logoLabel = new JLabel();
        ImageIcon logoImage = new ImageIcon("ImagesOfProject/logo.jpg");
        // Scale the logo image to a smaller size
        Image scaledLogoImage = logoImage.getImage().getScaledInstance(100, 70, Image.SCALE_SMOOTH);
        // Create a new ImageIcon with the scaled image
        ImageIcon scaledIcon = new ImageIcon(scaledLogoImage);
        logoLabel.setIcon(scaledIcon);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the logo
        // Add some padding to the top and bottom of the header panel to center the logo vertically
        headerPanel.add(Box.createVerticalStrut(0), BorderLayout.NORTH);
        headerPanel.add(logoLabel, BorderLayout.CENTER);
        
        menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());
        menuPanel.setBackground(Color.WHITE);

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        String[] toolbarButtons = {"About Us", "Safety", "Your Room", "Room Service", "Hotel Amenities",
                "Housekeeping", "Donate", "My Bill", "Alarm", "Wifi", "Requests"};
        for (String buttonLabel : toolbarButtons) {
            JButton button = new JButton(buttonLabel);
            // Set preferred size to make the button bigger
            button.setPreferredSize(new Dimension(120, 120)); // Adjust size as needed
            // Set circular shape
            button.setBorder(new RoundedBorder(20)); // Adjust the radius as needed
            button.setContentAreaFilled(false); // Make the button transparent
            button.setFont(new Font("Arial", Font.PLAIN, 14));
            toolBar.addSeparator(new Dimension(10, 10));
            toolBar.add(button);
            button.addActionListener(this);
        }
        menuPanel.add(toolBar, BorderLayout.NORTH);

        menuScrollPane = new JScrollPane(menuPanel);
        menuScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        menuScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        contentPane.add(menuScrollPane, BorderLayout.SOUTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BorderLayout()); // Use BorderLayout for mainPanel

        // Create a panel for centering components
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new GridLayout(3, 1, 0, 20));

        JLabel welcomeLabel = new JLabel("Welcome to Domus Bat Galim Hotel!");
        welcomeLabel.setForeground(Color.BLACK);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(welcomeLabel);

        JLabel experienceLabel = new JLabel("Experience the Best Stay!");
        experienceLabel.setForeground(Color.BLACK);
        experienceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        experienceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(experienceLabel);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        contentPane.add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println(command);
        if (command != null) {
            switch (command) {
                case "About Us":
                    new AboutUsView(this);
                    this.setVisible(false);
                    break;
                case "Safety":
                	new SafetyAndRegulationsView(this);
                    this.setVisible(false);
                    break;
                case "Your Room":
                	new YourRoomView(this);
                    this.setVisible(false);
                    break;
                case "Room Service":
                    new RoomServiceView(this);
                    this.setVisible(false);
                    break;
                case "Hotel Amenities":
                	new HotelAmenitiesView(this);
                    this.setVisible(false);
                    break;
                case "Housekeeping":
                    new HouseKeepingView(this);
                    this.setVisible(false);
                    break;
                case "Donate":
                	new DonationsView(this);
                    this.setVisible(false);
                    break;
                case "My Bill":
                	new ClientBillView(this);
                    this.setVisible(false);
                    break;
                case "Alarm":
                    new AlarmSettingsView(this);
                    this.setVisible(false);
                    break;
                case "Wifi":
                	new WifiInfoView(this);
                    this.setVisible(false);
                    break;
                case "Requests":
                	new RequestsView(this);
                    this.setVisible(false);
                    break;
            }
        }
    }
}
