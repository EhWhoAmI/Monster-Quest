package Zyun.Lam.Game.MonsterQuest;

/*Some art to liven up this bunch of boring code...
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
 * This is placed in somewhere like the medieval times. I tried to make it as 
 * historically "accurate" as possible, or make the clothing and weapons 
 * like then. But, I made some fake stuff, and so..... Yeah, you have this game.
 * 
 * This game has zombies, skeletons, golems and other evil monsters. You (the 
 * player) has to fight them off, and stop them from harming the innocent
 * villagers.
 * 
 * EULA : End-user license agreement
 * Last updated: 4 November 2016
 * Please read carefully before playing, using this software/game.
 * 
 * 1. Definitions
 *      a) "game" or "Game" or "Monster Quest", as in this software, or game
 *         that you are using right now.
 * 
 *         website listed here: <website>
 *      c) "We" or "we" or "us" or "Us" or "the maker of this game" or 
 *         "The maker of this game", as in the maker of this game, "Monster Quest".
 * 2. Introduction
 *      This is a agreement between: 
 *           a) you, as in the user of this game, whether it is a single 
 *              person, or company, meaning two or more people.
 *      and,
 *           b) The maker of this game, "Monster Quest", Zyun Lam.
 *      This governs the usage of this game.
 * 
 *      By downloading this game, you agree to the terms and conditions 
 *      listed below. Any user that uses this software on this device thereafter, 
 *      whether is you, the user, or another entity, will also be under these 
 *      terms and conditions.
 * 3. Licensing of use
 *      This software, is open source, and a source of this software can 
 *      be found at this address: https://github.com/EhWhoAmI/Monster-Quest. You 
 *      are allowed to edit, alter and change the software any way you want, for 
 *      example: Creating a mod, extending the game, or anything you can think of. 
 *      But, you must follow these guidelines:
 *           a) You must add the name "Zyun Lam" in the credits, if you 
 *              do reproduce this.
 *           b) We will not acknowledge anything you do to this software; 
 *              So, if you use this game for illegal purposes, or your computer 
 *              gets broken as a result, we will not be held responsible for that, 
 *              and not pay for damage as a result of you altering the software.
 *           c) This software must not be used for any illegal or malicious 
 *              uses, including and not up to: phishing, fraud, identiy thieft, 
 *              spam, spreading of computer virus, stealing data.
 *           d) We will not be held liable for anything that happens after 
 *              you alter this software.
 *           e) If you modify and distribute this software, you must make 
 *              this software open source. A good place to share is, 
 *              "https://github.com".
 *           f) If you modify and distribute this software, the software 
 *              must be free of other users to use. You must not use this for your 
 *              own profit.
 *      If you distribute this software, this software must be free to 
 *      distribute, or used for personal profit.
 * 4. Limitation of liability
 *      UNDER NO CIRCUMSTANCES, MAY "MONSTER QUEST" BE HELD LIABLE FOR 
 *      ANY DAMAGES CAUSED AS A RESULT OF MISUSE OF THIS SOFTWARE. IF YOU 
 *      DOWNLOAD AN ALTERATION THIS SOFTWARE, AND DISTRUBUTE THIS, ANY 
 *      DAMAGES WILL BE THE FAULT OF THE MAKER OF THE MODIFICATION. IF AN 
 *      ERROR, OR ISSUE OF YOUR DEVICE THAT RUNS OR PLAYS THIS GAME, WE 
 *      WILL NOT BE HELD LAIBLE FOR THE ISSUE. IF THE ISSUE IS IN THE GAME 
 *      ITSELF, YOU CAN GO TO THE WEBSITE OF THE GAME TO DOWNLOAd A FIX 
 *      FOR THE GAME. ANY DAMAGE AS A RESULT OF DOWNLOADING AND 
 *      INSTALLING A THIRD-PARTY MODFICATION OR HACK IS NOT TO BE HELD 
 *      LIABLE AGAINST US. IF ANY, INCLUDING AND NOT LIMITED TO, BODILY HARM,
 *      PROPERTY DAMAGE, EMOTIONAL HARM, IS CAUSED OF A RESULT OF PLAYING 
 *      THIS GAME DEEMED OVEREXESSIVELY BY, INCLUDING AND NOT LIMITED 
 *      TO: PARENTS, SPOUSES OR SIBLINGS, WE WILL NOT BE HELD RESPONSIBLE
 *      FOR THE EVENTS, AND THE COLORFUL LANGUAGE THAT IS USED DURING 
 *      AND AFTER THE EVENTS.
 * 5. Restrictions on use
 *      Under no circumstances, is this game to be used for malicious 
 *      purposes. Otherwise, we do not restrict you on how you use this 
 *      software, and certainly allow, and encourage you to have as much 
 *      fun as you can while using this software.
 * 6. Warranties
 *      You can download a fix of this game at the website of this game. 
 *      The fix should be able to fix any problems, but we do not guarantee
 *      that it will fix the software. If you downloaded any third-party 
 *      mods, we cannot guarantee that the mods will stay, in fact, we 
 *      expect the mod to disappear from the game. In that case, kindly 
 *      reinstall the mod into the game.
 *
 *      Bugs, errors or problems in the software. If any problems do occur, 
 *      kindly report the problem to the website of this game. We will try 
 *      our best to fix them. If you did not like this software, you are 
 *      almost allowed to uninstall this software from your device.
 * 7. Amendments to this agreement
 *      We reserve the right to amend this software, at our sole 
 *      discretion, without notice. Once it is amendmended, you 
 *      automatically agree to the newest version, after you click 
 *      "I agree", regardless of whether you know of this or not.
 * 8. The rights of the user
 *      You have the right to have as much fun as you can have while 
 *      playing this game.
 *      You have the right to distrubute this software, for free, and not 
 *      for personal profit.
 *
 * Other notes:
 * Estimated finish date: March 2016, we will have something playable
 * Done!!!!!
 */ 

