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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static MonsterQuest.MonsterQuestMain.playerStats;
/**
 *
 * @author Zyun
 */
public class GameKeyListener implements KeyListener{

    @Override
    public void keyPressed(KeyEvent e) {
        //If keys are part of the wasd things
        if (e.getKeyChar() == 'W' | e.getKeyChar() == 'w' | e.getKeyChar() == 'A' | e.getKeyChar() == 'a' | e.getKeyChar() == 'S' | e.getKeyChar() == 's' | e.getKeyChar() == 'D' | e.getKeyChar() == 'd') {
            switch (e.getKeyChar()) {
                case 'W':
                case 'w':
                    playerStats.playerPos.y -= 68;
                    //If position is out of bounds, change map.
                    //Detect map
                    //Change map to that map
                    MonsterQuest.MonsterQuestMain.MonsterQuestWindow.repaint();
                    break;
                case 'A':
                case 'a':
                    playerStats.playerPos.x -= 68;
                    MonsterQuest.MonsterQuestMain.MonsterQuestWindow.repaint();
                    break;
                case 'S':
                case 's':
                    playerStats.playerPos.y += 68;
                    MonsterQuest.MonsterQuestMain.MonsterQuestWindow.repaint();
                    break;
                case 'D':
                case 'd':
                    playerStats.playerPos.x += 68;
                    MonsterQuest.MonsterQuestMain.MonsterQuestWindow.repaint();
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
            
    }
    
}
