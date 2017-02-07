package Zyun.Lam.Game.MonsterQuest;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

class NewbiesTownCenter {
    private static BufferedImage map;
    final static int mapCode = 1;

    static void loadMap() {
        map = Graphics.loadImage("/resources/images/maps/MonsterQuestMap.png");
    }

    static void putMapOnIt(java.awt.Graphics g) {
        loadMap();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(map, 0, 0, null);
    }
    
    
}