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
    //New tilemap reader
    TileMapReader ground;

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        try {
            File tilemap = new File(System.getProperty("user.dir") + "/resources/tilemaps/things.png");
            ground = new TileMapReader(ImageIO.read(tilemap), new Dimension(16, 16));
            
        }catch (IOException ioe) {}
        BufferedImage i = ground.getImage(3);
        g2d.drawImage(i, 0, 0, null);
    }

    
    public NewbiesTownCenter() {
        super();
//        try {
//            ground = new TileMapReader(ImageIO.read(new File(System.getProperty("user.dir") + "/resources/tilemaps/things.png")), new Dimension(16, 16));
//            
//        }catch (IOException ioe) {
            
//        }
    }
    
    
}
