package view;


import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import controller.Controller;

public class GUI implements Observer{
	
	private Display d;
	private Controller controller;
	private JPanel panel;
	private SpringLayout springLayout;	
	private KeyboardState st;
	private Thread keyThread;
	
	
	public GUI(Controller c){

		controller = c;
		controller.addObserver(this);
		
		final JFrame frame = new JFrame("Emojinator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		d = new Display(controller.getLevel());
		
		panel.add(d);
		

		springLayout = new SpringLayout();
		panel.setLayout(springLayout);
		springLayout.putConstraint(SpringLayout.NORTH, d, 0,
				SpringLayout.NORTH, panel);
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 0,
				SpringLayout.SOUTH, d);
		springLayout.putConstraint(SpringLayout.EAST, panel, 0,
				SpringLayout.EAST, d);
		
		frame.add(panel);
		st = new KeyboardState(controller);
		keyThread = new Thread(st);
		keyThread.start();
		frame.addKeyListener(st);
		frame.setFocusable(true);
		frame.setResizable(true);
		frame.pack();
		frame.setVisible(true);
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	controller.exitGame();
		    	JOptionPane.showMessageDialog(frame, ""
						+ "CREDITS"
						+ "\n \n"
						+ "Programmet �r kodat av Tim L�fstr�m\n"
						+ "Bakgrundsbild �r gjord av Anders Hedstr�m\n"
						+ "Emojione - under a free culture Creative Commons License (CC-BY 4.0)\n"
						+ "Musik och Ljud - fr�n http://www.freesound.org/ under Creative Commons License (CC-BY 4.0)");
		    }
		});
		
		JOptionPane.showMessageDialog(frame, ""
				+ "V�lkommen till livet som student!"
				+ "\n \n"
				+ "Som vilsen student skuttar du vilset runt bland tenta-papper och kaffekoppar."
				+ "\n \n"
				+ "Plocka upp kaffekopparna f�r att f� mer energi och samla po�ng."
				+ "\n \n"
				+ "Om du tr�ffas av en tentamen f�r du minuspo�ng och ditt hum�r kommer att s�nkas d� du automatiskt misslyckas med tentan."
				+ "\n \n"
				+ "D� du blir speedad p� kaffet kommer takten i spelet att �ka, medan takten minskar av tentorna p� grund av att hum�ret sjunker."
				+ "\n \n"
				+ "�r du redo!? Anv�nd piltangenterna f�r att styra -- Klicka OK f�r att starta spelet!");
		
		controller.startGame();
		new Thread(controller).start();
		
	}
	
	
	@Override
	public void update(Observable caller, Object message) {

		if (caller == controller) {
			d.repaint();
		}
		

		
	}
}
	
	
