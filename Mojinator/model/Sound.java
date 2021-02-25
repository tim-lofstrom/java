package model;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	public static synchronized void playJump() {
		new Thread(new Runnable() {
			public void run() {
				try {
					
					InputStream audioSrc = this.getClass().getClassLoader().getResourceAsStream("sounds/jump.wav");
					InputStream bufferedIn = new BufferedInputStream(audioSrc);
					AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
					Clip clip = AudioSystem.getClip();
					clip.open(audioStream);
					clip.start();					

				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}).start();
	}

	public static synchronized void playSound() {
		new Thread(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					
					InputStream audioSrc = this.getClass().getClassLoader().getResourceAsStream("sounds/gameloop.wav");
					InputStream bufferedIn = new BufferedInputStream(audioSrc);
					AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
					Clip clip = AudioSystem.getClip();
					clip.open(audioStream);
					clip.loop(clip.LOOP_CONTINUOUSLY);
					

				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}).start();
	}

}
