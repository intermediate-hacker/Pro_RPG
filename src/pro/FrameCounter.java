package pro;

public class FrameCounter {
	
	static long currentTime = System.currentTimeMillis();
	static long lastTime = currentTime;
	static long totalTime = 0;
	static int fps = 0;
	static int frames = 0;
	
	public static int getFramesPerSecond(){
		lastTime = currentTime;
		currentTime = System.currentTimeMillis();
		totalTime += currentTime - lastTime;
		if ( totalTime > 100 ){
			totalTime -= 1000;
			fps = frames;
			frames = 0;
		}
		
		frames++;
		return fps;
	}

}
