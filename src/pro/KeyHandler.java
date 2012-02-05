package pro;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class KeyHandler extends JPanel implements KeyListener, Runnable {
	
	boolean LEFT = false, RIGHT = false, UP = false, DOWN = false, K_A = false;
	
	AstroSprite astro;

	KeyHandler(){
		this.init();
	}
	
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
		
		Image img = Toolkit.getDefaultToolkit().getImage("astro_down_1.png");
		astro = new AstroSprite( new Point(40,160), new Dimension(21,29), img );
	}
	
	/* RenderApplet */
	@Override
	public void update(Graphics g){
		super.update(g);
	}
	
	/* Runnable */
	
	@Override
	public void run(){
		astro.setMoving(true);
		if (LEFT){
			astro.setDirection( AstroSprite.LEFT );
		}
		else if (RIGHT){
			astro.setDirection( AstroSprite.RIGHT );
		}
		else if (UP){
			astro.setDirection( AstroSprite.UP );
		}
		else if (DOWN){
			astro.setDirection( AstroSprite.DOWN );
		}
		
		else{
			astro.setMoving(false);
		}
	}
	
	
	/* KeyListener */
	@Override  
	public void keyPressed( KeyEvent e ){
		int key = e.getKeyCode();
		
		if ( key == KeyEvent.VK_UP ){
			if (!DOWN){
				resetMoves();
				UP = true;
			}
			
		}
		
		else if ( key == KeyEvent.VK_DOWN ){
			if (!UP){
				resetMoves();
				DOWN = true;
			}
		}
		else if ( key == KeyEvent.VK_LEFT ){
			if (!RIGHT){
				resetMoves();
				LEFT = true;
			}
		}
		
		else if (key == KeyEvent.VK_RIGHT){
			if (!LEFT){
				resetMoves();
				RIGHT = true;
			}
		}
		
		else if (key == KeyEvent.VK_A){
			K_A = true;
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
		
		else if ( key == KeyEvent.VK_UP ){
			UP = false;
		}
		
		else if (key == KeyEvent.VK_DOWN ){
			DOWN = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	boolean alreadyMoving(){
		if ( UP || DOWN || LEFT || RIGHT ){
			return true;
		}
		return false;
	}
	
	void resetMoves(){
		UP = false;
		DOWN = false;
		LEFT = false;
		RIGHT = false;
	}
}

