package view;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Clock extends JPanel implements Runnable {
    private JLabel timeLabel;
    private SimpleDateFormat timeFormat;

    public Clock() {
        setOpaque(false); // Make the panel transparent
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        timeFormat = new SimpleDateFormat("hh:mm a", Locale.ENGLISH); // Format time in 12-hour format
        updateTime(); // Update time immediately
        add(timeLabel);
        Thread t = new Thread(this); // Start a thread to update time continuously
        t.start();
    }

    @Override
    public void run() {
        while (true) {
            updateTime();
            try {
                Thread.sleep(60000); // Update time every minute
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateTime() {
        String currentTime = timeFormat.format(new Date());
        timeLabel.setText(currentTime);
    }
}