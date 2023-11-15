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
package MANDSRegressionPack.WMS;

import MANDSRegressionPack.File.CSVWriter;
import org.sikuli.script.Env;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Location;
import org.sikuli.script.Screen;
import MANDSRegressionPack.File.LogWriter;
import java.io.File;
import java.io.IOException;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.ScreenImage;

/**
 *
 * @author Tone Walters (tone_walters@yahoo.com)
 */
public class WebUser {

    private Screen screen = new Screen();
    private int screenCaps = 1;

    /**
     * This method logs in to the application using the username and password
     * passed as the parameters.
     *
     * @param username - username of the user
     * @param password - password for the user
     * @return - Returns true if the login was successful.
     */
    public boolean logIn(String username, String password) {
        boolean logInSuccess = false;
        LogWriter.writeLogEntry("Attempting to log into the application.");
        try {
            screen.wait("images/loginPane.png", 60);
            screen.click("images/userID.png");
            screen.type(username);
            screen.type(Key.TAB);
            screen.type(password + Key.ENTER);
            screen.wait("images/banner.png", 15);
            LogWriter.writeLogEntry("Login successful");
            logInSuccess = true;
        } catch (FindFailed ex) {
            write(ex.toString());
            screenCap("WMSFindFailed" + screenCaps++);
        }
        return logInSuccess;
    }

    /**
     * This method is used to open the Pre Advice Header screen in the
     * application.
     *
     * @return - Returns true if screen opens
     */
    public boolean openPreAdviceHeaderScreen() {
        return false;
    }

    /**
     * This method is used to open the Pre Advice Line screen in the
     * application.
     *
     * @return - Returns true if the screen shows
     */
    public boolean openPreAdviceLineScreen() {
        return false;
    }

    /**
     * This method is used to open the Pre Advice Header screen in the
     * application and enters the pre advice id to display the record.
     *
     * @param preAdviceId - Pre-Advice ID
     * @return - Returns true if the record is displayed.
     */
    public boolean openPreAdviceHeaderScreen(String preAdviceId) {
        return false;
    }

    /**
     * This method is used to open the Pre Advice Line screen in the application
     * and enters the pre advice id to display the record.
     *
     * @param preAdviceId - Pre-Advice ID
     * @return Returns true if the record is displayed.
     */
    public boolean openPreAdviceLineScreen(String preAdviceId) {
        return false;
    }

    /**
     * This method is used to open the Inventory screen in the application.
     *
     * @return - returns true if the screen is successfully opened.
     */
    public boolean openInventoryScreen() {
        boolean inventoryScreenOpened = false;
        LogWriter.writeLogEntry("Attempting to open the Inventory Query Screen.");
        try {
            screen.click("images/Menu/menuData.png", 10);
            screen.hover("images/Menu/Data/dataInventory.png");
            screen.hover("images/Menu/Data/Inventory/inventoryCandEConsignor.png");
            screen.click("images/Menu/Data/Inventory/inventoryInventory.png");
            screen.wait("images/Menu/Data/Inventory/inventoryQuery", 10);
            LogWriter.writeLogEntry("Successfully opened the Inventory Query Screen.");
            inventoryScreenOpened = true;
        } catch (FindFailed ex) {
            write(ex.toString());
            screenCap("WMSFindFailed" + screenCaps++);
        }
        return inventoryScreenOpened;
    }

    /**
     * This method is used to open the Order Line screen in the application.
     *
     * @return true if the screen is successfully opened.
     */
    public boolean openOrderLineScreen() {
        boolean orderlineScreenOpened = false;
        LogWriter.writeLogEntry("Attempting to open the Order Line Query Screen.");
        try {
            screen.click("images/Menu/menuData.png", 10);
            screen.hover("images/Menu/Data/dataOrder.png");
            screen.hover("images/Menu/Data/Order/orderOrderHeader.png");
            screen.click("images/Menu/Data/Order/orderOrderLine.png");
            screen.wait("images/Menu/Data/Order/orderLineMaintenance", 10);
            LogWriter.writeLogEntry("Successfully opened the Order Line Query Screen.");
            orderlineScreenOpened = true;
        } catch (FindFailed ex) {
            write(ex.toString());
            screenCap("WMSFindFailed" + screenCaps++);
        }
        return orderlineScreenOpened;
    }

    /**
     * This method is used to open the Pick Face screen in the application.
     *
     * @return true if the screen is successfully opened.
     */
    public boolean openPickFaceScreen() {
        boolean pickFaceScreenOpened = false;
        LogWriter.writeLogEntry("Attempting to open the Pick Face Maintenance Screen.");
        try {
            screen.click("images/Menu/menuData.png", 10);
            screen.hover("images/Menu/Data/dataLocation.png");
            screen.hover("images/Menu/Data/Location/locationLocation.png");
            screen.click("images/Menu/Data/Location/locationPickFace.png");
            screen.wait("images/Menu/Data/Location/pickFaceMaintenance", 10);
            LogWriter.writeLogEntry("Successfully opened the the Pick Face Maintenance Screen.");
            pickFaceScreenOpened = true;
        } catch (FindFailed ex) {
            write(ex.toString());
            screenCap("WMSFindFailed" + screenCaps++);
        }
        return pickFaceScreenOpened;
    }

