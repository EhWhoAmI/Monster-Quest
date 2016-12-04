package Zyun.Lam.Game.MonsterQuest;
//Installation part
//Only executed 1 time, for installing. Will start up the game

import java.awt.FlowLayout;
import java.io.File;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import nu.xom.*;
class install extends JFrame{
    //Environment variables
    String appPosition;
    String operationSystem;
    String javaversion;
    int num = 0;
    JProgressBar loadingBar;
            
    public install() {
        super("Install Monster Quest");
        setSize(300, 150);
        //Get all environment variables
        JLabel jlAlpha = new JLabel("Please wait a few moments while");
        JLabel jlBeta = new JLabel ("we set up a few things up.");
        FlowLayout jLayout = new FlowLayout(FlowLayout.CENTER);
        //if (System.getProperty("java.version").equals(""))
        setLayout(jLayout);
        loadingBar = new JProgressBar(0, 100);
        loadingBar.setValue(0);
        loadingBar.setStringPainted(true);
        add(jlAlpha);
        add(jlBeta);
        add(loadingBar);
        setLookAndFeel();
        
    }
    
    public static void main(String[] args) {
        install ins = new install ();
        ins.setVisible(true);
        ins.iterate();
    }
    
    public void iterate() {
        System.out.println("Zyun.Lam.Game.MonsterQuest.install.iterate()");
        appPosition = System.getProperty("user.dir");
        num++;
        loadingBar.setValue(num);
        System.out.println("Zyun.Lam.Game.MonsterQuest.install.iterate()");
        operationSystem = System.getProperty("os.name");
        num++;
        loadingBar.setValue(num);
        javaversion = System.getProperty("java.version");
        num++;
        loadingBar.setValue(num);
        String stupsttingpos = appPosition + File.separator + "resources" + File.separator + "Startup-Settings";
        num++;
        System.out.println("setupUpSetting position : " + stupsttingpos);
        loadingBar.setValue(num);
        File startUpSettings = new File(stupsttingpos);
        num++;
        loadingBar.setValue(num);
        System.out.println("Zyun.Lam.Game.MonsterQuest.install.<init>() : App position = " + appPosition);
        if (startUpSettings.exists()) {
            //Okay, just overwrite it, and do whatever you want
            try {
                startUpSettings.delete();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Unable to unstall file. Please redownload this software", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
            Element root = new Element ("root");
            num++;
            loadingBar.setValue(num);
            Element systemPropoties = new Element ("Properties");
            num++;
            loadingBar.setValue(num);
            root.appendChild(systemPropoties);
            num++;
            loadingBar.setValue(num);
            Element appPos = new Element ("app-position");
            Text appPosTxt = new Text (appPosition);
            appPos.appendChild (appPosTxt);
            Element osName = new Element ("operation-system");
            Text osNameTxt = new Text (operationSystem);
            osName.appendChild(osNameTxt);
            
    }
    
    private void setLookAndFeel () {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
            System.out.println("Zyun.Lam.Game.MonsterQuest.install.setLookAndFeel()");
        } catch (Exception e) {
            System.err.println("Error: Unable to set look and feel: " + e.getMessage());
        }
    }
    
    private void addPercentage () {
        num++;
        loadingBar.setValue(num);
    }
}