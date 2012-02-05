package pro;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class RenderPanel extends JPanel implements ActionListener {
	
	Timer timer;
	AstroSprite astro;
	LevelParser levelParser = new LevelParser(20,20,this);
	
	final int FPS = 60; 
	
	public RenderPanel(){
		setBackground(Color.black);
		setSize(300,300);
		addKeyListener( new TAdapter() );
		setFocusable(true);
		
		Image img = Toolkit.getDefaultToolkit().getImage( "astro_down_1.png" );
		astro = new AstroSprite( new Point(20,20), new Dimension(21,29), img );
		
		astro.setLevelParser(levelParser);
		levelParser.parseLevel("level_1.txt");
		
		timer = new Timer( 1000/FPS, this);
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		astro.update(this);
		levelParser.updateTiles();
		
		repaint();
		
	}
	
	@Override
	public void paint(Graphics g){
		
		super.paint(g);
		
		levelParser.drawTiles(g);
		astro.draw(g);
		
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
	
	private class TAdapter extends KeyAdapter {

		@Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT)) {
                astro.setDirection( AstroSprite.LEFT );
                astro.setMoving(true);
            }

            if ((key == KeyEvent.VK_RIGHT) ) {
                astro.setDirection( AstroSprite.RIGHT );
                astro.setMoving(true);
            }

            if ((key == KeyEvent.VK_UP) ) {
                astro.setDirection( AstroSprite.UP );
                astro.setMoving(true);
            }

            if ((key == KeyEvent.VK_DOWN)) {
                astro.setDirection( AstroSprite.DOWN );
                astro.setMoving(true);
            }
        }
        
        @Override
        public void keyReleased(KeyEvent e){
        	int key = e.getKeyCode();
        	
        	if ( key == KeyEvent.VK_UP ){
        		astro.setMoving(false);
        	}
        	
        	if ( key == KeyEvent.VK_DOWN ){
        		astro.setMoving(false);
        	}
        	
        	if ( key == KeyEvent.VK_LEFT ){
        		astro.setMoving(false);
        	}
        	
        	if ( key == KeyEvent.VK_RIGHT ){
        		astro.setMoving(false);
        	}
        }
	}
	
}
