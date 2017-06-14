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
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.imageio.ImageIO;
import tiled.core.Map;
import tiled.core.MapLayer;
import tiled.core.TileLayer;
import tiled.view.IsometricRenderer;
import tiled.view.MapRenderer;
import tiled.view.OrthogonalRenderer;


/**
 *
 * @author Zyun
 */
public class MapImageLoad {
    private Map map;
    private String mapImagePath;
            
    public MapImageLoad(Map map) {
        this.map = map;
        loadMap();
    }
    
    private void loadMap () {
        //Load maps and stuff
        int index = 0;
        
        //Create tempoary image
        BufferedImage temp = new BufferedImage(1360, 748, BufferedImage.TYPE_INT_ARGB);
        
        //Create Graphics object to draw image
        Graphics2D g2d = temp.createGraphics();
        
        //To show the map
        MapRenderer renderer = createRenderer(map);
        g2d.setClip(0, 0, 1360, 748);
        //Draw on the image
        //Draw each tile map layer
        for (MapLayer layer : map) {
            if (layer instanceof TileLayer) {
                renderer.paintTileLayer(g2d, (TileLayer) layer);
            }
        }
        
        //Before placing the temp image, check whether the {user.dir}/resources/TEMP dir exists
        File TEMPdir = new File(System.getProperty("user.dir") + "/resources/TEMP");
        if (TEMPdir.mkdir()) {
            systemLog.log("Made a dir...");
        }
        //Get the name and id of the map
        Properties mapProperties = map.getProperties();
        String mapID = mapProperties.getProperty("ID");
        String mapName = mapProperties.getProperty("name");
        File imageFileName = new File(System.getProperty("user.dir") + "/resources/TEMP/" + mapID + "_" + mapName + "_temp.png");
        mapImagePath = imageFileName.getPath();
        
        systemLog.log("The path of the map image is: " + mapImagePath);
        try {
            imageFileName.createNewFile();
            imageFileName.deleteOnExit();
            ImageIO.write(temp, "PNG", imageFileName);
        } catch (IOException ex) {
            systemLog.log("Unable to write to file! " + ex.getMessage(), Logging.ERROR, ex);
        }
    }
    
    public String getMapImagePath () {
        return mapImagePath;
    }
    
    private static MapRenderer createRenderer(Map map) {
        switch (map.getOrientation()) {
            case ORTHOGONAL:
                return new OrthogonalRenderer(map);

            case ISOMETRIC:
                return new IsometricRenderer(map);

            default:
                return null;
        }
    }
}
