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
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.ParsingException;

/**
 *
 * @author Zyun
 */
public class Loading extends JPanel{
    private String quote;
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
            g2d.setFont(MonsterQuestMain.pixelFont);
            FontMetrics metrics = getFontMetrics(MonsterQuestMain.pixelFont);
            g2d.drawString("Loading...", (Toolkit.getDefaultToolkit().getScreenSize().width/2 - metrics.stringWidth("Loading...")/2), 700);
            //Show quote.
            g2d.drawString(quote, (Toolkit.getDefaultToolkit().getScreenSize().width/2 - metrics.stringWidth(quote)/2), 650);
        } catch (IOException e) {
            MonsterQuestMain.systemLog.log("Oh no! Unable to open file!" + e.getMessage());
        }
    }
    
    public Loading() {
        super();
        quote = createNewQuote();
    }
    
    public void loadFiles () {
        File startupSettings = new File (System.getProperty("user.dir") + "/data/settings/Startup-Settings.xml");
        if (startupSettings.exists()) {
            //Read from startup settings file
            try {
                MonsterQuestMain.systemLog.log("Startup settings file exists.");
                Builder startupSettingsBuilder = new Builder();
                Document startupSettingsDocument = startupSettingsBuilder.build(startupSettings);
                Element root = startupSettingsDocument.getRootElement();
                Element first_time_setup = root.getFirstChildElement("firsttimesetup");
                if (first_time_setup == null) {
                    //Not the first time this was setup!
                    MonsterQuestMain.systemLog.log("Not the first time setup!");
                    //Get version
                    MonsterQuestMain.app_Version = root.getFirstChildElement("version").getAttribute("value").getValue();
                    MonsterQuestMain.systemLog.log("App version: " + MonsterQuestMain.app_Version);
                }
                else {
                    //Deal with it. Remeber to delete firsttimestartup element!
                    //The space
                    root.removeChild(0);
                    //The element it self.
                    root.removeChild(1);
                    root.removeChild(2);
                    //Get version
                    MonsterQuestMain.app_Version = root.getFirstChildElement("version").getAttribute("value").toString();
                    MonsterQuestMain.systemLog.log("App version: " + MonsterQuestMain.app_Version);
                    
                    MonsterQuestMain.systemLog.log(root.toXML());
                    FileWriter writer = new FileWriter(startupSettings);
                    BufferedWriter bufferedWriter = new BufferedWriter(writer);
                    MonsterQuestMain.systemLog.log("Writing to file " + bufferedWriter.toString());
                    bufferedWriter.write(root.toXML());
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }
            } catch (ParsingException pe) {
                MonsterQuestMain.systemLog.log("Parsing exception! " + pe.getMessage());
                //Remake file.
                startupSettings.delete();
                loadFiles();
            } catch (IOException ioe) {
                MonsterQuestMain.systemLog.log("I/O exception! " + ioe.getMessage());
                //Remake file.
                startupSettings.delete();
                loadFiles();
            }
        }
        else {
            //Create new file.
            MonsterQuestMain.systemLog.log ("Creating new file!");
            try {
                startupSettings.createNewFile();
                Element root = new Element("root");
                Element firstTimeSetup = new Element ("firsttimesetup");
                root.appendChild(firstTimeSetup);
                Element version = new Element("version");
                version.addAttribute(new Attribute("value", "UNKNOWN"));
                root.appendChild(version);
                MonsterQuestMain.systemLog.log("Root = " + root.toXML());
                MonsterQuestMain.systemLog.log("Writing to file.");
                //Save file.
                FileWriter writer = new FileWriter(startupSettings);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                MonsterQuestMain.systemLog.log("Writing to file " + bufferedWriter.toString());
                bufferedWriter.write(root.toXML());
                bufferedWriter.flush();
                bufferedWriter.close();
            }catch (IOException ioe) {
                MonsterQuestMain.systemLog.log("Unable to write to file!!! I give up!!" + ioe.getMessage());
            }
        }
        
        ////////////////////
        //Now, read from resources.xml, and check all files.
        //File resources = new File (System.getProperty("user.dir") + "/resources/resources.xml");
        //We will skip all the verfying for now, and we will do it later.
        
        //Done!
    }
    
    private String createNewQuote () {
        //Array for quotes...
        String[] quoteList = {"Tip: Tips go here :P", 
                "This game is the best game in the world. Tell your mom that.",
                "Kill monsters to get rewards!(I know you know this. Just killing time)", 
                "Filling the world with monsters... Bwuwahahaha...",
                "Please buy a beginner's handbook from the shop. It's free.",
                "Welcome back to Monster Quest!",
                "Monster Quest is in constant development! Yay!!",
                "Always remember that you are absolutely unique. Just like everyone else.",
                "You can buy many things from the shop, if you have the money",
                "Had I been present at the Creation, I would have given some useful hints for the better ordering of the universe - Alfonso X, King of Castile"
                };
        int randomNo = ((int)(Math.random() * 1000) % quoteList.length);
        MonsterQuestMain.systemLog.log("Random quote number: " + randomNo);
        String randomQuote = quoteList[randomNo];
        MonsterQuestMain.systemLog.log("Quote: " + randomQuote);
        return randomQuote;
    }
}
