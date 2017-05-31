/*
 * The MIT License
 *
 * Copyright 2017 Zyun.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package MonsterQuest.util;

import MonsterQuest.MonsterQuestMain;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/** This class is used for logging.
 *
 * @author Zyun
 */
public class Logging {
    /** 
     * The filename of the log file
     */
    private String logString;
    
    /**
     * Determines whether the file can be opened or not
     */
    private boolean openable = true;
    
    //The IDs of the message type

    /**
     */
    public final static int INFO = 0;
    
    /**
     *
     */
    public final static int WARINING = 1;

    /**
     *
     */
    public final static int ERROR = 2;

    /**
     *
     */
    public final static int ALERT = 3;
    
    /** This inits the whole logging thing
     * @param logString this is the file to load
     */
    public Logging (String logString) {
        this.logString = logString;
        File logFile = new File (System.getProperty("user.dir") + this.logString);

        //Check if file is bigger than 100 kb, delete the file and start over.
        if (logFile.length() > (1024 * 100)) {
            logFile.delete();
        }

        //Check if file exists
        if (!logFile.exists()) {
            //Create new file.
            try {
                logFile.createNewFile();
            } catch (IOException ioe) {
                //File cannot be opened. Nothing we can do, except stop file to be opened later
                openable = false;
            }
        }

        if (openable) {

            //The thread for logging, makes it faster
            Runnable thread = () -> {
                FileWriter logFileOutput = null;
                BufferedWriter logFileWriter = null;
                //Create Time Stamp
                Date timeStamp = new Date();
                //Say that a  new log has started
                String content = ("--- NEW LOG STARTED [" + timeStamp + "]---");
                try {
                    //To write or not to write
                    if (MonsterQuestMain.PRINT_TO_LOGFILE) {
                        logFileOutput = new FileWriter(System.getProperty("user.dir") + this.logString, true);
                        logFileWriter = new BufferedWriter(logFileOutput);
                        logFileWriter.write(content);
                        logFileWriter.newLine();
                    }
                } catch (IOException ex) {
                }
                try {
                    if (logFileWriter != null)
                        logFileWriter.close();
                    if (logFileOutput != null)
                        logFileOutput.close();
                    System.gc();
                } catch (IOException ex) {
                    //Just clear it up, nothing we can do.
                    System.gc();
                }

            };

            //Start thread for logging
            Thread print = new Thread (thread);
            print.start();
        }
    }
    
    /** This will take <code>text</code> as a string, append it to the beginning of the text, and deal with it accodingly
     * @param text this is the text for the logging
     */
    public void log (String text) {
        //This is basically a copy of the constructor, just that you can change the text you write, not just --- NEW LOG STARTED [] ---
        if (openable) {

            //Thread for logging. makes it faster
            Runnable thread = () -> {
                FileWriter logFileOutput = null;
                BufferedWriter logFileWriter = null;
                //Create Time Stamp
                Date timeStamp = new Date();

                //The date, and the text
                String content = ("[" + timeStamp + "]{" + getLogfileName() + "}(" + INFO + "): " + text);

                //Check if to write to console
                if (MonsterQuestMain.DEBUG)
                    System.out.println(content);
                try {
                    //Check if write to logfile
                    if (MonsterQuestMain.PRINT_TO_LOGFILE) {
                        logFileOutput = new FileWriter(System.getProperty("user.dir") + this.logString, true);
                        logFileWriter = new BufferedWriter(logFileOutput);
                        //Write to file
                        logFileWriter.write(content);
                        logFileWriter.newLine();
                    }
                } catch (IOException ex) {
                }
                try {
                    if (logFileWriter != null)
                        logFileWriter.close();
                    if (logFileOutput != null)
                        logFileOutput.close();
                    System.gc();
                } catch (IOException ex) {
                    //Just clear it up, nothing we can do.
                    System.gc();
                }

            };

            //Start the logging in thread
            Thread print = new Thread (thread);
            print.start();
        }
    }
    
    /** This will take <code>text</code> as a string, append it to the beginning of the text, and deal with it accodingly
     * @param text this is the text for the logging
     * @param type the type of message
     */
    public void log (String text, int type) {
        //This is basically a copy of the constructor, just that you can change the text you write, not just --- NEW LOG STARTED [] ---
        if (openable) {

            //Thread for logging. makes it faster
            Runnable thread = () -> {
                FileWriter logFileOutput = null;
                BufferedWriter logFileWriter = null;
                //Create Time Stamp
                Date timeStamp = new Date();

                //The date, and the text
                String content = ("[" + timeStamp + "]{" + getLogfileName() + "}(" + type + "): " + text);

                //Check if to write to console
                if (MonsterQuestMain.DEBUG)
                    System.out.println(content);
                try {
                    //Check if write to logfile
                    if (MonsterQuestMain.PRINT_TO_LOGFILE) {
                        logFileOutput = new FileWriter(System.getProperty("user.dir") + this.logString, true);
                        logFileWriter = new BufferedWriter(logFileOutput);
                        //Write to file
                        logFileWriter.write(content);
                        logFileWriter.newLine();
                    }
                } catch (IOException ex) {
                }
                try {
                    if (logFileWriter != null)
                        logFileWriter.close();
                    if (logFileOutput != null)
                        logFileOutput.close();
                    System.gc();
                } catch (IOException ex) {
                    //Just clear it up, nothing we can do.
                    System.gc();
                }

            };

            //Start the logging in thread
            Thread print = new Thread (thread);
            print.start();
        }
    }
    
    /**
     * Returns the name of the file that you are logging on.
     * @return the filename of the log
     */

    public String getLogfileName () {
        return this.logString;
    }
}
