/*
 * The MIT License
 *
 * Copyright 2017 Lam Zyun.
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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;
import tiled.core.Map;
import tiled.core.MapLayer;
import tiled.core.TileLayer;
import tiled.view.IsometricRenderer;
import tiled.view.MapRenderer;
import tiled.view.OrthogonalRenderer;

/**
 *
 * @author Lam Zyun
 */
public class MapView extends JPanel implements Scrollable
{
    private Map map;
    private MapRenderer renderer;
    private BufferedImage currentImage;
    private String mapImagePath;
    public MapView(Map map) {
        this.map = map;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(800, 600));
        renderer = createRenderer(map);
        createImage();
        setPreferredSize(renderer.getMapSize());
        setOpaque(true);
        
    }

    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        final Graphics2D g2d = (Graphics2D) g.create();
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        try {
            img = ImageIO.read(new File(System.getProperty("user.dir") + "/image.png"));
        } catch (IOException ex) {
            Logger.getLogger(MapView.class.getName()).log(Level.SEVERE, null, ex);
        }
        g2d.drawImage(img, 0, 0, null);
        
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

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return getPreferredSize();
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect,
                                          int orientation, int direction) {
        if (orientation == SwingConstants.HORIZONTAL)
            return map.getTileWidth();
        else
            return map.getTileHeight();
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect,
                                           int orientation, int direction) {
        if (orientation == SwingConstants.HORIZONTAL) {
            final int tileWidth = map.getTileWidth();
            return (visibleRect.width / tileWidth - 1) * tileWidth;
        } else {
            final int tileHeight = map.getTileHeight();
            return (visibleRect.height / tileHeight - 1) * tileHeight;
        }
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }
    
    private void createImage () {
        System.out.println("Map dimensions: (" + map.getHeight() * map.getTileHeight() + ", " + map.getWidth()* map.getTileWidth()+")");
        currentImage = new BufferedImage(map.getWidth() * map.getTileWidth(), map.getHeight() * map.getTileHeight(), BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g2d = currentImage.createGraphics();
        
        final Rectangle clip = new Rectangle(currentImage.getWidth(), currentImage.getHeight());
        g2d.setClip(clip);
        // Draw a gray background
        g2d.setPaint(new Color(100, 100, 100));
        //g2d.fill(clip);
        
        // Draw each tile map layer
        for (MapLayer layer : map) {
            if (layer instanceof TileLayer) {
                renderer.paintTileLayer(g2d, (TileLayer) layer);
            }
        }
        try {
            Properties imageProperties = map.getProperties();
            String ID = imageProperties.getProperty("ID");
            String name = imageProperties.getProperty("name");
            
            mapImagePath = "/src/MonsterQuest/resources/maps/temp_" + name + "_" + ID + ".png";
            File imageFile = new File (System.getProperty("user.dir") + mapImagePath);
            systemLog.log("Loading image to " + mapImagePath);
            ImageIO.write(currentImage, "gif", imageFile);
            imageFile.deleteOnExit();
        } catch (IOException ex) {
            Logger.getLogger(MapView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getMapImagePath () {
        return mapImagePath;
    }
}