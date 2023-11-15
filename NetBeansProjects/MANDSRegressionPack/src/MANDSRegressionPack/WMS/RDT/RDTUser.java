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

import java.text.SimpleDateFormat;
import java.util.Date;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Screen;
import MANDSRegressionPack.File.CSVReader;
import MANDSRegressionPack.File.CSVWriter;
import MANDSRegressionPack.File.LogWriter;
import MANDSRegressionPack.Interface.OrderLine;
import MANDSRegressionPack.Interface.PreAdviceHeader;
import MANDSRegressionPack.Interface.PreAdviceLine;
import MANDSRegressionPack.SQL.Database;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sikuli.script.ScreenImage;

/**
 *
 * @author Tone Walters (tone_walters@yahoo.com)
 */
public class RDTUser {

    private Screen screen = new Screen();
    private Database db = new Database();
    private int screenCaps = 1;
    private String username;

    public RDTUser(String username, Database db) {
        this.db = db;
        this.db.setApplicationUser(username);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        db.setApplicationUser(username);

    }

    /**
     * This method is used to log in to the RDT. It will use the username and
     * password passed as parameters.
     *
     * @param username - username of the user
     * @param password - password for the user
     * @return Returns true if the MaiMnu screen is displayed after login.
     */
    public boolean login(String username, String password) {
        write("Trying to log into the RDT ");
        boolean success = false;
        try {
            screen.click("images/startButton.png");
            type("putty" + Key.ENTER);
            wait("images/optimusRDT.png", 15);
            screen.doubleClick("images/optimusRDT.png");
            wait("images/rdt/login.png", 60);
            type(username + Key.TAB);
            type(password + Key.ENTER);
            if (screen.exists("images/rdt/maiMnu.png") != null) {
                success = true;
            } else {
                write("Failed to login to RDT");
            }
        } catch (FindFailed ex) {
            write(ex.toString());
            screenCap("RDTFindFailed" + screenCaps++);
        }
        return success;
    }

    /**
     * This method is used to navigate from the MaiMnu screen to the RcvPreEnt
     * screen.
     *
     * @return - Returns true if RcvPreEnt screen is displayed.
     */
    public boolean navigateToPreAdviceReceiving() {
        LogWriter.writeLogEntry("Navigating to Pre-Advice Receiving");
        boolean navigationSuccess = false;
        try {
            type("2112" + Key.ENTER);
            wait("images/rdt/rcvPreEnt.png", 10);
            navigationSuccess = true;
        } catch (FindFailed ex) {
            write(ex.toString());
            screenCap("RDTFindFailed" + screenCaps++);
        }
        return navigationSuccess;
    }

    /**
     * This method is used to log the user out of the putty session
     *
     * @return - returns true if logout is successful.
     */
    public boolean logout() {
        boolean loggedOut = false;
        while (true) {
            if (screen.exists("images/rdt/maiMnu.png", 3) == null) {
                type(Key.F10);
            } else {
                type("3" + Key.ENTER);
                break;
            }
        }
        try {
            wait("images/rdt/logIn.png", 30);
            screen.type(Key.F4, KeyModifier.ALT);
            type(Key.SPACE);
            loggedOut = true;
        } catch (FindFailed ex) {
            write(ex.toString());
            screenCap("RDTFindFailed" + screenCaps++);
        }
        return loggedOut;
    }

    /**
     * This method is used to complete a putaway. It takes a 2 dimensional array
     * containing the tag and sku data.
     *
     * @param tagsAndSkus - array containing the tag and sku data
     * @return - returns true if putaway is successful.
     */
    public boolean putaway(String[][] tagsAndSkus) {
        boolean putaway = false;
        int currentTag = 0;

        while (tagsAndSkus[currentTag][0] != null) {
            try {
                wait("images/rdt/putEnt.png", 5);
            } catch (FindFailed ex) {
                write(ex.toString());
                screenCap("RDTFindFailed" + screenCaps++);
            }
            type(tagsAndSkus[currentTag][0]);
            type(Key.ENTER);
            if (screen.exists("images/rdt/putCmp.png", 5) != null) {
                try {
                    //wait("images/rdt/putCmp.png", 10);
                    type(Key.ENTER);
                    if (screen.exists("images/rdt/skuCnf.png", 2) != null) {
                        type(tagsAndSkus[currentTag][1] + Key.ENTER);
                        wait("images/rdt/chkTo.png", 2);
                        type(db.getPutAwayCheckString() + Key.ENTER);
                        putaway = true;
                        //break;
                    } else {
                        type(Key.ENTER);
                        wait("images/rdt/putCmp.png", 2);
                        type(Key.F10);
                        wait("images/rdt/putEnt.png", 2);
                        currentTag++;
                        putaway = true;
                        continue;
                    }
                } catch (FindFailed ex) {
                    write(ex.toString());
                    screenCap("RDTFindFailed" + screenCaps++);
                }
            } else {
                type(Key.ENTER);
            }
            currentTag++;
        }
        return putaway;
    }

