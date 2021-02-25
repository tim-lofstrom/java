package model;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Represent a ship to play with
 * @author kurt
 */
public class Ship {
	
	Vector2D oldDirection;
	Vector2D oldPosition;
	Vector2D position;
	Vector2D direction;
	Vector2D gravitation;
	Color color;
	public double maxSpeed;
	public double speed;
	int width;
	int height;
	int id;
	final double scale = 0.7;
	public double counter = 0;
	private BufferedImage image;
	public int effect;
	
	public Ship(int id, BufferedImage image){
		this.image = image;
		position = new Vector2D(100, 100);
		gravitation = new Vector2D(0.0, 1.0);
		oldPosition = position;
		direction = new Vector2D(0,0);
		speed = 6;
		oldDirection = direction;
		width = 60;
		height = 60;
		this.id = id;
		maxSpeed = 4.0;
	}
	
	public boolean intersect (Vector2D v, Vector2D size){
				
		return(!(this.position.x > v.x+size.x||
				this.position.x + this.width < v.x	||
				this.position.y > v.y+size.y		||
				this.position.y + this.height < v.y	));
		
		/*return(!(this.position.x > v.x+width		||
				this.position.x + this.width < v.x	||
				this.position.y > v.y+height		||
				this.position.y + this.height < v.y	));*/
	}
	
	public double getPotentialHeight(){
		return GameConstants.PlayfieldSizeY - position.y;
	}
	
	public Vector2D getGravitation(){
		return gravitation;
	}
	
	public void setGravitation(Vector2D gravitation){
		this.gravitation = gravitation;
	}
	
	public Color getColor(){
		return color;
	}
	
	public void setOldDirection(Vector2D old){
		oldDirection = old;
	}
	
	public Vector2D getOldDirection(){
		return oldDirection;
	}
	
	public void setOldPosition(Vector2D old){
		oldPosition = old;
	}
	
	public Vector2D getOldPosition(){
		return oldPosition;
	}
		
	public void setPosition(Vector2D position){
		this.position = position;
	}
		
	public void setDirection(Vector2D direction){
		this.direction = direction;
	}
	
	public Vector2D getPosition(){
		return position;
	}	

	public Vector2D getDirection() {
		return direction;
	}

	public int getID() {
		return id;
	}
	
	/**
	 * Calculate the ships movement
	 */
	public void update(){
		
		//om man åker utanför kanten
		if (position.x > GameConstants.PlayfieldSizeX)
			position.x = 0 - width;
        if (position.x + width < 0)
        	position.x = GameConstants.PlayfieldSizeX;
        if (position.y > GameConstants.PlayfieldSizeY)
        	position.y = 0 - height;
        if (position.y + height < 0)
        	position.y = GameConstants.PlayfieldSizeY;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public double getScale() {
		return scale;
	}

	public double getSpeed(){
		return speed;
	}

	public BufferedImage getImage(){
		return image;
	}
	
	public void setImage(BufferedImage bufferedImage) {
		this.image = bufferedImage;
	}

	public void setSize(int i, int j) {
		this.width=i;
		this.height=j;
		
	}
}
 