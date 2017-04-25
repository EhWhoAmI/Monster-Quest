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
package MonsterQuest.util.tilemapengine;

import MonsterQuest.MonsterQuestMain;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Zyun
 */
public class TileMapReader {
    BufferedImage tileImage;
    public ArrayList<BufferedImage> tiles;
    public Dimension sizeOfEachElement;
    public int NumberOfRecurrenceX;
    public int NumberOfRecurrenceY;
    private int posX = 0;
    private int posY = 0;
    
    public TileMapReader(BufferedImage tileImage, Dimension sizeOfEachElement) {
        this.tileImage = tileImage;
        this.sizeOfEachElement = sizeOfEachElement;
        
        //Find the amount of occurences of the tiles on each axes.
        NumberOfRecurrenceX = this.tileImage.getWidth() / this.sizeOfEachElement.width;
        NumberOfRecurrenceY = this.tileImage.getHeight() / this.sizeOfEachElement.height;
        
        MonsterQuestMain.systemLog.log("Occurunces (x, y), (" + NumberOfRecurrenceX + ", " + NumberOfRecurrenceY + ")");
        //Init the arraylist for the tiles.
        tiles = new ArrayList<>(NumberOfRecurrenceX * NumberOfRecurrenceY);
        
        //Loop to get all the tiles. Some might be empty, though.
        int index = 0;
        for (int i = 0; i < NumberOfRecurrenceY; i++) {
            for (int n = 0; n < NumberOfRecurrenceX; n++) {
                MonsterQuestMain.systemLog.log("Reading tile " + index + " Position, x " + posX + " y, " + posY);
                tiles.add(this.tileImage.getSubimage(posX, posY, this.sizeOfEachElement.width, this.sizeOfEachElement.height));
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ie) {
                }
                index ++;
                posX += this.sizeOfEachElement.width;
            }
            posX = 0;
            posY += sizeOfEachElement.height;
        }
        MonsterQuestMain.systemLog.log("Loaded " + index + " tiles.");
        //Done.
    }
    
    /**
     * @param index the index of the tile
     * @return the image of the tile specified in the index.
     */
    public BufferedImage getImage (int index) {
        return (tiles.get(index));
    }
}