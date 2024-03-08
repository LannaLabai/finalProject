package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import control.SQLQueries;
import model.Hotel;
import model.RoomType;

public class YourRoomView extends BasicViewTemplate {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YourRoomView frame = new YourRoomView();
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
	public YourRoomView() {
		super("Your Room");
	}
	
	public YourRoomView(JFrame nextFrame) {
		super("Your Room");
		this.nextFrame = nextFrame;
	}
	
	public void initialize() {
		initializeDefault();
		
		lblTitle.setText("<html><h1>Your Room</h1></html>");
		lblSubtext.setText("");
		
		int rt_id = Hotel.getInstance().getRoom().getRoomTypeID();
		RoomType rt = Hotel.getInstance().getRoomTypes().get(rt_id);
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
        
        JTextArea txtArea = new JTextArea(roomTypeDesc);
        
        txtArea.setLineWrap(true); // Enable line wrapping
        txtArea.setWrapStyleWord(true);
        
        JPanel txtAreaPanel = new JPanel();
        txtAreaPanel.add(txtArea);
        
        mainPanel.add(txtAreaPanel);
        
	}

}
