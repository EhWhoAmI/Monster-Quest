package Zyun.Lam.Game.MonsterQuest;

import java.io.BufferedWriter;
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
}
