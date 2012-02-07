package pro;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;

import javax.swing.JFrame;

public class ScreenManager {
	
	static GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
	static  GraphicsDevice device = environment.getDefaultScreenDevice();
	
	static boolean fullscreen;
	public static boolean isFullScreen(){
		return fullscreen;
	}
	
	public static void resetFullScreenWindow(){
		setFullScreenWindow( device.getDisplayMode(), MainApp.getInstance() );
	}
	
	public static void setFullScreenWindow( DisplayMode displayMode, JFrame window ){
		window.setUndecorated(true);
		window.setResizable(false);
		
		device.setFullScreenWindow(window);
		if ( displayMode != null && device.isDisplayChangeSupported() ){
			try{
				device.setDisplayMode(displayMode);
			}catch(IllegalArgumentException e) {}
		}
		
		fullscreen = true;
	}
	
	public static Window getFullScreenWindow(){
		return device.getFullScreenWindow();
	}
	
	public static void restoreScreen(){
		Window window = getFullScreenWindow();
		if ( window != null ){
			//window.dispose();
		}
		device.setFullScreenWindow(null);
	}

}
