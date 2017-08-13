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
package MonsterQuest.game;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import MonsterQuest.game.UI.PlayerDetails;
import MonsterQuest.MonsterQuestMain;
import static MonsterQuest.MonsterQuestMain.systemLog;
/**
 * The mouse events for the game.
 * 
 * @author Zyun 
 */
public class GameMouseListener implements MouseListener{
    //Note: Put all your code for the game(as in the map) in the if statements.
    @Override
    public void mouseClicked(MouseEvent e) {
        if (MonsterQuest.MonsterQuestMain.gameRunning) {
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if (MonsterQuest.MonsterQuestMain.gameRunning) {
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        if (MonsterQuest.MonsterQuestMain.gameRunning) {
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (MonsterQuest.MonsterQuestMain.gameRunning) {
        }
    }
    
    //So far, we are only interested in this one.
    @Override
    public void mouseReleased(MouseEvent e) {
        if (MonsterQuest.MonsterQuestMain.gameRunning) {
            if (PlayerDetails.mouseInSelection(e.getX(), e.getY())) {
                //Show the user infomation.
                systemLog.log("Show player details");
                MonsterQuestMain.gameRunning = false;
                MonsterQuestMain.cardLayout.show(MonsterQuestMain.MonsterQuestPanel, "playerStats");
            }
        }
    }
}
