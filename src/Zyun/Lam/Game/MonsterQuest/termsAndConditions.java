package Zyun.Lam.Game.MonsterQuest;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class termsAndConditions extends JFrame implements WindowListener, ActionListener{
    private JRadioButton agree;
    private JRadioButton disagree;
    private JButton next;
    private JButton cancel;
    private boolean thing = false;
    private boolean ended = false;
    public termsAndConditions(){
        super("Terms and condition");
        File trmsncnd = new File ("D:\\Zyun's Coding\\My Coding\\Monster Quest\\src\\Zyun\\Lam\\Game\\MonsterQuest\\termsandconditions.txt");
        if (!trmsncnd.exists()) {
            JOptionPane.showMessageDialog(null, "Terms and conditions does not exist!", "Error!", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        StringBuilder trmsBuilder = new StringBuilder();
        try {
            FileInputStream trmscdsFileInputStream = new FileInputStream(trmsncnd);
            boolean eof = false;
            while (!eof) {                
                int input = trmscdsFileInputStream.read();
                trmsBuilder.append((char) input);
                System.out.println("Reading...");
                if (input == -1) {
                    eof = true;
                }
            }
        } catch (FileNotFoundException ioe) {
            JOptionPane.showMessageDialog(null, "Terms and conditions does not exist!", "Error!", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "Terms and conditions does not exist!", "Error!", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        setSize(500, 500);
        lookAnFeel();
        JTextArea textArea = new JTextArea(trmsBuilder.toString());
        JScrollPane jsp = new JScrollPane(textArea);
        agree = new JRadioButton("I Agree", false);
        disagree  = new JRadioButton("I Disagree", true);
        next = new JButton("Next");
        cancel  = new JButton("Cancel");
        agree.addActionListener(this);
        disagree.addActionListener(this);
        cancel.addActionListener(this);
        next.addActionListener(this);
        addWindowListener(this);
        JPanel thingy = new JPanel();
        jsp.setSize(new Dimension(500, 300));
        jsp.add(textArea);
        thingy.add(jsp);
        next.setEnabled(false);
        thingy.add(agree);
        thingy.add(disagree);
        thingy.add(next);
        thingy.add(cancel);
        add(thingy);
        setVisible(true);
        while (!thing) {            
            //wait till the user agrees
            if (thing){
                break;
            }
        }
        ended = true;
        System.out.println("Hello!!!");
    }

    @Override
    public void windowActivated(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e) {
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e){
        System.exit(0);
    }

    @Override
    public void windowOpened(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if (source == agree){
            next.setEnabled(true);
            disagree.setSelected(false);
            agree.setSelected(true);
        }
        if (source == disagree){
            next.setEnabled(false);
            disagree.setSelected(true);
            agree.setSelected(false);
        }
        if (source == next){
            //Allow game
            this.setVisible(false);
            if (!disagree.isSelected()){
                this.dispose();
                thing = true;
            }
        }
        if (source == cancel){
            System.exit(0);
        }
    }
    void lookAnFeel (){
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        } catch (Exception e) {
            System.err.println("Unable to set the look nd feel Reason:" + e.getMessage());
        }
    }
}