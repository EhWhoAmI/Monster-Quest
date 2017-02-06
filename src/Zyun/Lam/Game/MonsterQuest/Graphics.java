package Zyun.Lam.Game.MonsterQuest;

/**
 * Graphics part. 
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

class Graphics extends JPanel{
    static String quote = BasicAnimation.createNewTip();
    
    @Override
    public void paintComponent(java.awt.Graphics comp) {
        if (ControlUnit.startScreenSplash) {
            StartScreen.stcs(comp);
        }
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
        if (gamePart1__Tutorial.genderChoose) {
            gamePart1__Tutorial.genderChoose(comp);
        }
        if (gamePart1__Tutorial.characterChoose) {
            gamePart1__Tutorial.playerChoose(comp);
        }
    }
    
    //For loading images...
    static BufferedImage loadImage (String file) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File (WindowMain.user_dir + file));
        } catch (final IOException ioe) {
            System.err.println("Unable to open image " + file + ": " + ioe.getMessage() + " and " + ioe.getLocalizedMessage());
            return null;
        }
        return image;
    }
}