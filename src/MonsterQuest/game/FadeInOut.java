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
package MonsterQuest.game;

import MonsterQuest.MonsterQuestMain;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 * This class was meant to let the screen fade to black, in that time you will process whatever you need, then make it show whatever that is underneath.
 * @deprecated INDEV
 * @author Zyun
 */
public class FadeInOut extends JPanel{
    int alpha = 0;
    @Override
    protected void paintComponent(Graphics g) {
        Color blackColor = new Color(0, 0, 0, alpha);
        Rectangle2D.Float rect = new Rectangle2D.Float(0, 0, MonsterQuestMain.MonsterQuestWindow.getWidth(), MonsterQuestMain.MonsterQuestWindow.getHeight());
        Graphics2D g2d = (Graphics2D) g;
        g2d.fill(rect);
    }
    
    /**
     * This is in charge of fading the fader in (make it appear)
     */
    public void fadeIn () {
        for (alpha = 0; alpha < 255; alpha ++) {
            try {Thread.sleep(2);}catch(InterruptedException ie){}
            this.repaint();
        }
    }
    
    /**
     * This is in charge of making the fader fade out (make it dissappear)
     */
    public void fadeOut () {
        for (alpha = 255; alpha > 0; alpha --) {
            try {Thread.sleep(2);}catch(InterruptedException ie){}
            this.repaint();
        }
    }
}
