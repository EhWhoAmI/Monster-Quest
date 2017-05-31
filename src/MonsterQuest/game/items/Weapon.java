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
package MonsterQuest.game.items;


/**
 * Weapon class for the weapons
 * @author Zyun
 */
public class Weapon implements Item{
    public String name;
    public String description;
    public int level;
    public int attackBoost;
    public final ItemType type = ItemType.weapon;
    
    /**
     * Get the description
     * @return the description
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Get the name of the weapon
     * @return The name of weapon
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Get the type of the item.
     * @return the item type
     */
    @Override
    public ItemType getType() {
        return type;
    }
    
    /**
     * The level of the weapon
     * @return the level of the weapon
     */
    public int getLevel () {
        return level;
    }
}
