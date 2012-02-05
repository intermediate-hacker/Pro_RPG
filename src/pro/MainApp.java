package pro;

/**
 * @author Muhammad Ahmad Tirmazi
 */

import javax.swing.JFrame;


public class MainApp extends JFrame{
	
	public MainApp(){
		setSize(300,300);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		getContentPane().add( new RenderPanel() );
	}
	
	public static void main(String[] args){
		MainApp mainApp = new MainApp();
		mainApp.setVisible(true);
	}
	
}


