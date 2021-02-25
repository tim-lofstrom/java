package model;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Emoji {
	
	
	public ArrayList<BufferedImage> emojis = new ArrayList<BufferedImage>();
	
	public Emoji(String[] names){
		
		String path = "png/";
		
		
		for (String name : names){
			
			try {
				InputStream in = this.getClass().getClassLoader().getResourceAsStream(path + name + ".png");
				InputStream bufferedIn = new BufferedInputStream(in);
				emojis.add(ImageIO.read(bufferedIn));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
