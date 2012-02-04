package pro;

import java.awt.Dimension;
import java.awt.Point;

/**
 * @author Muhammad Ahmad Tirmazi
 *
 */
public class Sprite {
	
	Point position;
	Dimension size;
	
	public Sprite( Point pos, Dimension size ){
		setPosition(pos);
		setSize(size);
	}
	
	public Sprite( int x, int y ){
		setPosition( new Point(x,y) );
	}
	
	public Sprite( int x, int y, int w, int h ){
		this( new Point(x,y), new Dimension(w,h) );
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}
	
	public int getX(){
		return position.x;
	}
	
	public void setX(int x){
		position.x = x;
	}
	
	public int getY(){
		return position.y;
	}
	
	public void setY(int y){
		position.y = y;
	}

	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}
	
	public double getWidth(){
		return size.getWidth();
	}
	
	public double getHeight(){
		return size.getHeight();
	}
	
	public void move(int dx, int dy){
		position.x += dx;
		position.y += dy;
	}
}
