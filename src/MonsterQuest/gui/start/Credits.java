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
import MonsterQuest.util.GraphicsUnit;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;
import javax.swing.JPanel;
import static MonsterQuest.MonsterQuestMain.systemLog;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This shows the credits screen
 * @author Zyun
 */
@GraphicsUnit
public class Credits extends JPanel implements ActionListener{
    private JButton exitButton;
    private ArrayList<String> creditsList = new ArrayList<>();
    @Override
    protected void paintComponent(Graphics g) {
        //Draw image for credits.
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D.Float bg = new Rectangle2D.Float(0, 0, (float)MonsterQuestMain.MonsterQuestWindow.getWidth(), (float)MonsterQuestMain.MonsterQuestWindow.getHeight());
        g2d.setColor(Color.BLACK);
        g2d.fill(bg);
        g2d.setColor(Color.WHITE);
        //Set font
        Font pixelFontBigger = new Font("Minecraft", Font.TRUETYPE_FONT, 35);
        g2d.setFont(pixelFontBigger);

        //The credits text, all the peeps who helped!
        //If you did help a bit, please edit this a bit to show your name. Just don't delete any names
        
        g2d.drawString("By The following: Zyun", 10, 40);
        g2d.drawString("Beta Testers: ", 10, 80);
        g2d.drawString("Not yet in beta mode!", 10, 120);
        //The resources and libraries we used.
        g2d.drawString("Libraries used:", 10, 160);
        g2d.drawString("XOM: XML object model", 10, 200);
        g2d.drawString("Tiled map editor", 10, 240);
        //To highlight the get out part
        g2d.drawString("Get out using this button: ", 10, 650);
    }
    
    /**
     * Create a new Credits 
     */
    public Credits() {
        super();
        setLayout(null);
        //The exit button for exiting this scene
        exitButton = new JButton("X");
        exitButton.setBackground(Color.red);
        exitButton.setBounds(10, 680, 60, 40);
        exitButton.addActionListener(this);
        exitButton.setFont(new Font("Minecraft", Font.TRUETYPE_FONT, 35));
        add(exitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Exit the credits place into the others place
         systemLog.log("Show others");
         MonsterQuestMain.cardLayout.show(MonsterQuestMain.MonsterQuestPanel, "othersOption");
         MonsterQuestMain.MonsterQuestWindow.repaint();
    }
    
    
}