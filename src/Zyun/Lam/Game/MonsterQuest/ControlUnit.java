package Zyun.Lam.Game.MonsterQuest;
/**
 * This class controls all the game physics. It also loads all the files.
 * @author Zyun
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import javax.swing.JOptionPane;
import nu.xom.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class ControlUnit implements MouseListener, KeyListener{
    //Remember to turn this into false when testing is over
    final public static boolean DEBUG = true;
    static boolean startScreenSplash = true;
    static boolean loadingfinished = true;
    static boolean startScreen = false;
    static boolean othersScreen = false;
    static boolean credits = false;
    static boolean tutorial = false;
    public static HashMap imageresourcesMap;
    public static HashMap textFileResourceMap;
    static StringBuilder playerNameBuilder;
    //A few constants...
    final static int NAME_MAX_SIZE = 16;
    final static boolean BOY_GENDER = true;
    final static boolean GIRL_GENDER = false;
    //IMPORTANT: player stats: level, name and stuff
    public static String playerName;
    public static boolean playerGender; //true is boy, false is girl
    public ControlUnit() {
        boolean loading = false;
        WindowMain thing = new WindowMain(0);
        WindowMain.window.addMouseListener(this);
        WindowMain.window.addKeyListener(this);
        ///Loading part
        System.out.println("Window height: " + WindowMain.window.getHeight());
        File resources = new File (WindowMain.user_dir + "/resources/resources.xml");
        if (!resources.exists()){
            JOptionPane.showMessageDialog(thing, "Resources does not exist! Program must Exit", "File not found", JOptionPane.ERROR_MESSAGE);
            SystemLog.log("Resources does not exist");
            System.exit(1);
        }
        
        try {
            Builder files = new Builder ();
            Document fileDocument = files.build(resources);
            
            //Get root element
            Element root = fileDocument.getRootElement();
            Elements eleme = root.getChildElements();
            BasicAnimation.percnt ++;
            WindowMain.frameRepaint();
            //Sort out the different types of elements for the sake of the hash map
            //Variables
            int textFiles = 0;
            int images = 0;
            for (int i = 1; i < eleme.size(); i++) {
                //TODO...
                Element toLoad = eleme.get(i);
                Attribute type = toLoad.getAttribute("type");
                if (type.getValue().equals("text-file")) {
                    textFiles ++;
                }
                else if (type.getValue().equals("image")){
                    images ++;
                }
            }
            
            BasicAnimation.percnt ++;
            WindowMain.frameRepaint();
            //Initiliaze hash maps
            SystemLog.log("Files: " + eleme.size());
            System.out.println("Files: " + eleme.size());
            SystemLog.log("Text files: " + textFiles);
            System.out.println("Text files: " + textFiles);
            SystemLog.log("Images: " + images);
            System.out.println("Images: " + images);
            //We will have a lot of these, so...
            imageresourcesMap = new HashMap(images, 0.75F);
            //We dont have much of these, so...
            textFileResourceMap = new HashMap(textFiles, 1);
            for (int i = 1; i < eleme.size(); i++) {
                //TODO...
                Element toLoad = eleme.get(i);
                Attribute type = toLoad.getAttribute("type");
                Attribute name = toLoad.getAttribute("name");
                if (toLoad == null) {
                    System.out.println("The element " + i + " is empty");
                }  else {
                    //Get first child element
                    Text thingy = (Text) toLoad.getChild(0);
                    String toAdd = thingy.toString();
                    if (name.toString().equals("text-file")) {
                        
                    } if (name.toString().equals("image"))
                        imageresourcesMap.put(name, toAdd);
                }
            }
        } catch (ParsingException e) {
            JOptionPane.showMessageDialog(thing, "Parsing error!\nReason: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            SystemLog.log("Parsing error! " + e.getLocalizedMessage());
            System.exit(1);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(thing, "I/O Error!\nReason" + e.getMessage(), "I/O Error", JOptionPane.ERROR_MESSAGE);
            SystemLog.log("IO Error: " + e.getLocalizedMessage());
            System.exit(1);
        }
        while (!loading) {
            WindowMain.frameRepaint();
            
            BasicAnimation.percnt++;
            if (BasicAnimation.percnt > 100) {
                loading = true;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {}
        }
        System.gc();
        try {
            Thread.sleep(1000);
        }catch (InterruptedException io){}
        WindowMain.frameRepaint();
        //Start screen open...
        loadingfinished = true; 
        startScreen = true;
        
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Zyun.Lam.Game.MonsterQuest.ControlUnit.mouseClicked(), @ X " + e.getX() + " Y " + e.getY());
        //If mouse pressed is within button.
        SystemLog.log("Zyun.Lam.Game.MonsterQuest.ControlUnit.mouseClicked(), @ X " + e.getX() + " Y " + e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //Resume game if paused, because mouse exited.
        System.out.println("Zyun.Lam.Game.MonsterQuest.ControlUnit.mouseEntered()");
        SystemLog.log("Zyun.Lam.Game.MonsterQuest.ControlUnit.mouseEntered()");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //Pause game...
        System.out.println("Zyun.Lam.Game.MonsterQuest.ControlUnit.mouseExited()");
        SystemLog.log("Zyun.Lam.Game.MonsterQuest.ControlUnit.mouseExited()");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Zyun.Lam.Game.MonsterQuest.ControlUnit.mousePressed()");
        SystemLog.log("Zyun.Lam.Game.MonsterQuest.ControlUnit.mousePressed()");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Zyun.Lam.Game.MonsterQuest.ControlUnit.mouseReleased()");
        SystemLog.log("Zyun.Lam.Game.MonsterQuest.ControlUnit.mouseReleased()");
        if (startScreen) {
            if ((e.getX() > 1000 & e.getX() < 1200 ) & (e.getY() > 200 & e.getY() < 330)) {
                System.out.println("Zyun.Lam.Game.MonsterQuest.ControlUnit.mouseReleased(), clicked button \"Start Game\"");
                //Start game
                SystemLog.log("Start game button pressed -- Start game");
                tutorial = true;
                startScreen = false;
                
                WindowMain.frameRepaint();
            }
            if ((e.getX() > 1000 & e.getX() < 1200 ) & (e.getY() > 375 & e.getY() < 480)) {
                //Show Options
                System.out.println("Zyun.Lam.Game.MonsterQuest.ControlUnit.mouseClicked(), clicked Option button");
                SystemLog.log("Clicked option button");
                
            }
            if ((e.getX() > 1000 & e.getX() < 1200 ) & (e.getY() > 525 & e.getY() < 630)) {
                System.out.println("Zyun.Lam.Game.MonsterQuest.ControlUnit.mouseClicked(), clicked others button");
                SystemLog.log("Clicked others button");
                othersScreen = true;
                startScreen = false;
                WindowMain.frameRepaint();
           }    
           
        }
        if (othersScreen) {
            //if pressed credits button
            if (((e.getX() > 585) & (e.getX() < 890)) & (e.getY() > 437 & e.getY() < 580)) {
                System.out.println("Zyun.Lam.Game.MonsterQuest.ControlUnit.mouseReleased() -- Pressed credits");
                SystemLog.log("Pressed credits");
                credits = true;
                othersScreen = false;
                startScreen = false;
                
                WindowMain.frameRepaint();
                //Wait till enter pressed
                //TODO
            }
            if (((e.getX() > 1072) & (e.getX() < 1117)) & ((e.getY() > 27) & (e.getY() < 95))) {
                System.out.println("Pressed exit button");
                SystemLog.log("Pressed exit button");
                othersScreen = false;
                startScreen = true;
                
                WindowMain.frameRepaint();
            }
        }
        if (gamePart1__Tutorial.genderChoose) {
            if ((e.getX() > 378 & e.getX() < 530 ) & (e.getY() > 198 & e.getY() < 273)) {
                //Pressed boy button
                System.out.println("Pressed boy button");
                SystemLog.log ("The player is a boy");
                gamePart1__Tutorial.genderChoose = false;
                ControlUnit.tutorial = true;
                gamePart1__Tutorial.wordToShow++;
                WindowMain.frameRepaint();
            }
            if ((e.getX() > 598 & e.getX() <  748) & (e.getY() > 198 & e.getY() < 273)) {
                //Pressed girl button
                System.out.println("Pressed girl button");
                SystemLog.log("The player is a girl");
                gamePart1__Tutorial.genderChoose = false;
                ControlUnit.tutorial = true;
                gamePart1__Tutorial.wordToShow++;
                WindowMain.frameRepaint();
                
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Audio every time the mouse is pressed.
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Zyun.Lam.Game.MonsterQuest.ControlUnit.keyReleased() Key pressed: " + e.getKeyChar() + " ASCII: " + e.getExtendedKeyCode());
        if (e.getKeyChar() == '\n')
            SystemLog.log("Key typed ASCII: " + e.getExtendedKeyCode());
        else 
            SystemLog.log("Key Pressed: " + e.getKeyChar() + " ASCII: " + e.getExtendedKeyCode());
        //Deal with all the commands
        if (credits) {
            credits = false;
            othersScreen = true;
            System.out.println ("Getting out of credits");
        }
        if (tutorial) {
            System.out.println("Tutorial is true");
            if (e.getKeyChar() == 32) {
                System.out.println("Pressed space character");
                //Deal with showing next text...
                //Do we need a try...catch thing to stop it?
                if (gamePart1__Tutorial.wordToShow < gamePart1__Tutorial.textsStrings.length) {
                    try {
                        if (gamePart1__Tutorial.textsStrings[gamePart1__Tutorial.wordToShow].equals("What is your name?") | gamePart1__Tutorial.wordToShow == 10){
                            //get name.
                            gamePart1__Tutorial.nameWrite = true;
                            tutorial = false;
                            //Clear the string builder, as in tests, there is always an extra space
                            playerNameBuilder = new StringBuilder();
                            System.out.println("Getting player name");
                        }
                        gamePart1__Tutorial.wordToShow++;
                        WindowMain.frameRepaint();
                        System.out.println("Villager next text: value, " + gamePart1__Tutorial.textsStrings[gamePart1__Tutorial.wordToShow]);
                        SystemLog.log("Villager next text: value, " + gamePart1__Tutorial.textsStrings[gamePart1__Tutorial.wordToShow]);
                    } catch (ArrayIndexOutOfBoundsException aioobe) {
                         //Exit...
                         //The text has finished
                         System.out.println("Text for villager talk finished");
                         //Do the character choose bit...
                         gamePart1__Tutorial.characterChoose = true;
                         tutorial = false;
                         WindowMain.frameRepaint();
                    }
                }
            }
        }    
        else if (gamePart1__Tutorial.nameWrite) { 
            System.out.println("Getting Player Name");
            if (playerNameBuilder.length() <= NAME_MAX_SIZE) {
                System.out.println("Write to player name");
                if (e.getKeyChar() == '\n') {
                   //Exit this place
                   //Save name
                    System.out.println("Enter pressed: exit");
                   playerName = playerNameBuilder.toString();
                   //Clear up things...
                   playerNameBuilder = null;
                   //Exit place
                   gamePart1__Tutorial.nameWrite = false;
                   gamePart1__Tutorial.wordToShow ++;
                   gamePart1__Tutorial.genderChoose = true;
                   //WILL DO GENDER PART SOON...
                   WindowMain.frameRepaint();
                }
                else {
                   if (e.getKeyChar() == 8 | e.getKeyChar() == 127) {
                       //If it is, delete
                       int ln = playerNameBuilder.length();
                       ln--;
                       System.out.println("Deleting character " + playerNameBuilder.charAt(ln) + "Which has ASCII of " + e.getKeyCode());
                       playerNameBuilder.deleteCharAt(ln);
                       WindowMain.frameRepaint();
                    } 
                    else {
                        int keyChar = e.getExtendedKeyCode();
                        if(!(keyChar >= 0 & keyChar <= 32 | keyChar == 127)){
                            System.out.println("Appending character " + e.getKeyChar() + " to player name , which has ASCII of " + e.getKeyCode() );
                            playerNameBuilder.append(e.getKeyChar());
                            WindowMain.frameRepaint();
                             System.out.println("Name length: " + playerNameBuilder.length());
                        } else {
                           //Do nothing
                            System.out.println("Control character pressed: ASCII of " + e.getKeyCode());
                        }
                    }
                }
            }
            else {
                //Allow deletion, but do not allow anything elseif (e.getKeyChar() == 8 | e.getKeyChar() == 127) {
               //If it is delete key, delete
               int ln = playerNameBuilder.length();
               ln--;
               System.out.println("Deleting character " + playerNameBuilder.charAt(ln) + "Which has ASCII of " + e.getKeyCode());
               //Delete the thing...
               playerNameBuilder.deleteCharAt(ln);
               WindowMain.frameRepaint(); 
            }
        }
        //Debug keys
        if (DEBUG) {
            if (e.getExtendedKeyCode() == 123) {
                    //Exit game...
                    WindowMain.window.dispose();
            }
        }
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
    
}