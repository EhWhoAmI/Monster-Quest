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
package MonsterQuest.game.UI;

import MonsterQuest.game.painter.Paintable;
import MonsterQuest.util.GraphicsUnit;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
/**
 * This is the implementation for the player details.
 * @author Zyun
 */
@GraphicsUnit
public class PlayerDetails implements Paintable{
    // All the rectangles for the UI
    public static Rectangle2D.Float userAvatar = new Rectangle2D.Float(50, 50, 100, 100);
    public static Rectangle2D.Float hpBar = new Rectangle2D.Float(150, 60, 200, 25); 
    public static Rectangle2D.Float energyBar = new Rectangle2D.Float(150, 100, 200, 25);
    
    @Override
    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.fill(userAvatar);
        g2d.fill(hpBar);
        
        g2d.setColor(Color.BLUE);
        g2d.fill(energyBar);
    }
    
    public static boolean mouseInSelection (int x, int y) {
        return (userAvatar.intersects(x, y, 1, 1) | hpBar.intersects(x, y, 1, 1) | energyBar.intersects(x, y, 1, 1));
    }
}
