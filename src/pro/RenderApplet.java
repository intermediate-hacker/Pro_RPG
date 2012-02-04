package pro;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;

public class RenderApplet extends Applet {
	
	Image dbImage;
	Graphics dbg;
	
	@Override
	public void update(Graphics g){
		if (dbImage == null){
			dbImage = createImage( getWidth(), getHeight() );
			dbg = dbImage.getGraphics();
		}
		
		dbg.setColor( getBackground() );
		dbg.fillRect(0, 0, getWidth(), getHeight());
		
		dbg.setColor(getForeground());
		paint(dbg); // call paint with double buffered graphics
		
		g.drawImage(dbImage, 0, 0, this); // draw the back buffer
	}
}