    /**
     * This method is used to open the Sku screen in the application.
     *
     * @return true if the screen is successfully opened.
     */
    public boolean openSkuScreen() {
        boolean skuScreenOpened = false;
        LogWriter.writeLogEntry("Attempting to open the Sku Maintenance Screen.");
        try {
            screen.click("images/Menu/menuData.png", 10);
            screen.hover("images/Menu/Data/dataSku.png");
            screen.hover(new Location(Env.getMouseLocation().x + 100, Env.getMouseLocation().y));
            screen.click("images/Menu/Data/Sku/skuSku.png");
            screen.wait("images/Menu/Data/Sku/skuMaintenance", 10);
            LogWriter.writeLogEntry("Successfully opened the the Sku Maintenance Screen.");
            skuScreenOpened = true;
        } catch (FindFailed ex) {
            write(ex.toString());
            screenCap("WMSFindFailed" + screenCaps++);
        }
        return skuScreenOpened;
    }

    /**
     * This method is used to select the user defined tab on any screen of the
     * application.
     *
     * @return - returns true if tab is selected successfully
     */
    public boolean selectUserDefinedTab() {
        boolean tabSelected = false;
        LogWriter.writeLogEntry("Attempting to select the user defined.");
        try {
            screen.click("images/Menu/Master/userDefined.png");
            LogWriter.writeLogEntry("succeeded to select the user defined tab.");
            tabSelected = true;
        } catch (FindFailed ex) {
            write(ex.toString());
            screenCap("WMSFindFailed" + screenCaps++);
        }
        return tabSelected;
    }

    /**
     * This method confirms that the custom label "vintage" is available on the
     * screen.
     *
     * @return - returns true if the vintage label is available on screen.
     */
    public boolean confirmCustomVintageLabel() {
        boolean customTagPresent = false;
        try {
            screen.wait("images/Menu/Master/vintage.png", 10);
            customTagPresent = true;
            screen.type(Key.ESC);
        } catch (FindFailed ex) {
            write(ex.toString());
            screenCap("WMSFindFailed" + screenCaps++);
        }
        return customTagPresent;
    }

    /**
     * This method creates a C&E consignment.
     *
     * @return; - returns true if consignment is created.
     */
    public boolean createCnEConsignment() {
        LogWriter.writeLogEntry("Creating C&E consignment:");
        boolean created = false;
        try {
            screen.click("images/Menu/menuData.png");
            screen.hover("images/Menu/data/dataInventory.png");
            screen.hover("images/Menu/Data/Inventory/inventoryCandEConsignor.png");
            screen.click("images/Menu/Data/Inventory/inventoryCandEConsignment.png");
            screen.wait("images/Menu/Data/Inventory/CandEConsignmentMaintenance.png", 60);
            screen.click("images/add.png");
            screen.click("/images/Menu/Data/Inventory/CandEConsignment/ready.png");
            screen.type("other");
            screen.type(Key.DOWN);
            screen.type(Key.DOWN);
            screen.type(Key.ENTER);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {

            }
            screen.click("/images/Menu/Data/Inventory/CandEConsignment/CandEStatus.png");
            screen.type("Re" + Key.ENTER);
            screen.click("/images/Menu/Data/Inventory/CandEConsignment/eADConsignors.png");
            screen.type("9771");
            screen.click("/images/Menu/Data/Inventory/CandEConsignment/EMCSeADARC.png");
            screen.type("AUTOPATEST");
            screen.click("/images/Menu/Data/Inventory/CandEConsignment/EMCSeADDate.png");
            screen.type("0" + Key.TAB);
            screen.type("0" + Key.TAB);
            screen.click("images/execute.png");
            screen.wait("/images/Menu/Data/Inventory/CandEConsignment/saveModifications.png", 10);
            screen.type(Key.SPACE);
            screen.wait("/images/Menu/Data/Inventory/CandEConsignment/canClose.png", 10);
            screen.click("images/close.png");
            created = true;
        } catch (FindFailed ex) {
            write(ex.toString());
            screenCap("WMSFindFailed" + screenCaps++);
        }
        return created;
    }

