package main;

import view.GUI;
import controller.Controller;

/**
 * AsteroidGame main starter
 * @author kurt
 */
public class Game {

	public static void main(String[] args) {

		Controller controller = new Controller();
		new GUI(controller);
	}
}
	