package view;import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BabysittingView extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JFrame nextFrame;
    private JButton btnOrder;
    private JComboBox<String> comboBoxStart;
    private JComboBox<String> comboBoxEnd;
    private JTextField txtNumChildren;
    private JButton btnBack;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                BabysittingView frame = new BabysittingView();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public BabysittingView() {
        super("Babysitting");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 350);
        initialize();
        setVisible(true);
    }

    public BabysittingView(JFrame nextFrame) {
        super("Babysitting");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 350);
        this.nextFrame = nextFrame;
        initialize();
        setVisible(true);
    }

    public void initialize() {
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("ImagesOfProject/backgroundImage.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout());
        contentPane.setOpaque(false);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Title Panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblTitle = new JLabel("<html><h1>Babysitting</h1></html>");
        titlePanel.add(lblTitle);

        // Subtext Panel
        JPanel subtextPanel = new JPanel(new BorderLayout());
        JLabel lblSubtext = new JLabel("<html>We provide babysitting services from 8:00 AM until 10:00 PM.<br>"
                + "You can only order the service on the same day, and not in advance.<br>"
                + "Babysitting services are 80 shekels an hour for each child.</html>");
        lblSubtext.setVerticalAlignment(SwingConstants.TOP);
        subtextPanel.add(lblSubtext, BorderLayout.CENTER);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        String[] hours = {"8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00",
                "18:00", "19:00", "20:00", "21:00", "22:00"};

        JLabel lblStart = new JLabel("Drop-off hour: ");
        lblStart.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        comboBoxStart = new JComboBox<>(hours);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblStart, gbc);
        gbc.gridx = 1;
        formPanel.add(comboBoxStart, gbc);

        JLabel lblEnd = new JLabel("Pick-up hour: ");
        lblEnd.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        comboBoxEnd = new JComboBox<>(hours);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lblEnd, gbc);
        gbc.gridx = 1;
        formPanel.add(comboBoxEnd, gbc);

        JLabel lblNumChildren = new JLabel("Number of children: ");
        lblNumChildren.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        txtNumChildren = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lblNumChildren, gbc);
        gbc.gridx = 1;
        formPanel.add(txtNumChildren, gbc);

        btnOrder = new JButton("Order");
        btnOrder.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        btnOrder.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(btnOrder, gbc);

        // Back Button Panel
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnBack = new JButton("Back");
        btnBack.addActionListener(this);
        backPanel.add(btnBack);

        // Adding panels to the main panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(titlePanel, gbc);
        gbc.gridy = 1;
        mainPanel.add(subtextPanel, gbc);
        gbc.gridy = 2;
        mainPanel.add(formPanel, gbc);
        gbc.gridy = 3;
        mainPanel.add(backPanel, gbc);

        contentPane.add(mainPanel, BorderLayout.CENTER);

        setContentPane(contentPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnOrder) {
            // Your order processing logic here
        } else if (e.getSource() == btnBack) {
            nextFrame.setVisible(true);
            this.setVisible(false);
        }
    }
}
