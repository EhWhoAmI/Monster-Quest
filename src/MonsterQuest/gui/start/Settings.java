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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
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
import nu.xom.Element;
import nu.xom.ParsingException;
import nu.xom.Document;
import nu.xom.Text;

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
        //Define font
        Font pixelFontBigger = new Font("Minecraft", Font.TRUETYPE_FONT, 25);
        
        //Title
        JLabel label = new JLabel("Settings");
        label.setFont(pixelFontBigger);
        add(label);
        
        //Sound button
        sound = new JRadioButton("Sounds");
        sound.setFont(pixelFontBigger);
        sound.addActionListener(this);
        add(sound);
        
        //Music button
        music = new JRadioButton("Music");
        music.setFont(pixelFontBigger);
        music.addActionListener(this);
        add(music);
        
        //Description button
        JLabel volLabel = new JLabel ("Volume: ");
        volLabel.setFont(pixelFontBigger);
        add(volLabel);
        
        //Volume slider
        volume = new JSlider(0, 100);
        volume.setFont(pixelFontBigger);
        add(volume);
        
        //How to play button
        how2Play = new JButton("How to play");
        how2Play.setFont(pixelFontBigger);
        how2Play.addActionListener(this);
        add(how2Play);
        
        //Exit button 
        exit = new JButton("X");
        exit.setBackground(Color.red);
        exit.setFont(pixelFontBigger);
        exit.addActionListener(this);
        add(exit);
        
        readFromFile();
    }
    
    /** This reads from the settings file.
     * 
     */
    void readFromFile () {
        File settings = new File (System.getProperty("user.dir") + "/data/settings/settings.xml");
        
        if (settings.exists()) {
            //Read from settings
            try {
                //Open file, and parse file
                settingsBuilder = new Builder();
                Document settingsDocument = settingsBuilder.build(settings);

                //Get root element
                root = settingsDocument.getRootElement();

                //Get sound element
                soundElement = root.getFirstChildElement("sound");

                //Check wether it is selected or not selected
                if (soundElement.getChild(0).getValue().equals("On")) {
                    sound.setSelected(true);
                    MonsterQuestMain.systemLog.log("Sound is on");
                } 
                else {
                    sound.setSelected(false);
                    MonsterQuestMain.systemLog.log("Sound is off");
                }
                
                //Volume element
                volumeElement = root.getFirstChildElement("volume");
                int volumeNum = Integer.valueOf(volumeElement.getChild(0).getValue());
                MonsterQuestMain.systemLog.log("Volume = " + volumeNum);
                volume.setValue(volumeNum);
                
                //Music element
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
        else {
            //Create new file... TODO
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(how2Play)) {
            //How to play this game
            JOptionPane.showMessageDialog(MonsterQuestMain.MonsterQuestWindow, "W, A, S, D to move about\nArrow keys to chose direction to attack\n1, 2, 3, Q, E keys to use ability\nZ for inventory\nX to pause","How to play", JOptionPane.OK_OPTION);
        }
        else if (source == exit) {
            MonsterQuestMain.systemLog.log("Writing to settings file");
            try (
                FileWriter fw = new FileWriter (System.getProperty("user.dir") + "/data/settings/settings.xml");
                BufferedWriter out = new BufferedWriter(fw);  
            ) {
                Document doc = new Document (root.getDocument());
                //Write to settings file
                out.write (doc.toXML());
            }catch (IOException ioe) {
                MonsterQuestMain.systemLog.log("UNABLE TO WRITE TO SETTINGS FILE: " + ioe.getMessage()); 
            }
            //Go back to start menu
            MonsterQuestMain.systemLog.log("Showing start menu");
            MonsterQuestMain.cardLayout.show(MonsterQuestMain.MonsterQuestPanel, "startMenu");
            MonsterQuestMain.MonsterQuestWindow.repaint();
        }
        else if (source.equals(music)) {
            //Change XM:
            Text toPut;
            if (music.isSelected()) {
                MonsterQuestMain.systemLog.log("Turn off music.");
                toPut = new Text("On");
            }
            else {
                MonsterQuestMain.systemLog.log("Turn on music");
                toPut = new Text("Off");
            }
            //Remove, then relpace the element
            musicElement.removeChild(0);
            musicElement.appendChild(toPut);
        }
        else if (source.equals(sound)) {
            Text toPut;
            if (sound.isSelected()) {
                MonsterQuestMain.systemLog.log("Turn off music.");
                toPut = new Text("On");
            }
            else {
                MonsterQuestMain.systemLog.log("Turn on music");
                toPut = new Text("Off");
            }
            soundElement.removeChild(0);
            soundElement.appendChild(toPut);
        }
        else if (source.equals(volume)) {
            Text valueText;
            int value = volume.getValue();
            valueText = new Text(Integer.toString(value));
            volumeElement.removeChild(0);
            volumeElement.appendChild(valueText);
        }
    }
    
}
    