    /**
     * This method is used to navigate to the putaway screen on the RDT.
     *
     * @return - returns true if navigation successful.
     */
    public boolean navigateToPutaways() {
        LogWriter.writeLogEntry("Navigating to Putaways");
        boolean navigationSuccess = false;
        try {
            type("221" + Key.ENTER);
            wait("images/rdt/putEnt.png", 60);
            navigationSuccess = true;
        } catch (FindFailed ex) {
            write(ex.toString());
            screenCap("RDTFindFailed" + screenCaps++);
        }
        return navigationSuccess;
    }

    /**
     * This method is used to navigate to the relocate screen on the RDT.
     *
     * @return - returns true if navigation successful.
     */
    public boolean navigateToRelocate() {
        LogWriter.writeLogEntry("Navigating to Relocate");
        boolean navigationSuccess = false;
        try {
            type("242" + Key.ENTER);
            wait("images/rdt/relNewEnt.png", 60);
            navigationSuccess = true;
        } catch (FindFailed ex) {
            write(ex.toString());
            screenCap("RDTFindFailed" + screenCaps++);
        }
        return navigationSuccess;
    }

    /**
     * This method is used to navigate to the container picking screen on the
     * RDT
     *
     * @return - returns true if navigation is successful
     */
    public boolean navigateToContainerPicking() {
        LogWriter.writeLogEntry("Navigating to Container Picking");
        boolean navigationSuccess = false;
        try {
            type("2313" + Key.ENTER);
            wait("images/rdt/pckEnt.png", 20);
            navigationSuccess = true;
        } catch (FindFailed ex) {
            write(ex.toString());
            screenCap("RDTFindFailed" + screenCaps++);
        }
        return navigationSuccess;
    }

    /**
     * This method is used to container pick. It will detect if the order is a
     * simple container pick or a trolley pick.
     *
     * @param orderDetails - array of data for the order to be picked.
     * @return - returns true if pick was successful
     */
    public boolean containerPicking(String orderNumber) throws FindFailed {

        boolean orderPicked = false;
        ArrayList<String> moveTasks = db.getMoveTasksForOrder(orderNumber);
        int numberOfTasks = db.getNumberOfMoveTasks(orderNumber);
        
        OrderLine ol = new OrderLine();
        ol.nextRecord();
        pckEnt(orderNumber);
        for (int numberOfLists = 0; numberOfLists < db.getNumberOfListsForOrder(orderNumber); numberOfLists++) {
            System.out.println(db.getNumberOfListsForOrder(orderNumber) + "::::::::::");
            if (db.isTrolleyPick(orderNumber, ol.getLineID())) {
                trolleyPick(orderNumber);
            }
        }
        orderPicked = true;
        return orderPicked;
    }

    public void trolleyPick(String orderNumber) {
        try {
            int numberOfTasks = 0;
            write("Trolley pick: " + orderNumber);
            
            
            
            wait("images/rdt/trolleyPick.png", 8);
            type(Key.ENTER);

            questEnt();
            int actualNumberOfTasks = db.getNumberOfMoveTasksForList(db.getListOfCurrentTask());
            write("Actual number of tasks on list: "+actualNumberOfTasks+"");
            numberOfTasks = actualNumberOfTasks;
            write("List ID: " + db.getListOfCurrentTask());
            for (int i = 0; i < numberOfTasks; i++) {
                tucSkuCon();
                pckSubConTro(orderNumber);
                skuCnf();
                trlSltCnf();
            }
            for (int i = 0; i < numberOfTasks; i++) {
                pckPalTo();
                chkTo();
            }

        } catch (FindFailed ex) {
            write(ex.toString());
            screenCap("RDTFindFailed" + screenCaps++);
        } catch (Exception ex) {
            write(ex.toString());
            screenCap("RDTException" + screenCaps++);
        }
    }

    public boolean arrayContains(String[][] array, String listID) {

        return false;
    }

    public void pckEnt(String orderNumber) throws FindFailed {
        wait("images/rdt/pckEnt.png", 10);
        type(orderNumber + Key.ENTER);
    }

    public void questEnt() throws FindFailed {
        wait("images/rdt/quesEnt.png", 3);
        type(Key.F4);
        wait("images/rdt/printerAnswer.png", 3);
        type("PRINTER1" + Key.ENTER);
    }

    public void tucSkuCon() throws FindFailed {
        wait("images/rdt/tucSkuCon", 3);
        type(Key.ENTER);
    }

