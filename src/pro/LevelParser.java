package pro;

import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class LevelParser {
	List<Sprite> tileArray = new ArrayList<Sprite>();
	Map<Character, String> tileMapping = new HashMap<Character, String>();
	Dimension tileSize;
	Applet applet;

	/* Constructors */
	
	public LevelParser(Dimension tileSize, Applet applet) {
		super();
		this.tileSize = tileSize;
		this.applet = applet;
	}
	
	public LevelParser( int w, int h, Applet applet ){
		this( new Dimension(w,h), applet );
	}

	/* Getters and Setters */

	
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

	public Applet getApplet() {
		return applet;
	}

	public void setApplet(Applet applet) {
		this.applet = applet;
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
	
	/* Drawing */
	
	public void drawTiles( Graphics g ){
		for( Sprite s : tileArray ){
			
			if( onScreen( s, applet ) ){
				g.drawImage(s.getImage(), s.getX(), s.getY(), getTileWidth(), getTileHeight(), getApplet());
			}
		}
	}
	
	boolean onScreen(Sprite spr, Applet applet){
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
				
				if (! line.startsWith("#")){
					
					String[] tmp = line.split("=");
					
					tileMapping.put( tmp[0].trim().charAt(0), tmp[1].trim() );
					System.out.println( tmp[0].trim().charAt(0) + "{}" +  tmp[1].trim() );
					
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
				
				if( ! line.startsWith("#")){
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
			
			for( Entry<Character, String> entry : tileMapping.entrySet() ){
				if ( entry.getKey() == c ){
					img = applet.getImage( applet.getCodeBase(), entry.getValue() );
				}
			}
			
			if ( img != null ){
				tileArray.add( new Sprite( x * getTileWidth(), y * getTileHeight(), img ));
			}
			
			x++;
		}
	}
}
