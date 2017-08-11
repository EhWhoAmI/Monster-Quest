package MonsterQuest.game.UI;

import MonsterQuest.game.painter.Paintable;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
/**
 * This is the implementation for the player details.
 * @author Zyun
 */
public class PlayerDetails implements Paintable{
    // All the rectangles for the UI
    public static Rectangle2D.Float userAvatar = new Rectangle2D.Float(50, 50, 100, 100);
    public static Rectangle2D.Float hpBar = new Rectangle2D.Float(150, 60, 200, 25); 
    public static Rectangle2D.Float energyBar = new Rectangle2D.Float(150, 100, 200, 25);
    
    @Override
    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.fill(userAvatar);
        g2d.fill(hpBar);
        
        g2d.setColor(Color.BLUE);
        g2d.fill(energyBar);
    }
    
    public static boolean mouseInSelection (int x, int y) {
        return (userAvatar.intersects(x, y, 1, 1) | hpBar.intersects(x, y, 1, 1) | energyBar.intersects(x, y, 1, 1));
    }
}
