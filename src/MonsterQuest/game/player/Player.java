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
package MonsterQuest.game.player;

import MonsterQuest.util.FileFormatException;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.ParsingException;
import nu.xom.Text;

/**
 *
 * @author Zyun
 */
public class Player {
    public String name;
    public PlayerType playerType;
    public boolean gender;
    public int Level;
    public int Experience;
    public int AttackLv;
    public int Attack;
    public int DefenseLv;
    public int Defense;
    public int SpeedLv;
    public int Speed;
    public int Gold;
    public int mapHash;
    public Point playerPos;
    //Need to add the inventory arraylist, and weapon slots
    public static final boolean BOY = true;
    public static final boolean GIRL = false;
    /**
     * This defines the default stats for player
     */
    public Player() {
        this.Level = 0;
        this.Experience = 0;
        this.AttackLv = 1;
        this.Attack = 10;
        this.DefenseLv = 1;
        this.Defense = 10;
        this.SpeedLv = 1;
        this.Speed = 10;
        this.Gold = 10;
    }

    public Player(PlayerType playerType, int Level, int Experience, int AttackLv, int Attack, int DefenseLv, int Defense, int SpeedLv, int Speed, int Gold) {
        this.playerType = playerType;
        this.Level = Level;
        this.Experience = Experience;
        this.AttackLv = AttackLv;
        this.Attack = Attack;
        this.DefenseLv = DefenseLv;
        this.Defense = Defense;
        this.SpeedLv = SpeedLv;
        this.Speed = Speed;
        this.Gold = Gold;
    }
    
    public void setName (String name) {
        this.name = name;
    }
    
    public void setLevel (int level) {
        this.Level = level;
    }
    
    public void setGender (boolean gender) {
        this.gender = gender;
    }
    
    /** This is to init the player, but it doesn't directly init the object. Rather, it reads from the file, and returns a player object
     * @param file the filename to read from
     * @throws FileNotFoundException if the file cannot be found
     * @throws FileFormatException when file is formatted wrongly
     * @return
     */
    public static Player PlayerInit (String file) throws FileNotFoundException, FileFormatException{
        File inputFile = new File (file);
        Player player = new Player();
        if (!inputFile.exists())
            throw new FileNotFoundException("The file, " + file + " cannot be found");
        try {
            Builder inputFileBuilder = new Builder();
        
            Document inputFileDoc = inputFileBuilder.build(inputFile);
            Element root = inputFileDoc.getRootElement();
            
            //Read name
            Element nameElement = root.getFirstChildElement("name");
            Text name = (Text) nameElement.getChild(0);
            player.name = name.getValue();
            
            Element gender = root.getFirstChildElement("gender");
            Text genderText = (Text) gender.getChild(0);
            if (genderText.getValue().equals("Boy")) {
                //The player is a boy
                player.setGender(GIRL);
            }
            else if (genderText.getValue().equals("Girl")) {
                //The gender is a girl
                player.setGender(GIRL);
            }
            else {
                throw new FileFormatException("The text is corrupted, when the text should have been either \"Boy\" or \"Girl\", it was" + genderText.getValue());
            }
        } catch (ParsingException pe) {
            //Throw a file format exception, because the file is formatted wrongly.
            throw new FileFormatException("OOPS! File parsed wrongly!" + pe.getMessage());
        } catch (IOException ioe ) {
            
        }
        return player;
    }
}