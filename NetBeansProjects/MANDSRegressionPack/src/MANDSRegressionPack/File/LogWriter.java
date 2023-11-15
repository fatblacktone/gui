package MANDSRegressionPack.File;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LogWriter {

    /**
     * This method  writes a file with the given string and filename.
     * 
     * @param toWrite - the string to write to the file
     * @param fileName - the name of the file  to be written.
     */
    public static void writeFile(String toWrite, String fileName) {
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
            writer.write(toWrite);
            writer.close();
        } catch (Exception ex) {

        }
    }

    /**
     * This method is used to write to the log file located in the log directory.
     * 
     * @param toWrite - The string to add to the file 
     */
    public static void writeLogEntry(String toWrite) {
        try {
            String dateString = getDate().split(" ")[0].replace("-","");
            File file = new File("log/"+dateString + ".log");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
            Writer writer = new BufferedWriter(fileWriter);
            writer.write("[log ("+getDate()+")]  "+toWrite+"\n");
            writer.close();
        } catch (Exception ex) {

        }
    }

    /** 
     * This method is used to create a date string to be used for the log files.
     * 
     * @return - returns the dates string
     */
    private static String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        return strDate;
    }
    
    /**
     * This method is used to write to the log file located in the log directory.
     * 
     * @param toWrite - The string to add to the file 
     */
    public static void writePalletEntry(String toWrite) {
        try {
            String dateString = getDate().split(" ")[0].replace("-","");
            File file = new File("data/"+dateString + ".dat");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
            Writer writer = new BufferedWriter(fileWriter);
            writer.write(toWrite+"\n");
            writer.close();
        } catch (Exception ex) {

        }
    }
}
