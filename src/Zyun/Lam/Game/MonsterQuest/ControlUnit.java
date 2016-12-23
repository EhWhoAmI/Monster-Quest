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
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import nu.xom.*;

public class ControlUnit implements MouseListener, KeyListener{
    static boolean loadingfinished = false;
    static boolean startScreen = false;
    static boolean othersScreen = false;
    static boolean credits = false;
    static boolean tutorial = false;
    public static HashMap imageresourcesMap;
    public static HashMap textFileResourceMap;
    public ControlUnit() {
        boolean loading = false;
        WindowMain thing = new WindowMain(0);
        WindowMain.window.addMouseListener(this);
        WindowMain.window.addKeyListener(this);
        ///Loading part
        System.out.println("Window height: " + WindowMain.window.getHeight());
        File resources = new File ("D:\\Zyun's Coding\\My Coding\\Monster Quest\\src\\Zyun\\Lam\\Game\\MonsterQuest\\resources\\resources.xml");
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
                if (type.getValue().equals("text-file")) {
                    textFileResourceMap.put(name, toLoad.getValue());
                }
                else if (type.getValue().equals("image")){
                    imageresourcesMap.put(name, toLoad.getValue());
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
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Deal with all the commands
        if (tutorial) {
            if (e.getKeyChar() == 32) {
                System.out.println("Pressed space character");
                //Deal with showing next text...
                //Do we need a try...catch thing to stop it?
                if (gamePart1__Tutorial.wordToShow <= gamePart1__Tutorial.textsStrings.length) {
                    try {
                        gamePart1__Tutorial.wordToShow++;
                        WindowMain.frameRepaint();
                        System.out.println("Next text: value, " + gamePart1__Tutorial.textsStrings[gamePart1__Tutorial.wordToShow]);
                        SystemLog.log("Next text: value, " + gamePart1__Tutorial.textsStrings[gamePart1__Tutorial.wordToShow]);
                    } catch (ArrayIndexOutOfBoundsException aioobe) {
                         //Exit...
                         //The text has finished
                         System.out.println("Text finished");
                    }
                }
            }    
        }
        if (e.getKeyChar() == 'p') {
                //Exit game...
                WindowMain.window.dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
}