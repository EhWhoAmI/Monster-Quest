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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * This reads ftom the tilemap
 * @author Zyun
 */
public class TileMapReader {
    /**
     * The original tilemap
     */
    BufferedImage tileImage;
    /**
     * The list of tiles in the tilemaps
     */
    public ArrayList<BufferedImage> tiles;
    
    /**
     * The size of each element
     */
    public Dimension sizeOfEachElement;
    
    /**
     * The number of reccurences of the tilemaps
     */
    public int NumberOfRecurrenceX;
    
    /**
     * Same as <code>NumberOfRecurrenceY</code>
     */
    public int NumberOfRecurrenceY;
    
    /**
     * The X position of the pointer to read the image
     */
    private int posX = 0;
    
    /**
     * The Y position of the pointer to read the image
     */
    private int posY = 0;

    /**
     * The name of the tilemap file
     */
    private String filename;
    /**
     * Splits the original tilemap, and puts it into the arraylist <code>tiles</code>
     * @param tileImage the original tilemap image
     * @param sizeOfEachElement the size of each element
     */
    public TileMapReader(BufferedImage tileImage, Dimension sizeOfEachElement) {
        //Get the values
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
                //Don't need to log this
                //MonsterQuestMain.systemLoh.log("Reading tile " + index + " Position, x " + posX + " y, " + posY);
                
                //Add the image to the arraylist
                tiles.add(this.tileImage.getSubimage(posX, posY, this.sizeOfEachElement.width, this.sizeOfEachElement.height));
                
                //Wait for 0.001 seconds, for the sake of the JVM (Don't bog it down)
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ie) {
                    //Ingore
                }
                //Get the number of files, and increment position
                index ++;
                posX += this.sizeOfEachElement.width;
            }
            
            //Increment the position
            posX = 0;
            posY += sizeOfEachElement.height;
        }
        MonsterQuestMain.systemLog.log("Loaded " + index + " tiles.");
        //Done.
    }
    
    /**
     * This just like <code>TileMapReader(BufferedImage, Dimension)</code>, just that it takes a string as a filename
     * @param fileName the name of the file
     * @param sizeOfElement the size of each element
     * @throws IOException If the file cannot be opened
     * @see @link TileMapReader(BufferedImage, Dimension)
     */
    public TileMapReader(String fileName, Dimension sizeOfElement) throws IOException{
        this(ImageIO.read(new File(fileName)), sizeOfElement);
    }
    
    
    /**
     * Get the tile of the index thingy
     * @param index the index of the tile
     * @return the image of the tile specified in the index.
     */
    public BufferedImage getImage (int index) {
        return (tiles.get(index));
    }
    
    
}