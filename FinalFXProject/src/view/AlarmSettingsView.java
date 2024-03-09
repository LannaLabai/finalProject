package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import utils.Ringtone;

public class AlarmSettingsView extends BasicViewTemplate {

    private JButton btnAddAlarm;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AlarmSettingsView frame = new AlarmSettingsView();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AlarmSettingsView() {
        super("Alarm Settings");
    }

    public AlarmSettingsView(JFrame nextFrame) {
        super("Alarm Settings", nextFrame);
    }

    public void initialize() {
        initializeDefault();

        // Create content panel
        JPanel contentPane = new JPanel(null); // Use null layout for precise positioning
        contentPane.setPreferredSize(new Dimension(800, 600)); // Adjust the size

        // Create background label
        ImageIcon backgroundImage = new ImageIcon("ImagesOfProject/backgroundImage.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());

        // Create the "Add Alarm" button
        btnAddAlarm = new JButton("Add Alarm");
        btnAddAlarm.setBounds(520, 500, 160, 30); // Adjusted position and size
        btnAddAlarm.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        contentPane.add(btnAddAlarm);

        // Add labels, spinners, comboboxes, sliders
        JLabel lblSetAlarm = new JLabel("Add alarm:");
        lblSetAlarm.setBounds(450, 200, 100, 20);
        lblSetAlarm.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        contentPane.add(lblSetAlarm);

        SpinnerModel hourModel = new SpinnerNumberModel(7, 0, 23, 1);
        JSpinner spinnerHour = new JSpinner(hourModel);
        spinnerHour.setBounds(630, 200, 50, 30);
        spinnerHour.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        contentPane.add(spinnerHour);

        SpinnerModel minuteModel = new SpinnerNumberModel(0, 0, 59, 5);
        JSpinner spinnerMinute = new JSpinner(minuteModel);
        spinnerMinute.setBounds(690, 200, 50, 30);
        spinnerMinute.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        contentPane.add(spinnerMinute);

        JLabel lblRingtone = new JLabel("Choose ringtone:");
        lblRingtone.setBounds(450, 300, 170, 20);
        lblRingtone.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        contentPane.add(lblRingtone);

        JComboBox<String> comboBoxRingtone = new JComboBox<>();
        comboBoxRingtone.addItem("Ringtone 1");
        comboBoxRingtone.addItem("Ringtone 2");
        comboBoxRingtone.setBounds(650, 300, 150, 30);
        comboBoxRingtone.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        contentPane.add(comboBoxRingtone);

        JLabel lblVolume = new JLabel("Select volume:");
        lblVolume.setBounds(450, 400, 150, 20);
        lblVolume.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        contentPane.add(lblVolume);

        JSlider sliderVolume = new JSlider(JSlider.HORIZONTAL, 0, 9, 0);
        sliderVolume.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 9));
        sliderVolume.setMajorTickSpacing(1);
        sliderVolume.setPaintTicks(true);
        sliderVolume.setPaintLabels(true);
        sliderVolume.setSnapToTicks(true);
        sliderVolume.setValue(3);
        sliderVolume.setBounds(650, 400, 150, 50);
        contentPane.add(sliderVolume);

        // Add background label to content pane
        contentPane.add(backgroundLabel);

        // Set the content pane to the custom JPanel
        setContentPane(contentPane);

        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
