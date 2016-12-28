package Zyun.Lam.Game.MonsterQuest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

//This is just a basic one... Will 
public class SystemLog {
    private static String log = "No Problem";
    static FileWriter logFileOutput = null;
    static BufferedWriter logFileWriter = null;

    public static void log (String text) {
        //Create Time Stamp
        Date timeStamp = new Date();
        String content = ("[" + timeStamp + "] " + text);
        try {
            logFileOutput = new FileWriter("system.log", true);
            logFileWriter = new BufferedWriter(logFileOutput);
            logFileWriter.write(content);
            logFileWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (logFileWriter != null)
                    logFileWriter.close();
                if (logFileOutput != null)
                    logFileOutput.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
        public static void startNewLog () {
        //Create Time Stamp
        Date timeStamp = new Date();
        String content = (" --- NEW LOG STARTED [" + timeStamp + "] ---");
        try {
            logFileOutput = new FileWriter("system.log", true);
            logFileWriter = new BufferedWriter(logFileOutput);
            logFileWriter.newLine();
            logFileWriter.write(content);
            logFileWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (logFileWriter != null)
                    logFileWriter.close();
                if (logFileOutput != null)
                    logFileOutput.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
        
        static void clearFile () {
            File logFile = new File ("system.log");
            //If log file does not exist, who cares, as we will make it later
            if (logFile.exists()) {
                if (logFile.length() > 102400) { //Size of 100 kb, will delete the file if so
                    if (!logFile.delete()) {
                        //Well, something is wrong, as it should be able to delete. Will there have a exception?
                        //Anyway, report it
                        System.err.println("Unable to delete log file");
                    } else {
                        try {
                            logFile.createNewFile();
                            log("Cleared file of things.");
                            System.out.println("Zyun.Lam.Game.MonsterQuest.SystemLog.clearFile() : Cleared file of things");
                        } catch (IOException ioe) {
                            System.err.println("Unable to open file: " + ioe.getMessage());
                            //Can't write to log files.
                        }
                    }
                }
            }
        }
}
