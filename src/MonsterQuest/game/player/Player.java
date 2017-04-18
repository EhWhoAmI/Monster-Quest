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
    public int Attack;
    public int Defense;
    public int Speed;
    public int Gold;
    public static final boolean BOY = true;
    public static final boolean GIRL = false;
    /**Player ()
     * This defines the default stats for player
     */
    public Player() {
        this.Level = 0;
        this.Experience = 0;
        this.Attack = 0;
        this.Defense = 0;
        this.Speed = 0;
        this.Gold = 0;
    }

    public Player(int Level, int Experience, int Attack, int Defense, int Speed, int Gold) {
        this.Level = Level;
        this.Experience = Experience;
        this.Attack = Attack;
        this.Defense = Defense;
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
}
