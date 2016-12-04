package Zyun.Lam.Game.MonsterQuest;
/**
 * This class controls all the game physics. It also loads all the files.
 * @author Zyun
 */
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import nu.xom.*;

public class ControlUnit implements MouseListener {
    static boolean loadingfinished = false;
    static boolean startScreen = false;
    static boolean othersScreen = false;
    static boolean credits = false;
    public ControlUnit() {
        boolean loading = false;
        WindowMain thing = new WindowMain(0);
        WindowMain.window.addMouseListener(this);
        ///Loading part
        System.out.println("Window height: " + WindowMain.window.getHeight());
        File resources = new File ("D:\\Zyun's Coding\\My Coding\\Monster Quest\\src\\Zyun\\Lam\\Game\\MonsterQuest\\resources\\resources.xml");
        if (!resources.exists()){
            JOptionPane.showMessageDialog(thing, "Resources does not exist! Program must Exit", "File not found", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        
        try {
            Builder files = new Builder ();
            Document fileDocument = files.build(resources);
            //Get root element
            
            Element root = fileDocument.getRootElement();
            Elements eleme = root.getChildElements();
            
            for (int i = 1; i < eleme.size(); i++) {
                
            }
        } catch (ParsingException e) {
            JOptionPane.showMessageDialog(thing, "Parsing error!\nReason: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(thing, "I/O Error!\nReason" + e.getMessage(), "I/O Error", JOptionPane.ERROR_MESSAGE);
        }
        while (!loading) {
            WindowMain.frameRepaint();
            //Load files
            //File resources = new File ("D:\\Zyun's Coding\\My Coding\\Monster Quest\\src\\Zyun\\Lam\\Game\\MonsterQuest\\resources\\resources.xml");
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
        loadingfinished = true; 
        startScreen = true;
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Zyun.Lam.Game.MonsterQuest.ControlUnit.mouseClicked(), @ X " + e.getX() + " Y " + e.getY());
        //If mouse pressed is within button.
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Zyun.Lam.Game.MonsterQuest.ControlUnit.mouseEntered()");
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("Zyun.Lam.Game.MonsterQuest.ControlUnit.mouseExited()");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Zyun.Lam.Game.MonsterQuest.ControlUnit.mousePressed()");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Zyun.Lam.Game.MonsterQuest.ControlUnit.mouseReleased()");
        if (startScreen) {
            if ((e.getX() > 1000 & e.getX() < 1200 ) & (e.getY() > 200 & e.getY() < 330)) {
                System.out.println("Zyun.Lam.Game.MonsterQuest.ControlUnit.mouseReleased(), clicked button 1");
                //Start game
                
            }
            if ((e.getX() > 1000 & e.getX() < 1200 ) & (e.getY() > 375 & e.getY() < 480)) {
                System.out.println("Zyun.Lam.Game.MonsterQuest.ControlUnit.mouseClicked(), clicked button 2");
            }
           if ((e.getX() > 1000 & e.getX() < 1200 ) & (e.getY() > 525 & e.getY() < 630)) {
                System.out.println("Zyun.Lam.Game.MonsterQuest.ControlUnit.mouseClicked(), clicked button 3");
                othersScreen = true;
                startScreen = false;
                WindowMain.frameRepaint();
           }    
        }
        if (othersScreen) {
            //if pressed credits button
            if (((e.getX() > 585) & (e.getX() < 890)) & (e.getY() > 437 & e.getY() < 580)) {
                System.out.println("Zyun.Lam.Game.MonsterQuest.ControlUnit.mouseReleased() -- Pressed credits");
                credits = true;
                othersScreen = false;
                startScreen = false;
                WindowMain.frameRepaint();
            }
            if (((e.getX() > 1072) & (e.getX() < 1117)) & ((e.getY() > 27) & (e.getY() < 95))) {
                System.out.println("Pressed exit button");
                othersScreen = false;
                startScreen = true;
                WindowMain.frameRepaint();
            }
        }
    }
}