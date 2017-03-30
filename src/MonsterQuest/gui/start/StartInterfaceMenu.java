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
package MonsterQuest.gui.start;

import MonsterQuest.MonsterQuestMain;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Zyun
 */
public class StartInterfaceMenu extends JPanel implements ActionListener{
    JButton options, startGame, others;
    @Override
    protected void paintComponent(Graphics g) {
        //Draw image for the splash screen in the middle.
        Graphics2D g2d = (Graphics2D) g;
        BufferedImage splashScreen = null;
        try {
            //show image
            splashScreen = ImageIO.read(new File (System.getProperty("user.dir") + "/resources/images/start/SplashScreen.png"));
            
            int splashScreenPosX = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - splashScreen.getWidth() / 2 ;
            g2d.setColor(Color.black);
            g2d.drawImage(splashScreen, splashScreenPosX, 0, null);
            MonsterQuestMain.systemLog.log("Just showed start splash image.");
        } catch (IOException e) {
            MonsterQuestMain.systemLog.log("Oh no! Unable to open file!" + e.getMessage());
        }
    }
    
    public StartInterfaceMenu() {
        super();
        this.setLayout(null);
        Dimension buttonSize = new Dimension(200, 100);
        Font pixelFontBigger = new Font("Minecraft", Font.TRUETYPE_FONT, 25);
        startGame = new JButton("Start Game");
        startGame.setBackground(Color.red);
        startGame.setBounds(1000, 200, 200, 100);
        startGame.setFont(pixelFontBigger);
        startGame.addActionListener(this);
        add(startGame);
        
        options = new JButton("Options");
        options.setBackground(Color.green);
        options.setBounds(1000, 350, 200, 100);
        options.setFont(pixelFontBigger);
        options.addActionListener(this);
        add(options);
        
        others = new JButton ("Others");
        others.setBackground(Color.blue);
        others.setBounds(1000, 500, 200, 100);
        others.setFont(pixelFontBigger);
        others.addActionListener(this);
        add(others);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == startGame) {
            MonsterQuestMain.systemLog.log("Start game");
        }
        else if (source == options) {
            MonsterQuestMain.systemLog.log("Options");
        } 
        else if (source == others) {
            MonsterQuestMain.systemLog.log("Show others");
            MonsterQuestMain.cardLayout.show(MonsterQuestMain.MonsterQuestPanel, "othersOption");
            MonsterQuestMain.MonsterQuestWindow.repaint();
        }
    }
}
