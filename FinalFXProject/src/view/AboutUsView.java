package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.*;

public class AboutUsView extends JFrame implements ActionListener {

    private JPanel contentPane;
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
        setPreferredSize(new Dimension(600, 600)); // Adjusted size for more vertical space
        initialize();
        pack(); // Pack the components to avoid stretching
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public AboutUsView(JFrame nextFrame) {
        super("About Us");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 600)); // Adjusted size for more vertical space
        this.nextFrame = nextFrame;
        initialize();
        pack(); // Pack the components to avoid stretching
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public void initialize() {
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS)); // Vertical BoxLayout
        
        JScrollPane scrollPane = new JScrollPane(contentPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Always show vertical scroll bar
        setContentPane(scrollPane);

        // Add title
        addSection("<html><h1>Welcome to Domus Bat Galim</h1></html>", Color.BLUE);

        // Add introductory text
        addSection("We hope that you have a pleasant stay here at Domus Bat Galim. In case you may need anything, please do not hesitate to reach out to us with the contact information below or to send a request through the Request page.", Color.LIGHT_GRAY);

        // Add hotel information
        addSection("Hotel Information:\n" +
                "Hotel name: " + Hotel.getHotelName() + "\n" +
                "Hotel phone number: " + Hotel.getHotelPhoneNumber() + "\n" +
                "Hotel address: " + Hotel.getHotelAddress() + "\n" +
                "Hotel email: " + Hotel.getHotelEmail(), Color.YELLOW);

        // Add more about rooms title
        addSection("<html><h2>More About Our Rooms:</h2></html>", Color.GREEN);

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
            addSection(roomTypeDesc, Color.ORANGE);
        }
    }

    // Method to add a section with colored container
    private void addSection(String text, Color color) {
        JPanel sectionPanel = new JPanel(new BorderLayout());
        sectionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add more vertical padding
        sectionPanel.setBackground(color);
        JTextArea textArea = new JTextArea(text);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        sectionPanel.add(textArea, BorderLayout.CENTER);
        contentPane.add(sectionPanel);
        contentPane.add(Box.createVerticalGlue()); // Add more vertical space between sections
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
