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
package MonsterQuest.tools;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FilenameUtils;

/**
 * The map generator class, Generates a map file for the game.
 * @author Zyun
 */
public class MapGenerator extends JFrame{

    public MapGenerator() {
        super("MQM Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1366, 768);
        setVisible(true);
        selectionMenu();
    }
    
    public static void main(String[] args) {
        new MapGenerator();
    }

    private void selectionMenu () {
        Object[] options = { "Open File", "New File" };
        int returnValue;
        do {
            returnValue = JOptionPane.showOptionDialog(this, "What would you like to do?", "Action you want to do",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION,
                    null, options, options[0]);
            System.out.println(returnValue);
        }while (returnValue == -1);
        
        switch (returnValue) {
            case 0:
                System.out.println("Open File");
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Open File");
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        ".mqm files", "mqm");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(this);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    //Check if it really is the file type:
                    File selection = chooser.getSelectedFile();
                    String extension = FilenameUtils.getExtension(selection.getPath());
                    if (!extension.equals(".mqm")) {
                        //If it is not .mqm extension,
                        JOptionPane.showMessageDialog(this, "Please select a .mqm file.", "File selection error", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }
                    else {
                        //Read file and dsplay.
                    }
                }
                break;
            case 1:
                
        }
    }
}