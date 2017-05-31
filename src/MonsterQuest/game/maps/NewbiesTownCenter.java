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
import MonsterQuest.util.Logging;
import MonsterQuest.util.tilemapengine.TileMapReader;
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

    /**
     * The ID of the map
     */
    public static final int MAP_HASH = 0x0000;
    //New tilemap reader
    /**
     * Read a new tilemap
     */
    private static TileMapReader ground;
    //Array size: [34][62],
    /**
     * The layout of the map.
     */
    private int[][] list = {
        {2, 0, 2, 2, 0, 0, 1, 0, 2, 1, 0, 2, 0, 2, 0, 2, 0, 0, 2, 0},
        {1, 2, 2, 2, 1, 1, 0, 0, 0, 1, 0, 2, 1, 0, 0, 2, 2, 2, 1, 0},
        {0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 2, 0, 0, 1, 0, 0, 0, 2, 0, 2},
        {0, 1, 2, 2, 1, 2, 1, 2, 1, 2, 2, 0, 0, 2, 0, 1, 2, 2, 0, 1},
        {2, 2, 0, 0, 1, 2, 2, 2, 1, 2, 2, 0, 1, 1, 0, 1, 1, 1, 2, 3},
        {0, 1, 2, 2, 1, 0, 2, 2, 1, 0, 0, 2, 1, 1, 0, 0, 0, 0, 0, 3},
        {0, 1, 0, 2, 0, 0, 2, 2, 1, 2, 1, 2, 1, 0, 2, 0, 1, 1, 0, 1},
        {0, 0, 1, 2, 1, 0, 0, 0, 2, 2, 0, 2, 1, 1, 1, 0, 2, 0, 2, 1},
        {2, 1, 1, 1, 1, 1, 0, 0, 0, 2, 0, 1, 0, 0, 2, 2, 0, 2, 2, 0},
        {1, 0, 1, 1, 1, 2, 1, 2, 1, 0, 2, 0, 2, 2, 2, 2, 1, 0, 2, 2},
        {2, 1, 2, 1, 1, 1, 0, 0, 1, 0, 2, 0, 1, 2, 2, 1, 0, 0, 1, 1},
};


    void loadMap () {
        //Load the maps and stuff
        int index = 0;
        //Create tempoary image
        //These values are the the size of the buffered multiplied by how much they would appear
        BufferedImage temp = new BufferedImage(1360, 748, BufferedImage.TYPE_INT_ARGB); 
        Graphics2D g2d = temp.createGraphics();
        for (int num = 0; num < list.length; num ++) {
            for (int num2 = 0; num2 < list[0].length; num2 ++) {
                //Write image to the graphics object
                g2d.drawImage(MapProcesser.genericGround.getImage(list[num][num2]), (num2*MapProcesser.genericGround.sizeOfEachElement.width), (num*MapProcesser.genericGround.sizeOfEachElement.width), null);
                MonsterQuestMain.systemLog.log("Loading square " + index + " Pos " + num + ", " + num2);
                index ++;
                try {Thread.sleep(1);}catch(InterruptedException ie){}
            }
        }
        MonsterQuestMain.systemLog.log("Loading image into temp file");
        File imageFileName = new File(System.getProperty("user.dir") + "/resources/tilemaps/TEMP/NewbiesTownCenter_temp.png");
        
        try {
            imageFileName.createNewFile();
            imageFileName.deleteOnExit();
            ImageIO.write(temp, "PNG", imageFileName);
        } catch (IOException ex) {
            MonsterQuestMain.systemLog.log("Unable to write to file! " + ex.getMessage(), Logging.ERROR);
        }
    }
            
    //Only one problem: it loads each time the window repaints.

    /**
     *
     * @param g
     */
    public void printMap (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
            //Load the maps and stuff
            //This will be kept for historic purposes only....
            /*
            int index = 0;
            for (int num = 0; num < list.length; num ++) {
            for (int num2 = 0; num2 < list[0].length; num2 ++) {
            g2d.drawImage(MapProcesser.genericGround.getImage(list[num][num2]), (num2*MapProcesser.genericGround.sizeOfEachElement.width), (num*MapProcesser.genericGround.sizeOfEachElement.width), null);
            MonsterQuestMain.systemLog.log("Loading square " + index + " Pos " + num + ", " + num2);
            index ++;
            try {Thread.sleep(1);}catch(InterruptedException ie){}
            }
            }
            */
        BufferedImage imagetemp;
        try {
            MonsterQuestMain.systemLog.log("Loading the file onto screen");
            //Draw the temp image
            imagetemp = ImageIO.read(new File (System.getProperty("user.dir") + "/resources/tilemaps/TEMP/NewbiesTownCenter_temp.png"));
            g2d.drawImage(imagetemp, 0, 0, null);
        } catch (IOException ex) {
            MonsterQuestMain.systemLog.log("Unable to open file!", Logging.ERROR);
        }
        
    }
    
    /**
     *
     */
    public NewbiesTownCenter() {
        super();
        loadMap();
    }
    
    
}
