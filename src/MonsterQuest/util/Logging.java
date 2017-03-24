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

/**
 *
 * @author Zyun
 */
public class Logging {
    private String logString;
    private boolean openable = true;
    
    public Logging (String logString) {
        this.logString = logString;
        //Start new log, but first check if the file is bigger than 128 kb.
        File logFile = new File (System.getProperty("user.dir") + this.logString);
        if (!logFile.exists()) {
            //Create new file.
            try {
                logFile.createNewFile();
            } catch (IOException ioe) {
                //File cannot be opened. Nothing we can do, except stop file to be opened later
                openable = false;
            }
        }
        if (logFile.length() > (1024 * 100)) {
            logFile.delete();
        }
        
        if (openable) {
            Runnable thread = () -> {
                FileWriter logFileOutput = null;
                BufferedWriter logFileWriter = null;
                //Create Time Stamp
                Date timeStamp = new Date();
                String content = ("--- NEW LOG STARTED [" + timeStamp + "]---");
                try {
                    logFileOutput = new FileWriter(System.getProperty("user.dir") + this.logString, true);
                    logFileWriter = new BufferedWriter(logFileOutput);
                    logFileWriter.write(content);
                    logFileWriter.newLine();
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
            Thread print = new Thread (thread);
            print.start();
        }
    }
    
    public void log (String text) {
        if (openable) {
            Runnable thread = () -> {
                FileWriter logFileOutput = null;
                BufferedWriter logFileWriter = null;
                //Create Time Stamp
                Date timeStamp = new Date();
                String content = ("[" + timeStamp + "]: " + text);
                if (MonsterQuestMain.DEBUG)
                    System.out.println(content);
                try {
                    logFileOutput = new FileWriter(System.getProperty("user.dir") + this.logString, true);
                    logFileWriter = new BufferedWriter(logFileOutput);
                    logFileWriter.write(content);
                    logFileWriter.newLine();
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
            Thread print = new Thread (thread);
            print.start();
        }
    }
    
    public String getLogString () {
        return this.logString;
    }
}
