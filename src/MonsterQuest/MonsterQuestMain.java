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

package MonsterQuest;

import MonsterQuest.game.FadeInOut;
import MonsterQuest.game.Player;
import MonsterQuest.game.tutorial.VillagerSpeech;
import MonsterQuest.gui.start.About;
import MonsterQuest.gui.start.Credits;
import MonsterQuest.gui.start.Loading;
import MonsterQuest.gui.start.Others;
import MonsterQuest.gui.start.Settings;
import MonsterQuest.gui.start.StartInterfaceMenu;
import MonsterQuest.gui.start.StartIntroScreen;
import java.awt.Toolkit;
import javax.swing.JFrame;
import MonsterQuest.util.Logging;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**Some art to liven up this bunch of boring code...
 *                     _____________          _    ___________    ______________    _____________    ____________
 * |\            /|    |  _______  |  |\     | |   | _________|   |_____  _____|   |  ___________|   | _______  |
 * | \          / |    |  |     |  |  | \    | |   | |                 |  |        |  |              | |      | |
 * |  \        /  |    |  |     |  |  |  \   | |   | |_________        |  |        |  |________      | |______| |
 * | | \      / | |    |  |     |  |  | | \  | |   |_________  |       |  |        |   ________|     |  _  _____|
 * | |\ \    / /| |    |  |     |  |  | |\ \ | |             | |       |  |        |  |              | | \ \
 * | | \ \  / / | |    |  |     |  |  | | \ \| |             | |       |  |        |  |              | |  \ \
 * | |  \ \/ /  | |    |  |_____|  |  | |  \ | |   __________| |       |  |        |  |__________    | |   \ \
 * |_|   \__/   |_|    |___________|  |_|   \__|   |___________|       |__|        |_____________|   |_|    \_\
 * ___________     __       __      _____________     ___________   ______________ 
 * | ________ |   |  |     |  |    |  ___________|   | _________|   |_____  _____|
 * | |      | |   |  |     |  |    |  |              | |                 |  | 
 * | |      | |   |  |     |  |    |  |________      | |_________        |  | 
 * | |      | |   |  |     |  |    |   ________|     |_________  |       |  | 
 * | |______| |   |  |     |  |    |  |                        | |       |  | 
 * |_____  ___|   |  |_____|  |    |  |__________    __________| |       |  | 
 *       \_\      |___________|    |_____________|   |___________|       |__| 
 * 
 * 
 * This is placed in somewhere like the medieval times. I tried to make it as 
 * historically "accurate" as possible, or make the clothing and weapons 
 * like then. But, I made some fake stuff, and so..... Yeah, you have this game.
 * 
 * This game has zombies, skeletons, golems and other evil monsters. You (the 
 * player) has to fight them off, and stop them from harming the innocent
 * villagers.
 * 
 * This game's github repo: <a href="https://github.com/EhWhoAmI/Monster-Quest">https://github.com/EhWhoAmI/Monster-Quest<a>
 */ 

/**The main class of Monster Quest.
 */

public class MonsterQuestMain {
    public static JFrame MonsterQuestWindow;
    public static JPanel MonsterQuestPanel;
    public static CardLayout cardLayout = new CardLayout();;
    public static Logging systemLog;
    public static boolean DEBUG = false;
    public static boolean PRINT_TO_LOGFILE = false;
    public static Font pixelFont;
    public static FadeInOut fader = new FadeInOut();
    public static Player playerStats;
    //All the values for the version of the game...
    public static String app_Version;
    /**
     * The Main function of the monster quest game.
     * @param args 
     */
    public static void main(String[] args) {
        //Set up first logging.
        systemLog = new Logging("/data/logs/system.log");
        //Command line args: 
        for (int n = 0; n < args.length; n++) {
            if (args[n].equals("-d")) {
                //Turn on debug tools
                DEBUG = true;
                systemLog.log("Debugging mode is on");
            }
            if (args[n].equals("-l")) {
                //Allow to print to debug file
                PRINT_TO_LOGFILE = true;
                systemLog.log("Writing to logfile...");
            }
            if (args[n].equals("-c")) {
                //Enable cheats.
                //TODO
            }
        }
        new MonsterQuestMain();
    }
    
