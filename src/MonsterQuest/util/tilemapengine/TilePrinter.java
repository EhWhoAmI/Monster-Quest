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
package MonsterQuest.util.tilemapengine;

import MonsterQuest.MonsterQuestMain;
import java.awt.Graphics;

/**
 *
 * @author Lam Zyun
 */
public class TilePrinter {
    TileMapReader tilemap;
    Integer [][] map;
    Graphics g;

    public TilePrinter(TileMapReader tilemap, Integer[][] map, Graphics g) {
        this.tilemap = tilemap;
        this.map = map;
        this.g = g;
        
        MonsterQuestMain.systemLog.log("Drawing the map...");
        int posX = 0, posY = 0;
        for (int i = 0; i < map.length; i++) {
            posX = 0;
            posY += tilemap.sizeOfEachElement.height;
            for (int n = 0; n < map[0].length; n++) {
                g.drawImage(tilemap.getImage(map[i][n]), posX, posY, null);
                posX += tilemap.sizeOfEachElement.width;
            }
        }
    }
}
