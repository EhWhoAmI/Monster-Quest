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

import MonsterQuest.MonsterQuestMain;
import MonsterQuest.util.Logging;
import MonsterQuest.util.tilemapengine.TileMap;
import MonsterQuest.util.tilemapengine.TileMapReader;
import MonsterQuest.util.tilemapengine.TilemapRFFile;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static MonsterQuest.MonsterQuestMain.systemLog;

/**
 * This class processes all the maps.
 * @author Zyun
 */
public class MapProcesser extends JPanel{
    int mapID;
    //All the tilemaps

    /**
     * The tilemap for the generic ground.
     */
    static public TileMapReader genericGround;
    
    /**
     * Newbies town center map;
     */
    
    public static ArrayList<TileMap> tilemapList = new ArrayList<>();
    
    private BufferedImage currentMapImage;
    
    private int currentMap;
    /**
     * Constructor for a new MapProcesser class
     */
    public MapProcesser() {
        super();
        loadMap();
        loadTilemaps();
        setLayout(null);
    }

    /**
     * Load all the maps onto the screen
     * @param g
     */
    public void printMaps(Graphics g) {
        //Get current map
        //Find it.
        //Note for later: Remember to move all this into a separate place, and place the path of the image into a file, then open it when needed. If even needed, store it into a buffered image.
        systemLog.log("Loading current map");
        boolean found = false;
        int i;
        
        if (currentMap == MonsterQuestMain.playerStats.mapHash) {
            //Load the map which is stored in variable
        }
        for (i = 0; i < tilemapList.size();i++) {
            if (tilemapList.get(i).getMapID() == MonsterQuestMain.playerStats.mapHash) {
                //Found it
                found = true;
                break;
            }
        }
        if (!found) {
                systemLog.log("Bug: Unable to find the map id " + i + ".", Logging.ALERT);
        } else {
            try {
                MapImageLoad load = new MapImageLoad(tilemapList.get(i));
                //Open file and load image
                File tempOpenFile = new File(load.getMapImagePath());
                BufferedImage tempOpen = ImageIO.read(tempOpenFile);
                g.drawImage(tempOpen, 0, 0, null);
            } catch (IOException ex) {
                systemLog.log("Unable to open image!", Logging.ERROR);
            }
        }
    }
    
    /**
     * Load all the tilemaps.
     */
    void loadTilemaps () {
        try {
            genericGround = new TileMapReader(System.getProperty("user.dir") + "/resources/tilemaps/GenericGroundDebug.png", new Dimension(68, 68));
        } catch (IOException ioe) {
                systemLog.log("Unable to open tilemap, " + ioe.getMessage() + " Unable to do anything because tilemaps are essential.", Logging.ERROR);
                JOptionPane.showMessageDialog(MonsterQuestMain.MonsterQuestWindow, "Unable to open the file", "Unable to open file", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    static void loadMap () {
        try {
            //Get list of map files.
            systemLog.log("Loading list of maps");
            File readMapFileName = new File (System.getProperty("user.dir") + "/data/maplist");
            Scanner readmapfiles = new Scanner(readMapFileName);
            ArrayList<String> fileList = new ArrayList<>();
            int numberOfFiles = 0;
            
            while (readmapfiles.hasNext()) {
                String fileName = readmapfiles.nextLine();
                File exists = new File(System.getProperty("user.dir") + fileName);
                if (!exists.exists()) {
                    //Report it, and exit game...
                    JOptionPane.showMessageDialog(MonsterQuestMain.MonsterQuestWindow, "You don't have the file " + fileName, "Missing File", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                } else {
                    fileList.add(fileName);
                }
                numberOfFiles++;
            }
            systemLog.log("There are " + numberOfFiles + " map name files.");
            
            //Then load all the stuff into tilemap objects
            systemLog.log("Loading stuff into tilemap objects");
            if (fileList.size() <=0 ) {
                systemLog.log("There are 0 files in the directory!", Logging.ERROR);
                
            }
            int f = 0;
            for (int i = 0; i < fileList.size(); i++) {
                TilemapRFFile reader = new TilemapRFFile(fileList.get(i));
                systemLog.log("There are " + reader.getNumberOfMaps() + " in the mapfile");
                for (f = 0; f < reader.getNumberOfMaps(); f++) {
                    systemLog.log("Loading tilemap" + f);
                    tilemapList.add(reader.getTileMap(f));
                }
            }
            systemLog.log("Loaded " + f + " maps");
            //Done...
        } catch (FileNotFoundException ex) {
            systemLog.log("File not found!!!", Logging.ERROR);
        }
    }
    
    
    
}
