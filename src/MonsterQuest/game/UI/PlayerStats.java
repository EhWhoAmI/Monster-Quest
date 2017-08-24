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
package MonsterQuest.game.UI;

import MonsterQuest.MonsterQuestMain;
import static MonsterQuest.MonsterQuestMain.systemLog;
import MonsterQuest.util.Logging;
import MonsterQuest.game.player.PlayerType;
import MonsterQuest.game.player.Player;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Shows the player stats.
 * 
 * @author Zyun 
 */
public class PlayerStats extends JPanel {
    public PlayerStats () {
        super();
        
        this.setBackground(new Color(255, 153, 0));
        this.setLayout(null);
        
        JLabel title = new JLabel("Your details");
        title.setFont(new Font("Minecraft", Font.PLAIN, 36));
        title.setForeground(Color.BLACK);
        this.add(title);
        
        JButton exitButton = new JButton ("X");
        exitButton.setBackground(Color.RED);
        exitButton.setFont(new Font("Minecraft", Font.PLAIN, 36));
        exitButton.setBounds(1275, 20, 60, 40);
        exitButton.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Load the game place...
                MonsterQuestMain.cardLayout.show(MonsterQuestMain.MonsterQuestPanel, "game");
                MonsterQuestMain.gameRunning = true;
            }
        });
        this.add(exitButton);
    }
    
    @Override
    protected void paintComponent (Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;

        //Chamge this to alter the alignment of the character Y, or vertical alignment
        int alignmentY = 150;
        //Change this to alter the alignment of the character X, or horizontal alignment
        int alignmentX = 25;
        g2d.setFont(new Font("Minecraft", Font.PLAIN, 36));
        g2d.drawString(MonsterQuestMain.playerStats.name, alignmentX, 125);
        //Get the user class.
        try {
            switch (MonsterQuestMain.playerStats.playerType) {
                case warrior:
                    if (MonsterQuestMain.playerStats.gender == Player.BOY) 
                        g.drawImage(ImageIO.read(new File (System.getProperty("user.dir") + "/resources/images/tutorial/WarriorImageMale.png")), alignmentX, alignmentY, null);
                    else
                        g.drawImage(ImageIO.read(new File (System.getProperty("user.dir") + "/resources/images/tutorial/WarriorImageWoman.png")), alignmentX, alignmentY, null);
                    break;
                case swordsman:
                    if (MonsterQuestMain.playerStats.gender == Player.BOY) 
                        g.drawImage(ImageIO.read(new File (System.getProperty("user.dir") + "/resources/images/tutorial/SwordsManImageMale.png")), alignmentX, alignmentY, null);
                    else
                        g.drawImage(ImageIO.read(new File (System.getProperty("user.dir") + "/resources/images/tutorial/SwordsManImageWoman.png")), alignmentX, alignmentY, null);
                    break;
                case wizard:
                    if (MonsterQuestMain.playerStats.gender == Player.BOY) 
                        g.drawImage(ImageIO.read(new File (System.getProperty("user.dir") + "/resources/images/tutorial/WizardImage.png")), alignmentX, alignmentY, null);
                    else
                        g.drawImage(ImageIO.read(new File (System.getProperty("user.dir") + "/resources/images/tutorial/WitchImage.png")), alignmentX, alignmentY, null);
                    break;
                case archer:
                    if (MonsterQuestMain.playerStats.gender == Player.BOY) 
                         g.drawImage(ImageIO.read(new File (System.getProperty("user.dir") + "/resources/images/tutorial/ArcherMaleImage.png")), alignmentX, alignmentY, null);
                    else
                        g.drawImage(ImageIO.read(new File (System.getProperty("user.dir") + "/resources/images/tutorial/ArcherFemaleImage.png")), alignmentX, alignmentY, null);
                    break;
            }
        } catch(IOException ioe) {
            //Near impossible, because we hava already accessed these files before.
            systemLog.log ("Unable to open player sprites.", Logging.ERROR, ioe);
        }
    }
}
