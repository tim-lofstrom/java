package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class Stats {

	public ArrayList<Node> nodes = new ArrayList<Node>();

	public Stats() {

		readFile();
	}

	private void readFile() {

		InputStream in = this.getClass().getClassLoader().getResourceAsStream("others/stats.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line = null;

		try {
			while ((line = br.readLine()) != null) {
				String[] temp = line.split(";");
				nodes.add(new Node(temp[0], Integer.parseInt(temp[1])));
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveFile() {
		
		try {

			OutputStream output = new FileOutputStream("others/stats.txt");
			PrintWriter writer = new PrintWriter(output);
			
			for (Node n : nodes) {
				writer.println(n.name + ";" + n.score);
			}

			writer.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public class Node {
		String name;
		int score;

		public Node(String n, int s) {
			name = n;
			score = s;
		}

		public String getName() {
			return name;
		}

		public int getScore() {
			return score;
		}
	}

	public void addStat(String name, int score, int i) {
		if ((nodes.size() >= 10) && (i < 10)) {
			nodes.add(i, new Node(name, score));
		} else {
			nodes.add(i, new Node(name, score));
		}
		while (nodes.size() > 10) {
			nodes.remove(10);
		}

	}

}
