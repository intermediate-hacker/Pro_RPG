package pro;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationLoader {
	
	public static String IMAGE_URL = "";
	public static String PLAYER_URL = "";
	
	private static String FILE; 
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
			if ( s.trim().startsWith(".player") ){
				PLAYER_URL = s.split("=")[1].trim() + "_";
			}
			
			if ( s.trim().startsWith(".images") ){
				IMAGE_URL = s.split("=")[1].trim() + "/";
			}
		}
	}

}
