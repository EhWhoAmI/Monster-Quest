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
import com.sun.org.apache.bcel.internal.generic.FCMPG;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;

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
    private MapRenderer renderer;
    private BufferedImage currentImage;
    private String mapPath;
    
    public MapImageLoad(Map map) {
        this.map = map;
        
        //Create renderer
        renderer = createRenderer(map);
    }

    public MapImageLoad(Map map, String mapPath) {
        this(map);
        
        this.mapPath = mapPath;
    }
    
    private static MapRenderer createRenderer(Map map) {
        switch (map.getOrientation()) {
            //I've a feeling there're more, but they didn't put it on.
            case ORTHOGONAL:
                return new OrthogonalRenderer(map);

            case ISOMETRIC:
                return new IsometricRenderer(map);
             
            default:
                return null;
                
        }
    }
    
    public void createMapImage() {
        try {
            //Create the image
            int mapWidth = map.getWidth() * map.getTileWidth();
            int mapHeight = map.getHeight() * map.getTileHeight();
            Dimension mapSize = new Dimension(mapWidth, mapHeight);
            systemLog.log("Map dimensions = " + mapSize.toString());
            BufferedImage imageToWrite = new BufferedImage(mapSize.width, mapSize.height, BufferedImage.TYPE_INT_RGB);
            
            //Create graphics to write on image.
            Graphics2D g2d = imageToWrite.createGraphics();
            
            //Create and set Clip
            Rectangle clip = new Rectangle(mapSize);
            g2d.setClip(clip);
            
            //Paint layers.
            for (MapLayer layer : map) {
                if (layer instanceof TileLayer)
                    renderer.paintTileLayer(g2d, (TileLayer) layer);
            }
            
            if (mapPath.isEmpty()) {
                //Create temp name.
                //Get property of map name.
                Properties mapProperties = map.getProperties();
                String mapName = mapProperties.getProperty("name");
                if (mapName == null) {
                    //Just use the filename, and change it a bit
                    
                }
            }
            File toWriteFile = new File(mapPath);
            ImageIO.write(imageToWrite, mapPath, output);
        } catch (Exception e) {
        }
    }
     
    public String getImagePath() {
        return mapPath;
    }
    
    private String createTempFilename (String filename) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(filename, File.separator);
        
        StringBuilder path = new StringBuilder();
        path.append(System.getProperty("user.dir"));
        ///Get the full path of the thingy, and create a temp folder
        while (tokenizer.hasMoreTokens()) {
            String nextElement = tokenizer.nextToken();
            //If it is a file, and it is the last one, abort or else...
            if (!(nextElement.contains(".") & tokenizer.hasMoreTokens())) {
                path.append(File.separator);
                path.append(nextElement);
            }
        }
        //Then add a TEMP folder
        path.append(File.separator + "TEMP" + File.separator);
        File TEMPfolder = new File(path.toString());
        
        //Debugging
        if (!TEMPfolder.isDirectory()) {
            systemLog.log("Oh no! The Filename is not a directory", Logging.ERROR);
            throw new IOException("File path is not a dir");
        } 
        else {
            TEMPfolder.mkdir();
            
        }
        
    }
}
