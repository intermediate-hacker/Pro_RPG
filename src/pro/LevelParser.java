package pro;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

public class LevelParser {
	List<Sprite> tileArray = new ArrayList<Sprite>();
	Map<Character, String> tileMapping = new HashMap<Character, String>();
	
	Point offset = new Point(0,0);
	Dimension levelSize = new Dimension(0,0);

	Dimension tileSize;
	JPanel frame;

	/* Constructors */
	
	public LevelParser(Dimension tileSize, JPanel frame) {
		super();
		this.tileSize = tileSize;
		this.frame = frame;
	}

	public LevelParser( int w, int h, JPanel applet ){
		this( new Dimension(w,h), applet );
	}

	/* Getters and Setters */

	/* Parser Lists */
	
	public List<Sprite> getTiles() {
		return tileArray;
	}

	public List<Sprite> getTileArray() {
		return tileArray;
	}

	public void setTileArray(List<Sprite> tileArray) {
		this.tileArray = tileArray;
	}

	public Map<Character, String> getTileMapping() {
		return tileMapping;
	}

	public void setTileMapping(Map<Character, String> tileMapping) {
		this.tileMapping = tileMapping;
	}

	public void setTiles(List<Sprite> tiles) {
		this.tileArray = tiles;
	}

	public Dimension getTileSize() {
		return tileSize;
	}

	public void setTileSize(Dimension tileSize) {
		this.tileSize = tileSize;
	}

	/* Members */
	
	public JPanel getApplet() {
		return frame;
	}

	public void setApplet(JPanel applet) {
		this.frame = applet;
	}
	
	public int getTileWidth(){
		return getTileSize().width;
	}
	
	public void setTileWidth(int w){
		this.tileSize.width = w;
	}
	
	public int getTileHeight(){
		return getTileSize().height;
	}
	
	public void setTileHeight(int h){
		this.tileSize.height = h;
	}
	
	public Point getOffset() {
		return offset;
	}

	public void setOffset(Point offset) {
		this.offset = offset;
	}
	
	/* Level Size */
	
	public Dimension getLevelSize() {
		return levelSize;
	}

	public void setLevelSize(Dimension levelSize) {
		this.levelSize = levelSize;
	}
	
	public void setLevelWidth(int w){
		levelSize.width = w;
	}
	
	public int getLevelWidth(){
		return levelSize.width;
	}
	
	public void setLevelHeight(int h){
		levelSize.height = h;
	}
	
	public int getLevelHeight(){
		return levelSize.height;
	}
	
	/* Collision Detection */
	
	public boolean checkCollision( AstroSprite plyr ){
		for( Sprite s : tileArray ){
			if ( s.isCollideable() && s.isCollidingRect(plyr) ){
				
				// if player is moving or the tiles are scrolling...
				if (plyr.getVectorY() < 0 || offset.y > 0) plyr.setTop( s.getBottom());
				else if (plyr.getVectorY() > 0 || offset.y < 0) plyr.setBottom( s.getTop());
				else if (plyr.getVectorX() < 0 || offset.x > 0) plyr.setLeft( s.getRight());
				else if (plyr.getVectorX() > 0 || offset.x < 0) plyr.setRight( s.getLeft());
			}
		}
		
		return false;
	}
	
	/* Drawing */
	
	public void drawTiles( Graphics g ){
		for( Sprite s : tileArray ){
			
			s.move(offset.x, offset.y);
			
			if( onScreen( s, frame ) ){ 
				if ( ! s.draw(g) )
				g.drawImage(s.getImage(), 
						s.getX(),
						s.getY(),
						getTileWidth(), getTileHeight(), getApplet());
			}
		}
	}
	
	public void updateTiles(){
		for( Sprite s : tileArray ){
			if ( onScreen( s, frame ) ){
				s.update(frame);
			}
		}
	}
	
	boolean onScreen(Sprite spr, JPanel applet){
		if ( spr.getX() < applet.getWidth() && spr.getY() < applet.getHeight() ){
			return true;
		}
		
		return false;
	}
	
	/* Parsing */
	
	/* Mapping */
	
	public void parseTileMapping( String filename ){
		try{
			BufferedReader reader = new BufferedReader( new FileReader(filename) );
			
			String line;
			
			while( (line = reader.readLine()) != null ){
				
				if (line.startsWith("$")){
					String[] tmp = line.split("=");
					
					String p = tmp[0].substring(1).trim();
					tileMapping.put( p.charAt(0), tmp[1].trim() );
				}
				
			}
			
		} catch(FileNotFoundException e){
			System.out.println("Could not find " + filename);
			System.exit(1);
		} catch( IOException e){
			System.out.println("Could not read line!");
			System.exit(1);	
		}
	}
	
	/* Level */
	public void parseLevel( String filename ){
		
		parseTileMapping( filename );
		
		BufferedReader reader;
		try{
			reader = new BufferedReader( new FileReader(filename) );
			parseTiles( reader );
			
		} catch(FileNotFoundException e){
			System.out.println("Could not find " + filename );
			System.exit(1);
		}
		
	}

	public void parseTiles( BufferedReader reader ){
		
		String line;
		
		try{
			
			int count = 1;
			while( (line = reader.readLine()) != null){
				
				if( ! line.startsWith("#") && ! line.startsWith("$") ){
					parseLine( line, count++ );
				}
			}
			
		}catch(IOException e){
			System.out.println("Failed to read line!");
			System.exit(1);
		}
	}
	
	void parseLine( String line, int count ){
		int x = 0, y = count;
		
		for( int i = 0; i < line.length(); i++){
			
			char c = line.charAt(i);
			Image img = null;
			
			if (tileMapping.containsKey(c)){
				img = Toolkit.getDefaultToolkit().getImage( "data/"+tileMapping.get(c));
			}
			
			if ( img != null ){
				
				Sprite s = new Sprite( new Point( x * getTileWidth(), y * getTileHeight() ),
						               new Dimension( getTileWidth(), getTileHeight() ),
						               img);
				
				if ( Character.isDigit(c)) s.setCollideable(false);
				
				tileArray.add( s );
			}
			
			x++;
			setLevelWidth( Math.max( x*getTileWidth(), getLevelWidth() ) );
			setLevelHeight( Math.max( y*getTileHeight(), getLevelHeight() ) );
		}
	}
}
