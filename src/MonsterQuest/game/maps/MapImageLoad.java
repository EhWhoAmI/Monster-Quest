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
    private MapRenderer renderer;
    private BufferedImage currentImage;
    private String mapPath;
    
    public MapImageLoad (Map map) {
        this.map = map;
        renderer = createRenderer(map);
        createImage();
        
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
    
    private void createImage () {
        //System.out.println("Map dimensions: (" + map.getHeight() * map.getTileHeight() + ", " + map.getHeight() * map.getTileWidth()+")");
        currentImage = new BufferedImage(map.getWidth() * map.getTileWidth(), map.getHeight() * map.getTileHeight(), BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g2d = currentImage.createGraphics();
        
        final Rectangle clip = new Rectangle(currentImage.getWidth(), currentImage.getHeight());
        g2d.setClip(clip);
        // Draw a gray background
        g2d.setPaint(Color.white);
        //g2d.fill(clip);
        
        // Draw each tile map layer
        for (MapLayer layer : map) {
            if (layer instanceof TileLayer) {
                renderer.paintTileLayer(g2d, (TileLayer) layer);
            }
        }
        try {
            //Place image into a file
            //Get name and ID of map
            Properties imageProperties = map.getProperties();
            String ID = imageProperties.getProperty("ID");
            String name = imageProperties.getProperty("name");
            
            mapPath = "/test.png";///src/MonsterQuest/resources/maps/temp_" + name + "_" + ID + ".png";
            File imageFile = new File (System.getProperty("user.dir") + mapPath);
            systemLog.log("Loading image to " + mapPath);
            ImageIO.write(currentImage, "gif", imageFile);
            imageFile.deleteOnExit();
        } catch (IOException ex) {
            systemLog.log("Unable to open image!" + ex.getMessage(), Logging.ERROR, ex);
        }
    }
    
    public String getImagePath () {
        return mapPath;
    }
}
