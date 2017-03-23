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
package MonsterQuest.gui.start;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import MonsterQuest.MonsterQuestMain;

/**
 *
 * @author Zyun
 */
public class StartIntroScreen extends JPanel{

    @Override
    protected void paintComponent(Graphics g) {
        //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2d = (Graphics2D) g;
        MonsterQuestMain.systemLog.log("Showing start intro");
        loadImage(g);
        Rectangle2D.Float bg = new Rectangle2D.Float (0, 0, 100, 100);
        g2d.fill(bg);
    }
    
    void loadImage (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        BufferedImage splashScreen = null;
        try {
            //show image
            splashScreen = ImageIO.read(new File (System.getProperty("user.dir") + "/resources/images/start/titlepageSplash.png"));
            g2d.setColor(Color.white);
            Rectangle2D.Float bg = new Rectangle2D.Float (0,0,MonsterQuestMain.MonsterQuestWindow.getWidth(), MonsterQuestMain.MonsterQuestWindow.getHeight());
            g2d.fill(bg);
            g.drawImage(splashScreen, 250, 0, null);
            MonsterQuestMain.systemLog.log("Just showed image.");
        } catch (IOException e) {
            MonsterQuestMain.systemLog.log("Oh no! Unable to open file!" + e.getMessage());
        }
    }
}
