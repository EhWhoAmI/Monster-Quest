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
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
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
public class Others extends JPanel implements ActionListener{
    private JButton about, credits, exit;
    protected void paintComponent(Graphics g) {
        //Draw image for the splash screen in the middle.
        Graphics2D g2d = (Graphics2D) g;
        BufferedImage splashScreen = null;
        try {
            //show image
            splashScreen = ImageIO.read(new File (System.getProperty("user.dir") + "/resources/images/start/SplashScreen.png"));
            Font pixelFontBigger = new Font("Minecraft", Font.TRUETYPE_FONT, 35);
            int splashScreenPosX = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - splashScreen.getWidth() / 2 ;
            g2d.drawImage(splashScreen, splashScreenPosX, 0, null);
            MonsterQuestMain.systemLog.log("Just showed start splash image.");
            RoundRectangle2D.Float WindowPopup = new RoundRectangle2D.Float ((MonsterQuestMain.MonsterQuestWindow.getWidth() / 2)- 450, 10, 900, 700, 10, 10);
            RoundRectangle2D.Float WindowPopupBack = new RoundRectangle2D.Float((MonsterQuestMain.MonsterQuestWindow.getWidth() / 2) - 452.5F, 7.5F, 905, 705, 10, 10);
            g2d.setFont(pixelFontBigger);
            g2d.setColor(Color.black);
            g2d.fill(WindowPopupBack);
            g2d.setColor(new Color (186, 191, 198));
            g2d.fill(WindowPopup);
            g2d.setColor(Color.black);
            FontMetrics metrics = getFontMetrics(pixelFontBigger);
            g2d.drawString("Others", ((Toolkit.getDefaultToolkit().getScreenSize().width/2) - metrics.stringWidth("Others")/2), 100);
        } catch (IOException e) {
            MonsterQuestMain.systemLog.log("Oh no! Unable to open file!" + e.getMessage());
        }
    }
    public Others() {
        super();
        setLayout(null);
        Font pixelFontBigger = new Font("Minecraft", Font.TRUETYPE_FONT, 25);
        about = new JButton("About");
        about.setBackground(Color.red);
        about.setBounds((MonsterQuestMain.MonsterQuestWindow.getWidth() /2) - 100, 200, 300, 150);
        about.setFont(pixelFontBigger);
        about.addActionListener(this);
        add(about);
        
        credits = new JButton("Credits");
        credits.setBackground(Color.blue);
        credits.setBounds((MonsterQuestMain.MonsterQuestWindow.getWidth() /2) - 100, 400, 300, 150);
        credits.setFont(pixelFontBigger);
        credits.addActionListener(this);
        add (credits);
        
        exit = new JButton("X");
        exit.setBackground(Color.red);
        exit.setBounds(1060, 30, 60, 40);
        exit.setFont(pixelFontBigger);
        exit.addActionListener(this);
        add(exit);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == credits) {
            MonsterQuestMain.systemLog.log("Showing credits scene");
            MonsterQuestMain.cardLayout.show(MonsterQuestMain.MonsterQuestPanel, "creditsScene");
            MonsterQuestMain.MonsterQuestPanel.repaint();
        } 
        else if (source == about) {
            MonsterQuestMain.systemLog.log("Showing about scene");
            MonsterQuestMain.cardLayout.show(MonsterQuestMain.MonsterQuestPanel, "aboutScene");
            MonsterQuestMain.MonsterQuestPanel.repaint();
        }
        else if (source == exit) {
            MonsterQuestMain.systemLog.log("Showing start menu");
            MonsterQuestMain.cardLayout.show(MonsterQuestMain.MonsterQuestPanel, "startMenu");
            MonsterQuestMain.MonsterQuestWindow.repaint();
        }
    }
    
    
}
