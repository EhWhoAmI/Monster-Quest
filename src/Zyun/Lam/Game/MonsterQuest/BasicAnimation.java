package Zyun.Lam.Game.MonsterQuest;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;
/**
 * The animation for all the Menus when you start the game. Should change name, though, but I'm too lazy...
 * @author Zyun
 */
class BasicAnimation {
    static public Image startScreen;
    static public int alpha = 0;
    public static float percnt = 0;
    
    static void loadImages () {
        ImageIcon ii = new ImageIcon(WindowMain.user_dir + "/resources/Monster Quest Splash Screen 6.png");
        startScreen = ii.getImage();
    }
    
    static void initImages () {
        loadImages();
    }
    
    static void loadingBar (java.awt.Graphics comp, float percentage) {
        Graphics2D comp2D = (Graphics2D) comp;
        comp2D.setColor(Color.black);
        Rectangle2D.Float loading = new Rectangle2D.Float(450, 680, 500, 30);
        comp2D.fill(loading);
        float x = percentage * 4.9F; 
        Rectangle2D.Float barinsideloading = new Rectangle2D.Float(455, 685, x, 20);
        comp2D.setColor(new Color(139, 0, 0));
        comp2D.fill(barinsideloading);
    }
    
    static void loadingBars (java.awt.Graphics comp) {
        String [] Text = {"Loading", "Loading.", "Loading..", "Loading..."};
        Graphics2D comp2D = (Graphics2D) comp;
        initImages();
        comp.drawImage(startScreen, 175, 0, null);
        //Rectangle2D.Float newRect = new Rectangle2D.Float(100, 100, 100, 100);
        //comp2D.fill(newRect);
        comp2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font ("Arial", Font.PLAIN, 20);
        comp2D.setFont(font);
        Color reddishKindaofColor = new Color(153, 0, 0);
        alpha++;
        comp2D.setColor(reddishKindaofColor);
        if (percnt < 100) {
            loadingBar(comp, percnt);
        }
        else {
            loadingBar(comp, 100);
        }
        comp2D.setColor(Color.white);
        if (percnt < 100){
            comp2D.drawString(Text[alpha%4], 625, 702);
            comp2D.drawString("" + (int)percnt + "%", 725, 702);
        } else {
            comp2D.drawString("Done!", 650, 702);
        }
    }
    
    static void startMenu (java.awt.Graphics comp) {
        Graphics2D comp2D = (Graphics2D) comp;
        comp2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        comp.drawImage(startScreen, 175, 0, null);
        {
            //Start of start game button
            //x and y positions unknown 
            float startButtonX = 1000F;
            float startButtonY = 200F;
            float startButtonWidth = 200F;
            float startButtonLength = 100F;
            //For back "Shadow"/outline
            RoundRectangle2D.Float startButtonBack = new RoundRectangle2D.Float (startButtonX, startButtonY, startButtonWidth + 5, startButtonLength + 5, 30, 30);
            //Actual Button
            RoundRectangle2D.Float startButtonFront = new RoundRectangle2D.Float (startButtonX + 2.5F, startButtonY + 2.5F, startButtonWidth, startButtonLength, 30, 30);
            comp2D.setColor (Color.BLACK);
            comp2D.fill (startButtonBack);
            comp2D.setColor (Color.RED);
            comp2D.fill (startButtonFront);
            Font fontForMenu = new Font ("Arial", Font.PLAIN, 30);
            comp2D.setFont(fontForMenu);
            comp2D.setColor (new Color (240, 248, 255));
            comp2D.drawString ("Start Game", startButtonX + 20, startButtonY + 60);
            //End of start Button
        }
        {
            //Start of option button
            float optionButtonX = 1000F;
            float optionButtonY = 350F;
            float optionButtonWidth = 200F;
            float optionButtonLength = 100F;
            //For back "Shadow"/outline
            RoundRectangle2D.Float optionButtonBack = new RoundRectangle2D.Float (optionButtonX, optionButtonY, optionButtonWidth + 5, optionButtonLength + 5, 30, 30);
            //Actual Button
            RoundRectangle2D.Float optionButtonFront = new RoundRectangle2D.Float (optionButtonX + 2.5F, optionButtonY + 2.5F, optionButtonWidth, optionButtonLength, 30, 30);
            comp2D.setColor (Color.BLACK);
            comp2D.fill (optionButtonBack);
            comp2D.setColor (Color.BLUE);
            comp2D.fill (optionButtonFront);
            Font fontForMenu = new Font ("Arial", Font.PLAIN, 30);
            comp2D.setFont(fontForMenu);
            comp2D.setColor (new Color (240, 248, 255));
            comp2D.drawString ("Options", optionButtonX + 20, optionButtonY + 60);
            //End of option Button (untested)
        }
        {
            //Start of others button
            //x and y positions unknown 
            float othersButtonX = 1000F;
            float othersButtonY = 500F;
            float othersButtonWidth = 200F;
            float othersButtonLength = 100F;
            //For back "Shadow"/outline
            RoundRectangle2D.Float othersButtonBack = new RoundRectangle2D.Float (othersButtonX, othersButtonY, othersButtonWidth + 5, othersButtonLength + 5, 30, 30);
            //Actual Button
            RoundRectangle2D.Float othersButtonFront = new RoundRectangle2D.Float (othersButtonX + 2.5F, othersButtonY + 2.5F, othersButtonWidth, othersButtonLength, 30,30);
            comp2D.setColor (Color.BLACK);
            comp2D.fill (othersButtonBack);
            comp2D.setColor (Color.GREEN);
            comp2D.fill (othersButtonFront);
            Font fontForMenu = new Font ("Arial", Font.PLAIN, 30);
            comp2D.setFont(fontForMenu);
            comp2D.setColor (new Color (240, 248, 255));
            comp2D.drawString ("Others", othersButtonX + 20, othersButtonY + 60);
            //End of others Button (untested)
        }
    }
    
