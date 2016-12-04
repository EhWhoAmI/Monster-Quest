package Zyun.Lam.Game.MonsterQuest;

import java.awt.List;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zyun
 */
public class SystemLog implements Runnable{
    private static String log = "No Error";

    public SystemLog(String logString) {
        Log(logString);
    }
    
    public void Log (String text) {
        log = text;
        Thread logging = new Thread(this);
        logging.start();
    }

    @Override
    public void run() {
        //Time Stamp
        Date timeStamp = new Date();
        
        try {
            WindowMain.logFile.setWritable(true);
            String date = "[" + timeStamp.toString() + "]\t";
            for (int i = 0; i < date.length(); i++) {
                WindowMain.logFileOutput.write(178);
            }
            for (int b = 0; b < log.length(); b++) {
                WindowMain.logFileOutput.write((int) log.charAt(b));
            }
            WindowMain.logFileOutput.write('\n');
            WindowMain.logFileOutput.flush();
            //Files.write(WindowMain.logFile, lines, options)
        } catch (IOException ioe) {
            //This wouldn't happen a lot...
            System.err.println("Unable to write to log file: " + ioe.toString());
        } catch (Exception e) {
            System.err.println("Unable to write to log file: " + e.getMessage() + " @class " + e.getClass() + " because " + e.getCause() + " and " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
    
    
}
