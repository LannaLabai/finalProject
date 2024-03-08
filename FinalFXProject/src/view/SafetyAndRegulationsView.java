package view;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

import control.SafetyController;

public class SafetyAndRegulationsView extends BasicViewTemplate {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SafetyAndRegulationsView frame = new SafetyAndRegulationsView();
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
	public SafetyAndRegulationsView() {
		super("Safety and Regulations");
	}
	
	public SafetyAndRegulationsView(JFrame nextFrame) {
		super("Safety and Regulations",nextFrame);
	}
	
	public void initialize() {
		initializeDefault();
		
		lblTitle.setText("<html><h1>Safety and Regulations</h1></html>");
		lblSubtext.setText("");
		
		String safetyText="";
		try {
			safetyText = SafetyController.readTextFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JTextArea txtSafety = new JTextArea(safetyText);
		txtSafety.setEditable(false);
		txtSafety.setLineWrap(true);  // Enable line wrapping
		txtSafety.setWrapStyleWord(true);
		
		mainPanel.add(txtSafety);
		
	}

}
