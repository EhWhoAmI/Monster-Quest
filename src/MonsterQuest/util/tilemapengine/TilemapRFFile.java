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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static MonsterQuest.MonsterQuestMain.systemLog;
import MonsterQuest.util.Logging;
import java.util.ArrayList;
/** 
 * RFFile stands for Read From File. This reads from the tilemap file specified 
 * in the constuctor. will need to make a map object soon.
 * @author Zyun
 */
public class TilemapRFFile {
    int mapReccurences;
    int mapID;
    String mapName;
    int [][][] layers = new int [3][11][20];
    boolean [][] accessability = new boolean[11][20];
    ArrayList<TileMap> tilemaps = new ArrayList<>();
    
    /**
     * Important! Do not add the <code>System.getProperty("user.dir")</code> before this.
     * @param tilemapName
     */
    public TilemapRFFile(String tilemapName) {
        try {
            FileInputStream stream = new FileInputStream(System.getProperty("user.dir") + tilemapName);
            
            //The number of maps in the file
            mapReccurences = stream.read();
            systemLog.log("Number of map reccurences: " + mapReccurences);
            int num;
            for (num = 0; num < mapReccurences; num++) {
                //Get the map ID
                mapID = stream.read();
                systemLog.log("Got map ID, it is " + mapID);

                //String builder for mapname
                StringBuilder builder = new StringBuilder();

                //Get name
                for (int i = 0; i < 50; i++) {
                    int read = stream.read();
                    builder.append((char)read);
                }
                
                mapName = builder.toString();
                
                mapName = mapName.trim();
                
                systemLog.log("Map name is: " + mapName);
                //Get layers
                for (int layer = 0; layer < 3; layer++) {
                    //Get one layer
                    //Get the row
                    for (int row1 = 0; row1 < 11; row1 ++) {
                        //Get columns
                        for (int column = 0; column < 20; column++) {
                            layers[layer][row1][column] = stream.read();
                        }
                    }
                }
                
                //get accessability
                for (int row1 = 0; row1 < 11; row1 ++) {
                    //Get columns
                    for (int column = 0; column < 20; column++) {
                        int read = stream.read();
                        if (read == 0) {
                            //No go.
                            accessability[row1][column] = false;
                        }
                        else {
                            //Can go
                            accessability[row1][column] = true;
                        }
                    }
                }
                //Add all of that onto a tilemap object...
                TileMap map = new TileMap(mapID, mapName, layers[0], layers[1], layers[2], accessability, mapName);
                tilemaps.add(map);
                //end of read map
            }
            systemLog.log("Loaded " + num + " files");
            //Close file.
            stream.close();
            
        } catch (FileNotFoundException fnfe) {
            systemLog.log("File not found! " + fnfe.getMessage(), Logging.ERROR);
            //Might add code to download from internet.
        } catch (IOException ioe) {
            systemLog.log("IO exception! " + ioe.getMessage(), Logging.ERROR);
        }
    }
    
    /**
     * This returns the tilemap in the number.
     * @param index the number of the tiemap
     * @return the tilemap with the number in the array.
     */
    public TileMap getTileMap (int index) {
        return  tilemaps.get(index);
    }
    
    /**
     * This returns the tilemap, with the name.
     * @param name the name of the tilemap to search 
     * @return the tilemap associated with the name, or null if it is not found
     */
    public TileMap getTileMap (String name) {
        //Search for the name
        for (int i = 0; i < tilemaps.size(); i++) {
            //Found it!
            if (tilemaps.get(i).getMapName().equals(name)) {
                return getTileMap(i);
            }
        }
        //Did not find it
        return null;
    }
    
    public int getNumberOfMaps () {
        return mapReccurences;
    }
}
