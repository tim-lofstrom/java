package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.GameConstants;
import model.Level;
import model.Ship;

public class Display extends JPanel {
	
	private static final long serialVersionUID = 1L;
	Level level;
	private BufferedImage backgroundImage;
	
	public Display(Level level){
		this.level = level;

		try {
			
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("img/background.png");
			InputStream bufferedIn = new BufferedInputStream(in);
			backgroundImage = ImageIO.read(bufferedIn);
		} catch (IOException e) {
			backgroundImage = null;
		}
		
		setBackground(Color.black);
		setPreferredSize(new Dimension(GameConstants.PlayfieldSizeX,GameConstants.PlayfieldSizeY));
		setFocusable(true);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(backgroundImage, 0, 0, GameConstants.PlayfieldSizeX, GameConstants.PlayfieldSizeY, null);
		
		
		
		for(Ship s : level.objects){
			g.drawImage(s.getImage(), (int)s.getPosition().getX(), (int)s.getPosition().getY(), s.getWidth(), s.getHeight() , null);
		}
		
		
		for(int i = 0; i < level.numberOfShips(); i++){
			Ship s = level.getShip(i);
			g.drawImage(s.getImage(), (int)s.getPosition().getX(), (int)s.getPosition().getY(), s.getWidth(), s.getHeight() , null);
			
		}

		
		java.awt.Font currentFont = g.getFont();
		java.awt.Font newFont = currentFont.deriveFont(currentFont.getSize() * 2F);

		g.setFont(newFont);
		g.drawString("Exams left to fail: " + (level.examsMaxFail - level.examsFailed), 20, 40);
		g.drawString("Score: " + level.score, 20, 80);
		
		if(level.dead == true){
			g.drawString("Highscores: ", (GameConstants.PlayfieldSizeX / 2) - 100, 100);
			for(int i = 0; i < level.stats.nodes.size(); i++){
				g.drawString(i+1 + ". " + level.stats.nodes.get(i).getName() + " - " + level.stats.nodes.get(i).getScore(), 
						(GameConstants.PlayfieldSizeX / 2) - 100, 100 + ( 25 * (i+1)));
			}
			g.drawString("Press ESC for new game.", (GameConstants.PlayfieldSizeX / 2) - 100, 40);
		}
	}

}