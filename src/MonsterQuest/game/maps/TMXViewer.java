package MonsterQuest.game.maps;

/*
 * Copyright 2010, Thorbjørn Lindeijer <thorbjorn@lindeijer.nl>
 *
 * This file is part of tmxviewer-java.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice,
 *       this list of conditions and the following disclaimer.
 *
 *    2. Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE CONTRIBUTORS ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
 * EVENT SHALL THE CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import tiled.core.Map;
import tiled.core.MapLayer;
import tiled.core.TileLayer;
import tiled.io.TMXMapReader;
import tiled.view.MapRenderer;
import tiled.view.OrthogonalRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import tiled.view.IsometricRenderer;

/**
 * An example showing how to use libtiled-java to do a simple TMX viewer.
 */
public class TMXViewer
{
    public static void main(String[] arguments) {
        String fileToOpen = null;

        for (String arg : arguments) {
            if ("-?".equals(arg) || "-help".equals(arg)) {
                printHelpMessage();
                return;
            } else if (arg.startsWith("-")) {
                System.out.println("Unknown option: " + arg);
                printHelpMessage();
                return;
            } else if (fileToOpen == null) {
                fileToOpen = arg;
            }
        }

        if (fileToOpen == null) {
            printHelpMessage();
            return;
        }

        Map map;
        try {
            TMXMapReader mapReader = new TMXMapReader();
            map = mapReader.readMap(/*System.getProperty("user.dir") + */fileToOpen);
            System.out.println("Opening " + fileToOpen);
        } catch (Exception e) {
            System.err.println("Error while reading the map:\n" + e.getMessage() + ", Class: " + e.getClass().getName() + " while trying to open " + fileToOpen);
            e.printStackTrace();
            return;
        }

        System.out.println(map.toString() + " loaded");

        JScrollPane scrollPane = new JScrollPane(new MapView(map));
        scrollPane.setBorder(null);
        scrollPane.setPreferredSize(new Dimension(800, 600));

        JFrame appFrame = new JFrame("TMX Viewer");
        appFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        appFrame.setContentPane(scrollPane);
        appFrame.pack();
        appFrame.setVisible(true);
    }

    private static void printHelpMessage() {
        System.out.println("Java TMX Viewer\n" +
                "\n" +
                "When a parameter is given, it can either be a file name or an \n" +
                "option starting with '-'. These options are available:\n" +
                "\n" +
                "-?\n" +
                "-help\n" +
                "\tDisplays this help message\n");
    }
}

class MapView extends JPanel implements Scrollable
{
    private Map map;
    private MapRenderer renderer;

    public MapView(Map map) {
        this.map = map;
        renderer = createRenderer(map);
        
        setPreferredSize(renderer.getMapSize());
        setOpaque(true);
        loadPhoto();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            final Graphics2D g2d = (Graphics2D) g.create();
            final Rectangle clip = g2d.getClipBounds();
            System.out.println(clip.toString());
            /*
            BufferedImage toWrite = new BufferedImage(clip.width, clip.width, BufferedImage.TYPE_INT_RGB);
            Graphics2D gd = toWrite.createGraphics();
            
            gd.setClip(clip);
            // Draw a gray background
            gd.setPaint(new Color(100, 100, 100));
            gd.fill(clip);
            
            // Draw each tile map layer
            for (MapLayer layer : map) {
                if (layer instanceof TileLayer) {
                    renderer.paintTileLayer(gd, (TileLayer) layer);
                }
            }
            
            
            //ImageIO.write(toWrite, "PNG", new File("test.png"));
*/
            BufferedImage toPut = ImageIO.read(new File ("test.png"));
            g2d.drawImage(toPut, 0, 0, null);
        } catch (IOException ex) {
            Logger.getLogger(MapView.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public void loadPhoto () {
        try{
            Rectangle clip = new Rectangle(map.getWidth() * map.getTileWidth(), map.getWidth()* map.getTileHeight());
            BufferedImage toWrite = new BufferedImage(clip.width, clip.width, BufferedImage.TYPE_INT_RGB);
            Graphics2D gd = toWrite.createGraphics();
            
            gd.setClip(clip);
            // Draw a gray background
            gd.setPaint(new Color(100, 100, 100));
            gd.fill(clip);
            
            // Draw each tile map layer
            for (MapLayer layer : map) {
                if (layer instanceof TileLayer) {
                    renderer.paintTileLayer(gd, (TileLayer) layer);
                }
            }
            
            
            ImageIO.write(toWrite, "PNG", new File("test.png"));
//            BufferedImage toPut = ImageIO.read(new File ("test.png"));
//            g2d.drawImage(toPut, 0, 0, null);
        } catch (IOException ex) {
            Logger.getLogger(MapView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}