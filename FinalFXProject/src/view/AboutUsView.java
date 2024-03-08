package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.*;

public class AboutUsView extends JFrame implements ActionListener {

    private JFrame nextFrame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AboutUsView frame = new AboutUsView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AboutUsView() {
        super("About Us");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600)); // Adjusted size for more vertical space
        initialize();
        pack(); // Pack the components to avoid stretching
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public AboutUsView(JFrame nextFrame) {
        super("About Us");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600)); // Adjusted size for more vertical space
        this.nextFrame = nextFrame;
        initialize();
        pack(); // Pack the components to avoid stretching
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public void initialize() {
        // Create layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600)); // Adjusted size for more vertical space

        // Create background label
        ImageIcon backgroundImage = new ImageIcon("ImagesOfProject/backgroundImage.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());

        // Create content panel
        JPanel contentPane = new JPanel();
        contentPane.setOpaque(false); // Make the panel transparent
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS)); // Vertical BoxLayout

        // Add title
        addSection(contentPane, "<html><h1>Welcome to Domus Bat Galim</h1></html>", Color.BLUE);

        // Add introductory text
        addSection(contentPane, "We hope that you have a pleasant stay here at Domus Bat Galim. In case you may need anything, please do not hesitate to reach out to us with the contact information below or to send a request through the Request page.", Color.LIGHT_GRAY);

        // Add hotel information
        addSection(contentPane, "Hotel Information:\n" +
                "Hotel name: " + Hotel.getHotelName() + "\n" +
                "Hotel phone number: " + Hotel.getHotelPhoneNumber() + "\n" +
                "Hotel address: " + Hotel.getHotelAddress() + "\n" +
                "Hotel email: " + Hotel.getHotelEmail(), Color.YELLOW);

        // Add more about rooms title
        addSection(contentPane, "<html><h2>More About Our Rooms:</h2></html>", Color.GREEN);

        // Add room type descriptions
        for (RoomType rt : Hotel.getInstance().getRoomTypes()) {
            String roomTypeDesc = rt.getRoomType() + "\n";
            if (rt.getRoomSize() != null && !rt.getRoomSize().isEmpty()) {
                roomTypeDesc += rt.getRoomSize() + "\n";
            }
            roomTypeDesc += rt.getMaxNumOfGuests() + " guests\n";
            roomTypeDesc += rt.getRoomTypeDesc() + "\n";
            if (rt.getKeyFeatures() != null && !rt.getKeyFeatures().isEmpty()) {
                roomTypeDesc += "Key features: " + rt.getKeyFeatures() + "\n";
            }
            roomTypeDesc += "Beds included: " + rt.getBedsDesc() + "\n";
            addSection(contentPane, roomTypeDesc, Color.RED);
        }

        // Add content panel to a scroll pane
        JScrollPane scrollPane = new JScrollPane(contentPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(0, 0, 800, 600); // Adjusted bounds to match layered pane size

        // Center the content pane within the scroll pane
        scrollPane.getViewport().setPreferredSize(new Dimension(800, contentPane.getPreferredSize().height));

        // Center the content pane within the screen
        layeredPane.add(scrollPane);
        contentPane.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        contentPane.setAlignmentY(Component.CENTER_ALIGNMENT); // Center vertically

        // Add components to layered pane
        layeredPane.add(backgroundLabel, new Integer(0));

        // Set layered pane as content pane
        setContentPane(layeredPane);
    }

    // Method to add a section with colored container
    private void addSection(JPanel parentPane, String text, Color color) {
        JPanel sectionPanel = new JPanel(new BorderLayout());
        sectionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add more vertical padding
        sectionPanel.setBackground(color);
        // Set maximum width for the panel
        Dimension maxDimension = new Dimension(700, Integer.MAX_VALUE);
        sectionPanel.setMaximumSize(maxDimension);
        JTextArea textArea = new JTextArea(text);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        sectionPanel.add(textArea, BorderLayout.CENTER);
        parentPane.add(sectionPanel);
        parentPane.add(Box.createVerticalGlue()); // Add more vertical space between sections
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
