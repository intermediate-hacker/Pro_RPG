package pro;

/**
 * @author Muhammad Ahmad Tirmazi
 */

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;


public class MainApp extends JFrame{
	
	private static MainApp instance;
	public static MainApp getInstance(){
		if ( instance == null) instance = new MainApp();
		return instance;
	}
	
	private MainApp(){
		setSize(300,300);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		getContentPane().add( new RenderPanel() );
		
		DisplayMode displayMode = new DisplayMode(800, 600, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
		//ScreenManager.setFullScreenWindow(displayMode, this);
	}
	
	public static void main(String[] args){
		MainApp mainApp = MainApp.getInstance();
		
		mainApp.setVisible(true);
	}
	
}


