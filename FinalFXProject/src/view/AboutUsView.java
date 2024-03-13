package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.*;

public class AboutUsView extends JFrame implements ActionListener {

    private JFrame nextFrame;
    private JButton btnBack;

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
        

        btnBack = new JButton("Back");
        btnBack.addActionListener(this);
        contentPane.add(btnBack);

        // Add sections before "More About Our Rooms"
        addSections(contentPane);

        // Create encapsulating section for room type descriptions
        addRoomTypeDescriptions(contentPane);

        // Create scroll pane
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setOpaque(false); // Make the scroll pane transparent
        scrollPane.getViewport().setOpaque(false); // Make the scroll pane viewport transparent
        scrollPane.setViewportView(contentPane); // Set content pane as viewport

        // Set the scroll pane bounds to cover the entire screen
        scrollPane.setBounds(0, 50, 1280, 600); // Adjusted bounds to cover the right side of the screen

        // Add scroll pane to layered pane
        layeredPane.add(scrollPane);

        // Add background label to layered pane
        layeredPane.add(backgroundLabel, Integer.MIN_VALUE);

        // Set layered pane as content pane
        setContentPane(layeredPane);
    }

    // Method to add sections
    private void addSections(JPanel parentPane) {
        // Add title
        addSection(parentPane, "Welcome to Domus Bat Galim", Color.BLUE,false);

        // Add introductory text
        addSection(parentPane, "We hope that you have a pleasant stay here at Domus Bat Galim.\n" + "In case you may need anything, please do not hesitate to reach out to us with the contact information below or to send a request through the Request page.\n", Color.LIGHT_GRAY,false);

        // Add hotel information
        addSection(parentPane, "Hotel Information:\n" +
                "Hotel name: " + Hotel.getHotelName() + "\n" +
                "Hotel phone number: " + Hotel.getHotelPhoneNumber() + "\n" +
                "Hotel address: " + Hotel.getHotelAddress() + "\n" +
                "Hotel email: " + Hotel.getHotelEmail(), Color.YELLOW,false);

        // Add more about rooms title
        addSection(parentPane, "More About Our Rooms:", Color.GREEN,false);
    }

    // Method to add encapsulating section for room type descriptions
    private void addRoomTypeDescriptions(JPanel parentPane) {
        JPanel roomTypesPanel = new JPanel();
        roomTypesPanel.setLayout(new BoxLayout(roomTypesPanel, BoxLayout.Y_AXIS));
        roomTypesPanel.setOpaque(false); // Make the panel transparent

        for (RoomType rt : Hotel.getInstance().getRoomTypes()) {
            // Create room type description section
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

            addSection(roomTypesPanel, roomTypeDesc, Color.RED,true);
        }

        // Add encapsulating section to parent pane
        addSection(parentPane, "Room Type Descriptions:", Color.GREEN,false);
        parentPane.add(roomTypesPanel);
    }

    // Method to add a section with colored container
    private void addSection(JPanel parentPane, String text, Color color, boolean applyGradientBackground) {
        // Create the background panel
        JPanel backgroundPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (applyGradientBackground) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    int w = getWidth();
                    int h = getHeight();
                    Color color1 = new Color(229, 228, 226, 132); // Light gray color with translucency
                    Color color2 = color1.darker(); // Darker shade of the light gray color
                    GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
                    g2d.setPaint(gp);
                    g2d.fillRect(0, 0, w, h);
                    g2d.dispose();
                }
            }
        };

        backgroundPanel.setOpaque(false); // Make the panel transparent
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add more padding

        JTextArea textArea = new JTextArea(text);
        textArea.setEditable(false);
        textArea.setOpaque(false); // Make the text area transparent
        textArea.setForeground(Color.BLACK); // Set text color
        textArea.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setPreferredSize(new Dimension(720, textArea.getPreferredSize().height)); // Set preferred width
        textArea.setBackground(new Color(0, 0, 0, 0)); // Set transparent background

        // Set the background color of the text area content to white
        textArea.getCaret().setSelectionVisible(true);
        textArea.setSelectionColor(Color.WHITE);

        // Create empty borders around the text area for spacing
        textArea.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        // Center the text vertically
        JPanel textWrapperPanel = new JPanel(new GridBagLayout());
        textWrapperPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1; // Fill remaining vertical space
        textWrapperPanel.add(textArea, gbc);

        backgroundPanel.add(textWrapperPanel, BorderLayout.CENTER);

        parentPane.add(backgroundPanel);
        parentPane.add(Box.createVerticalGlue()); // Add more vertical space between sections
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource()==btnBack) {
    		nextFrame.setVisible(true);
            this.setVisible(false);
    	}
    }
}