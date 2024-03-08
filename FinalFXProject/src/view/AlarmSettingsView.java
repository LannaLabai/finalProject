package view;

import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.JSpinner.DefaultEditor;

import utils.Ringtone;

public class AlarmSettingsView extends BasicViewTemplate{

	private JButton btnAddAlarm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlarmSettingsView frame = new AlarmSettingsView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AlarmSettingsView() {
		super("Alarm Settings");
	}
	
	public AlarmSettingsView(JFrame nextFrame) {
		super("Alarm Settings",nextFrame);
	}
	
	public void initialize() {
		initializeDefault();
		
		lblTitle.setText("<html><h1>Alarm Settings</h1></html>");
		lblSubtext.setText("");
		
		JLabel lblSetAlarm = new JLabel("Add alarm:");
		JLabel lblRingtone = new JLabel("Choose ringtone:");
		JLabel lblVolume = new JLabel("Select volume:");
		
		JPanel timeSelectionPanel = new JPanel();
        mainPanel.add(timeSelectionPanel);

        SpinnerModel hourModel = new SpinnerNumberModel(7, 0, 23, 1);
        JSpinner spinnerHour = new JSpinner(hourModel);
        timeSelectionPanel.add(spinnerHour);

        SpinnerModel minuteModel = new SpinnerNumberModel(0, 0, 59, 5);
        JSpinner spinnerMinute = new JSpinner(minuteModel);
        timeSelectionPanel.add(spinnerMinute);
        
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinnerMinute, "00");
        spinnerMinute.setEditor(editor);
		
		JComboBox<String> comboBoxRingtone = new JComboBox<>();
	    for(Ringtone r: Ringtone.values()) {
	    	comboBoxRingtone.addItem(r.toString());
	    }

	    // Create JSlider for selecting volume
	    JSlider sliderVolume = new JSlider(JSlider.HORIZONTAL, 0, 9, 0);
	    sliderVolume.setMajorTickSpacing(1);
	    sliderVolume.setPaintTicks(true);
	    sliderVolume.setPaintLabels(true);
	    sliderVolume.setSnapToTicks(true);
	    sliderVolume.setValue(3);

	    // Create JButton for adding alarm
	    JButton btnAddAlarm = new JButton("Add Alarm");
	    btnAddAlarm.addActionListener(e -> {
            int selectedHour = (int) spinnerHour.getValue();
            int selectedMinute = (int) spinnerMinute.getValue();

            LocalDate currentDate = LocalDate.now();

            // Get the selected time
            LocalTime selectedTime = LocalTime.of(selectedHour, selectedMinute);

            // Create LocalDateTime for the selected time on the day after
            LocalDateTime alarmDateTime = LocalDateTime.of(currentDate.plusDays(1), selectedTime);

            // Display the selected alarm time
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            JOptionPane.showMessageDialog(this, "Alarm set for: " + alarmDateTime.format(formatter), "Alarm Set", JOptionPane.INFORMATION_MESSAGE);

        });

	    // Add components to the panel
	    mainPanel.add(lblSetAlarm);
	    // Add more components as needed
	    mainPanel.add(lblRingtone);
	    mainPanel.add(comboBoxRingtone);
	    mainPanel.add(lblVolume);
	    mainPanel.add(sliderVolume);
	    mainPanel.add(btnAddAlarm);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
