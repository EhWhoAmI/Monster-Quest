/*
 * The MIT License
 *
 * Copyright 2017 Zyun.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package MonsterQuest.game.tutorial;

import MonsterQuest.MonsterQuestMain;
import MonsterQuest.game.MainProcessor;
import MonsterQuest.game.maps.MapProcesser;
import MonsterQuest.game.player.Player;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Zyun
 */
public class CharacterChoose extends JPanel implements ActionListener {
    //Buttons for the character choose
    private JButton swordsMan;
    private JButton warrior;
    private JButton wizard;
    private JButton archer;
    private JButton select;
    
    private int characterShowing = 0;
    //This is the indexes for the character displays.
    private final int NOTHING = 0;
    private final int SWORDS = 1;
    private final int WARRIOR = 2;
    private final int MAGIC = 3;
    private final int ARCHER = 4;
    
    
    //The descriptions for the character
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
    //The master array for the strings
    String[][] thingsToSay = {
            swordsText, //Swords
            warriorText, //Warrior
            WizardText, //Wizard
            ArcherText, //Archer
        };
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            Rectangle2D.Float bg = new Rectangle2D.Float(0, 0, MonsterQuestMain.MonsterQuestWindow.getWidth(), MonsterQuestMain.MonsterQuestWindow.getWidth());
            g2d.setColor(Color.blue);
            g2d.fill(bg);
            Font fontToShow = new Font("Minecraft", Font.PLAIN, 30);
            g2d.setFont(fontToShow);
            FontMetrics metrics = getFontMetrics(fontToShow);
            g2d.drawString("Choose your character", (MonsterQuestMain.MonsterQuestWindow.getWidth() / 2) - (metrics.stringWidth("Choose your character") / 2), 50);
            //Draw box that is supposed to be in the middle.
            Rectangle2D.Float showCharacterArea = new Rectangle2D.Float(500, 75, 850, 550);
            g2d.setColor(Color.green);
            g2d.fill(showCharacterArea);
            //Show image of character.
            g2d.setColor(Color.black);
            g2d.setFont(new Font("Minecraft", Font.PLAIN, 25));
            try {
                switch (characterShowing) {
                    //Nothing
                    case 0:
                        //Draw a string that tells you to press a button
                        g2d.setColor(Color.black);
                        g2d.drawString("Please choose a character", 550, 384);
                        break;
                    case 1:
                        if (MonsterQuestMain.playerStats.gender == Player.BOY) 
                            g.drawImage(ImageIO.read(new File (System.getProperty("user.dir") + "/resources/images/tutorial/SwordsManImageMale.png")), 500, 75, null);
                        else
                            g.drawImage(ImageIO.read(new File (System.getProperty("user.dir") + "/resources/images/tutorial/SwordsManImageWoman.png")), 500, 75, null);
                        //Draw the description for the player.
                        g2d.drawString(thingsToSay[0][0], 775, 100);
                        g2d.drawString(thingsToSay[0][1], 775, 125);
                        g2d.drawString(thingsToSay[0][2], 775, 150);
                        g2d.drawString(thingsToSay[0][3], 775, 175);
                        g2d.drawString(thingsToSay[0][4], 775, 200);
                        break;
                    case 2:
                        if (MonsterQuestMain.playerStats.gender == Player.BOY) 
                            g.drawImage(ImageIO.read(new File (System.getProperty("user.dir") + "/resources/images/tutorial/WarriorImageMale.png")), 500, 75, null);
                        else
                            g.drawImage(ImageIO.read(new File (System.getProperty("user.dir") + "/resources/images/tutorial/WarriorImageWoman.png")), 500, 75, null);
                        //Draw the description for the player.
                        g2d.drawString(thingsToSay[1][0], 775, 100);
                        g2d.drawString(thingsToSay[1][1], 775, 125);
                        g2d.drawString(thingsToSay[1][2], 775, 150);
                        g2d.drawString(thingsToSay[1][3], 775, 175);
                    break;
                    case 3:
                        if (MonsterQuestMain.playerStats.gender == Player.BOY) 
                            g.drawImage(ImageIO.read(new File (System.getProperty("user.dir") + "/resources/images/tutorial/WizardImage.png")), 500, 75, null);
                        else
                            g.drawImage(ImageIO.read(new File (System.getProperty("user.dir") + "/resources/images/tutorial/WitchImage.png")), 500, 75, null);
                        //Draw the description for the player.
                        g2d.drawString(thingsToSay[2][0], 775, 100);
                        g2d.drawString(thingsToSay[2][1], 775, 125);
                        g2d.drawString(thingsToSay[2][2], 775, 150);
                        g2d.drawString(thingsToSay[2][3], 775, 175);
                        break;
                    case 4:
                        if (MonsterQuestMain.playerStats.gender == Player.BOY) 
                            g.drawImage(ImageIO.read(new File (System.getProperty("user.dir") + "/resources/images/tutorial/ArcherMaleImage.png")), 500, 75, null);
                        else
                            g.drawImage(ImageIO.read(new File (System.getProperty("user.dir") + "/resources/images/tutorial/ArcherFemaleImage.png")), 480, 75, null);
                        //Draw the description for the player.
                        g2d.drawString(thingsToSay[3][0], 775, 100);
                        g2d.drawString(thingsToSay[3][1], 775, 125);
                        g2d.drawString(thingsToSay[3][2], 775, 150);
                        g2d.drawString(thingsToSay[3][3], 775, 175);
                        break;
                }
            }catch (IOException ioe) {
                
            }
        }
    public CharacterChoose() {
        super();
        //layout = new CardLayout();
        //setLayout(layout);
        setLayout(null);
        
        swordsMan = new JButton();
        wizard = new JButton();
        warrior = new JButton("Warrior");
        archer = new JButton("Archer");
        select = new JButton("Select");

        //Init the buttons...
        //Swordsman
        if (MonsterQuestMain.playerStats.gender == Player.BOY)
            swordsMan.setText("Swordsman");
        else 
            swordsMan.setText("Swordswoman");
        MonsterQuestMain.systemLog.log("Just set swordsman button title to " + swordsMan.getText());
        swordsMan.setBackground(Color.green);
        swordsMan.setBounds(100, 100, 250, 75);
        swordsMan.setIcon(new ImageIcon(System.getProperty("user.dir") + "/resources/images/tutorial/SwordImage.png"));
        swordsMan.setFont(new Font("Minecraft", Font.PLAIN, 17));
        swordsMan.addActionListener(this);

        //Warrior
        warrior.setBackground(Color.green);
        warrior.setBounds(100, 250, 250, 75);
        warrior.setIcon(new ImageIcon(System.getProperty("user.dir") + "/resources/images/tutorial/AxeImage.png"));
        warrior.setFont(new Font("Minecraft", Font.PLAIN, 17));
        warrior.addActionListener(this);
        
        //Wizard
        if (MonsterQuestMain.playerStats.gender == Player.BOY)
            wizard.setText("Wizard");
        else 
            wizard.setText("Witch");
        MonsterQuestMain.systemLog.log("Just set wizard button title to " + wizard.getText());
        wizard.setBackground(Color.green);
        wizard.setBounds(100, 400, 250, 75);
        wizard.setIcon(new ImageIcon(System.getProperty("user.dir") + "/resources/images/tutorial/WandImage.png"));
        wizard.setFont(new Font("Minecraft", Font.PLAIN, 17));
        wizard.addActionListener(this);
        
        //Archer
        archer.setBackground(Color.green);
        archer.setBounds(100, 550, 250, 75);
        archer.setIcon(new ImageIcon(System.getProperty("user.dir") + "/resources/images/tutorial/BowImage.png"));
        archer.setFont(new Font("Minecraft", Font.PLAIN, 17));
        archer.addActionListener(this);
        
        select.setBackground(Color.green);
        select.setBounds(1150, 550, 150, 45);
        select.setFont(new Font("Minecraft", Font.PLAIN, 17));
        select.addActionListener(this);
        add(archer);
        add(swordsMan);
        add(warrior);
        add(wizard);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(swordsMan)) {
            characterShowing = SWORDS;
            this.repaint();
            add(select);
        }
        else if (source.equals(warrior)) {
            characterShowing = WARRIOR;
            this.repaint();
            add (select);
        }
        else if (source.equals(wizard)) {
            characterShowing = MAGIC;
            this.repaint();
            add (select);
        } 
        else if (source.equals(archer)) {
            characterShowing = ARCHER;
            this.repaint();
            add (select);
        }
        else if (source.equals(select)) {
            //Start game...
            MonsterQuestMain.systemLog.log("Start game!!!");
            MonsterQuestMain.systemLog.log("Loading map");
            
            //Show loading screen while the map loads.
            //Doesn't work, it only shows after the things load.
            MonsterQuestMain.cardLayout.show(MonsterQuestMain.MonsterQuestPanel, "loadScreen");
            MonsterQuestMain.MonsterQuestPanel.repaint();

            Runnable thread = () -> {
                MainProcessor.mapProcesser = new MapProcesser();
                MonsterQuestMain.MonsterQuestPanel.add(MainProcessor.mapProcesser , "Map");
                MonsterQuestMain.systemLog.log("Done loading");
            };
            new Thread(thread).run();
            try {
                    Thread.sleep(10);
            } catch (InterruptedException ie) {
            }
            //After done loading, show the map.
            MonsterQuestMain.cardLayout.show(MonsterQuestMain.MonsterQuestPanel, "Map");
            MonsterQuestMain.MonsterQuestPanel.repaint();
        }
    }
}