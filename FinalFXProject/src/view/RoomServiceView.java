package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import control.SQLQueries;
import model.*;

import java.awt.*;

public class RoomServiceView extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JFrame nextFrame;
	private JButton btnOrder;
	private JButton btnBack;
	private JLabel lblTotalPrice;
	
	private double totalPrice = 0;
	
	private ArrayList<FoodOrderItems> foodOrderItems;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoomServiceView frame = new RoomServiceView();
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
	public RoomServiceView() {
		super("Room Service");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setVisible(true);
        initialize();
    }
	
	public RoomServiceView(JFrame nextFrame) {
		super("Room Service");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100,800, 600);
        setVisible(true);
        this.nextFrame = nextFrame;
        initialize();
    }

    public void initialize() {
    	contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
        
        btnBack = new JButton("Back");
        btnBack.addActionListener(this);
        contentPane.add(btnBack,BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel(); // Panel to hold foodItemSelection panels
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Set layout to BoxLayout for vertical stacking
        scrollPane.setViewportView(panel);
        
        foodOrderItems = new ArrayList<>();

        // list of checkboxes containing food items with buttons to add or subtract from the amount
        for (FoodItem f : Hotel.getInstance().getFoodItems()) {
            JPanel foodItemSelectionPanel = new JPanel(); // Panel for each food item selection
            JLabel lblFoodItem = new JLabel(f.getFoodItemName());
            JLabel lblPrice = new JLabel(String.valueOf(f.getFoodItemCost()));
            JButton btnAddOne = new JButton("+");
            JLabel lblAmount = new JLabel("0");
            JButton btnSubtractOne = new JButton("-");
            
            FoodOrderItems item = new FoodOrderItems(f.getFoodItemID(),0);
            foodOrderItems.add(item);
            
            foodItemSelectionPanel.add(lblFoodItem);
            foodItemSelectionPanel.add(lblPrice);
            foodItemSelectionPanel.add(btnSubtractOne);
            foodItemSelectionPanel.add(lblAmount);
            foodItemSelectionPanel.add(btnAddOne);

            btnAddOne.addActionListener(e -> updateAmount(lblAmount,f, 1));
            btnSubtractOne.addActionListener(e -> updateAmount(lblAmount,f, -1));

            panel.add(foodItemSelectionPanel); // Add foodItemSelectionPanel to the main panel
        }
        
        btnOrder = new JButton("Order");
        
        lblTotalPrice = new JLabel("Total Price: "+String.valueOf(totalPrice));
        
        panel.add(lblTotalPrice);
        panel.add(btnOrder);
        
        btnOrder.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnOrder) {
        	double totalPrice = calculateTotalPrice();
        	LocalDateTime timeOfOrder = LocalDateTime.now();
        	String clientID = Hotel.getClientID();
        	int roomNumber = Hotel.getRoomNumber();
        	FoodOrder order = new FoodOrder(clientID, roomNumber, totalPrice, timeOfOrder);
        	SQLQueries.insertDataIntoTblFoodOrder(order);
        	order.setFoodOrderID(SQLQueries.readLastFoodOrderByClient(clientID, roomNumber));
        	System.out.println(order);
        	
        	for(FoodOrderItems f: foodOrderItems) {
        		if(f.getQuantity()>0) {
        			f.setFoodOrderID(order.getFoodOrderID());
            		SQLQueries.insertDataIntoTblFoodOrderItems(f);
            		order.addOrderContent(f);
            		System.out.println(f);
        		}
        		
        	}
        }
        if(e.getSource()==btnBack) {
			nextFrame.setVisible(true);
            this.setVisible(false);
		}
    }

 
    private double calculateTotalPrice() {
        double totalPrice = 0;
        for (FoodOrderItems f: foodOrderItems) {
        	double foodItemCost = Hotel.getInstance().getFoodItems().get(f.getFoodItemID()-1).getFoodItemCost();
            totalPrice += f.getQuantity() * foodItemCost;
        }
        return totalPrice;
    }

    private void updateAmount(JLabel lblAmount, FoodItem f, int delta) {
        int currentAmount = Integer.parseInt(lblAmount.getText()) + delta;
        if (currentAmount >= 0) {
            lblAmount.setText(String.valueOf(currentAmount));
            foodOrderItems.get(f.getFoodItemID()-1).setQuantity(currentAmount); 
            totalPrice = totalPrice+delta*f.getFoodItemCost();
            lblTotalPrice.setText("Total Price: "+String.valueOf(totalPrice));
        }
        else {
        	lblAmount.setText("0");
        	foodOrderItems.get(f.getFoodItemID()-1).setQuantity(0); 
        }
    }
	

}
