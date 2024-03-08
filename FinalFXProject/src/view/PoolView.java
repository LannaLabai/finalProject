package view;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

public class PoolView extends BasicViewTemplate {


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PoolView frame = new PoolView();
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
	public PoolView() {
		super("Pool");
	}
	
	public PoolView(JFrame nextFrame) {
		super("Pool",nextFrame);
		
	}
	
	@Override
	public void initialize() {
		
	}

}