    /**
     * This method links the given Pre Advice with the given C&E Consignment
     *
     * @param preAdviceId - The Pre Advice to be linked
     * @param consignment - The consignment to be linked
     * @return; - returns true if successfully linked.
     */
    public boolean cneConsignmentLinking(String preAdviceId, String consignment) {
        boolean linked = false;
        try {
            screen.click("images/Menu/menuOperations.png");
            screen.hover("images/Menu/Operations/opertationCustomsandExcise.png");
            screen.click("images/Menu/Operations/customsAndExcise/customsAndExciseCNEConsignmentLinking");
            screen.wait("images/Menu/operations/customsAndexcise/cneLinking.png", 60);
            screen.click("images/Menu/Operations/customsAndExcise/customsAndExcisePreAdviceId.png");
            screen.type(preAdviceId);
            screen.click("images/Menu/Operations/customsAndExcise/customsAndExciseNext.png");
            screen.click("images/Menu/Operations/customsAndExcise/customsAndExciseNext.png");
            screen.wait("images/Menu/Operations/customsAndExcise/customsAndExciseConsignment.png", 60);
            screen.click("images/Menu/Operations/customsAndExcise/customsAndExciseConsignment.png");
            screen.type(consignment);
            screen.click("images/Menu/Operations/customsAndExcise/customsAndExciseDone.png");
            screen.click("images/close.png");
            linked = true;
        } catch (FindFailed ex) {
            LogWriter.writeLogEntry("Failed to link consignment\n");
            LogWriter.writeLogEntry(ex.toString());
        }

        return linked;
    }

    public boolean releaseMoveTasks(String orderNumber) {
        boolean taskReleased = false;
        try {
            screen.click("images/Menu/menuOperations.png");
            screen.hover("images/Menu/Operations/operationMoveTask.png");
            screen.hover("images/Menu/Operations/MoveTask/moveTaskMoveTaskListGeneration.png");
            screen.click("images/Menu/Operations/MoveTask/moveTaskMoveTaskManagement.png");
            screen.wait("images/Menu/Operations/MoveTask/MoveTaskManagement.png", 30);
            screen.click("images/next.png");
            screen.click("images/Menu/Operations/MoveTask/mtmTaskID.png");
            screen.type(orderNumber);
            screen.click("images/next.png");
            screen.rightClick(new Location(Env.getMouseLocation().x, Env.getMouseLocation().y - 175));
            //screen.rightClick();
            screen.click("images/Menu/Operations/MoveTask/selectAllTasks.png");
            screen.click("images/updateStatus.png");
            screen.click("images/Menu/Operations/MoveTask/OK.png");
            screen.click("images/Menu/Operations/MoveTask/yes.png");
            screen.click("images/close.png");

            taskReleased = true;
        } catch (FindFailed ex) {
            write(ex.toString());
            screenCap("WMSFindFailed" + screenCaps++);
        }
        return taskReleased;
    }

    public boolean runOrderGrouping() {
        boolean groupingRan = false;
        try {
            screen.click("images/Menu/menuOperations.png");
            for (int i = 0; i < 9; i++) {
                screen.type(Key.DOWN);
            }
            screen.type(Key.RIGHT);
            screen.type(Key.DOWN);
            screen.type(Key.ENTER);
            screen.wait("images/Menu/Operations/Order/orderGrouping.png", 10);
            screen.click("images/next.png");
            screen.click(new Location(Env.getMouseLocation().x, Env.getMouseLocation().y - 175));
            screen.type("a", KeyModifier.KEY_CTRL);
            screen.click("images/next.png");
            screen.wait("images/Menu/Operations/Order/grouping.png", 10);
            screen.click("images/next2.png");
            screen.wait("images/Menu/Operations/Order/ordersGrouped.png", 60);
            screen.click("images/done.png");
            screen.click("images/close.png");
            groupingRan = true;
        } catch (FindFailed ex) {
            write(ex.toString());
            screenCap("WMSFindFailed" + screenCaps++);
        }
        return groupingRan;
    }

    public boolean createTrailer() {
        boolean trailerCreated = false;
        try {
            screen.click("images/Menu/menuData.png");
            screen.hover("images/Menu/Data/dataGeneral.png");
            screen.hover("images/Menu/Data/General/generalAssets.png");
            screen.hover("images/Menu/Data/General/Assets/assetsTransport.png");
            screen.hover("images/Menu/Data/General/Assets/Transport/transportTractorType.png");
            screen.click("images/Menu/Data/General/Assets/Transport/transportTrailer.png");
            screen.wait("images/Menu/Data/General/Assets/Transport/trailerMaintenance.png");
            trailerCreated = true;
        } catch (FindFailed ex) {
            write(ex.toString());
            screenCap("WMSFindFailed" + screenCaps++);
        }

        return trailerCreated;
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

    public void logout() {
        try {
            write("Logging out of teh application");
            screen.click("images/logout.png");
            screen.wait("images/loginPane.png",60);
        } catch (FindFailed ex) {

        }
    }

    private void write(String toWrite) {
        System.out.println(toWrite);
        LogWriter.writeLogEntry(toWrite);
    }
}
