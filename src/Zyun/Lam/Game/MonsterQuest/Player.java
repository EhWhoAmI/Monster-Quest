package Zyun.Lam.Game.MonsterQuest;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

class Player {
    public static int level;
    public static int experience;
    public static int money;
    public static int attacklv;
    public static int defenselv;
    public static int speedlv;
    public static int attack;
    public static int speed;
    public static int defense;
    public static Point playerPos = new Point(816, 408);
    //Generates default stats
    public Player() {
    }
    
    //Invoked when player presses W

    //Invoked when player presses S
        static void SKey (java.awt.Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        /*Move player Up
         * Move in 0.5 seconds!
         * Three frames.
         * So, 0.5 / 3 = 0.166
         * Oh yeah, this guy is right-footed */
        //Load images
        //Still
        BufferedImage animatedimage1 = Graphics.loadImage("/resources/images/sprites/PersonWalking1");
        //Right leg down
        BufferedImage animatedimage2 = Graphics.loadImage("/resources/images/sprites/PersonWalking2");
        //Left leg down
        BufferedImage animatedimage3 = Graphics.loadImage("/resources/images/sprites/PersonWalking3");
        //Make player move up
        //Subtract 34 / 3 pixels, or 11 pixels, the last one will be 12 seconds 
        //Subtract 11 pixels Y from current posistion
        try {
            playerPos.y -= 11;
            g2d.drawImage(animatedimage2, playerPos.x, playerPos.y, null);
            //Wait 0.166 seconds
            Thread.sleep (166);
            WindowMain.frameRepaint();
            playerPos.y -= 11;
            g2d.drawImage(animatedimage3, playerPos.x, playerPos.y, null);
            //Wait 0.166 seconds
            Thread.sleep (166);
            WindowMain.frameRepaint();
            playerPos.y -= 12;
            g2d.drawImage(animatedimage1, playerPos.x, playerPos.y, null);
            //Wait 0.166 seconds          
            Thread.sleep (166);
            WindowMain.frameRepaint();
            //Done
        } catch (InterruptedException ie) {
            //Ingore - Who cares.
        }
    }
    //Invoked when player presses A
    //Invoked when player presses D
}