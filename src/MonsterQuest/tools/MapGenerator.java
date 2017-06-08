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
package MonsterQuest.tools;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * The map generator class, Generates a map file for the game.
 * @author Zyun
 */
public class MapGenerator {

    /**
     *
     * @param args the filename of the map
     */
    public static void main(String[] args) {
        //Check for args
        if (args.length == 0) {
            System.out.println("Args: filename of the map");
            System.exit(0);
        }
        try {
            //Open file 
            File fn = new File (System.getProperty("user.dir") + args[0]);
            fn.createNewFile();
            
            //Open file output streams
            FileOutputStream file = new FileOutputStream(System.getProperty("user.dir") + args[0]);
            BufferedOutputStream buff = new BufferedOutputStream(file);
            
            //Sacanner to read the map.
            Scanner scan = new Scanner(System.in);
            
            //Get number of maps to put on this.
            System.out.println("How many maps do you want to put on?");
            int numMaps = Integer.parseInt(scan.nextLine());
            buff.write(numMaps);
            
            System.out.println("Map name: " + args[0] + "\n");
            
            //Get map id
            System.out.print("Enter map id:");
            int mapID = Integer.parseInt(scan.nextLine());
            buff.write(mapID);
            
            //Get map details
            //Get map name -- max 50 characters
            String mapname;
            do {
                System.out.println("Map name: remember, max 50 characters. Extra will be cut off");
                mapname = scan.nextLine();
                mapname = mapname.replace('\n', (char)0);
                if (mapname.length() > 50) {
                    System.out.println("The map name is too long! max 50 characters!");
                }
            }while (mapname.length() > 50);
            
            //If it passes the loop
            //Append null characters in the end
            StringBuilder builder = new StringBuilder(mapname);

            //Loop to append
            for (int i = 0; i < (50 - mapname.length()); i ++)
                builder.append((char)0);  

            //Write to file
            buff.write(builder.toString().getBytes(StandardCharsets.UTF_8));
            
            //loop for layers 
            for (int maps = 0; maps < numMaps; maps++) {
                System.out.println("Enter map " + maps);
                for (int layers = 0; layers < 3; layers ++) {
                    for (int i = 0; i < 11; i++){
                        System.out.println("Enter row " + (i + 1) + " of layer " + layers + ",separated by a space. 20 squares only!");
                        String character = scan.nextLine();
                        StringTokenizer tokenizer = new StringTokenizer(character);
                        
                        //Write to file
                        for (int n = 0; n < tokenizer.countTokens(); n++)
                            buff.write(Integer.parseInt(tokenizer.nextToken()));
                    }
                }

                //Accessability
                for (int i = 0; i < 11; i++){
                    System.out.println("Enter row " + i + " for accessability, separated by a space, 1 for can go, 0 for no go");
                    String character = scan.nextLine();
                    StringTokenizer tokenizer = new StringTokenizer(character);
                    for (int n = 0; n < tokenizer.countTokens(); n++)
                        buff.write(Integer.parseInt(tokenizer.nextToken()));
                }
            }
            
            //Save file and flush
            buff.flush();
        } catch (FileNotFoundException fe) {
            System.err.println("File not found");
        } catch (IOException ioe) {
            System.err.println("IO exception " + ioe.getMessage());
            ioe.printStackTrace();
        }
    }
}