    public void pckSubConTro(String orderNumber) throws FindFailed {
        wait("images/rdt/pckSubConTro.png", 3);
        type(Key.DOWN);
        type(Key.DOWN);
        type(getQtyToMove());

        if (db.getOrderType(orderNumber).equals("INTAIR") || db.getOrderType(orderNumber).equals("INTSEA")) {
            type(Key.DOWN);
            type("01012017");
            type("01012020");
        }
        type(Key.ENTER);

    }

    public void pckSubConCmp(String orderNumber) throws FindFailed {
        wait("images/rdt/pckSubConCmp.png", 3);
        type(Key.DOWN);
        type(Key.DOWN);
        type(getQtyToMove());

        if (db.getOrderType(orderNumber).equals("INTAIR") || db.getOrderType(orderNumber).equals("INTSEA")) {
            type(Key.DOWN);
            type("01012017");
            type("01012020");
        }
        type(Key.ENTER);
    }

    public void skuCnf() throws FindFailed {
        wait("images/rdt/skuCnf.png", 5);
        type(db.getSkuFromCurrentMoveTask() + Key.ENTER);
    }

    public void trlSltCnf() throws FindFailed {
        wait("images/rdt/trlSltCnf.png", 3);
        CSVWriter.writeToPalletFile(db.getContainerOfCurrentTask());
        type(db.getContainerOfCurrentTask() + Key.ENTER);
    }

    public void pckConCnf() throws FindFailed {
        wait("images/rdt/pckConCnf.png", 3);
        CSVWriter.writeToPalletFile(db.getContainerOfCurrentTask());
        type(db.getContainerOfCurrentTask() + Key.ENTER);
    }

    public void pckPalTo() throws FindFailed {
        wait("images/rdt/pckPalTo.png", 15);
        type(Key.ENTER);
    }

    public void chkTo() throws FindFailed {
        wait("images/rdt/chkTo.png", 15);
        type(db.getCheckStringForToLocation() + Key.ENTER);
    }

    /**
     * This method is used to relocate inventory. It takes a tag for the
     * inventory to be moved and a location to move the inventory to.
     *
     * @param tag - the tag of the inventory
     * @param location - location to be moved to
     * @return - returns true if the relocate was successful.
     */
    public boolean relocate(String tag, String location) {
        type(tag + Key.ENTER);
        try {
            wait("images/rdt/chkFrm.png", 5);
            type(db.geCheckString(location) + Key.ENTER);
            wait("images/rdt/relNewCmp.png", 5);
            String results[][] = db.getEmptyLocationsAndCheckString();
            type(results[0][0] + Key.ENTER);
        } catch (FindFailed ex) {
            write(ex.toString());
            screenCap("RDTFindFailed" + screenCaps++);
        }
        return false;
    }

    public void receivePreAdvice(String preAdviceId, PreAdviceLine pal, String username, String password) {
        pal.nextRecord();
        while (!pal.getPreAdviceID().equals(preAdviceId)) {
            pal.nextRecord();
        }
        try {
            login(username, password);
            navigateToPreAdviceReceiving();
            rcvPreEnt(preAdviceId);
            for (int i = 0; i < Integer.parseInt(db.getNumberOfPaLines(preAdviceId)); i++) {
                rcvLinEnt(pal.getSku());
                rcvPreCmp(pal.getQtyDue(), pal.getCaseRatio(), db.isCnESku(pal.getSku()), db.getABV(pal.getSku()), pal.getSku());
                if (screen.exists("images/rdt/rcvVol.png") != null) {
                    rcvVol();
                }
                Thread.sleep(500);
                pal.nextRecord();
            }
            paComplete();
        } catch (FindFailed ex) {

        } catch (InterruptedException ex) {

        }

    }

    /**
     * This method is used to complete the RcvPreEnt screen on the RDT. It will
     * enter the Pre-Advice ID passed in as the parameter and confirm that the
     * next screen is displayed as expected.
     *
     * @param preAdviceId - Pre-Advice ID
     * @return - Returns true if correct screen is displayed.
     */
    public boolean rcvPreEnt(String preAdviceId) {
        LogWriter.writeLogEntry("Entering Pre-Advice ID");
        boolean navigationSuccess = false;
        try {
            type(preAdviceId + Key.ENTER);
            wait("images/rdt/rcvLinEnt.png", 60);
            navigationSuccess = true;
        } catch (FindFailed ex) {
            write(ex.toString());
            screenCap("RDTFindFailed" + screenCaps++);
        }
        return navigationSuccess;
    }

