package MonsterQuest;

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

 

import MonsterQuest.game.player.Player;
import MonsterQuest.game.tutorial.CharacterChoose;
import MonsterQuest.game.tutorial.VillagerSpeech;
import MonsterQuest.gui.start.About;
import MonsterQuest.gui.start.Credits;
import MonsterQuest.gui.start.Loading;
import MonsterQuest.gui.start.Others;
import MonsterQuest.gui.start.Settings;
import MonsterQuest.gui.start.StartInterfaceMenu;
import MonsterQuest.gui.start.StartIntroScreen;
import MonsterQuest.util.LoadingScreen;
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

/**
 * The main class of Monster Quest.
 */

public class MonsterQuestMain {
    /**
     * This is for the window of the game.
     */
    public static JFrame MonsterQuestWindow;
    /**
     * This stores the container for the whole game.
     */
    public static JPanel MonsterQuestPanel;
    /**
     * This is the card layout, it is the 'traffic police' of the game.
     */
    public static CardLayout cardLayout = new CardLayout();
    /**
     * This is the logger for logging all that goes on.
     */
    public static Logging systemLog;
    /**
     * This enables the debug mode, use -d to enable.
     */
    public static boolean DEBUG = false;
    /**
     * This variable deciedes whether or not we should write to the log file, use -l to enable.
     */
    public static boolean PRINT_TO_LOGFILE = false;
    /**
     * We might not need this, but this is for the font.
     */
    public static Font pixelFont;
    /**
     * This is to store the stats of the player. 
     */
    public static Player playerStats;
    /** 
     * This is the string that will store the app version.
     */
    public static String app_Version;
    /**
     * We only need this because the buttons need to load. Might find a better solution in the future.
     */
    public static CharacterChoose choose;
    /**
     * The Main function of the monster quest game.
     * @param args The command line arguments
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
        //Set up all the window and stuff
        systemLog.log("Starting game...");
        MonsterQuestWindow = new JFrame("Monster Quest");
        
        //Do all the frame init stuff
        MonsterQuestPanel = new JPanel();
        MonsterQuestWindow.add(MonsterQuestPanel);
        
        //Card layout for sorting out all the cards.
        MonsterQuestPanel.setLayout(cardLayout); 
        
        //Set window size
        //this is the size of my computer screen, so 
        MonsterQuestWindow.setSize(1366, 768);
        systemLog.log("Screen size: H= " + MonsterQuestWindow.getHeight() + " W= " + MonsterQuestWindow.getWidth());
        MonsterQuestWindow.setResizable(false);
        MonsterQuestWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Set icon
        MonsterQuestWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "/resources/icon/Icon.png"));
        
        //Add the start intro screen to the card layout.
        MonsterQuestPanel.add(new StartIntroScreen(), "intro");
        MonsterQuestPanel.add(new Loading(), "loading");
        MonsterQuestPanel.add(new StartInterfaceMenu(), "startMenu");
        
        //Load the fonts neccesary for the game...
        loadFont();
        
        //Show window.
        MonsterQuestWindow.setVisible(true);
        
        //Show the start screen
        cardLayout.show(MonsterQuestPanel, "intro");
        MonsterQuestWindow.repaint();
        
        //Play music
        playSound("/resources/audio/start.wav");
        
        //Wait for music to finish playing
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            //Ingore if interrupted
        }
        
        //Now, load all the things for the game
        cardLayout.show(MonsterQuestPanel, "loading");
        MonsterQuestWindow.repaint();
        
        //Load all the crucial bits for the start menu
        //The panels will be all here
        MonsterQuestPanel.add(new Others(), "othersOption");
        MonsterQuestPanel.add(new Credits(), "creditsScene");
        MonsterQuestPanel.add(new About(), "aboutScene");
        MonsterQuestPanel.add(new Settings(), "settings");
        MonsterQuestPanel.add(new VillagerSpeech(), "villagerSpeech");
        MonsterQuestPanel.add(new LoadingScreen(), "loadScreen");
        
        //Init player
        playerStats = new Player();
        
        //Load files
        Loading.loadFiles();
        
        //Fake loading after that.
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        
        //Show start menu
        cardLayout.show(MonsterQuestPanel, "startMenu");
    }
    
    /**
     * This plays the sound specified in the parameters.
     * @param soundFileName The file name for the sound file
     */
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
    
    
    /**
     * This loads the font, and registers the font onto the computer.
     */
    private void loadFont () {
        try {
            //create the font to use. Specify the size!
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File(System.getProperty("user.dir") + "/resources/fonts/Minecraft.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(System.getProperty("user.dir") + "/resources/fonts/Minecraft.ttf")));
            //List avaible fonts
        } catch (IOException e) {
            //Will add a find file on the internet soon.
            systemLog.log("Unable to open file! " + e.getMessage());
        } catch(FontFormatException e) {
            systemLog.log("Font format exception!");
        }
    }
}
