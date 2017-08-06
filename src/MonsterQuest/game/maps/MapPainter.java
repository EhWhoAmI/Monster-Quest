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

import MonsterQuest.MonsterQuestMain;
import static MonsterQuest.MonsterQuestMain.systemLog;
import MonsterQuest.game.painter.Paintable;
import MonsterQuest.util.Logging;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.util.List;
import tiled.core.Map;
import tiled.core.MapLayer;
import tiled.core.TileLayer;
import tiled.core.TileSet;
import tiled.io.TMXMapReader;
import tiled.view.IsometricRenderer;
import tiled.view.MapRenderer;
import tiled.view.OrthogonalRenderer;

/**
 *
 * @author Lam Zyun
 */
public class MapPainter implements Paintable{
    private static Map map;
    private static MapRenderer renderer;
    
    @Override
    public void paint(Graphics g) {
        getCurrentMap();
        final Graphics2D g2d = (Graphics2D) g.create();
        final Rectangle clip = g2d.getClipBounds();

        // Draw a gray background
        g2d.setPaint(new Color(100, 100, 100));
        g2d.fill(clip);
        systemLog.log("Loading " + map.getLayerCount() + " layers.");
        // Draw each tile map layer
        for (MapLayer layer : map) {
            if (layer instanceof TileLayer) {
                renderer.paintTileLayer(g2d, (TileLayer) layer);
            }
        }
        
    }
    
    public static void reloadMap (Map map)  {
        //Uncomment for debug
        //systemLog.log("Loading map: " + map.getFilename());
        MapPainter.map = map;
        List<TileSet> tileSets = map.getTileSets();
        //systemLog.log("Tilesets: " + tileSets.size());
        //for (TileSet ts : tileSets) {
        //    systemLog.log("Tileset is: " + ts.getBaseDir());
        //}
        renderer = createRenderer(map);
    }
    
    private void getCurrentMap () {
        try {
            int hash = MonsterQuestMain.playerStats.mapHash;
            TMXMapReader reader = new TMXMapReader();
            String filepath = (System.getProperty("user.dir") + "%cresources%cmaps%cnewbiesTownCenter_0x0000.tmx");
            String finalFilePath = String.format(filepath, File.separatorChar, File.separatorChar, File.separatorChar);
            Map current = reader.readMap (finalFilePath);
            reloadMap(current);
            
        } catch (Exception ex) {
            systemLog.log("Unable to open tmx file!", Logging.ERROR, ex);
        }
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
