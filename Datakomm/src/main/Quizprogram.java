package main;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class Quizprogram extends JPanel{
	
	ArrayList<Question> questions_done = new ArrayList<Question>();
	ArrayList<Question> questions = new ArrayList<Question>();
	Question currentQuestion;
	
	int counter;
	int tries;
	boolean done;
	Random random;
	JFrame frame;
	JPanel buttons;
	JButton yes, no;
	JLabel question;
	JLabel resultLabel;
	JLabel counterLabel;
	JLabel numTries;
	SpringLayout springLayout;
	JTextArea area;
	
	public Quizprogram(){

		frame = new JFrame("Computer Communications Quizgame");
		frame.setPreferredSize(new Dimension(600, 400));
		buttons = new JPanel();
		yes = new JButton("True [q]");
		no = new JButton("False [w]");
		question = new JLabel();
		area = new JTextArea(10,10);
		area.setFont(new Font("Helvetica", Font.PLAIN, 16));
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		area.setEditable(false);
		area.setMargin(new Insets(10, 10, 10, 10));
		
		random = new Random();
		resultLabel = new JLabel();
		counterLabel = new JLabel();
		numTries = new JLabel();
		initGame();
		
		buttons.add(yes);
		buttons.add(no);
		Container pane = frame.getContentPane(); 
		
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		counterLabel.setAlignmentX(pane.CENTER_ALIGNMENT);
		pane.add(counterLabel);
		numTries.setAlignmentX(pane.CENTER_ALIGNMENT);
		pane.add(numTries);
		resultLabel.setAlignmentX(pane.CENTER_ALIGNMENT);
		pane.add(resultLabel);
		buttons.setAlignmentX(pane.CENTER_ALIGNMENT);
		pane.add(buttons);
		question.setAlignmentX(pane.CENTER_ALIGNMENT);
		pane.add(question);
		area.setAlignmentX(pane.CENTER_ALIGNMENT);
		pane.add(area);

		
		frame.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				frame.requestFocus();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		frame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if(String.valueOf(e.getKeyChar()).compareTo("q") == 0){
					yesEvent();
				} else if(String.valueOf(e.getKeyChar()).compareTo("w") == 0){
					noEvent();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		yes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
					
				yesEvent();
		
			}
		});
		
		no.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					noEvent();

			}
		});
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		frame.setResizable(true);
		frame.pack();
		frame.setLocationRelativeTo(null); 
		frame.setVisible(true);
	}
	
	public void initGame(){
		resultLabel.setText("-");
		done = false;
		tries = 0;
		
		numTries.setText("Number of tries " + Integer.toString(tries));
		questions.clear();
		questions_done.clear();
		currentQuestion = null;
		counter = 0;
		
		FileParser parser = new FileParser("C:\\Users\\Kurt\\workspace\\Datakomm\\src\\main\\questions_file.txt");
		questions = parser.ReadFile();
		
		counter = questions.size();
		counterLabel.setText("Progress (" + questions_done.size() + "/" + counter+")");
		
		setNextRandom();
	}
	
	public void yesEvent(){
		if(!done){
			tries++;
			numTries.setText("Number of tries " + Integer.toString(tries));
			if(currentQuestion.answer == true){
				questions_done.add(currentQuestion);
				counterLabel.setText("Progress (" + questions_done.size() + "/" + counter+")");
				resultLabel.setText("Rätt");
				if(questions_done.size() == counter){
					done = true;
					resultLabel.setText("Congratulations, you have mastered all the questions!");
					no.setEnabled(false);
					yes.setText("Restart");
				}
			}else{
				questions.add(currentQuestion);
				resultLabel.setText("Fel");
			}
			setNextRandom();
		}else{
			initGame();
			no.setEnabled(true);
			yes.setText("True [q]");
		}
	}
	
	public void noEvent(){
		if(!done){
			tries++;
			numTries.setText("Number of tries " + Integer.toString(tries));
			if(currentQuestion.answer == false){
				questions_done.add(currentQuestion);
				counterLabel.setText("Progress (" + questions_done.size() + "/" + counter+")");
				resultLabel.setText("Rätt");
				if(questions_done.size() == counter){
					done = true;
					resultLabel.setText("Congratulations, you have mastered all the questions!");
					no.setEnabled(false);
					yes.setText("Restart");
				}
			}else{
				questions.add(currentQuestion);
				resultLabel.setText("Fel");
			}
			setNextRandom();
		}
	}
	
	public void setNextRandom(){
		
		if(questions.size() > 0){
			currentQuestion = questions.remove(random.nextInt(questions.size()));
			question.setText("From " + currentQuestion.hw_number);
			area.setText(currentQuestion.question);
		}		
	}
	

}
