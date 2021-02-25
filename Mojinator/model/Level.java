package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

/**
 * Represents tha game and all contents
 * @author kurt
 */
public class Level extends Observable{

	public int examsMaxFail = 6;
	public Sound sound;
	public Stats stats;
	public int score = 0;
	private ArrayList<Ship> ships = new ArrayList<Ship>();
	public ArrayList<Ship> objects = new ArrayList<Ship>();
	private String oldPositions = "";
	private int id = -1;
	public Emoji emoji;
	public Emoji emojidead;
	private Emoji emojiObjects;
	public int playerHumor;
	public int examsFailed;
	public boolean dead = false;
	
	
	public Level(){
		
		stats = new Stats();
		sound = new Sound();
		Sound.playSound();
		dead = false;
		
		playerHumor = 5;
		
		//load deadface
		String[] dead = {"1f635"};
		emojidead = new Emoji(dead);
		
		//load playeremojis, 1f610 is neutral = 5
		String[] names = {"1f915","1f630","1f614","1f613","1f612", /*neutral=*/"1f610", "1f609", "1f603", "1f604", "1f605"};
		emoji = new Emoji(names);
		
		//load object-emojis
		String[] objs = {/*"1f48a"*/ "1f4dd", "2615"};
		emojiObjects = new Emoji(objs);
		
	}
	
	/**
	 * Adda ships to the gameplan
	 * @param id
	 */
	public void addPlayer(){
		ships.add(new Ship(id, emoji.emojis.get(5)));
	}
	
	/**
	 * get a ship by id
	 * @param id
	 * @return Ship
	 */
	public Ship getShipByID(int id){
		for(Ship s : ships){
			if(s.getID() == id){
				return s;
			}
		}
		return null;
	}
	
	/**
	 * Get ship by index
	 * @param index
	 * @return Ship
	 */
	public Ship getShip(int index){
		if(ships.size() != 0){
			return ships.get(index);
		}
		return null;

	}
	
	/**
	 * Number of ships on the gameplan
	 * @return noberofships int
	 */
	public int numberOfShips() {
		return ships.size();
	}

	/**
	 *recalculate all things needed 
	 */
	public void update() {
		
		//let all ships update their velocity etc
		for(Ship s : ships){
			s.update();
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * Set own client-id
	 * @param id
	 */
	public void setID(int id) {
		this.id = id;
	}
	
	/**
	 * Get own client-id
	 * @return id int
	 */
	public int getID(){
		return id;
	}

	/**
	 * Remove all ships and clear id
	 */
	public void removeAll() {
		ships.clear();
		objects.clear();
	}

	/**
	 * Delete a specific ships by id
	 * @param id int
	 */
	public void deletePlayer(int id) {
		for(int i = 0; i < numberOfShips(); i++){
			if(ships.get(i).getID() == id){
				ships.remove(i);
			}
		}
	}

	public String getOldPositions() {
		return oldPositions;
	}
	
	public void setOldPositions(String p){
		oldPositions = p;
	}

	public void addObjects() {
		Random r = new Random();
		// TODO Auto-generated method stub
		
		for(int i = 0; i < 6; i++){
			Ship newShip = new Ship(id, emojiObjects.emojis.get(0));
			newShip.effect = -1;
			newShip.setPosition(new Vector2D(GameConstants.PlayfieldSizeX,r.nextInt(GameConstants.PlayfieldSizeY- newShip.getHeight())));
			newShip.speed = (r.nextDouble() + 0.2)* 2;
			newShip.setDirection(new Vector2D(Math.PI));
			newShip.setSize(30,30);
			objects.add(newShip);		
		}
		
		for(int i = 0; i < 6; i++){
			Ship newShip = new Ship(id, emojiObjects.emojis.get(1));
			newShip.effect = 1;
			newShip.setPosition(new Vector2D(GameConstants.PlayfieldSizeX,r.nextInt(GameConstants.PlayfieldSizeY- newShip.getHeight())));
			newShip.speed = (r.nextDouble() + 0.2)* 2;
			newShip.setDirection(new Vector2D(Math.PI));
			newShip.setSize(30,30);
			objects.add(newShip);		
		}
	
		
	}

	public void addObject() {
		// TODO Auto-generated method stub
		Random r = new Random();
		int e = 0;
		int temp = r.nextInt(2);
		if(temp == 0){
			e = -1;
		}else{
			e = 1;
		}
		
		int adjuster = 2;
		if(playerHumor > 5){
			adjuster = playerHumor - 5;
		}
		
		int posneg = r.nextInt(2) - 1;
		double dir = r.nextDouble() * 0.5 * posneg + Math.PI;
		
		Ship newShip = new Ship(id, emojiObjects.emojis.get(temp));
		newShip.effect = e;
		newShip.setPosition(new Vector2D(GameConstants.PlayfieldSizeX,r.nextInt(GameConstants.PlayfieldSizeY - newShip.getHeight())));
		newShip.speed = (r.nextDouble() + 0.2)* adjuster;
		newShip.setDirection(new Vector2D(dir));
		newShip.setSize(30,30);
		objects.add(newShip);	
	}

	public void addScore(String s, int i) {
		stats.addStat(s, score, i);
	}


	
}
