package pro;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandlerApplet extends RenderApplet implements KeyListener, Runnable {
	
	Sprite ball;
	
	boolean LEFT = false, RIGHT = false;
	
	/* Applet */
	public void init(){
		setSize(300, 300);
		addKeyListener(this);
		
		Image img = getImage( getCodeBase(), "rider_down_1.gif" );
		ball = new Sprite( 20, 20, img );
	}
	
	/* RenderApplet */
	@Override
	public void update(Graphics g){
		super.update(g);
	}
	
	/* Runnable */
	
	@Override
	public void run(){
		if (LEFT){
			//ball.setImage( getImage( getCodeBase(), "pikachu_surfing_left.gif" ) );
			ball.move(-4, 0);
		}
		else if (RIGHT){
			//ball.setImage( getImage( getCodeBase(), "pikachu_surfing_right.gif" ) );
			ball.move(4, 0);
		}
	}
	
	
	/* KeyListener */
	@Override  
	public void keyPressed( KeyEvent e ){
		int key = e.getKeyCode();
		
		if ( key == KeyEvent.VK_LEFT ){
			LEFT = true;
			RIGHT = false;
		}
		
		else if (key == KeyEvent.VK_RIGHT){
			RIGHT = true;
			LEFT = false;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if ( key == KeyEvent.VK_LEFT){
			LEFT = false;
		}
		
		else if (key == KeyEvent.VK_RIGHT){
			RIGHT = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/* Getters / Setters */
	public Sprite getBall() {
		return ball;
	}
	public void setBall(Sprite ball) {
		this.ball = ball;
	}
}
