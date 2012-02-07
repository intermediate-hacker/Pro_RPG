package pro;

import javax.swing.JOptionPane;

public class TextMessage {
	
	public static void Display( String msg ){
		JOptionPane.showMessageDialog(MainApp.getInstance(), msg);
	}

}
