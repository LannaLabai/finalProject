package view;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                "Housekeeping", "Donate", "My Bill", "Alarm", "WiFi", "Request", "TV"};
        for (String buttonLabel : toolbarButtons) {
            JButton button = new JButton(buttonLabel);
            // Set preferred size to make the button bigger
            button.setPreferredSize(new Dimension(120, 120)); // Adjust size as needed
            // Set circular shape
            button.setBorder(new RoundedBorder(20)); // Adjust the radius as needed
            button.setContentAreaFilled(false); // Make the button transparent
            button.setFont(new Font("Arial", Font.PLAIN, 14));
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
        centerPanel.setLayout(new GridLayout(3, 1, 0, 20)); // 3 rows, 1 column, vertical gap of 20 pixels

        JLabel welcomeLabel = new JLabel("Welcome to Our Hotel!");
        welcomeLabel.setForeground(Color.BLACK);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the label
        centerPanel.add(welcomeLabel);

        JLabel experienceLabel = new JLabel("Experience the Best Stay Ever!");
        experienceLabel.setForeground(Color.BLACK);
        experienceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        experienceLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the label
        centerPanel.add(experienceLabel);

        // Create a panel for the "Book Now" button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        JButton bookNowButton = new JButton("Book Now");
        bookNowButton.setFont(new Font("Arial", Font.PLAIN, 16));
        bookNowButton.addActionListener(this);
        buttonPanel.add(bookNowButton);

        centerPanel.add(buttonPanel);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        contentPane.add(mainPanel, BorderLayout.CENTER); // Add mainPanel to the center
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
                    // Handle the "Safety" button click
                    System.out.println("Safety button clicked");
                    break;
                case "Your Room":
                    // Handle the "Your Room" button click
                    System.out.println("Your Room button clicked");
                    break;
                // Add cases for other buttons as needed
                case "Room Service":
                    new RoomServiceView(this);
                    this.setVisible(false);
                    break;
                case "Book Now":
                    // Handle the "Book Now" button click
                    System.out.println("Book Now button clicked");
                    break;
            }
        }
    }
}
