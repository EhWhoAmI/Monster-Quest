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

import MonsterQuest.gui.start.Loading;
import MonsterQuest.gui.start.StartIntroScreen;
import java.awt.Toolkit;
import javax.swing.JFrame;
import MonsterQuest.util.Logging;
import Zyun.Lam.Game.MonsterQuest.SystemLog;
import Zyun.Lam.Game.MonsterQuest.WindowMain;
import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.PopupMenu;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
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
    public static Logging systemLog;
    CardLayout cardLayout;
    //All the values for the version of the game...
    public static String app_Version;
    /**
     * The Main function of the monster quest game.
     * @param args 
     */
    public static void main(String[] args) {
        new MonsterQuestMain();
    }
    
    /**
     * The constructor for the Monster Quest thingy. This will be in charge of
     * opening the window, and initializing the whole thing, too
     */
    public MonsterQuestMain () {
        //Set up first logging.
        systemLog = new Logging("/data/logs/system.log");
        systemLog.log("Starting game...");
        MonsterQuestWindow = new JFrame("Monster Quest");
        MonsterQuestWindow.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/icon/Icon.png"));
        //Set size for window
        MonsterQuestWindow.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        MonsterQuestWindow.setResizable(false);
        MonsterQuestWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Show window.
        MonsterQuestWindow.setVisible(true);
        //Add the start intro screen to the card layout.
        StartIntroScreen intro = new StartIntroScreen();
        MonsterQuestWindow.add(intro);
        MonsterQuestWindow.repaint();
        playSound("/resources/audio/start.wav");
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
        }
        MonsterQuestWindow.remove(intro);
        MonsterQuestWindow.remove(intro);
        MonsterQuestWindow.repaint();
        Loading loadScreen = new Loading();
        MonsterQuestMain.systemLog.log("Loading the bar.");
        MonsterQuestWindow.add(loadScreen);
        MonsterQuestWindow.repaint();
        MonsterQuestWindow.repaint(10);
    }
    
    static void playSound (String soundFileName) {
        try {
                String soundFile = (System.getProperty("user.dir") + soundFileName);
                InputStream in = new FileInputStream(soundFile);

                // create an audiostream from the inputstream
                AudioStream audioStream = new AudioStream(in);
                // play the audio clip with the audioplayer class
                AudioPlayer.player.start(audioStream);
                audioStream.close();
            } catch (FileNotFoundException fnfe) {
                systemLog.log("Unable to open file! " + fnfe.getMessage());
            } catch (IOException ioe) {
                systemLog.log("IO error! " + ioe.getMessage());
            }
    }
}