    public void rcvLinEnt(String sku) {
        write("Receiving PA Line ");
        type(sku + Key.ENTER);//enter SKU
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
        }
        type(Key.ENTER);
    }

    public void rcvPreCmp(String qtyDue, String caseRatio, boolean ce, String abv, String sku) {
        try {
            wait("images/rdt/rcvPreCmp.png", 60);
            type("REC001" + Key.DOWN);
            type(CSVReader.getTodaysLatestTag());
            type(Key.F4);
            wait("images/rdt/2of3.png", 60);
            Thread.sleep(1000);
            type(qtyDue + Key.DOWN);
            type(caseRatio);
            type(Key.DOWN);
            type(Key.DOWN);
            type("PALLET");
            if (ce) {
                type(Key.DOWN);
                type("2014");
                type(abv);
            }
            type(Key.F4);
            wait("images/rdt/3of3.png", 60);
            type(Key.DOWN);
            type(Key.DOWN);
            type(day());
            type(month());
            type((Integer.parseInt(year()) + 5) + "");
            if (db.isCnEDutyStamp(sku)) {
                type("Y");
            }
            type(Key.ENTER);
        } catch (FindFailed ex) {

        } catch (InterruptedException ex) {

        }

    }

    public void rcvVol() throws FindFailed {
        wait("images/rdt/rcvVol.png", 60);
        type("Y" + Key.ENTER);
    }

    public void paComplete() throws FindFailed, InterruptedException {
        wait("images/rdt/paComplete.png", 5);
        Thread.sleep(500);
        type(Key.ENTER);
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

    private String getQtyToMove() {
        int qtyToMove = Integer.parseInt(db.getCurrentMoveTaskQty());
        int ratio = db.getRatio();
        int fullCases = qtyToMove / ratio;
        int eaches = qtyToMove % ratio;
        String qtyToMoveString = "";
        if (fullCases > 0) {
            qtyToMoveString += fullCases + "c ";
        }
        if (eaches > 0) {
            qtyToMoveString += eaches + "e";
        }
        return qtyToMoveString;
    }

    private void write(String toWrite) {
        System.out.println(toWrite);
        LogWriter.writeLogEntry(toWrite);
    }

    public boolean navigateToMarshal() {
        boolean navigationSuccess = false;
        try {
            type("2" + Key.ENTER);
            wait("images/rdt/usrMnu.png", 5);
            type("6" + Key.ENTER);
            wait("images/rdt/marEnt", 5);
            navigationSuccess = true;
        } catch (FindFailed ex) {
            write(ex.toString());
            screenCap("RDTFindFailed" + screenCaps++);
        }
        return navigationSuccess;
    }

    public boolean marshalPallets() {
        boolean palletsMarshalled = false;
        ArrayList<String> pallets = CSVReader.readFile("csvData/orderPallets.csv");
        for (int i = 0; i < pallets.size(); i++) {
            if (db.isMarshalTask(pallets.get(i))) {
                marEnt(pallets.get(i));
                marCmp();
                locMveStg();
                try {
                    chkTo();
                } catch (FindFailed ex) {

                }
            }
            palletsMarshalled = true;
        }
        return palletsMarshalled;
    }

    public boolean marEnt(String palletID) {
        try {
            wait("images/rdt/marEnt", 5);
            type(Key.DOWN);
            type(palletID + Key.ENTER);
        } catch (FindFailed ex) {
            write(ex.toString());
            screenCap("RDTFindFailed" + screenCaps++);
        }

        return false;
    }

    public void marCmp() {
        try {
            wait("images/rdt/marCmp.png", 5);
            type(Key.ENTER);
        } catch (FindFailed ex) {
            write(ex.toString());
            screenCap("RDTFindFailed" + screenCaps++);
        }
    }

    public void locMveStg() {
        try {
            wait("images/rdt/locMveStg.png", 10);
            type(Key.ENTER);
            wait("images/rdt/marEnt", 10);
        } catch (FindFailed ex) {
            write(ex.toString());
            screenCap("RDTFindFailed" + screenCaps++);
        }
    }

    public void screenCap(String filename) {
        ScreenImage img = screen.capture();
        String tempFilename = img.getFile();
        write("Screen Cap FILE::::::::" + tempFilename);
        //write("Screen cap new filename:"+"images/error/" + tempFilename.split("/")[6]);
        File temp = new File(tempFilename);
        try {
            CSVWriter.copyFile(temp, new File("log/" + filename + ".png"));
        } catch (IOException ex) {

        }
    }

    private void type(String toType) {
        screen.type(toType);
        write("Typing: " + toType);
    }

    private void wait(String imageFile, int wait) throws FindFailed {
        screen.wait(imageFile, wait);
        write("Waiting for image: " + imageFile + " for " + wait + " seconds");
    }
}
