package pro;

import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

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

	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	/* Overrides */
	
	@Override
	public void update(Applet app){
		
		frame++;
		if ( frame > 30 ) frame = 10;
		
		String dir;
		
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
		
		int current = 2;
		if ( isMoving() ){
			current = frame/10;
		}
		else{
			setVectorX(0);
			setVectorY(0);
		}
		
		move( getVectorX(), getVectorY() );
		levelParser.checkCollision(this);
		
		setImage( app.getImage( app.getCodeBase(), "astro_" + dir + current + ".png" ) );
		
		
	}
	
	@Override
	public boolean draw(Graphics g){
		g.drawImage(getImage(), getX(), getY(), null);
		return true;
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
