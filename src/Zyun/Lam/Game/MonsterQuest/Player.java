package Zyun.Lam.Game.MonsterQuest;

import com.sun.corba.se.impl.orbutil.graph.Graph;
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
    public volatile static int speed;
    public static int defense;
    public static Point playerPos = new Point(820, 408);
    public static byte playerDirection = Player.NODIRECTION;
    public volatile static byte FrameNum = 1;
    public static String playerName;
    public static boolean playerGender; //true is boy, false is girl
    public static CharacterType playerCharacter;
    //Define for player direction
    public static final byte FOWARD = 1;
    public static final byte BACKWARD = 2;
    public static final byte LEFT = 3;
    public static final byte RIGHT = 4;
    public static final byte NODIRECTION = 0;
    //For player 
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
    //Invoked when nothing happens
    static void nothingHappens (java.awt.Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //Show player in frame 1
        BufferedImage animatedimage1 = Graphics.loadImage("/resources/images/sprites/PersonWalking1");
        g2d.drawImage(animatedimage1, playerPos.x, playerPos.y, null);
    }
    
    static void showCharacter (java.awt.Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
            switch (playerDirection) {
                case NODIRECTION:
                    //Display player as not moving...
                    BufferedImage playerLookingFoward = Graphics.loadImage("/resources/images/sprites/PersonWalking1.png");
                    //Add image...
                    g2d.drawImage(playerLookingFoward, playerPos.x, playerPos.y, null);
                    break;
                case FOWARD:
                    //Display movement...
                    //three times...
                    System.out.println("Showing thing");
                    BufferedImage playerToShow = Graphics.loadImage("/resources/images/sprites/PersonWalking"+ Player.FrameNum + ".png");
                    g2d.drawImage(playerToShow,playerPos.x , playerPos.y, null);
                    if (Player.FrameNum > 3) {
                        Player.FrameNum = 1;
                        playerDirection = NODIRECTION;
                        break;
                    }
                    if (Player.FrameNum <= 3 ) {
                        Player.FrameNum ++;
                        try {Thread.sleep(166);}catch (InterruptedException ie){}
                        WindowMain.frameRepaint();
                    }
                    break;
                case BACKWARD:
                    break;
                case LEFT:
                    break;
                case RIGHT:
                    break;
            }
        
    }
}