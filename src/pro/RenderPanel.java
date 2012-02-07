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
	
	final int FPS = 100; 
	int currentFPS = 60; 
	
	public RenderPanel(){
		setBackground(Color.black);
		setSize(300,300);
		addKeyListener( new TAdapter() );
		setFocusable(true);
		
		ConfigurationLoader.loadFile(".game_conf");
		
		Image img = Toolkit.getDefaultToolkit().getImage( "data/player/astro_down_1.png" );
		astro = new AstroSprite( new Point(20,120), new Dimension(21,29), img );
		astro.setFrame(this);
		
		astro.setLevelParser(levelParser);
		levelParser.parseLevel("level_1.txt");
		
		timer = new Timer( 1000/FPS, this);
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		astro.update(this);
		levelParser.updateTiles();
		
		currentFPS = FrameCounter.getFramesPerSecond();
		
		repaint();
		
	}
	
	@Override
	public void paint(Graphics g){
		
		super.paint(g);
		
		g.setColor(Color.white);
		g.drawString("FPS: " + currentFPS, 10, 10);
		
		levelParser.drawTiles(g);
		astro.draw(g);
		
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
	
	private class TAdapter extends KeyAdapter {

		@Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
            int dir = astro.getDirection();

            if ( astro.isMoving() ) return;
            if ((key == KeyEvent.VK_LEFT)) {
                astro.setDirection( AstroSprite.LEFT );
                astro.setMoving(true);
            }

            if ((key == KeyEvent.VK_RIGHT) ) {
                astro.setDirection( AstroSprite.RIGHT );
                astro.setMoving(true);
            }

            if ((key == KeyEvent.VK_UP)  ) {
                astro.setDirection( AstroSprite.UP );
                astro.setMoving(true);
            }

            if ((key == KeyEvent.VK_DOWN)  ) {
                astro.setDirection( AstroSprite.DOWN );
                astro.setMoving(true);
            }
            
            if ( key == KeyEvent.VK_ESCAPE ){
            	
            	if ( ScreenManager.isFullScreen() ){
            		ScreenManager.restoreScreen();
            		MainApp.getInstance().setUndecorated(false);
            	} else{
            		ScreenManager.resetFullScreenWindow();
            	}
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
