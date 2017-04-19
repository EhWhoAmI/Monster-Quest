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
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.ParsingException;

/**
 *
 * @author Zyun
 */
public class Settings extends JPanel implements ActionListener{
    JRadioButton sound, music;
    JSlider volume;
    JButton how2Play, exit;
    Builder settingsBuilder;
    Element root, soundElement, volumeElement, musicElement;
    
    public Settings() {
        super(); 
        setBackground(Color.white);
        GridLayout layout = new GridLayout(12, 3);
        Font pixelFontBigger = new Font("Minecraft", Font.TRUETYPE_FONT, 25);
        
        JLabel label = new JLabel("Settings");
        label.setFont(pixelFontBigger);
        add(label);
        
        sound = new JRadioButton("Sounds");
        sound.setFont(pixelFontBigger);
        sound.addActionListener(this);
        add(sound);
        
        music = new JRadioButton("Music");
        music.setFont(pixelFontBigger);
        add(music);
        
        JLabel volLabel = new JLabel ("Volume: ");
        volLabel.setFont(pixelFontBigger);
        add(volLabel);
        
        volume = new JSlider(0, 100);
        
        volume.setFont(pixelFontBigger);
        add(volume);
        
        how2Play = new JButton("How to play");
        how2Play.setFont(pixelFontBigger);
        how2Play.addActionListener(this);
        add(how2Play);
        
        exit = new JButton("X");
        exit.setBackground(Color.red);
        exit.setFont(pixelFontBigger);
        exit.addActionListener(this);
        add(exit);
        
        readFromFile();
    }
    
    void readFromFile () {
        File settings = new File (System.getProperty("user.dir") + "/data/settings/settings.xml");
        
        if (settings.exists()) {
            //Read from settings
            try {
                settingsBuilder = new Builder();
                Document settingsDocument = settingsBuilder.build(settings);
                root = settingsDocument.getRootElement();
                soundElement = root.getFirstChildElement("sound");
                if (soundElement.getChild(0).getValue().equals("On")) {
                    sound.setSelected(true);
                    MonsterQuestMain.systemLog.log("Sound is on");
                } 
                else {
                    sound.setSelected(false);
                    MonsterQuestMain.systemLog.log("Sound is off");
                }
                
                volumeElement = root.getFirstChildElement("volume");
                int volumeNum = Integer.valueOf(volumeElement.getChild(0).getValue());
                MonsterQuestMain.systemLog.log("Volume = " + volumeNum);
                volume.setValue(volumeNum);
                
                musicElement = root.getFirstChildElement("music");
                if (musicElement.getChild(0).getValue().equals("On")) {
                    music.setSelected(true);
                    MonsterQuestMain.systemLog.log("Music is on");
                }
                else {
                    music.setSelected(false);
                    MonsterQuestMain.systemLog.log("Music is off");
                }
            } catch (IOException | ParsingException e) {
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == how2Play) {
            JOptionPane.showMessageDialog(MonsterQuestMain.MonsterQuestWindow, "W, A, S, D to move about\nArrow keys to chose direction to attack\n1, 2, 3, Q, E keys to use ability\nZ for inventory\nX to pause","How to play", JOptionPane.OK_OPTION);
        }
        else if (source == exit) {
            MonsterQuestMain.systemLog.log("Writing to settings file");
            try {
                FileWriter 
            } catch () {
            }
            MonsterQuestMain.systemLog.log("Showing start menu");
            MonsterQuestMain.cardLayout.show(MonsterQuestMain.MonsterQuestPanel, "startMenu");
            MonsterQuestMain.MonsterQuestWindow.repaint();
        }
        else if (source == music) {
            
        }
    }
    
}
    
