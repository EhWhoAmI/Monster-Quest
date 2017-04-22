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

import java.awt.Dimension;
import java.awt.image.BufferedImage;

/**
 *
 * @author Zyun
 */
public class TileMapReader {
    BufferedImage tileImage;
    BufferedImage[] tiles;
    Dimension sizeOfEachElement;
    int NumberOfRecurrenceX;
    int NumberOfRecurrenceY;
    private int posX;
    private int posY;
    
    public TileMapReader(BufferedImage tileImage, Dimension sizeOfEachElement) {
        this.tileImage = tileImage;
        this.sizeOfEachElement = sizeOfEachElement;
        
        //Find the amount of occurences of the tiles on each axes.
        NumberOfRecurrenceX = this.tileImage.getWidth() / this.sizeOfEachElement.width;
        NumberOfRecurrenceY = this.tileImage.getHeight() / this.sizeOfEachElement.height;
        //Loop to get all the tiles. Some might be empty, though.
        int index = 0;
        for (int i = 0; i < NumberOfRecurrenceY; i++) {
            posY += sizeOfEachElement.height;
            for (int n = 0; n < NumberOfRecurrenceX; n++) {
                posX += this.sizeOfEachElement.width;
                tiles[index] = this.tileImage.getSubimage(posX, posY, this.sizeOfEachElement.width, this.sizeOfEachElement.height);
                index ++;
            }
        }
        //Done.
    }
    
    /**
     * @param index the index of the tile
     * @return the image of the tile specified in the index.
     */
    BufferedImage getImage (int index) {
        return (tiles[index]);
    }
}