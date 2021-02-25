package bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		
		try {
			URL l = new URL("http://www.ltu.se/");
			
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(l.openStream()));
			
			String inputLine;
	        while ((inputLine = in.readLine()) != null)
	            System.out.println(inputLine);
	        in.close();
	        
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		
		
//		Funkt f = new Funkt();
		
		
//		for(int i = 0; i < 200; i++){
//			f.move(100, 100+i);
//		}


	}

}
