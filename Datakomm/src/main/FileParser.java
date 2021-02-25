package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileParser {
	
	BufferedReader reader;
	File file;
	
	public FileParser(String filename){
		
		file = new File(filename);
	}
	
	public ArrayList<Question> ReadFile(){
		
		ArrayList<Question> questions = new ArrayList<Question>();
		
		try {
			
			reader = new BufferedReader(new FileReader(file));
			
			String line;
			
			while((line =  reader.readLine()) != null){
				String[] arr;
				arr = line.split(";");
				questions.add(new Question(arr[1], arr[0], Boolean.parseBoolean(arr[2])));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return questions;
	}

}
