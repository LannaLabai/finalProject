package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;
//we made this in order to show the user massage

public abstract class HotelDialog {
	public static Alert showZooDialog(AlertType atype, String title, 
			String message, ButtonType btype) {
		Alert alert = new Alert(atype, message, btype);
		alert.setTitle(title);
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.show();
		return alert;
	}
}