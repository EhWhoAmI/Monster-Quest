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

import MonsterQuest.MonsterQuestMain;
import MonsterQuest.util.tilemapengine.TileMapReader;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Zyun
 */
public class NewbiesTownCenter extends JPanel{
    public static final int MAP_HASH = 0x0000;
    //New tilemap reader
    private static TileMapReader ground;
    //Array size: [34][62],
    private int[][] list = {
        {2, 0, 2, 2, 0, 0, 1, 0, 2, 1, 0, 2, 0, 2, 0, 2, 0, 0, 2, 2, 1, 2, 2, 2, 1, 1, 0, 0, 0, 1, 0, 2, 1, 0, 0, 2, 2, 2, 1, 2},
        {0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 2, 0, 0, 1, 0, 0, 0, 2, 0, 1, 0, 1, 2, 2, 1, 2, 1, 2, 1, 2, 2, 0, 0, 2, 0, 1, 2, 2, 0, 0},
        {2, 2, 0, 0, 1, 2, 2, 2, 1, 2, 2, 0, 1, 1, 0, 1, 1, 1, 2, 2, 0, 1, 2, 2, 1, 0, 2, 2, 1, 0, 0, 2, 1, 1, 0, 0, 0, 0, 0, 0},
        {0, 1, 0, 2, 0, 0, 2, 2, 1, 2, 1, 2, 1, 0, 2, 0, 1, 1, 0, 2, 0, 0, 1, 2, 1, 0, 0, 0, 2, 2, 0, 2, 1, 1, 1, 0, 2, 0, 2, 2},
        {2, 1, 1, 1, 1, 1, 0, 0, 0, 2, 0, 1, 0, 0, 2, 2, 0, 2, 2, 0, 1, 0, 1, 1, 1, 2, 1, 2, 1, 0, 2, 0, 2, 2, 2, 2, 1, 0, 2, 0},
        {2, 1, 2, 1, 1, 1, 0, 0, 1, 0, 2, 0, 1, 2, 2, 1, 0, 0, 1, 0, 2, 1, 1, 2, 2, 0, 0, 0, 1, 1, 0, 2, 0, 2, 0, 0, 1, 0, 0, 2},
        {1, 0, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 1, 2, 0, 0, 1, 0, 1, 0, 1, 2, 1, 0, 1, 1, 1, 0, 0, 1, 0, 2, 0, 1, 2, 1, 1, 0, 2, 1},
        {2, 1, 1, 1, 0, 0, 2, 0, 0, 1, 1, 2, 0, 0, 0, 0, 2, 1, 2, 1, 2, 2, 0, 2, 1, 1, 2, 1, 0, 0, 2, 0, 1, 1, 0, 1, 2, 2, 0, 2},
        {2, 1, 0, 2, 2, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 2, 1, 2, 0, 2, 0, 2, 0, 0, 2, 0, 1, 1, 1, 2, 0, 2, 2, 2, 2},
        {0, 2, 0, 0, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 2, 2, 1, 0, 2, 2, 1, 1, 2, 1, 2, 0, 0, 2, 0, 2, 2, 2, 2, 0, 0, 0, 1},
        {1, 0, 0, 2, 1, 0, 2, 2, 0, 2, 1, 2, 2, 0, 0, 1, 0, 0, 1, 0, 0, 2, 0, 1, 1, 2, 0, 0, 0, 2, 1, 1, 2, 1, 1, 0, 1, 2, 1, 0},
        {2, 1, 0, 1, 1, 0, 2, 2, 0, 1, 1, 0, 0, 2, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 2, 0, 0, 1, 2, 2, 0, 2, 2, 2, 0, 2, 0, 2, 0},
        {0, 1, 2, 2, 2, 1, 0, 1, 2, 2, 1, 1, 0, 1, 1, 2, 0, 0, 1, 2, 2, 2, 0, 1, 2, 0, 0, 2, 1, 2, 2, 1, 2, 2, 0, 1, 0, 1, 0, 1},
        {1, 0, 2, 2, 0, 2, 1, 2, 1, 0, 1, 2, 0, 2, 1, 1, 0, 1, 0, 1, 0, 1, 2, 0, 0, 0, 1, 2, 2, 0, 0, 1, 1, 0, 2, 2, 2, 2, 0, 1},
        {0, 2, 0, 2, 1, 2, 0, 0, 2, 1, 1, 1, 2, 2, 0, 2, 0, 1, 2, 2, 1, 1, 2, 2, 1, 1, 1, 0, 0, 1, 0, 1, 2, 1, 2, 2, 0, 0, 1, 2},
        {2, 1, 1, 2, 1, 2, 0, 2, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 2, 1, 0, 2, 1, 1, 1, 2, 2, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 2},
        {2, 2, 1, 0, 1, 2, 0, 1, 1, 2, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 2, 1, 1, 0, 2, 0, 2, 1, 1, 0, 0, 2, 2, 1, 2, 2, 1, 0, 1, 0},
        {0, 2, 0, 0, 1, 0, 1, 1, 2, 0, 0, 1, 0, 2, 1, 1, 2, 1, 2, 1, 2, 0, 1, 0, 1, 0, 1, 2, 1, 2, 0, 0, 2, 1, 0, 0, 1, 0, 2, 0},
        {2, 2, 0, 2, 1, 2, 1, 1, 1, 2, 0, 0, 0, 1, 1, 2, 2, 1, 2, 1, 0, 0, 0, 0, 0, 1, 2, 2, 0, 2, 0, 1, 2, 1, 1, 1, 0, 1, 2, 0},
        {2, 1, 0, 0, 0, 0, 2, 1, 0, 0, 2, 0, 2, 2, 0, 2, 0, 0, 2, 1, 1, 1, 1, 2, 2, 0, 2, 0, 1, 0, 2, 2, 2, 0, 1, 2, 1, 1, 0, 2},
        {1, 0, 1, 2, 2, 0, 2, 1, 2, 1, 2, 0, 0, 2, 2, 1, 0, 2, 1, 2, 1, 1, 1, 1, 2, 2, 0, 0, 1, 0, 2, 2, 0, 1, 1, 0, 0, 1, 1, 0},
        {2, 1, 0, 2, 0, 1, 1, 1, 0, 1, 2, 1, 2, 0, 1, 0, 1, 0, 0, 0, 2, 2, 0, 2, 1, 0, 2, 2, 1, 2, 1, 2, 0, 2, 1, 1, 1, 2, 2, 2},
};


    void loadMap () {
        
    }
            
    public void printMap (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //Load the maps and stuff
        int index = 0;
        for (int num = 0; num < list.length; num ++) {
            for (int num2 = 0; num2 < list[0].length; num2 ++) {
                g2d.drawImage(MapProcesser.genericGround.getImage(list[num][num2]), (num2*MapProcesser.genericGround.sizeOfEachElement.width), (num*MapProcesser.genericGround.sizeOfEachElement.width), null);
                MonsterQuestMain.systemLog.log("Loading square " + index + " Pos " + num + ", " + num2);
                index ++;
                try {Thread.sleep(1);}catch(InterruptedException ie){}
            }
        }
    }
    
    public NewbiesTownCenter() {
        super();
        try {
            File tilemap = new File(System.getProperty("user.dir") + "/resources/tilemaps/things.png");
            //ground = new TileMapReader(ImageIO.read(tilemap), new Dimension(16, 16));      
        }catch (Exception ioe) {}
    }
    
    
}
