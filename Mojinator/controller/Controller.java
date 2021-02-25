package controller;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.JOptionPane;

import model.GameConstants;
import model.Level;
import model.Ship;
import model.Sound;
import model.Vector2D;
import view.KeyboardState;

/**
 * Controls the model
 * @author kurt
 */
public class Controller extends Observable implements Observer, Runnable{

	int vk_up_oldState = 0;
	boolean run = false;
	Level level;
	long gameTime;
	long prevGameTime = 0;
	long update;
	long time = 0;
	double oldSpeed = 0;
	double oldRot = 0;
	int timeout = 3;

	public Controller(){
		level = new Level();
		level.addObserver(this);
		run = false;
	}

	/**
	 * Returns the level that represents the players and the playfield
	 * @return level Level
	 */
	public Level getLevel(){
		return level;
	}

	/**
	 * Refresh and calculate what to do
	 */
	@Override
	public void update(Observable caller, Object message) {

		if(updateObjects() == false){
			calcScore();
		}else{
			
			if(((long)message - prevGameTime) > 1000){
				timeout --;
				if (timeout == 0){
					slowDownObjects(0);
					timeout = 3;
				}
				prevGameTime =(long)message; 
			}

			Ship me = level.getShipByID(level.getID());
			if(me != null){
				if((me.getPosition().getY() + me.getHeight()) < GameConstants.PlayfieldSizeY){
					Vector2D newPos = me.getPosition().add(me.getGravitation().dot(me.counter)); 
					if(newPos.getY() > 0){
						if(newPos.getY() + me.getHeight() > GameConstants.PlayfieldSizeY){
							newPos.set(newPos.getX(), GameConstants.PlayfieldSizeY - me.getHeight());
						}
						me.setPosition(newPos);						
					}else{
						me.counter = 0;
					}
					
					int adjust = 1;
					if(level.playerHumor > 5){
						adjust = level.playerHumor - 4;
					}
					me.counter += 0.1 * adjust;
				}
			}

				
		}
			
		
	}
		
	private boolean updateObjects() {
		
		Ship me = level.getShipByID(level.getID());
		
		Ship toRemove = null;
		
		for(Ship obj : level.objects){
			obj.setPosition(obj.getPosition().add(obj.getDirection().dot(obj.getSpeed())));
			if(obj.intersect(me.getPosition(), new Vector2D(me.getWidth(), me.getHeight()))){
				toRemove = obj;
				if((toRemove.effect == -1) && (level.playerHumor >= 0)){
					
					
					if(level.playerHumor > 5){
						level.playerHumor =5;
					}else if (level.playerHumor > 0){
						level.playerHumor += toRemove.effect;	
					}
					
					//had to write exam... :/
					level.score --;
					level.examsFailed ++;
					if(level.examsFailed >= level.examsMaxFail){
						//set dead
						level.getShipByID(level.getID()).setImage(level.emojidead.emojis.get(0));
						exitGame();
						return false;
					}
					
					
					slowDownObjects(0);
					level.getShipByID(level.getID()).setImage(level.emoji.emojis.get(level.playerHumor));
//				}else if((toRemove.effect == 1) && (level.playerHumor <= level.emoji.emojis.size()-1)){
				}else if(toRemove.effect == 1){
					
					if(level.playerHumor < 5){
						level.playerHumor =5;
					}else if (level.playerHumor < level.emoji.emojis.size()-1){
						level.playerHumor += toRemove.effect;
					}
					
					//got coffe
					level.score += 10;
					timeout = 3;
					
					for(int i = 0; i < level.objects.size(); i++){
						if(level.playerHumor > 5){
							int adjuster = level.playerHumor - 5;
							level.objects.get(i).speed += adjuster;
						}
					}
				
					level.getShipByID(level.getID()).setImage(level.emoji.emojis.get(level.playerHumor));
				}
			}
		}
		
		Random r = new Random();
		
		if(toRemove != null){
			int posneg = r.nextInt(2) - 1;
			double dir = r.nextDouble() * 0.5 * posneg + Math.PI;
			int y_rand = r.nextInt(GameConstants.PlayfieldSizeY);
			toRemove.setDirection(new Vector2D(dir));
			toRemove.setPosition(new Vector2D(GameConstants.PlayfieldSizeX + 150, y_rand));
		}
		
		for(int i = 0; i < level.objects.size(); i++){
			if(level.objects.get(i).getPosition().getX() < 0){
				int y_rand = r.nextInt(GameConstants.PlayfieldSizeY);
				int posneg = r.nextInt(2) - 1;
				double dir = r.nextDouble() * 0.5 * posneg + Math.PI;
				level.objects.get(i).setDirection(new Vector2D(dir));
				level.objects.get(i).setPosition(new Vector2D(GameConstants.PlayfieldSizeX + 150, y_rand));
			}
		}
		
		return true;
		
	}
	

