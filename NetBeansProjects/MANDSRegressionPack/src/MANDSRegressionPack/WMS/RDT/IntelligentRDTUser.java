/*
 * Copyright (C) 2017 P9134107
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package MANDSRegressionPack.WMS.RDT;

import MANDSRegressionPack.File.CSVReader;
import MANDSRegressionPack.File.LogWriter;
import MANDSRegressionPack.SQL.Database;
import MANDSRegressionPack.WMS.WebUser;
import MANDSRegressionPack.enums.RDTTask;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;



/**
 *
 * @author Tone Walters (tone_walters@yahoo.com)
 */
public class IntelligentRDTUser {
    
    private String username;
    private String password;
    private String preAdviceId;
    private String sku;
    
    private Screen screen = new Screen();
    private Database db = new Database();
    
    private RDTTask currentTask;

    public IntelligentRDTUser(String username, String password) {
        this.username = username;
        this.password = password;
        if(!db.connect("jdbc:oracle:thin:@hlxd0dc003.unix.marksandspencerdev.com:1521:WMSBACD", "dcsdba", "jdadcbacd")){
            System.err.println("Failed to connect");
        }
    }

    public RDTTask getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(RDTTask currentTask) {
        this.currentTask = currentTask;
    }

    public String getPreAdviceId() {
        return preAdviceId;
    }

    public void setPreAdviceId(String preAdviceId) {
        this.preAdviceId = preAdviceId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
    
    
    
    
    
    
    
    public boolean doScreen(){
        
        String rdtScreen = db.executeStatmentSingleResponse("select RDT_SCREEN from (select * from RDT_SCREEN_LOG where USER_ID = '"+username+"' order by LAST_DSTAMP desc) where rownum = 1");
        System.out.println(rdtScreen);
        while(rdtScreen.contains("  ")){
            rdtScreen = rdtScreen.replace("  ", " ");
        }
        String[] parts = rdtScreen.split(" ");
        
        System.out.println(parts[2]);
        switch(parts[2]){
            case "MaiMnu":
                maiMnu();
                break;
            case "UsrMnu":
                usrMnu();
                break;
            case "RcvMnu":
                rcvMnu();
                break;
            case "BasRcvMnu":
                basRcvMnu();
                break;
            case "RcvPreEnt":
                rcvPreEnt();
                break;
            case "RcvPreCmpPre-Advice:":
                rcvPreCmp();
                break;
            case "MrgPrgMsgPre":
                mrgPrgMsg();
                break;
        }
        
        return false;
    }

    private void maiMnu() {
        write("At the main menu");
        switch(currentTask){
            case PRE_ADVICE_RECEIVE:
                type("2");
                type(Key.ENTER);
                break;
            case PUTAWAY:
                type("2");
                type(Key.ENTER);
                break;
        }
    }

    private void usrMnu() {
        write("At the user defined menu");
        switch(currentTask){
            case PRE_ADVICE_RECEIVE:
                type("1");
                type(Key.ENTER);
                break;
            case PUTAWAY:
                type("2");
                type(Key.ENTER);
                break;
            case PICKING:
                type("3");
                type(Key.ENTER);
                break;
        }
    }
    
    public void rcvMnu(){
        write("At the receiving menu");
        switch(currentTask){
            case PRE_ADVICE_RECEIVE:
                type("1");
                type(Key.ENTER);
                break;
            case BLIND_RECEIVE:
                type("1");
                type(Key.ENTER);
                break;
            default:
                write("Something went wrong. I should not be here");
        }
    }
    
    public void basRcvMnu(){
        write("At the basic receiving screen");
        switch(currentTask){
            case PRE_ADVICE_RECEIVE:
                type("2");
                type(Key.ENTER);
                break;
            case BLIND_RECEIVE:
                type("1");
                type(Key.ENTER);
                break;
            default:
                write("Something went wrong. I should not be here");
        }
    }
    
    public void rcvPreEnt(){
        write("At the Pre Advice entry screen");
        type(preAdviceId);
        type(sku);
        type(Key.ENTER);    
    }
    
    public void mrgPrgMsg(){
        App.open("C:\\Program Files\\Internet Explorer\\iexplore.exe http://hlxd0dc003.unix.marksandspencerdev.com:8880/wmsbacd/UserLogin.html");
        try{
            screen.wait("images/loginPane.png",30);
            WebUser wms = new WebUser();
            wms.logIn("OPTIMUS", "1234");
            wms.createCnEConsignment();
            wms.cneConsignmentLinking(preAdviceId, db.getLastCnEconsignment());
        }catch(FindFailed ex){
            
        }
        //System.setProperty("webdriver.ie.driver","C:\\Users\\P9134107\\Documents\\IEDriverServer.exe");
        //WebDriver driver = new InternetExplorerDriver();
        //driver.get("http://hlxd0dc003.unix.marksandspencerdev.com:8880/wmsbacd/UserLogin.html");
    }
    
    
    public void rcvPreCmp(){
        type("REC001");
        type(Key.DOWN);
        type(year()+month()+day()+CSVReader.getTodaysLatestTag());
        type(Key.F4);
        type(db.getCurrentMoveTaskQty());
        type(db.executeStatmentSingleResponse("select * from SKU_CONFIG where CONFIG_ID like '"+sku+"%'"));
        type(Key.DOWN);
        type(Key.DOWN);
        type("PALLET");
        type(Key.F4);        
        type(Key.DOWN);
        type(Key.DOWN);
        type(day());
        type(month());
        type(year()+5);
        
    }
    private void type(String string){
        write(string);
        screen.type(string);
    }
    
    
    private void write(String string) {
        LogWriter.writeLogEntry(string);
        System.out.println(string);
    }
    
    /**
     * This method is used to return a string representing the day of the month.
     *
     * @return - the date string dd
     */
    private String day() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        Date now = new Date();
        String strDate = sdf.format(now);
        return strDate;
    }

    /**
     * This method is used to return a string representing the Month of the year
     *
     * @return - the date string MM
     */
    private String month() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        Date now = new Date();
        String strDate = sdf.format(now);
        return strDate;
    }

    /**
     * This method is used to return a string representing the year.
     *
     * @return - the date string dd
     */
    private String year() {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY");
        Date now = new Date();
        String strDate = sdf.format(now);
        return strDate;
    }

}