    /**
     * The constructor for the Monster Quest thingy. This will be in charge of
     * opening the window, and initializing the whole thing, too
     */
    public MonsterQuestMain () {
        systemLog.log("Starting game...");
        MonsterQuestWindow = new JFrame("Monster Quest");
        MonsterQuestWindow.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/icon/Icon.png"));
        //Set size for window
        MonsterQuestWindow.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        systemLog.log("Screen size: H= " + Toolkit.getDefaultToolkit().getScreenSize().height + " W= " + Toolkit.getDefaultToolkit().getScreenSize().width);
        MonsterQuestWindow.setResizable(false);
        MonsterQuestWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Do all the frame init stuff
        StartIntroScreen intro = new StartIntroScreen();
        Loading loadScreen = new Loading();
        MonsterQuestPanel = new JPanel();
        MonsterQuestWindow.add(MonsterQuestPanel);
        MonsterQuestPanel.setLayout(cardLayout); 
        //Add the start intro screen to the card layout.
        MonsterQuestPanel.add(intro, "intro");
        MonsterQuestPanel.add(loadScreen, "loading");
        MonsterQuestPanel.add(new StartInterfaceMenu(), "startMenu");
        loadFont();
        //Show window.
        MonsterQuestWindow.setVisible(true);
        cardLayout.show(MonsterQuestPanel, "intro");
        MonsterQuestWindow.repaint();
        playSound("/resources/audio/start.wav");
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
        }
        cardLayout.show(MonsterQuestPanel, "loading");
        MonsterQuestWindow.repaint();
        //Load all the crucial bits for the start menu
        MonsterQuestPanel.add(new Others(), "othersOption");
        MonsterQuestPanel.add(new Credits(), "creditsScene");
        MonsterQuestPanel.add(new About(), "aboutScene");
        MonsterQuestPanel.add(new Settings(), "settings");
        MonsterQuestPanel.add(new VillagerSpeech(), "villagerSpeech");
        MonsterQuestPanel.add(fader);
        playerStats = new Player();
        //Load files
        loadScreen.loadFiles();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        cardLayout.show(MonsterQuestPanel, "startMenu");
    }
    
    static void playSound (String soundFileName) {
        try {
                String soundFile = (System.getProperty("user.dir") + soundFileName);
                InputStream in = new FileInputStream(soundFile);

                // create an audiostream from the inputstream
                AudioStream audioStream = new AudioStream(in);
                // play the audio clip with the audioplayer class
                systemLog.log("Loading sound...");
                AudioPlayer.player.start(audioStream);
        } catch (FileNotFoundException fnfe) {
                systemLog.log("Unable to open file! " + fnfe.getMessage());
        } catch (IOException ioe) {
                systemLog.log("IO error! " + ioe.getMessage());
        }
    }
    
    private void setLookAndFeel () {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            systemLog.log("Setting look and feel");
        } catch (UnsupportedLookAndFeelException ex) {
            systemLog.log("Unsupported Look and feel!");
        } catch (IllegalAccessException ex) {
            systemLog.log("Illegal access!");
        } catch (InstantiationException ex) {
            systemLog.log("Instantiation Exception!");
        } catch (ClassNotFoundException ex) {
            systemLog.log("Class not found!");
        }
    }
    
    private void loadFont () {
        try {
            //create the font to use. Specify the size!
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File(System.getProperty("user.dir") + "/resources/fonts/Minecraft.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(System.getProperty("user.dir") + "/resources/fonts/Minecraft.ttf")));
            //List avaible fonts
        } catch (IOException e) {
            systemLog.log("Unable to open file! " + e.getMessage());
        } catch(FontFormatException e) {
            systemLog.log("Font format exception!");
        }
    }
}
