package view;import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import control.SQLQueries;
import model.Hotel;
import model.Request;
import utils.RequestType;

public class RequestsView extends JFrame implements ActionListener {

    private JFrame nextFrame;
    private JPanel contentPane;
    private JButton btnSend;
    private JTextArea txtArea;
    private JButton btnBack;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RequestsView frame = new RequestsView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public RequestsView() {
        super("Requests");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 250); // Adjusted size
        setLocationRelativeTo(null); // Center the frame
        initialize();
        setVisible(true);
    }

    public RequestsView(JFrame nextFrame) {
        super("Requests");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 250); // Adjusted size
        setLocationRelativeTo(null); // Center the frame
        this.nextFrame = nextFrame;
        initialize();
        setVisible(true);
    }

    public void initialize() {
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10)); // Adjusted border
        contentPane.setLayout(new BorderLayout());
  

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Set horizontal scroll bar policy
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        scrollPane.setViewportView(panel);

        JLabel lblTitle = new JLabel("<html><h1>Requests</h1></html>");
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblTitle);

        JLabel lblDesc = new JLabel("<html>If you have any additional requests, do not hesitate to send them through here.<br>"
                + "If your requests are about housekeeping/laundry, any hotel services, or room service,<br>"
                + "we ask that you leave those requests in their respective pages.</html>");
        lblDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblDesc.setBorder(new EmptyBorder(10, 380, 20, 0)); // Adjusted border
        panel.add(lblDesc);

        JLabel lblQuestion = new JLabel("What may we help you with?");
        lblQuestion.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblQuestion.setBorder(new EmptyBorder(10, 0, 10, 0)); // Adjusted border
        panel.add(lblQuestion);

        txtArea = new JTextArea(10, 30); // Adjusted size
        txtArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtArea.setMaximumSize(new Dimension(500, 400)); // Adjusted width
        panel.add(txtArea);

        btnSend = new JButton("Send Request");
        btnSend.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSend.addActionListener(this);
        panel.add(btnSend);

        btnBack = new JButton("Back");
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBack.addActionListener(this);
        contentPane.add(btnBack, BorderLayout.NORTH);

        setContentPane(contentPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSend) {
            sendRequest();
        }
        if (e.getSource() == btnBack) {
            navigateBack();
        }
    }

    private void sendRequest() {
        Request request = new Request(Hotel.getRoomNumber(), Hotel.getClientID(), LocalDateTime.now(),
                RequestType.OTHER, txtArea.getText());
        Hotel.getInstance().getRequests().add(request);
        boolean success = SQLQueries.insertDataIntoTblRequest(request);
        if (success) {
            JOptionPane.showMessageDialog(this, "Request sent successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to send request.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void navigateBack() {
        nextFrame.setVisible(true);
        this.setVisible(false);
    }
}
