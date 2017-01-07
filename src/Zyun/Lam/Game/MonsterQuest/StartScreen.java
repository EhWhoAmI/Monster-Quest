package Zyun.Lam.Game.MonsterQuest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

class StartScreen extends JPanel{

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        stcs(g);
    }
    
    static void stcs(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        BufferedImage splashScreen = null;
        try {
            System.out.println("Doing the audio...");
            //Retrive Image from hash map
            //TODO
            //show image
            splashScreen = ImageIO.read(new File (WindowMain.user_dir + "/resources/titlepageSplash.png"));
            g2d.setColor(Color.white);
            Rectangle2D.Float bg = new Rectangle2D.Float (0,0,WindowMain.window.getWidth(),WindowMain.window.getHeight());
            g2d.fill(bg);
            g.drawImage(splashScreen, 250, 0, null);
        } catch (IOException e) {
            System.err.println("Unable to open image: " + e.getMessage());
            SystemLog.log("Unable to open Image: " + e.getMessage(), SystemLog.IOEXCEPTION);
        }
        playSound();
        
    }
    
    
    static private void playSound () {
            try {
                String gongFile = (WindowMain.user_dir + "/resources/start.wav");
                InputStream in = new FileInputStream(gongFile);

                // create an audiostream from the inputstream
                AudioStream audioStream = new AudioStream(in);

                // play the audio clip with the audioplayer class
                AudioPlayer.player.start(audioStream);
            } catch (FileNotFoundException fnfe) {
                System.out.println("Sound not found!!" + fnfe.getMessage());
                SystemLog.log("File not found: " + fnfe.getMessage(), SystemLog.FILE_NOT_FOUND);
            } catch (IOException ioe) {
                System.out.println("IO Exception: " + ioe.getCause() + " Message: " + ioe.getMessage());
                SystemLog.log("IOException: " + ioe.getMessage(), SystemLog.IOEXCEPTION);
            }
    }
}