    static void Tips (java.awt.Graphics comp) {
        Graphics2D comp2D = (Graphics2D) comp;
        comp2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font ("Arial", Font.PLAIN, 20);     
        FontMetrics metrics = comp.getFontMetrics(font);
        int windowWidth = WindowMain.window.getWidth();
        int textWidth = metrics.stringWidth(Graphics.quote);
        comp2D.setColor(Color.BLACK);
        comp2D.setFont(font);
        comp2D.drawString(Graphics.quote, (windowWidth / 2) - (textWidth / 2), 650);
    }
    
    
    static String createNewTip () {
        String[] Tipandquotelist = {"Tip: Tips go here :P", "This game is the best game in the world. Tell your mom that.", "Kill monsters to get rewards!(I know you know this. Just killing time)", "Filling the world with monsters... Bwuwahahaha..."};
        int tipToPrint = ((int)(Math.random() * 1000) % Tipandquotelist.length);
        System.out.print("Zyun.Lam.Game.MonsterQuest.BasicAnimation.createNewTip(): ");
        System.out.println("Quote: " + Tipandquotelist[tipToPrint]);
        return Tipandquotelist[tipToPrint];
    }
    
    static void ShowOptions (java.awt.Graphics comp) {
        
    }
    
    static void others (java.awt.Graphics comp) {
        Graphics2D comp2D = (Graphics2D) comp;
        comp2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        comp.drawImage(startScreen, 175, 0, null);
        Font fontForMenu = new Font ("Arial", Font.PLAIN, 100);
        comp2D.setFont(fontForMenu);
        RoundRectangle2D.Float WindowPopup = new RoundRectangle2D.Float ((WindowMain.window.getWidth() / 2)- 450, 10, 900, 700, 10, 10);
        RoundRectangle2D.Float WindowPopupBack = new RoundRectangle2D.Float((WindowMain.window.getWidth() / 2) - 452.5F, 7.5F, 905, 705, 10, 10);
        comp2D.setColor(Color.BLACK);
        comp2D.fill(WindowPopupBack);
        comp2D.setColor(new Color (210, 210, 210));
        comp2D.fill(WindowPopup);
        comp2D.setColor (Color.BLACK);
        FontMetrics fnt = comp.getFontMetrics(fontForMenu);
        comp2D.drawString ("Others", (WindowMain.window.getWidth() / 2) - (fnt.stringWidth("Others") / 2) + 20, 100);
        {
            //Start of terms and conditions button
            //x and y positions unknown 
            float EULAButtonX = (WindowMain.window.getWidth() /2) - 100;
            float EULAButtonY = 200F;
            float EULAButtonWidth = 300F;
            float EULAButtonLength = 150F;
            //For back "Shadow" outline
            RoundRectangle2D.Float EULAButtonBack = new RoundRectangle2D.Float (EULAButtonX, EULAButtonY, EULAButtonWidth + 5, EULAButtonLength + 5, 30, 30);
            //Actual Button
            RoundRectangle2D.Float EULAButtonFront = new RoundRectangle2D.Float (EULAButtonX + 2.5F, EULAButtonY + 2.5F, EULAButtonWidth, EULAButtonLength, 30, 30);
            comp2D.setColor (Color.BLACK);
            comp2D.fill (EULAButtonBack);
            comp2D.setColor (Color.RED);
            comp2D.fill (EULAButtonFront);
            Font fontForMenubigga = new Font ("Arial", Font.PLAIN, 50);
            comp2D.setFont(fontForMenubigga);
            comp2D.setColor (new Color (240, 248, 255));
            comp2D.drawString ("EULA", EULAButtonX + (EULAButtonLength / 2), EULAButtonY + 90);
            //End of terms and conditions Button
        }
        {
            //Start of credits button
            //x and y positions unknown 
            float creditsButtonX = (WindowMain.window.getWidth() /2) - 100;
            float creditsButtonY = 400F;
            float creditsButtonWidth = 300F;
            float creditsButtonLength = 150F;
            //For back "Shadow" outline
            RoundRectangle2D.Float creditsButtonBack = new RoundRectangle2D.Float (creditsButtonX, creditsButtonY, creditsButtonWidth + 5, creditsButtonLength + 5, 30, 30);
            //Actual Button
            RoundRectangle2D.Float creditsButtonFront = new RoundRectangle2D.Float (creditsButtonX + 2.5F, creditsButtonY + 2.5F, creditsButtonWidth, creditsButtonLength, 30, 30);
            comp2D.setColor (Color.BLACK);
            comp2D.fill (creditsButtonBack);
            comp2D.setColor (Color.BLUE);
            comp2D.fill (creditsButtonFront); 
            Font fontForMenubigga = new Font ("Arial", Font.PLAIN, 50);
            comp2D.setFont(fontForMenubigga);
            comp2D.setColor (new Color (240, 248, 255));
            comp2D.drawString ("Credits", creditsButtonX + (creditsButtonLength / 2), creditsButtonY + 90);
            //End of credits Button
        }
        {
            //Exit button
            RoundRectangle2D.Float exitButtonBack = new RoundRectangle2D.Float(1072.5F, 27.5F, 45, 45, 30, 30);
            RoundRectangle2D.Float exitButton = new RoundRectangle2D.Float(1075, 30, 40, 40, 30, 30);
            comp2D.setColor(Color.BLACK);
            comp2D.fill(exitButtonBack);
            comp2D.setColor(Color.red);
            comp2D.fill(exitButton);
            comp2D.setColor(Color.WHITE);
            GeneralPath exitSign = new GeneralPath();
        }
    }
    
    static void showCredits (java.awt.Graphics comp) {
        Graphics2D comp2D = (Graphics2D) comp;
        Rectangle2D.Float bg = new Rectangle2D.Float(0, 0, (float)WindowMain.window.getWidth(), (float)WindowMain.window.getHeight());
        comp2D.fill(bg);
        comp2D.setColor(Color.WHITE);
        Font fontForMenubigga = new Font ("Arial", Font.PLAIN, 50);
        comp2D.setFont(fontForMenubigga);
        FontMetrics fnt = comp2D.getFontMetrics(fontForMenubigga);
        comp2D.drawString("By : Zyun", (WindowMain.window.getWidth()/ 2) - (fnt.stringWidth("By : Zyun") / 2), (WindowMain.window.getHeight() / 2) - (fnt.stringWidth("By : Zyun") / 2));
        comp2D.drawString("Press any key to exit.", 10, 500);
        System.out.println("Zyun.Lam.Game.MonsterQuest.BasicAnimation.showCredits()");
        ControlUnit.othersScreen = false;
        ControlUnit.credits = true;
        WindowMain.frameRepaint();
        
    }
}
