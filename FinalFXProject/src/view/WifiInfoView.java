package view;
import java.awt.*;
import javax.swing.*;
import model.Hotel;

public class WifiInfoView extends BasicViewTemplate {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WifiInfoView frame = new WifiInfoView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public WifiInfoView() {
        super("Wifi Information");
    }

    public WifiInfoView(JFrame nextFrame) {
        super("Wifi Information", nextFrame);
    }

    @Override
    public void initialize() {
        initializeDefault();

        // Create background label
        ImageIcon backgroundImage = new ImageIcon("ImagesOfProject/backgroundImage.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());

        // Create a panel to hold components
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false); // Make the panel transparent
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS)); // Stack components vertically
        contentPanel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        // Add title
        JLabel lblTitle = new JLabel("<html><h1>Wifi Information</h1></html>");
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label horizontally
        contentPanel.add(lblTitle);

        // Add some vertical space between title and subtext
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Add subtext
        JLabel lblSubtext = new JLabel(""); // No subtext for this view
        lblSubtext.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label horizontally
        contentPanel.add(lblSubtext);

        // Add username label with larger font size
        JLabel lblUsername = new JLabel("Username: " + Hotel.getWifiUsername());
        lblUsername.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label horizontally
        lblUsername.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18)); // Set font size to 18 and bold
        contentPanel.add(lblUsername);

        // Add password label with larger font size
        JLabel lblPassword = new JLabel("Password: " + Hotel.getWifipassword());
        lblPassword.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label horizontally
        lblPassword.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18)); // Set font size to 18 and bold
        contentPanel.add(lblPassword);

        // Center the title horizontally
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create a layered pane to hold the background image and content panel
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600)); // Adjusted size for more vertical space
        layeredPane.add(backgroundLabel, new Integer(0)); // Add background label to the lowest layer
        layeredPane.add(contentPanel, new Integer(1)); // Add content panel to a higher layer

        // Add the layered pane to the content pane
        contentPane.add(layeredPane, BorderLayout.CENTER);
    }
}
