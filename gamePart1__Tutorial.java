package Zyun.Lam.Game.MonsterQuest;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

class gamePart1__Tutorial {
    static boolean nameWrite = false; 
    static boolean genderChoose = false;
    static boolean characterChoose = false;
    static CharacterType characterShow = CharacterType.nothing;
    //The strings of what the villager is about to say
    static String textsStrings [] = {"Press Space to continue...", 
        "Hello, I am a villager from Newbies Town!", 
        "Welcome to Monster Quest.", 
        "We live, in a beautiful place.", 
        "But, monsters have come out from nowhere,", 
        "Killing us all.", 
        "Many brave souls ventured out to rid the monsters,", 
        "But none came back.", 
        "Help us all, my dear friend", 
        "And save us all!", 
        "Let's get into some important things.", 
        "What is your name?",
        "Are you a boy or a girl?",
        "Now, it is an important thing you are choosing.",
        "Your class is very important, ", 
        "because it chooses what you do in the future.",
        "You CANNOT change it. So, choose carefully."
        };
    //The word to show -- Will be used in the textsStrings array
    static int wordToShow = 0;
    
    static void drawImage (java.awt.Graphics g) {
        //The image variable
        BufferedImage villagerImage = null;
        try {
            //Retrive Image from hash map
            //TODO
            //show image
            villagerImage = ImageIO.read(new File (WindowMain.user_dir + "/resources/images/tutorial/villagerimg.png"));
            g.drawImage(villagerImage, 0, 0, null);
            textBox(g);
            drawWords(g, wordToShow);
        } catch (IOException e) {
            System.err.println("Unable to open image: " + e.getMessage());
            SystemLog.log("Unable to open Image: " + e.getMessage(), SystemLog.IOEXCEPTION);
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
        Font fontToShow = new Font ("Arial", Font.PLAIN, 30);
        g2d.setFont(fontToShow);
        g2d.setColor(Color.BLACK);
        g2d.drawString(textsStrings[textToShow], 350, 100);
        System.out.println("Showing text for villager speech...");
    }
    
    static void getName (java.awt.Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //The image variable
        BufferedImage villagerImage = null;
        try {
            //Retrive Image from hash map
            //TODO
            //show image
            villagerImage = ImageIO.read(new File (WindowMain.user_dir + "/resources/images/tutorial/villagerimg.png"));
            g.drawImage(villagerImage, 0, 0, null);
            textBox(g);
            Font fontToShow = new Font ("Arial", Font.PLAIN, 30);
            g2d.setFont(fontToShow);
            g2d.setColor(Color.BLACK);
            g2d.drawString("What is your name?", 350, 100);
            g2d.drawString(ControlUnit.playerNameBuilder.toString(), 350, 150);
            g2d.drawString("Characters Left: " + (ControlUnit.NAME_MAX_SIZE - ControlUnit.playerNameBuilder.length()), 350, 250);
        } catch (IOException e) {
            System.err.println("Unable to open image: " + e.getMessage());
            SystemLog.log("Unable to open Image: " + e.getMessage(), SystemLog.IOEXCEPTION);
        }
    }
    
    static void genderChoose (java.awt.Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //The image variable
        BufferedImage villagerImage = null;
        try {
            //Retrive Image from hash map
            //TODO
            //show image
            villagerImage = ImageIO.read(new File (WindowMain.user_dir + "/resources/images/tutorial/villagerimg.png"));
            g.drawImage(villagerImage, 0, 0, null);
            Font fontToShow = new Font ("Arial", Font.PLAIN, 30);
            g2d.setFont(fontToShow);
            textBox(g);
            g2d.setColor(Color.black);
            g2d.drawString("Are you a boy or a girl?", 350, 100);
            //Two boxes for the boy and girl chosing
            {
            	//Boy box
                Rectangle2D.Float boy = new Rectangle2D.Float(380, 200, 150, 75);
                Rectangle2D.Float boyBorder = new Rectangle2D.Float(378.5F, 198.5F, 155, 80);
                g2d.fill(boyBorder);
                g2d.setColor(new Color(79, 143, 247));
                g2d.fill(boy);
                g2d.setColor (Color.black);
                g2d.drawString("Boy", 390, 250);
                //Symbol...
                BufferedImage boySymbol = ImageIO.read(new File (WindowMain.user_dir + "/resources/images/tutorial/boysymbol.png"));
                g.drawImage(boySymbol, 460, 210, null);
            }
            {
            	//Girl box...
            	Rectangle2D.Float girl = new Rectangle2D.Float(600, 200, 150, 75);
                Rectangle2D.Float girlBorder = new Rectangle2D.Float(598.5F, 198.5F, 155, 80);
                g2d.setColor(Color.black);
                g2d.fill(girlBorder);
                g2d.setColor(Color.pink);
                g2d.fill(girl);
                g2d.setColor(Color.black);
                g2d.drawString("Girl", 610, 250);
                //Image will come soon
                BufferedImage girlSymbol = ImageIO.read(new File (WindowMain.user_dir + "/resources/images/tutorial/Girlsymbol.png"));
                g.drawImage(girlSymbol, 680, 210, null);
            }
        } catch (IOException e) {
            System.err.println("Unable to open image: " + e.getMessage());
            SystemLog.log("Unable to open Image: " + e.getMessage(), SystemLog.IOEXCEPTION);
        }
    }
    
    static void playerChoose (java.awt.Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D.Float bg = new Rectangle2D.Float(0, 0, WindowMain.window.getWidth(), WindowMain.window.getWidth());
        g2d.setColor(Color.blue);
        g2d.fill(bg);
        g2d.setColor(Color.green);
        Font fontToShow = new Font ("Arial", Font.PLAIN, 30);
        g2d.setFont(fontToShow);
        {
            //Button for Swordsman/woman class
            g2d.setColor(Color.green);
            Rectangle2D.Float button = new Rectangle2D.Float(100, 100, 250, 75);
            g2d.fill(button);
            g2d.setColor(Color.black);
            String character = (!Player.playerGender)?"Swordswoman":"Swordsman";
            g2d.drawString(character, 110, 140);
            //Draw the image.
            BufferedImage swordImage;
            swordImage = Graphics.loadImage("/resources/images/tutorial/SwordImage.png");
            g2d.drawImage(swordImage, 275, 100, null);
        }
        {
            //Button for Warrior class
            g2d.setColor(Color.green);
            Rectangle2D.Float button = new Rectangle2D.Float(100, 250, 250, 75);
            g2d.fill(button);
            g2d.setColor(Color.black);
            String character = "Warrior";
            g2d.drawString(character, 110, 290);
            //Draw the axe image
            BufferedImage AxeImage;
            AxeImage = Graphics.loadImage("/resources/images/tutorial/AxeImage.png");
            g2d.drawImage(AxeImage, 275, 250, null);
            
        }
        {
            //Button for Wizard class
            g2d.setColor(Color.green);
            Rectangle2D.Float button = new Rectangle2D.Float(100, 400, 250, 75);
            g2d.fill(button);
            g2d.setColor(Color.black);
            String character = (!Player.playerGender)?"Witch":"Wizard";
            g2d.drawString(character, 110, 440);
            //Magician wand image
            BufferedImage WandImage;
            WandImage = Graphics.loadImage("/resources/images/tutorial/WandImage.png");
            g2d.drawImage(WandImage, 275, 402, null);
            
            
            
        }
        {
            //Button for archer class
            g2d.setColor(Color.green);
            Rectangle2D.Float button = new Rectangle2D.Float(100, 550, 250, 75);
            g2d.fill(button);
            g2d.setColor(Color.black);
            String character = "Archer";
            g2d.drawString(character, 110, 590);
            BufferedImage BowImage;
            BowImage = Graphics.loadImage("/resources/images/tutorial/BowImage.png");
            g2d.drawImage(BowImage, 275, 550, null);
        }
        //Area for the character choose
        Rectangle2D.Float showCharacterArea = new Rectangle2D.Float (500, 75, 650, 550);
        g2d.setColor(Color.green);
        g2d.fill(showCharacterArea);
        showCharacter(characterShow ,g);
        //Button for selection.
        if (characterShow != CharacterType.nothing) {
            Rectangle2D.Float button = new Rectangle2D.Float(950, 550, 150, 45);
            g2d.setColor(Color.orange);
            g2d.fill(button);
            g2d.setColor(Color.black);
            g2d.setFont(new Font("Arial", Font.PLAIN, 25));
            g2d.drawString("Select", 953, 575);
        }
    }
    
    static void showCharacter ( CharacterType character, java.awt.Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        String fileName = new String("/resources/images/tutorial/");
        Point pos;
        String explanation;
        switch (character) {
            case swords:
                fileName += (Player.playerGender)?"SwordsManImageMale":"SwordsManImageWoman";
                //Deal with the point...
                //                                  Boy,                             Girl...
                pos = (Player.playerGender)? new Point(500, 75): new Point(500, 75);
                break;
            case warrior:
                fileName += (Player.playerGender)?"WarriorImageMale":"WarriorImageWoman";
                pos = (Player.playerGender)? new Point(500, 75): new Point(500, 75);
                break;
            case magician:
                fileName += (Player.playerGender)?"WizardImage":"WitchImage";
                pos = (Player.playerGender)? new Point(500, 75): new Point(500, 75);
                break;
            case archer:
                fileName += (Player.playerGender)?"ArcherMaleImage":"ArcherFemaleImage";
                pos = (Player.playerGender)? new Point(500, 75): new Point(480, 75);
                break;
            default:
                pos = new Point(0, 0);
                //Show nothing
        }
        fileName += ".png";
        //Draw image to the place...
        BufferedImage img = Graphics.loadImage(fileName);
        g2d.drawImage(img, pos.x, pos.y, null);
        //And the text will be put here.
        String [] swordsText = {"These people are well known throughout",
                                "the land. They are powerful, and have ",
                                "decent damage, decent armour and a",
                                "short range, that can prove devasting",
                                "to anybody.. "};
        String [] warriorText = {"They are strong, and powerful, their",
                                 "axes cutting through anything. They ",
                                 "have massive damage and good range, ",
                                 "but they lack any armour."};
        String [] WizardText = {"Well known for their magic, they are", 
                                "powerful, from afar. They have good", 
                                "damage, good range, and lower armour. ",
                                "They also have improved effect dealing."};
        String [] ArcherText = {"They pick off enemies from afar.",
                                "They are also fast. They have low", 
                                "damage, massive range, and boast",
                                "good concealment."};
        String [] nothing = {""};
        String[][] thingsToSay = {
            swordsText, //Swords
            warriorText, //Warrior
            WizardText, //Wizard
            ArcherText, //Archer
            nothing}; //Empty
        //Draw string...
        //The area you have is limited, but too bad Graphics.drawString does not allow \n stuff. 
        //So, a loop should do.
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        g2d.setColor(Color.black);
        //Note, go in incerements of 25 pixels every time.
        switch (character) {
            case swords:
                g2d.drawString(thingsToSay[0][0], 775, 100);
                g2d.drawString(thingsToSay[0][1], 775, 125);
                g2d.drawString(thingsToSay[0][2], 775, 150);
                g2d.drawString(thingsToSay[0][3], 775, 175);
                g2d.drawString(thingsToSay[0][4], 775, 200);
                break;
            case warrior:
                g2d.drawString(thingsToSay[1][0], 775, 100);
                g2d.drawString(thingsToSay[1][1], 775, 125);
                g2d.drawString(thingsToSay[1][2], 775, 150);
                g2d.drawString(thingsToSay[1][3], 775, 175);
                break;
            case magician:
                g2d.drawString(thingsToSay[2][0], 775, 100);
                g2d.drawString(thingsToSay[2][1], 775, 125);
                g2d.drawString(thingsToSay[2][2], 775, 150);
                g2d.drawString(thingsToSay[2][3], 775, 175);
                break;
            case archer:
                g2d.drawString(thingsToSay[3][0], 775, 100);
                g2d.drawString(thingsToSay[3][1], 775, 125);
                g2d.drawString(thingsToSay[3][2], 775, 150);
                g2d.drawString(thingsToSay[3][3], 775, 175);
                break;
            case nothing:
                g2d.drawString(thingsToSay[4][0], 0, 0);
                break;
        
        }
    }
}

enum CharacterType {
    swords, warrior, magician, archer, nothing; 
}