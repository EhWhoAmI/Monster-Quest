package Zyun.Lam.Game.MonsterQuest;

/**
 * Graphics part. 
 */

import javax.swing.JPanel;

class Graphics extends JPanel{
    static String quote = BasicAnimation.createNewTip();
    @Override
    public void paintComponent(java.awt.Graphics comp) {
        if (!ControlUnit.loadingfinished){
            BasicAnimation.loadingBars(comp);
            BasicAnimation.Tips(comp);
        }
        else if (ControlUnit.startScreen){
            BasicAnimation.startMenu(comp);
        }
        else if (ControlUnit.othersScreen) {
            BasicAnimation.others(comp);
        }
        if (ControlUnit.credits) {
            BasicAnimation.showCredits(comp);
        }
        if (ControlUnit.tutorial) {
            gamePart1__Tutorial.drawImage(comp);
        }
        if (gamePart1__Tutorial.nameWrite) {
            gamePart1__Tutorial.getName(comp);
        }
    }
}
