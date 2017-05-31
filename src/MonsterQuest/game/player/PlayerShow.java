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

import MonsterQuest.MonsterQuestMain;
import static MonsterQuest.MonsterQuestMain.systemLog;
import MonsterQuest.util.Logging;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Zyun
 */
public class PlayerShow {

    /**
     *
     * @param g
     */
    public static void printCharacter (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //Open image for the player
        try {
            File image = new File(System.getProperty("user.dir") + "/resources/images/sprites/PersonWalking1.png");
            BufferedImage spriteImage = ImageIO.read(image);
            g2d.drawImage(spriteImage, MonsterQuestMain.playerStats.playerPos.x, MonsterQuestMain.playerStats.playerPos.y, null);
        } catch (IOException ioe) {
            systemLog.log("Unable to open file! " + ioe.getMessage(), Logging.ERROR);
        }
        
    }
}
