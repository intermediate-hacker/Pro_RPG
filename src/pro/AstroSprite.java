package pro;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JPanel;

public class AstroSprite extends Sprite {
	
	int frame = 20;
	public static final int LEFT = 0, RIGHT = 1, UP = 2, DOWN = 3;
	
	int direction = LEFT;
	boolean moving = false;
	
	LevelParser levelParser;
	
	/* Getters / Setters */
	
	public LevelParser getLevelParser() {
		return levelParser;
	}

	public void setLevelParser(LevelParser levelParser) {
		this.levelParser = levelParser;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public int getFrameCount() {
		return frame;
	}

	public void setFrameCount(int frame) {
		this.frame = frame;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	/* Overrides */
	
	/* Loop */
	@Override
	public void update(JPanel app){
		
		int current = 2;
		String dir = "down_";
		
		if (!levelParser.checkCollision(this)){
			frame++;
			if ( frame > 30 ) frame = 10;
			
			setVectorX(0);
			if (direction == LEFT){
				dir = "left_";
				setVector( new Point(-2,0) );
			}
			else if (direction == RIGHT){
				dir = "right_";
				setVector( new Point(2, 0) );
			}
			else if(direction == UP){
				dir = "up_";
				setVector( new Point(0, -2) );
			}
			else{
				dir = "down_";
				setVector( new Point(0, 2) );
			}
			
			if ( isMoving() ){
				current = frame/10;
			}
			else{
				setVectorX(0);
				setVectorY(0);
			}
			
			calculateOffset();
			move( getVectorX(), getVectorY() );
		}
		
		setImage( Toolkit.getDefaultToolkit().getImage( ConfigurationLoader.IMAGE_URL
														+ ConfigurationLoader.PLAYER_URL
														+ dir + current + ".png" ) );	
	}
	
	@Override
	public boolean draw(Graphics g){
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
		return true;
	}

	/* Methods */
	
	/* Calculations */
	
	public void calculateOffset(){
		
		/* Calculate the Offsets for scrolling around the level */
		
		// constants
		final int centerx = getFrame().getWidth() / 2;
		final int centery = getFrame().getHeight() / 2;
		
		levelParser.setOffset( new Point(0,0) );
		
		if  (isMoving()){
			
			/* the confusing conditionals 'if' jungle */
			if ( getDirection() == LEFT){
				if ( getLeft() < centerx ){
					//if ( levelTop > 0 ){
							levelParser.getOffset().x = 2;
							setVectorX(0); /* stop the player from moving */
					//}
				}
			}
			
			if ( getDirection() == RIGHT ){
				// if sprite has passed the frame center 
				if ( getRight() > centerx ){
					//if ( levelWidth > levelRight ){ // this check is not working.
						levelParser.getOffset().x = -2;
						setVectorX(0);
					//}
				}
			}
			
			if ( getDirection() == UP ){
				if ( getTop() < centery ){
				//	if( levelLeft < 0 ){
						levelParser.getOffset().y = 2;
						setVectorY(0);
					//}
				}
			}
			
			if ( getDirection() == DOWN ){
				if ( getBottom() > centery ){
					//if ( levelHeight > levelBottom ){ // fixme
						levelParser.getOffset().y = -2;
						setVectorY(0);
					//}
				}
			}
			/* you crossed it! hope you comprehended a word or two */
			
		} /* if isMoving() */
	}
	
	/* Constructors */
	
	public AstroSprite(int x, int y, Image img) {
		super(x, y, img);
		// TODO Auto-generated constructor stub
	}

	public AstroSprite(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	public AstroSprite(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public AstroSprite(Point position, Dimension size, Image image) {
		super(position, size, image);
		// TODO Auto-generated constructor stub
	}

	public AstroSprite(Point position, Dimension size) {
		super(position, size);
		// TODO Auto-generated constructor stub
	}

	public AstroSprite(Point position, Image image) {
		super(position, image);
		// TODO Auto-generated constructor stub
	}

	public AstroSprite(Point position) {
		super(position);
		// TODO Auto-generated constructor stub
	}

	
}
