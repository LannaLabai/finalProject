package view;

import javax.swing.*;
import javax.swing.border.*;

import control.SQLQueries;
import model.AlarmSettings;
import model.Hotel;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import utils.Ringtone;

public class AlarmSettingsView extends BasicViewTemplate {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnAddAlarm;
	private JSpinner spinnerMinute;
	private JSpinner spinnerHour;
	private JSlider sliderVolume;
	private JComboBox<String> comboBoxRingtone;
	

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
        //JPanel contentPane = new JPanel(null); // Use null layout for precise positioning
        contentPane.setPreferredSize(new Dimension(800, 600)); // Adjust the size

        // Create background label
        ImageIcon backgroundImage = new ImageIcon("ImagesOfProject/backgroundImage.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        
        //contentPane.add(btnBack, BorderLayout.WEST);

        // Create the "Add Alarm" button
        btnAddAlarm = new JButton("Add Alarm");
        btnAddAlarm.setBounds(520, 500, 160, 30); // Adjusted position and size
        btnAddAlarm.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        contentPane.add(btnAddAlarm, BorderLayout.CENTER);

        // Add labels, spinners, comboboxes, sliders
        JLabel lblSetAlarm = new JLabel("Add alarm:");
        lblSetAlarm.setBounds(450, 200, 100, 20);
        lblSetAlarm.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        contentPane.add(lblSetAlarm, BorderLayout.CENTER);

        SpinnerModel hourModel = new SpinnerNumberModel(7, 0, 23, 1);
        spinnerHour = new JSpinner(hourModel);
        spinnerHour.setEditor(new JSpinner.NumberEditor(spinnerHour, "00"));
        spinnerHour.setBounds(630, 200, 50, 30);
        spinnerHour.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        contentPane.add(spinnerHour, BorderLayout.CENTER);

        SpinnerModel minuteModel = new SpinnerNumberModel(0, 0, 59, 5);
        spinnerMinute = new JSpinner(minuteModel);
        spinnerMinute.setEditor(new JSpinner.NumberEditor(spinnerMinute, "00"));
        spinnerMinute.setBounds(690, 200, 50, 30);
        spinnerMinute.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        contentPane.add(spinnerMinute, BorderLayout.CENTER);

        JLabel lblRingtone = new JLabel("Choose ringtone:");
        lblRingtone.setBounds(450, 300, 170, 20);
        lblRingtone.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        contentPane.add(lblRingtone, BorderLayout.CENTER);
        
        String[] ringtones = new String[Ringtone.values().length];
        int i=0;
        for(Ringtone r : Ringtone.values()) {
        	ringtones[i++]=r.toString();
        }

        comboBoxRingtone = new JComboBox<>(ringtones);
        
        comboBoxRingtone.setBounds(650, 300, 150, 30);
        comboBoxRingtone.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        contentPane.add(comboBoxRingtone, BorderLayout.CENTER);

        JLabel lblVolume = new JLabel("Select volume:");
        lblVolume.setBounds(450, 400, 150, 20);
        lblVolume.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        contentPane.add(lblVolume, BorderLayout.CENTER);

        sliderVolume = new JSlider(JSlider.HORIZONTAL, 0, 9, 0);
        sliderVolume.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 9));
        sliderVolume.setMajorTickSpacing(1);
        sliderVolume.setPaintTicks(true);
        sliderVolume.setPaintLabels(true);
        sliderVolume.setSnapToTicks(true);
        sliderVolume.setValue(3);
        sliderVolume.setBounds(650, 400, 150, 50);
        contentPane.add(sliderVolume, BorderLayout.CENTER);
        
        btnAddAlarm.addActionListener(this);

        // Add background label to content pane
        contentPane.add(backgroundLabel);

        // Set the content pane to the custom JPanel
        //setContentPane(contentPane);

        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAddAlarm) {
            int hour = (int) spinnerHour.getValue();
            int minute = (int) spinnerMinute.getValue();
            LocalTime time = LocalTime.of(hour, minute);

            Ringtone ringtone = Ringtone.valueOf((String) comboBoxRingtone.getSelectedItem());

            int volume = sliderVolume.getValue();

            LocalDateTime alarmDateTime = LocalDate.now().atTime(time);

            AlarmSettings alarm = new AlarmSettings(Hotel.getClientID(), Hotel.getRoomNumber(), alarmDateTime, ringtone, volume);
            
            boolean success = SQLQueries.insertDataIntoTblAlarmSettings(alarm);
            
            if (success) {
                Hotel.getInstance().getAlarmSettings().add(alarm);
                JOptionPane.showMessageDialog(this, "Alarm settings added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add alarm settings.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource()==btnBack) {
        	nextFrame.setVisible(true);
            this.setVisible(false);
        }
    }

}
