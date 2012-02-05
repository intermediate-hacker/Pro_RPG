package pro;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandlerApplet extends RenderApplet implements KeyListener, Runnable {
	
	boolean LEFT = false, RIGHT = false;
	
	AstroSprite astro;
	
	/* Getters / Setters */
	
	public AstroSprite getAstro() {
		return astro;
	}

	public void setAstro(AstroSprite astro) {
		this.astro = astro;
	}

	/* Applet */
	public void init(){
		setSize(300, 300);
		addKeyListener(this);
		
		astro = new AstroSprite(40,00, getImage( getCodeBase(), "astro_1.png" ));
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
			astro.setDirection( AstroSprite.LEFT );
		}
		else if (RIGHT){
			astro.setDirection( AstroSprite.RIGHT );
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
}
