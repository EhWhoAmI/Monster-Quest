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
package MonsterQuest.game.maps;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Zyun
 */
public class MapProcesser extends JPanel{
    int mapID;
    //All the tilemaps
    
    NewbiesTownCenter newbiesTownCenter_ID_0x0000 = new NewbiesTownCenter();
    public MapProcesser() {
        super();
        setLayout(null);
        
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Just for testing, will remove later
        newbiesTownCenter_ID_0x0000.printMap(g);
    }
    
    static void loadTilemaps () {
        
    }
    
}
