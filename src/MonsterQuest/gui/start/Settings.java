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
import javax.swing.JButton;
import javax.swing.JLabel;
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
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
    }

    public Settings() {
        super();
        GridLayout layout = new GridLayout(12, 3);
        Font pixelFontBigger = new Font("Minecraft", Font.TRUETYPE_FONT, 25);
        JLabel label = new JLabel("Settings");
        label.setFont(pixelFontBigger);
        add(label);
        
        sound = new JRadioButton("Sounds");
        sound.setFont(pixelFontBigger);
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
        add(how2Play);
        
        exit = new JButton("X");
        exit.setBackground(Color.red);
        exit.setFont(pixelFontBigger);
        add(exit);
        
        readFromFile();
    }
    
    void readFromFile () {
        File settings = new File (System.getProperty("user.dir") + "/data/settings/settings.xml");
        
        if (settings.exists()) {
            //Read from settings
            try {
                Builder settingsBuilder = new Builder();
                Document settingsDocument = settingsBuilder.build(settings);
                Element root = settingsDocument.getRootElement();
                Element soundElement = root.getFirstChildElement("sound");
                if (soundElement.getChild(0).getValue().equals("On")) {
                    sound.setSelected(true);
                    MonsterQuestMain.systemLog.log("Sound is on");
                } 
                else {
                    sound.setSelected(false);
                    MonsterQuestMain.systemLog.log("Sound is off");
                }
                
                Element volumeElement = root.getFirstChildElement("volume");
                int volumeNum = Integer.valueOf(volumeElement.getChild(0).getValue());
                MonsterQuestMain.systemLog.log("Volume = " + volumeNum);
                volume.setValue(volumeNum);
                
                Element musicElement = root.getFirstChildElement("music");
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
        
    }
    
    
    
}