//Imports
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import javax.swing.JFrame;
import nu.xom.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class WindowMain extends JFrame implements WindowListener{
    public static JFrame window;
    public static String version = "";
    public static boolean firstTimeSetup = false;
    public static String user_dir = System.getProperty("user.dir");
    public static File logFile;
    public static FileOutputStream logFileOutput;
    public WindowMain (int nothing) {
        //Do nothing -- empty constructor for you know what...
    }
    public WindowMain() {
        //Open log file
        logFile = new File ("system.log");
        if (!logFile.exists()){
            //Create File
            try {
                if (!logFile.createNewFile()) {
                    //Do nothing. Cannot write to log file.
                    System.out.println ("Cannot open log file!");
                } else {
                    logFileOutput = new FileOutputStream(logFile, true);
                    System.out.println("logFileOutput equals " + logFileOutput);
                    if (logFileOutput.equals(null)) {
                        System.err.println("logFileOutput equals null");
                    }
                }
            } catch (IOException ioe) {
                System.out.println("Unable to open file");
            }
        } else {
            try {
            logFileOutput = new FileOutputStream(logFile, true);
            System.out.println("logFileOutput equals " + logFileOutput);
            if (logFileOutput.equals(null)) {
                System.err.println("logFileOutput equals null");
            }
            } catch (FileNotFoundException fnfe) {
                System.err.println("File not found.");
            }
        }
        new SystemLog ("Opened log file: Hello, world");
        new SystemLog("Opening window");
        window = new JFrame("Monster Quest");
        setLookAndFeel();
        //Read from startup setting file
            File startupSettings = new File ("/resources/Startup-Settings.xml");
            if (!startupSettings.exists()){
                new SystemLog("Startup settings does not exist");
                //Do a patch for it
                try {
                    startupSettings.createNewFile();
                } catch (IOException file){
                    //Somehow, we cannot do it...
                    new SystemLog("Failed to open file with a IOException: " + file.getMessage() + ", Because" + file.getCause());
                    JOptionPane.showMessageDialog(null, "Error, unable to open the file " + startupSettings.getName() + "\nError: " + file.getMessage()+", in class" + file.getClass() + "\nReason"+ file.getCause() + "\nExiting the program now...", "Unable to open file", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                }
                //Set up a new xml document to handle all these stuff
                Element root = new Element ("root");
                Element fullscreen = new Element("fullscreen");
                Attribute fullscreenValue = new Attribute("value", "false");
                fullscreen.addAttribute(fullscreenValue);
                root.appendChild(fullscreen);
                Element firstTimeSetupElement = new Element("firsttimesetup");
                root.appendChild(firstTimeSetupElement);
                Element javaVersion = new Element ("java-version");
                Attribute javaVersionValue = new Attribute ("value", System.getProperty ("java.version"));
                javaVersion.addAttribute (javaVersionalue);
                Document startupDocument = new Document(root);
                try (FileWriter stupdoc = new FileWriter(startupSettings);
                    BufferedWriter out = new BufferedWriter(stupdoc);){
                    out.write(startupDocument.toXML());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Sorry, we really tried, and it could not do make it work.\nPlease uninstall this again", "Error making file", JOptionPane.ERROR_MESSAGE);
                }
                //That would do!
            }
            else {
                //file exists
                System.out.println("Startup File exists");
                //Read from xml document
                //JOptionPane.showMessageDialog(null, "Hello!", "Hello", JOptionPane.INFORMATION_MESSAGE);
                Builder document = new Builder ();
                try{
                    Document startDocument = document.build(startupSettings);
                    Element root = startDocument.getRootElement();
                    window.setSize(getToolkit().getScreenSize());
                    System.out.println("Read From document");
                    {
                        Element irsttimesetup = null;
                        irsttimesetup = root.getFirstChildElement("firsttimesetup");
                        if (irsttimesetup != null){
                            //First time setup
                            firstTimeSetup = true;
                        }
                        Element versionElement = root.getFirstChildElement("version");
                        if (versionElement == null){
                            System.out.println("Version element is null");
                        }
                        version = versionElement.getAttributeValue("value").toString();
                        //version = verElement.toString();
                        System.out.println("Version: " + version);
                    }
                } catch (ParsingException ioe) {
                    JOptionPane.showMessageDialog(null, "Parsing Error!", "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null, "I/O Error", "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                } catch (Exception ioe) {
                    JOptionPane.showMessageDialog(null, "Unknown Error!\nValue :" + ioe.getMessage() + "\nInfo :" + ioe.getCause() + "\nIn class: " + ioe.getClass() + "\nStack Trace: " + ioe.getStackTrace() + "\n@ line" + ioe.getStackTrace()[1].getLineNumber(), "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                }finally {
                    System.gc();
                }
                System.out.println("Open window");
                window.setVisible(true);
                window.addWindowListener(this);
                Graphics g = new Graphics();
                window.add(g);
                window.setResizable(false);
                System.out.println("Game start!!!!!!!! YAY!!!!!!!!");
                ControlUnit controlUnit = new ControlUnit();   
            }
    }
    
    public static void main(String[] args) {
        //Screw this...
        //termsAndConditions tmcds = new termsAndConditions();
        new WindowMain();
    }
    
    private void setLookAndFeel (){
        try {
            
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            //Do nothing
            System.err.println("Unable to set look and feel");
        }
    }
    
    //Window listeners
    @Override
    public void windowActivated(WindowEvent e) {
        System.out.println("Zyun.Lam.Game.MonsterQuest.WindowMain.windowActivated()");
        
    }

    @Override
    public void windowClosed(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("Zyun.Lam.Game.MonsterQuest.WindowMain.windowClosed()");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //Prevent close window, and ask user for verfication
        System.out.println("Zyun.Lam.Game.MonsterQuest.WindowMain.windowClosing()");
        setVisible(false);
        System.exit(0);
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        System.out.println("Zyun.Lam.Game.MonsterQuest.WindowMain.windowDeactivated()");
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        System.out.println("Zyun.Lam.Game.MonsterQuest.WindowMain.windowDeiconified()");
    }

    @Override
    public void windowIconified(WindowEvent e) {
        System.out.println("Zyun.Lam.Game.MonsterQuest.WindowMain.windowIconified()");
    }

    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("Zyun.Lam.Game.MonsterQuest.WindowMain.windowOpened()");
    }
    
    public static void frameRepaint () {
        window.repaint();
    }
}