package Zyun.Lam.Game.MonsterQuest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Zyun
 */

//This is just a basic one... Will 
public class SystemLog {
    private static String log = "No Problem";
    //Seceptions that we will need
    public static final int UNKNOWN_EXCEPTION = 0;
    public static final int FILE_NOT_FOUND = 1;
    public static final int PARSING_EXCEPTION = 2;
    public static final int IOEXCEPTION = 3;

    public static void log (String text) {
        //Create Time Stamp
        //Put it into a thread for speed issues. Doesn't affect anything yrt, but it may.
        Runnable thread = () -> {
            FileWriter logFileOutput = null;
            BufferedWriter logFileWriter = null;
            Date timeStamp = new Date();
            String content = ("[" + timeStamp + "] " + text);
            try {
                logFileOutput = new FileWriter("system.log", true);
                logFileWriter = new BufferedWriter(logFileOutput);
                logFileWriter.write(content);
                logFileWriter.newLine();
            } catch (IOException e) {
                //No point killing the whole program for the sake of a logging problem...
                e.printStackTrace();
            }
            
            try {
                if (logFileWriter != null)
                    logFileWriter.close();
                if (logFileOutput != null)
                    logFileOutput.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            //Clear these variables, just in case
            logFileOutput = null;
            logFileWriter = null;
            
        };
        Thread print = new Thread (thread);
        print.start();
    }
    
    public static void startNewLog () {
            FileWriter logFileOutput = null;
            BufferedWriter logFileWriter = null;
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
                //As the file is probally going to fail to do do anything to the file, no writing to file...
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
                //Clear these variables, just in case
                logFileOutput = null;
                logFileWriter = null;
                
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
            } else {
                try {
                    logFile.createNewFile();
                    System.out.println("Created new file, as it did not exist.");
                } catch (IOException e) {
                    System.err.println("Unable to open log file: " + e.getMessage());
                }                
            }
        }
    
    public static void log (String text, int errorNumber) {
        Runnable thread = () -> {
            FileWriter logFileOutput = null;
            BufferedWriter logFileWriter = null;
            //Create Time Stamp
            Date timeStamp = new Date();
            String content = ("[" + timeStamp + "] (" + errorNumber + "): " + text);
            try {
                logFileOutput = new FileWriter("system.log", true);
                logFileWriter = new BufferedWriter(logFileOutput);
                logFileWriter.write(content);
                logFileWriter.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (logFileWriter != null)
                    logFileWriter.close();
                if (logFileOutput != null)
                    logFileOutput.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        };
        Thread print = new Thread (thread);
        print.start();
    }
    
    public static void log (String text, int errorNumber, long threadID) {
        Runnable thread = () -> {
            FileWriter logFileOutput = null;
            BufferedWriter logFileWriter = null;
            //Create Time Stamp
            Date timeStamp = new Date();
            String content = ("[" + timeStamp + "] (" + errorNumber + "),  Thread " + threadID + ": " + text);
            try {
                logFileOutput = new FileWriter("system.log", true);
                logFileWriter = new BufferedWriter(logFileOutput);
                logFileWriter.write(content);
                logFileWriter.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (logFileWriter != null)
                    logFileWriter.close();
                if (logFileOutput != null)
                    logFileOutput.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        };
        Thread print = new Thread (thread);
        print.start();
    }
        
    static void printStackTrace (final Exception ex) {
        Runnable thread = () -> {
            FileWriter logFileOutput = null;
            BufferedWriter logFileWriter = null;
            PrintWriter file = new PrintWriter(logFileOutput);
            //Create Time Stamp
            Date timeStamp = new Date();
            String content = ("[" + timeStamp + "]");
            try {
                logFileOutput = new FileWriter("system.log", true);
                logFileWriter = new BufferedWriter(logFileOutput);
                ex.printStackTrace(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (logFileWriter != null)
                    logFileWriter.close();
                if (logFileOutput != null)
                    logFileOutput.close();
            } catch (IOException exc) {
                exc.printStackTrace();
            }
            
        };
        Thread print = new Thread (thread);
        print.start();
    }
}