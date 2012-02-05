package pro;

import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

/**
 * @author Muhammad Ahmad Tirmazi
 *
 */
public class Sprite {
	
	/* membars */
	Point position;
	Dimension size;
	Image image;
	Applet applet;
	
	/* Constructors */
	public Sprite(Point position, Dimension size, Image image) {
		super();
		this.position = position;
		this.size = size;
		this.image = image;
	}
	
	
	public Sprite(Point position, Dimension size) {
		super();
		this.position = position;
		this.size = size;
	}
	
	public Sprite(Point position, Image image) {
		super();
		this.position = position;
		this.image = image;
	}


	public Sprite(Point position) {
		super();
		this.position = position;
	}

	public Sprite( int x, int y ){
		setPosition( new Point(x,y) );
	}
	
	public Sprite( int x, int y, int w, int h ){
		this( new Point(x,y), new Dimension(w,h) );
	}
	
	public Sprite( int x, int y, Image img ){
		this( new Point(x,y), img );
	}

	/* Getters / Setters */
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
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
	
	/* Methods */
	public void move(int dx, int dy){
		position.x += dx;
		position.y += dy;
	}
	
	public void update(Applet app){
		
	}
	
	public boolean draw(Graphics g){
		return false;
	}
}
