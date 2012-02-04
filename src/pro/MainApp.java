package pro;

/**
 * @author Muhammad Ahmad Tirmazi
 */

import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;


public class MainApp extends RenderApplet implements Runnable {
	
	/* constants */
	long FPS = 60;
	int FRAME = 0;
	
	/* members */
	Thread thread;
	
	Image ball_img;
	Sprite ball;
	
	/** singleton code **/
	static MainApp instance;
	public MainApp getInstance(){
		if ( instance == null )
			instance = new MainApp();
		return instance;
	}
	
	/* override methods */
	
	/* Applet - Loop */
	@Override
	public void init(){
		setSize(300, 300);
		ball_img = getImage( getCodeBase(), "rider.png" );
		ball = new Sprite( 20, 20 );
	}
	
	@Override 
	public void start(){
		thread = new Thread( this );
		thread.start();
	}
	
	@Override 
	public void stop(){
		thread = null;
	}

	/* Applet - Graphics */
	@Override
	public void paint(Graphics g){
		super.paint(g);
		
		g.drawString("Frame: " + FRAME, 20, 20);
		g.drawImage(ball_img, ball.getX(), ball.getY(), null);
	}
	
	/* Applet - Input */
	@Override
	public boolean keyDown(Event e, int key){
		if ( key == Event.LEFT ) ball.move(-5, 0);
		if ( key == Event.RIGHT ) ball.move(5, 0);
		
		return true;
	}
	
	/* Runnable */
	
	@Override 
	public void run(){
		while( Thread.currentThread() == thread ){
			repaint();
			try{
				Thread.sleep( 1000 / FPS );
			}catch (InterruptedException e){
				break;
			}
			
			/* increment the frame */
			FRAME++;
		}
	}
	
}


