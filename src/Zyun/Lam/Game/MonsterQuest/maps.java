package Zyun.Lam.Game.MonsterQuest;

class maps {
    /**
     * Notes: the map will be 1360 * 748 pixels in size, 
     * And 1 step is 34 * 34 pixels, so one map will have 40 * 22 steps. 
     */
    //To add on the thingy
    static void maps (java.awt.Graphics g) {
        if (ControlUnit.currentMap == 1) {
            //Load the first map
            System.out.println("Loading map 1");
            NewbiesTownCenter.putMapOnIt(g);
        }
    } 
}