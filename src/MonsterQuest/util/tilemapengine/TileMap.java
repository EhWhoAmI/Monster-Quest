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

/**
 *
 * @author Zyun
 */
public class TileMap {

    private int mapID;
    private String mapName;
    public int[][] layer1;
    public int[][] layer2;
    public int[][] layer3;
    public boolean[][] accessability;
    /**
     * Might not need this soon...
     */
    String mapDescription;

    /**
     * Creates a new Tilemap object
     * @param mapID The number of the map
     * @param mapName the name of the map
     * @param layer1 layer 1 (Bottom)
     * @param layer2 layer 2 (Middle)
     * @param layer3 layer 3 (Top)
     * @param accessability the accessability of the map;
     * @param mapDescription The description of the map. If nothing, just put <code>null</code>.
     */
    public TileMap(int mapID, String mapName, int[][] layer1, int[][] layer2, int[][] layer3, boolean[][] accessability, String mapDescription) {
        this.mapID = mapID;
        this.mapName = mapName;
        this.layer1 = layer1;
        this.layer2 = layer2;
        this.layer3 = layer3;
        this.accessability = accessability;
        this.mapDescription = mapDescription;
    }
    
    /**
     * Get the name of the map
     * @return the name of the map.
     */
    public String getMapName () {
        return this.mapName;
    }
    
    public int getMapID (){
        return mapID;
    }
}
