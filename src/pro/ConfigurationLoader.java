package pro;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationLoader {
	
	public static String IMAGE_URL = "";
	public static String TILE_URL = "";
	public static String PLAYER_URL = "";
	
	private static String FILE = "Empty"; 
	public static void loadFile(String filename){
		StringBuilder file = new StringBuilder();
		try{
			
			BufferedReader reader = new BufferedReader( new FileReader(filename) );
			try{
				String line = null;
				
				while( (line = reader.readLine()) != null ){
					file.append(line);
					file.append(System.getProperty("line.separator"));
				}
				
				reader.close();
			}catch(IOException e){
				System.out.println(e.getMessage());
			}finally{
				FILE = file.toString();
			}
			
		}catch(FileNotFoundException e){
			System.out.println("Could not find: " + file);
		}
		
		parseFile();
	}
	
	private static void parseFile(){
		for( String s : FILE.split("\n")){
			String v;
			if ( (v = Parse( s, ".player", "_" )) != null){
				PLAYER_URL = v;
			}
			
			if ( (v = Parse( s, ".images", "/" )) != null){
				IMAGE_URL = v;
			}
		}
	}
	
	public static String Parse( String s, String value, String suffix ){
		if ( s.trim().startsWith(value) ){
			return ( s.split("=")[1].trim() + suffix );
		}
		else{
			return null;
		}
	}

}
