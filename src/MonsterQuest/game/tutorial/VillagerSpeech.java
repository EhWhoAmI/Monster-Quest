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
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author Zyun
 */
public class VillagerSpeech extends JPanel implements ActionListener, PropertyChangeListener{
    private final static String TEXT_STRINGS [] = { 
        "Hello, I am a villager from Newbies Town!", 
        "Welcome to Monster Quest.", 
        "We live in a beautiful place.", 
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
    int words = 0;
    JTextField nameBox;
    JButton next;
    @Override
    protected void paintComponent(Graphics g) {
        //The image variable
        BufferedImage villagerImage = null;
        try {
            //Retrive Image from hash map
            //TODO
            //show image
            villagerImage = ImageIO.read(new File (System.getProperty("user.dir") + "/resources/images/tutorial/villagerimg.png"));
            g.drawImage(villagerImage, 0, 0, null);
            textBox(g);
            drawWords(g, words);
        } catch (IOException e) {
            
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
        Font fontToShow = new Font ("Minecraft", Font.PLAIN, 30);
        g2d.setFont(fontToShow);
        g2d.setColor(Color.BLACK);
        g2d.drawString(TEXT_STRINGS[textToShow], 350, 100);
    }

    public VillagerSpeech() {
        super();
        setLayout(null);
        next = new JButton("Next>");
        next.setBackground(Color.green);
        next.setBounds (1050, 225, 100, 50);
        next.setFont(new Font("Minecraft", Font.PLAIN, 20));
        next.addActionListener(this);
        
        add(next);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JLabel label;
        
        //Define behavour for player read name
        if (words == 10) {
            nameBox = new JTextField();
            nameBox.setFocusable(true);
            nameBox.setBounds(350, 200, 500, 30);
            nameBox.setFont(new Font("Minecraft", Font.PLAIN, 30));
            nameBox.setBorder(BorderFactory.createLineBorder(Color.black));
            nameBox.setToolTipText("15 characters only!");
            nameBox.addActionListener(this);
            this.add(nameBox);
            next.setEnabled(false);
            
            label = new JLabel("15 characters only!");
            
        }
        if (e.getSource() == nameBox) {
            if (nameBox.getText().length() >= 16) {
                nameBox.setText("");
                MonsterQuestMain.systemLog.log("String is too long! change to 0 characters!");
            }
            MonsterQuestMain.systemLog.log("Written to name box");
            MonsterQuestMain.playerStats.name = nameBox.getText();
            remove(nameBox);
            next.setEnabled(true);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
       
    }
    
}
