package pro;

import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

public class AstroSprite extends Sprite {
	
	int frame = 20;
	public static final int LEFT = 0, RIGHT = 1;
	int direction = LEFT;
	boolean moving = false;
	
	/* Getters / Setters */
	
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
		
		int dx = 0;
		if (direction == LEFT){
			dir = "left_";
			dx = -2;
		}
		else{
			dir = "right_";
			dx = 2;
		}
		
		int current = 2;
		if ( isMoving() ){
			current = frame/10;
			move(dx,0);
		}
		
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
