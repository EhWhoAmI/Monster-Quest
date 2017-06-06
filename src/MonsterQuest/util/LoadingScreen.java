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
package MonsterQuest.util;

import MonsterQuest.MonsterQuestMain;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/** This is just a black screen, for loading.
 *
 * @author Zyun
 */
public class LoadingScreen extends JPanel{

    @Override
    protected void paintComponent(Graphics g) {
        //show the loading black screen
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D.Float bg = new Rectangle2D.Float(0, 0, MonsterQuestMain.MonsterQuestWindow.getWidth(), MonsterQuestMain.MonsterQuestWindow.getHeight());
        g2d.setColor(new Color(0, 0, 0));
        g2d.fill(bg);
        g2d.setColor(Color.white);
        g2d.setFont(MonsterQuestMain.pixelFont.deriveFont(40F));
        g2d.drawString("Loading", 25, 650);
    }
    
    
    /**
     * This inits the loading screen
     */
    public LoadingScreen() {
        super();
        setLayout(null);
        setBackground(Color.black);
    }
    
}
