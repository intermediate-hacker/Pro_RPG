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
	
	/* members */
	Point position;
	Point vector = new Point(0,0);
	
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
		this( position, size, null );
	}
	
	public Sprite(Point position, Image image) {
		this( position, 
				new Dimension( image.getWidth( null ), image.getHeight( null ) ),
				image
				);
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
	
	/* Image */
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	/* Position */
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

	/* Size */
	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}
	
	public int getWidth(){
		return size.width;
	}
	
	public int getHeight(){
		return size.height;
	}
	
	/* Rect */
	public void setTop(int arg0){
		setY(arg0);
	}
	
	public int getTop(){
		return getY();
	}
	
	public void setBottom(int arg0){
		setY( arg0 - getWidth() );
	}
	
	public int getBottom(){
		return getY() + getWidth();
	}

	public void setRight(int arg0){
		setY( arg0 - getWidth() );
	}
	
	public int getRight(){
		return getY() + getWidth();
	}
	
	public void setLeft(int arg0){
		setY(arg0);
	}
	
	public int getLeft(){
		return getY();
	}
	
	/* Movement */
	public Point getVector() {
		return vector;
	}

	public void setVector(Point vector) {
		this.vector = vector;
	}
	
	public void setVectorX(int x){
		this.vector.x = x;
	}
	
	public int getVectorX(){
		return vector.x;
	}
	
	public void setVectorY(int y){
		this.vector.y = y;
	}
	
	public int getVectorY(){
		return vector.y;
	}

	/* Other */
	public Applet getApplet() {
		return applet;
	}


	public void setApplet(Applet applet) {
		this.applet = applet;
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
	
	public boolean isCollidingRect( Sprite s ){
		if ( getRight() > s.getLeft() 
			&& getLeft() < s.getRight()
			&& getBottom() > s.getTop()
			&& getTop() < s.getBottom() ) 
			return true;
		return false;
	}
}