	public void slowDownObjects(int delay){
		new Thread(new Runnable() {
			public void run() {
				try {	
					Random r = new Random();
					for(int i = 20; i > 0; i++){
						for(Ship s : level.objects){
							double newSpeed = s.speed - r.nextDouble();
							if(newSpeed > 5){
								s.speed = newSpeed;
							} 
						}
						Thread.sleep(8);
					}
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}).start();
	}
	
	/**
	 * Checks the state of the keyboard, so the player can move
	 * @param s KeyboardState
	 */
	public void updateInput(KeyboardState s) {
		

		Ship me = level.getShipByID(level.getID());

		if(me != null){
			
			if(s.VK_ESC == KeyboardState.RELEASED){
				s.VK_ESC = KeyboardState.PRESSED;
				
				exitGame();
				startGame();
			} else {
				if(s.VK_LEFT == KeyboardState.PRESSED){
					
					if(me.getDirection().getX() == 1.0){
						me.speed = 0.1;
					}
					me.setDirection(new Vector2D(-1.0,0.0));				
					if(me.speed < me.maxSpeed){
						me.speed += 0.15;					
					}
					
				}
				if(s.VK_RIGHT == KeyboardState.PRESSED){
					
					if(me.getDirection().getX() == -1.0){
						me.speed = 0.1;
					}
					me.setDirection(new Vector2D(1.0,0.0));
					if(me.speed < me.maxSpeed){
						me.speed += 0.15;					
					}
					
				}
				
				if(me.speed > 0){
					me.speed -= 0.05;
					if(me.speed < 0){
						me.speed = 0;
					}
				}
				
				Vector2D newPos = me.getPosition().add(me.getDirection().dot(me.getSpeed()));
				if((newPos.getX() < GameConstants.PlayfieldSizeX - me.getWidth()) && (newPos.getX() > 0)){
					me.setPosition(newPos);
				}			
				
				
	
				if((s.VK_UP == KeyboardState.PRESSED) && (vk_up_oldState == 0)){
					me.counter = -2 * ((level.playerHumor + 1) / 2);
					Sound.playJump();
					Vector2D newPos2 = me.getPosition().add(new Vector2D(0.0, -1.0).dot(2));
					if(newPos2.getY() > 0){
						me.setPosition(newPos2);
					}else{
						me.counter = 0;
					}
				}
				
			}
			}
		
		vk_up_oldState = s.VK_UP;
	}

	/**
	 * Gameloop
	 */
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
			}
			if(run == true){
				this.update(this, gameTime);
				setChanged();
				notifyObservers();
				gameTime = System.currentTimeMillis();

			}

		}
	}

	/**
	 * Exit the current game
	 */
	public void exitGame() {		
		level.dead = true;
		run = false;
	}
	
	public boolean calcScore(){
		
		for(int i = 0; i < level.stats.nodes.size(); i++){
			if(level.score > level.stats.nodes.get(i).getScore()){
				String s = (String)JOptionPane.showInputDialog("New Highscore! Enter nick:");
				level.addScore(s, i);
				level.stats.saveFile();
				return true;
			}
		}
		
		if(level.stats.nodes.size() == 0){
			String s = (String)JOptionPane.showInputDialog("New Highscore! Enter nick:");
			level.addScore(s, 0);
			level.stats.saveFile();
		}
		
		return true;
	}

	/**
	 * Start a new game
	 */
	public void startGame() {
		level.removeAll();
		level.dead = false;
		level.playerHumor = 5;
		level.score = 0;
		level.examsFailed = 0;
		level.setID(1);
		level.addPlayer();
		level.addObjects();
		run = true;
	}

	public boolean running() {
		return run;
	}

}

