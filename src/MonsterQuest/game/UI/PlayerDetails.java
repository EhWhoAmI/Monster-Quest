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
    // All the rectangles for the wiki
    Rectangle2D.Float userAvatar = new Rectangle2D.Float(50, 50, 100, 100);
    @Override
    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.fill(userAvatar);
    }
}
