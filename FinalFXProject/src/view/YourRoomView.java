package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
		
		
	}

}
