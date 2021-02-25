package bot;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;

public class Funkt {


	
	Robot r;
	
	public Funkt(){
		try {
			r = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void move(int x, int y){
		r.mouseMove(x, y);
	}


	public boolean exit() {
		
	
		return false;
	}
	
}
