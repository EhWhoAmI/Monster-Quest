package Zyun.Lam.Game.MonsterQuest;

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
 * This is placed in somewhere like the medieval times. I tried to make it as 
 * historically "accurate" as possible, or make the clothing and weapons 
 * like then. But, I made some fake stuff, and so..... Yeah, you have this game.
 * 
 * This game has zombies, skeletons, golems and other evil monsters. You (the 
 * player) has to fight them off, and stop them from harming the innocent
 * villagers.
 * 
 * Done!!!!!
 */ 

//Imports
import java.awt.Cursor;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import javax.swing.JFrame;
import nu.xom.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Zyun
 */
public class WindowMain extends JFrame implements WindowListener {

    /**
     *
     */
    public static JFrame window;

    /**
     *
     */
    public static String version = "";

    /**
     *
     */
    public static boolean firstTimeSetup = false;

    /**
     *
     */
    public static String user_dir = System.getProperty("user.dir");

    /**
     *
     * @param nothing
     */
    public WindowMain (int nothing) {
        //Do nothing -- empty constructor for you know what...
    }
    
    /**
     *
     */
    public WindowMain() {
        //Deal with log file issue
        SystemLog.clearFile();
        SystemLog.startNewLog();
        SystemLog.log("Application opened");
        SystemLog.log("Open window...");
        window = new JFrame("Monster Quest");
        setLookAndFeel();
            window.setSize(1366, 748);
            System.out.println("Open window");
            SystemLog.log("Show window");
            //Do all the necessary window initalitaion part
            window.setVisible(true);
            window.setResizable(false);
            window.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            window.addWindowListener(this);
            
            Graphics g = new Graphics();
            window.add(g);
            try {
                Thread.sleep(6000);
            } catch (InterruptedException ie) {
                //Ignore
                System.out.println("Interrupted sleep...");
            }
            System.out.println("Done with music");
            ControlUnit.loadingfinished = false;
            ControlUnit.startScreenSplash = false;
            frameRepaint();
            //Read from startup setting file
            File startupSettings = new File (user_dir + "/resources/Startup-Settings.xml");
            if (!startupSettings.exists()){
                //Do a patch for it
                SystemLog.log("Startup settings does not exist -- Creating new file");
                try {
                    startupSettings.createNewFile();
                } catch (IOException file){
                    //Somehow, we cannot do it.
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
                
                Document startupDocument = new Document(root);
                try (FileWriter stupdoc = new FileWriter(startupSettings);
                    BufferedWriter out = new BufferedWriter(stupdoc);){
                    out.write(startupDocument.toXML());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Sorry, we really tried, and it could not do make it work.\nPlease uninstall this again", "Error making file", JOptionPane.ERROR_MESSAGE);
                    SystemLog.log("Failed with an error: " + e.getClass() + " Cause " + e.getCause());
                }
                //That would do!
            }
            else {
                //file exists
                SystemLog.log("Startup file exists");
                System.out.println("Startup File exists");
                //Read from xml document
                SystemLog.log("Reading from XML document");
                Builder document = new Builder ();
                try{
                    Document startDocument = document.build(startupSettings);
                    Element root = startDocument.getRootElement();
                    System.out.println("Read From startup file");
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
                        } else { 
                            version = versionElement.getAttributeValue("value").toString();
                        }
                        //version = verElement.toString();
                        System.out.println("Version: " + version);
                    }
                } catch (ParsingException ioe) {
                    JOptionPane.showMessageDialog(null, "Parsing Error!", "Error", JOptionPane.ERROR_MESSAGE);
                    SystemLog.log("Parsing Error: " + ioe.getCause());
                    System.exit(1);
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null, "I/O Error", "Error", JOptionPane.ERROR_MESSAGE);
                    SystemLog.log("IO Error!" + ioe.getCause());
                    System.exit(1);
                } catch (Exception ioe) {
                    JOptionPane.showMessageDialog(null, "Unknown Error!\nValue :" + ioe.getMessage() + "\nInfo :" + ioe.getCause() + "\nIn class: " + ioe.getClass() + "\nStack Trace: " + ioe.getStackTrace() + "\n@ line" + ioe.getStackTrace()[1].getLineNumber(), "Error", JOptionPane.ERROR_MESSAGE);
                    if (ioe instanceof NullPointerException) {
                        SystemLog.log("Failed because of null pointer exception");
                    }
                    SystemLog.log("Exception: " + ioe.getClass());
                    ioe.printStackTrace();
                    System.exit(1);
                }finally {
                    System.gc();
                }
            }
            SystemLog.log("Game start");
            System.out.println("Game start!!!!!!!! YAY!!!!!!!!");
            new ControlUnit();   
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        //Screw this...
        //termsAndConditions tmcds = new termsAndConditions();
        new WindowMain();
    }
    
    private void setLookAndFeel (){
        try {
            SystemLog.log("Set look and feel");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            //Do nothing
            System.err.println("Unable to set look and feel");
            SystemLog.log("Unable to set look and feel: " + e.getMessage());
        }
    }
    
    //Window listeners

    //Window in the front resume.
    @Override
    public void windowActivated(WindowEvent e) {
        System.out.println("Zyun.Lam.Game.MonsterQuest.WindowMain.windowActivated()");
        SystemLog.log("Zyun.Lam.Game.MonsterQuest.WindowMain.windowActivated()");
    }

    @Override
    public void windowClosed(WindowEvent e) {  
        System.out.println("Zyun.Lam.Game.MonsterQuest.WindowMain.windowClosed()");
        SystemLog.log("Zyun.Lam.Game.MonsterQuest.WindowMain.windowClosed()");
        //The files were already closed, so no need th closed
        System.out.println("Exiting program...");
        System.exit(0);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //Prevent close window, and ask user for verfication
        System.out.println("Zyun.Lam.Game.MonsterQuest.WindowMain.windowClosing()");
        SystemLog.log("Zyun.Lam.Game.MonsterQuest.WindowMain.windowClosing()");
        setVisible(false);
        SystemLog.log("Closing Program");
        SystemLog.log("Run finishing: closing streams...");
        window.dispose();
    }
    //Pause game. The user may be on the toilet or something
    @Override
    public void windowDeactivated(WindowEvent e) {
        System.out.println("Zyun.Lam.Game.MonsterQuest.WindowMain.windowDeactivated()");
        SystemLog.log("Zyun.Lam.Game.MonsterQuest.WindowMain.windowDeactivated()");
    }

    //Continue, unless the user has already paused it
    @Override
    public void windowDeiconified(WindowEvent e) {
        System.out.println("Zyun.Lam.Game.MonsterQuest.WindowMain.windowDeiconified()");
        SystemLog.log("Zyun.Lam.Game.MonsterQuest.WindowMain.windowDeiconified()");
    }

    //Pause game.
    @Override
    public void windowIconified(WindowEvent e) {
        System.out.println("Zyun.Lam.Game.MonsterQuest.WindowMain.windowIconified()");
        SystemLog.log("Zyun.Lam.Game.MonsterQuest.WindowMain.windowIconified()");
    }

    //When window is opened. What should we do. Find on use for it.
    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("Zyun.Lam.Game.MonsterQuest.WindowMain.windowOpened()");
        SystemLog.log("Zyun.Lam.Game.MonsterQuest.WindowMain.windowOpened()");
    }
    
    /**
     * For repainting the window. Might do a separate one for the game, because it doesn't have a way to measure time. 
     * THis one is just for the menu parts.
     */
    public static void frameRepaint () {
        window.repaint();
    }
}
