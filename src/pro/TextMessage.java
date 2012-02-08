package pro;

import javax.swing.JOptionPane;


public class TextMessage {
	
	public static void Display( String msg ){
		JOptionPane.showMessageDialog(MainApp.getInstance(), msg, "Message", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void Error( String msg ){
		JOptionPane.showMessageDialog(MainApp.getInstance(), msg, "Error", JOptionPane.ERROR_MESSAGE );
	}

}
