package MANDSRegressionPack.File;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CSVReader {

    /**
     * This method is used to read the CSV file 
     * 
     * @param file - the path to the file relative to the application
     * 
     * @return - returns an ArrayList for the lines of the file
     */
    public static ArrayList<String> readFile(String file) {
        BufferedReader br = null;
        ArrayList<String> output = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                output.add(line);
                write("Reading line from file: "+line);
                line = br.readLine();
            }
            br.close();
        } catch (Exception ex) {
        }
        return output;
    }
    
    /**
     * This method loads the Pre-Advice Header details. The file should be named
     * preAdviceHeader.csv and should be stored in the csvData folder.
     * 
     * @return - returns a 2 dimensional array of strings containing the data  
     */
    
    public static String[][] getPAHeaderData(){
        BufferedReader br = null;
        String[][] output = new String[50][4];
        int row = 0;
        boolean firstLine = true;
        try {
            br = new BufferedReader(new FileReader("csvData/preAdviceHeader.csv"));
            String line = br.readLine();
            while (line != null) {
                if(firstLine){
                    firstLine = false;
                    line = br.readLine();
                    continue;
                }
                String[] parts = line.split(",");
                for(int i=0;i<4;i++){
                    output[row][i] = parts[i];
                }
                line = br.readLine();
                row++;
            }
            br.close();
        } catch (Exception ex) {
        }
        return output;
    }
    
    /**
     * This method opens the file that contains the last used tag for that day.
     * if the file does not exist it creates a new one.
     * 
     * @return - returns the tag 
     */
    
    public static String getTodaysLatestTag(){
        BufferedReader br = null;
        String output = "";
        int row = 0;
        boolean firstLine = true;
        try {
            File file = new File("csvData/"+createDay()+".csv");
            if(!file.exists()){
                LogWriter.writeFile("01", "csvData/"+createDay()+".csv");
            }
            br = new BufferedReader(new FileReader("csvData/"+createDay()+".csv"));
            output = br.readLine();   
            String newTag = "";
            int asNumber = Integer.parseInt(output);
            if(asNumber<9){
                newTag = "0"+(++asNumber);                   
            }else{
                newTag = ""+(++asNumber);
            }
            LogWriter.writeFile(newTag, "csvData/"+createDay()+".csv");
            br.close();
        } catch (Exception ex) {
            
        }
        return createDay()+output;
    }
    
    /**
     * This method loads the Pre-Advice Line details. The file should be named
     * preAdviceLine.csv and should be stored in the csvData folder.
     * 
     * @return - Returns a 2 dimensional array of strings containing the data
     */
    public static String[][] getPALineData(){
        BufferedReader br = null;
        String[][] output = new String[1000][10];
        int row = 0;
        boolean firstLine = true;
        try {
            
            br = new BufferedReader(new FileReader("csvData/PreAdviceLine.csv"));
            String line = br.readLine();
            while (line != null) {
                if(firstLine){
                    firstLine = false;
                    line = br.readLine();
                    continue;
                }
                String[] parts = line.split(",");
                for(int i=0;i<parts.length;i++){
                    output[row][i] = parts[i];
                }
                line = br.readLine();
                row++;
            }
            br.close();
        } catch (Exception ex) {
            write(ex.toString());
        }
        return output;
    }
    
    /**
     * This method loads the Pre-Advice Header details. The file should be named
     * preAdviceHeader.csv and should be stored in the csvData folder.
     * 
     * @return - Returns a 2 dimensional array of strings containing the data
     */
    
    public static String[][] getOrderHeaderData(){
        BufferedReader br = null;
        String[][] output = new String[1000][8];
        int row = 0;
        boolean firstLine = true;
        try {
            br = new BufferedReader(new FileReader("csvData/orderHeader.csv"));
            String line = br.readLine();
            while (line != null) {
                if(firstLine){
                    firstLine = false;
                    line = br.readLine();
                    continue;
                }
                String[] parts = line.split(",");
                for(int i=0;i<8;i++){
                    output[row][i] = parts[i];
                }
                line = br.readLine();
                row++;
            }
            br.close();
        } catch (Exception ex) {
        }
        return output;
    }
    
    /**
     * This method loads the Order Line details. The file should be named
     * orderLine.csv and should be stored in the csvData folder.
     * 
     * @return - Returns a 2 dimensional array of strings containing the data
     */
    
    public static String[][] getOrderLineData(){
        BufferedReader br = null;
        String[][] output = new String[1000][8];
        int row = 0;
        boolean firstLine = true;
        try {
            br = new BufferedReader(new FileReader("csvData/orderLine.csv"));
            String line = br.readLine();
            while (line != null) {
                if(firstLine){
                    firstLine = false;
                    line = br.readLine();
                    continue;
                }
                String[] parts = line.split(",");
                for(int i=0;i<8;i++){
                    try{
                        output[row][i] = parts[i];
                    }catch(ArrayIndexOutOfBoundsException ex){
                        output[row][i] = "";
                    }
                }
                line = br.readLine();
                row++;
            }
            br.close();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return output;
    }
    
    /**
     * This method is used to create a string representing the day to be used 
     * for pallet tags.
     * 
     * @return - date string YYYYMMDD 
     */
    
    private static String createDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date();
        String strDate = sdf.format(now);
        return strDate;
    }
    
    /**
     * This method counts the number of lines in the header file. The file should be named
     * orderHeader.csv and should be stored in the csvData folder.
     * 
     * @return - number of rows 
     */
    
    public static int getNumberOfOrderHeaders(){
        BufferedReader br = null;
        String[][] output = new String[1000][7];
        int row = 0;
        boolean firstLine = true;
        try {
            br = new BufferedReader(new FileReader("csvData/orderHeader.csv"));
            String line = br.readLine();
            while (line != null) {
                if(firstLine){
                    firstLine = false;
                    line = br.readLine();
                    continue;
                }
                line = br.readLine();
                row++;
            }
            br.close();
        } catch (Exception ex) {
        }
        return row;
    }
    
    private static void write(String string){
        LogWriter.writeLogEntry(string);
        System.out.println(string);
    }
    
    /**
     * This method is used to count the row inthe Pre Advice header file.
     * The file shoud be named preAdviceHeader.csv and should be stored in the csvData folder
     * 
     * @return - Returns a 2 dimensional array of strings containing the data
     */

    public static int getNumberOfPreAdviceHeaders() {
        BufferedReader br = null;
        String[][] output = new String[50][4];
        int row = 0;
        boolean firstLine = true;
        try {
            br = new BufferedReader(new FileReader("csvData/preAdviceHeader.csv"));
            String line = br.readLine();
            while (line != null) {
                if(firstLine){
                    firstLine = false;
                    line = br.readLine();
                    continue;
                }
                line = br.readLine();
                row++;
            }
            br.close();
        } catch (Exception ex) {
        }
        return row;
    }

}
