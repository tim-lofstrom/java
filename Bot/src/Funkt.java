import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.KeyEvent;


public class Funkt {
	
	Robot robot;
	
	int startX;
	int startY;
	
	public Funkt(){
		
	    try {
	        
	        robot = new Robot();
	        // Creates the delay of 5 sec so that you can open notepad before
	        // Robot start writting
	        robot.delay(3000);	        
	    } catch (AWTException e) {
	        e.printStackTrace();
	    }
	}
	
	public void pressButton(){
		robot.keyPress(KeyEvent.VK_SPACE);
		sleep(100);
		robot.keyRelease(KeyEvent.VK_SPACE);		
	}

	public void mouseMove(int x, int y){
		
		robot.mouseMove(startX + x, startY + y);
		
	}
	
	public void initializeMouse(){
		//startX = MouseInfo.getPointerInfo().getLocation().x;
		//startY = MouseInfo.getPointerInfo().getLocation().y;
		
		startX = 585;
		startY = 283;
		
		//515
	}
	
	public void sleep(long d){
		try {
			Thread.sleep(d);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean exit(){
		int x = MouseInfo.getPointerInfo().getLocation().x;
		int y = MouseInfo.getPointerInfo().getLocation().y;
		
		if(x < startX || y < startY){
			return true;
		}
		else{
			return false;
		}
	}
	
}





