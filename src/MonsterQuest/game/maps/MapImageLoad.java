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

import static MonsterQuest.MonsterQuestMain.systemLog;
import MonsterQuest.util.Logging;
import MonsterQuest.util.tilemapengine.TileMap;
import MonsterQuest.util.tilemapengine.TileMapReader;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 *
 * @author Zyun
 */
public class MapImageLoad {
    private TileMap map;
    private String mapImagePath;
            
    public MapImageLoad(TileMap map) {
        this.map = map;
        loadMap();
    }
    
    private void loadMap () {
        //Load maps and stuff
        int index = 0;
        
        //Array of list of tilemaps to read from
        TileMapReader[] reader = {
            MapProcesser.genericGround
        };
        //Create tempoary image
        BufferedImage temp = new BufferedImage(1360, 748, BufferedImage.TYPE_INT_ARGB);
        
        //Create Graphics object to draw image
        Graphics2D g2d = temp.createGraphics();
        
        //Loop to add layers
        for (int i = 0; i < map.layer1.length; i++) {
            for (int f = 0; f < map.layer1[1].length; f++) {
                g2d.drawImage(MapProcesser.genericGround.getImage(map.layer1[i][f]), (f*MapProcesser.genericGround.sizeOfEachElement.width), (i*MapProcesser.genericGround.sizeOfEachElement.height), null);
                try {Thread.sleep(1);}catch(InterruptedException ie){}
            }   
        }
        
        File imageFileName = new File(System.getProperty("user.dir") + "/resources/tilemaps/TEMP/" + map.getMapName() + ".png");
        mapImagePath = imageFileName.getPath();
        systemLog.log("The path of the map image is: " + mapImagePath);
        try {
            imageFileName.createNewFile();
            imageFileName.deleteOnExit();
            ImageIO.write(temp, "PNG", imageFileName);
        } catch (IOException ex) {
            systemLog.log("Unable to write to file! " + ex.getMessage(), Logging.ERROR);
        }
    }
    
    public String getMapImagePath () {
        return mapImagePath;
    }
}
