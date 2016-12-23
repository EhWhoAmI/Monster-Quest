package Zyun.Lam.Game.MonsterQuest;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

class gamePart1__Tutorial {
    static String textsStrings [] = {"Press Space to continue...", "Hello, I am a villager from Newbies Town!", "Welcome to Monster Quest.", "We live, in a beautiful place.", "But, monsters have come out from nowhere,", "Killing us all.", "Many brave souls ventured out to rid the monsters,", "But none came back.", "Help us all, my dear friend", "And save us all!"};
    static int wordToShow = 0;
    static void drawImage (java.awt.Graphics g) {
        BufferedImage villagerImage = null;
        try {
            villagerImage = ImageIO.read(new File ("D:\\Zyun's Coding\\My Coding\\Monster Quest\\src\\Zyun\\Lam\\Game\\MonsterQuest\\resources\\villagerimg.png"));
            g.drawImage(villagerImage, 0, 0, null);
            textBox(g);
            drawWords(g, wordToShow);
        } catch (Exception e) {
        }
    }
    static void textBox (java.awt.Graphics g) {
        Graphics2D g2d = (java.awt.Graphics2D) g;
        GeneralPath txtbox = new GeneralPath();
        txtbox.moveTo(650, 340);
        txtbox.lineTo(746, 280);
        txtbox.lineTo(1172, 280);
        txtbox.lineTo(1172, 42);
        txtbox.lineTo(321, 42);
        txtbox.lineTo(319, 280);
        txtbox.lineTo(650, 288);
        txtbox.closePath();
        BasicStroke s = new BasicStroke(5.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        g2d.setStroke(s);
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.BLACK);
        g2d.draw(txtbox);
        g2d.setColor(Color.WHITE);
        g2d.fill(txtbox);
    }
    
    static void drawWords (java.awt.Graphics g, int textToShow) {
        Graphics2D g2d = (Graphics2D) g;
        Font fontToShow = new Font ("Curlz MT", Font.PLAIN, 30);
        g2d.setFont(fontToShow);
        g2d.setColor(Color.BLACK);
        g2d.drawString(textsStrings[textToShow], 350, 100);
        System.out.println("Showing font...");
    }
}