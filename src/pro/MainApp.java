package pro;

/**
 * @author Muhammad Ahmad Tirmazi
 */

import java.awt.DisplayMode;

import javax.swing.JFrame;
import javax.swing.UIManager;


public class MainApp extends JFrame{
	
	private static MainApp instance;
	public static MainApp getInstance(){
		if ( instance == null) instance = new MainApp();
		return instance;
	}
	
	private MainApp(){
	
		try{
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		}catch(Exception e){
			TextMessage.Display("Failed to set Native look and feel!");
		}
		
		setSize(800,600);
		setResizable(false);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		getContentPane().add( new RenderPanel() );
		
		DisplayMode displayMode = new DisplayMode(800, 600, 16, 75);
//		ScreenManager.setFullScreenWindow(displayMode, this);
	}
	
	public static void main(String[] args){
		MainApp mainApp = MainApp.getInstance();
		
		mainApp.setVisible(true);
	}
	
}


