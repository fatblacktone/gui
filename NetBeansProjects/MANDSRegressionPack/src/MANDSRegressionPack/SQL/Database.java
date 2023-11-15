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
package MANDSRegressionPack.SQL;

import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import MANDSRegressionPack.File.LogWriter;
import java.util.ArrayList;

/**
 *
 * @author Tone Walters (tone_walters@yahoo.com)
 */
public class Database {

    private String applicationUser;

    private Connection connection;

    /**
     * This method creates a connection to the database using the parameters
     * provided. Returns true if the connection is a success.
     *
     * @param address - address of the server
     * @param username - username
     * @param password - password
     * @return - returns true if the connection is successful.
     */
    public boolean connect(String address, String username, String password) {
        boolean connectionSucessful = false;
        try {
            connection = DriverManager.getConnection(address, username, password);
            connection.setAutoCommit(true);
            connectionSucessful = true;
        } catch (SQLException ex) {
            LogWriter.writeLogEntry("Something went wrong connecting to the database");
            LogWriter.writeLogEntry(ex.toString());
        }
        return connectionSucessful;
    }

    /**
     * This method is used to place a file into the interface tables of the
     * application
     *
     * @param xml - the XML to be passed into the system.
     * @return - returns true if the file is successfully placed
     */
    public boolean insertInterfaceFile(String xml) {
        boolean success = false;
        try {
            CallableStatement vStatement
                    = connection.prepareCall("begin mands.Lib_Interface.LoadData(?, ?); end;");
            vStatement.setClob(2, new StringReader(xml));
            vStatement.registerOutParameter(1, Types.VARCHAR);
            vStatement.executeUpdate();
            System.out.println(vStatement.getString(1));
            success = true;
        } catch (SQLException ex) {
            write("Failed to execute statement:: " + ex.toString());
        }
        return success;
    }

    public String getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(String applicationUser) {
        this.applicationUser = applicationUser;
    }

    /**
     * This method returns a string representing the check string of the to
     * location for the current move task.
     *
     * @return - check string
     */
    public String getPutAwayCheckString() {
        String result = "";
        String sql = "";
        try {
            Statement stmt = connection.createStatement();
            sql = "select check_string from location where location_id = (select to_loc_id from move_task where user_id = '" + applicationUser + "')";
            ResultSet rs = stmt.executeQuery(sql);
            int row = 1;
            rs.next();
            result = rs.getString(1);
        } catch (SQLException ex) {
            write("It failed to make the statement::" + ex.toString());
            write("Filed statment = "+sql);
        }
        return result;
    }

    /**
     * This method returns the check string of the location passed in as a
     * parameter
     *
     * @param location - the location
     * @return - the check string
     */
    public String geCheckString(String location) {
        String result = "";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select check_string from location where location_id = '" + location + "'");
            int row = 1;
            rs.next();
            result = rs.getString(1);
        } catch (SQLException ex) {
            System.out.println("It failed to make the statement::" + ex.toString());
        }
        return result;
    }

    /**
     * This method is used to get all of the putaways currently available in the
     * system.
     *
     * @return - array of putaway data
     */
    public String[][] getPutAways() {
        String[][] results = new String[1000][2];
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select tag_id,sku_id from move_task where task_id = 'PUTAWAY' and STATUS != 'Hold'");
            int row = 1;
            while (rs.next()) {

                for (int i = 1; i < 3; i++) {
                    results[row - 1][i - 1] = rs.getString(i);
                    System.out.print(rs.getString(i) + ":");
                }
                System.out.print("\n");
                row++;
            }
        } catch (SQLException ex) {

        }

        return results;
    }

    /**
     * This returns the data for all the orders available in the system.
     *
     * @return - array of order data
     */
    public String[][] getOrders() {
        String[][] results = new String[5000][6];
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select task_id,list_id,sku_id,from_loc_id,to_loc_id,QTY_TO_MOVE from move_task where status = 'Released' and task_type = 'O' and list_id != '(null)'");
            int row = 1;
            while (rs.next()) {

                for (int i = 1; i < 7; i++) {
                    results[row - 1][i - 1] = rs.getString(i);
                    System.out.print(rs.getString(i) + ":");
                }
                System.out.print("\n");
                row++;
            }
        } catch (SQLException ex) {

        }

        return results;
    }

    /**
     * This method returns all inventory currently held in the system.
     *
     * @return - returns array of order data
     */
    public String[][] getInventory() {
        String[][] results = new String[2000][2];
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select tag_id,location_id from inventory");
            int row = 1;
            while (rs.next()) {

                for (int i = 1; i < 3; i++) {
                    results[row - 1][i - 1] = rs.getString(i);
                    System.out.print(rs.getString(i) + ":");
                }
                System.out.print("\n");
                row++;
            }
        } catch (SQLException ex) {

        }

        return results;
    }

    /**
     * This method returns the locations in the system that are currently empty
     *
     * @return - returns an array with the locations and check strings
     */
    public String[][] getEmptyLocationsAndCheckString() {
        String[][] results = new String[200000][2];
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select location_id from location where CURRENT_VOLUME = '0' and ALLOC_VOLUME = '0'");
            int row = 1;
            while (rs.next()) {

                for (int i = 1; i < 3; i++) {
                    results[row - 1][i - 1] = rs.getString(i);
                    System.out.print(rs.getString(i) + ":");
                }
                System.out.print("\n");
                row++;
            }
        } catch (SQLException ex) {

        }

        return results;
    }

    /**
     * This method is used to get Pre-Advice line detail from the database. It
     * will use the Pre-Advice ID passed in as a parameter and return a
     * 2Dimensional array representing the SKU and qty for each line.
     *
     * @param preAdviceID - Pre-Advice ID to get the line details for
     * @return - Returns a n*2 array. Where n is number of lines.
     */
    public String[][] getPreAdviceData(String preAdviceID) {
        String[][] results = new String[50][2];
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select pal.sku_id,pal.qty_due from pre_advice_header pah"
                    + " left join pre_advice_line pal on "
                    + "pal.pre_advice_id = pah.pre_advice_id"
                    + " where pah.pre_advice_id = '" + preAdviceID + "'");
            int row = 1;
            while (rs.next()) {

                for (int i = 1; i < 3; i++) {
                    results[row - 1][i - 1] = rs.getString(i);
                    System.out.print(rs.getString(i) + ":");
                }
                System.out.print("\n");
                row++;
            }
        } catch (SQLException ex) {

        }

        return results;
    }

    /**
     * This method is used to confirm that a pre advice doe exist on the system
     * and that a line has been created for the sku
     *
     * @param preAdviceID - the pre advice to check
     * @param lines
     * @return - returns true if the PA exists
     */
    public boolean checkPreAdviceExists(String preAdviceID, int lines) {
        boolean exists = false;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select count(*) from pre_advice_line where pre_advice_id = '" + preAdviceID + "'");
            int row = 1;
            while (rs.next()) {
                String string = rs.getString(1);
                try {
                    if (Integer.parseInt(string) == lines) {
                        exists = true;
                    }
                } catch (NullPointerException ex) {

                }
                row++;
            }
        } catch (SQLException ex) {

        }
        return exists;
    }

    /**
     * This method is used to find the ABV of a given sku
     *
     * @param sku - the sku
     * @return - returns the ABV
     */
    public String getABV(String sku) {
        String abv = "";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select CE_ALCOHOLIC_STRENGTH from sku where sku_id = '" + sku + "'");
            rs.next();
            abv = rs.getString(1);

        } catch (SQLException ex) {

        }
        return abv;
    }

    /**
     * This method returns the last C&E consignment to be created.
     *
     * @return - the last consignment
     */
    public String getLastCnEconsignment() {
        String consignment = "";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select consignment_id from CUSTOMS_CONSIGNMENT where ROWNUM = 1 order by CONSIGNMENT_ID desc");
            while (rs.next()) {
                consignment = rs.getString(1);
            }
        } catch (SQLException ex) {

        }
        return consignment;
    }

    /**
     * This method is used to test if a sku is a customs and excise sku.
     *
     * @param sku - the sku
     * @return - returns true if the sku is C&E
     */
    public boolean isCnESku(String sku) {
        boolean exists = false;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select CE_CUSTOMS_EXCISE from sku"
                    + " where sku_id = '" + sku + "'");
            while (rs.next()) {
                if (rs.getString(1).equals("Y")) {
                    exists = true;
                }
            }
        } catch (SQLException ex) {

        }
        return exists;
    }

    /**
     * This method is used to check if an order exists in the main tables of JDA
     * it uses the given order number and number of lines and confirms that 
     * they exist in the DB
     * 
     * @param orderNumber
     * @param numberOfLines
     * @return exists  
     */
    public boolean checkOrderExists(String orderNumber, int numberOfLines) {
        boolean exists = false;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select count(*) from order_line where order_id = '" + orderNumber + "'");
            int row = 1;
            while (rs.next()) {
                if (Integer.parseInt(rs.getString(1)) == numberOfLines) {
                    exists = true;
                }
                row++;
            }
        } catch (SQLException ex) {

        } catch (NullPointerException ex) {

        }
        return exists;

    }
    
    /**
     * This method is used to tick the allocation flag of a given order. 
     * 
     * @param orderName
     * @return allocated
     */

    public boolean allocateOrder(String orderName) {
        boolean allocated = false;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("update order_line set allocate = 'Y'"
                    + " where order_id = '" + orderName + "'");
            write("Allocate flag set");
            int seconds = 70;
            while (seconds > 0) {
                Thread.sleep(1000);
                write("Waiting for allocation...." + seconds);
                rs = stmt.executeQuery("select qty_tasked from order_line where order_id = '" + orderName + "'");
                rs.next();
                String qty = rs.getString(1);
                if (qty != null) {
                    if (Integer.parseInt(qty) > 0) {
                        allocated = true;
                        break;
                    }
                }
                rs = stmt.executeQuery("select back_ordered from order_line where order_id = '" + orderName + "'");
                rs.next();
                String backOrdered = rs.getString(1);
                if (backOrdered != null) {
                    if (backOrdered.equals("Y")) {
                        if (Integer.parseInt(qty) > 0) {
                            write("Failed to allocate. Put on Back Order.");
                            break;
                        }
                    }
                }
                seconds--;
            }
        } catch (SQLException ex) {
            System.out.println("statement failed!!!" + ex.toString());
        } catch (InterruptedException ex) {
            System.out.println("Failed to wait!!");
        }
        return allocated;
    }
    
    /**
     * This method is used to get the container id for the task that is 
     * currently in progress
     * 
     * @return container
     */

    public String getContainerOfCurrentTask() {
        String container = "";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select TO_CONTAINER_ID from move_task where user_id = '" + applicationUser + "' and to_container_id != 'null'");
            int row = 1;
            rs.next();
            container = rs.getString(1);

        } catch (SQLException ex) {

        }
        return container;
    }
    
    /**
     * This method is used to get the qty to move from the  move task that 
     * is currently in progress 
     * 
     * @return qty 
     */

    public String getCurrentMoveTaskQty() {
        String qty = "";
        try {
            Statement stmt = connection.createStatement();
            String query = "select QTY_TO_MOVE from move_task where user_id = '" + applicationUser + "' and status = 'In Progress'";
            ResultSet rs = stmt.executeQuery(query);
            int row = 1;
            rs.next();
            qty = rs.getString(1);

        } catch (SQLException ex) {

        }
        return qty;
    }
    
    /**
     * This method is used to get the case ratio for the task currently 
     * in progress
     * 
     * @return ratio
     */

    public int getRatio() {
        int ratio = 0;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select ratio_1_to_2 from sku_config where CONFIG_ID in (select config_id from move_task where user_id = '" + applicationUser + "' and status = 'In Progress')");
            int row = 1;
            rs.next();
            ratio = Integer.parseInt(rs.getString(1));

        } catch (SQLException ex) {

        }
        return ratio;
    }
    
    /**
     * This method gets the SKU from the task that is currently in progress
     * 
     * @return sku
     */

    public String getSkuFromCurrentMoveTask() {
        return executeStatmentSingleResponse("select sku_id from MOVE_TASK where"
                + " user_id = '" + applicationUser + "' and status = 'In Progress'");
    }
    
    /**
     * This methodis used to get the number of move tasks for the given order
     * 
     * @param orderID
     * @return numOfTasks 
     */

    public int getNumberOfMoveTasks(String orderID) {
        String asString = executeStatmentSingleResponse("select count(*) from MOVE_TASK where task_id = '" + orderID + "' and from_loc_id not like '%IN'");
        return Integer.parseInt(asString);
    }

    /**
     * This method is used to execute a query that has only a single string 
     * as the response
     * 
     * @param query
     * @return response
     */
    
    public String executeStatmentSingleResponse(String query) {
        String response = "";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            response = rs.getString(1);

        } catch (SQLException ex) {

        } catch (NullPointerException ex){
            System.err.println(ex.toString());
        }
        return response;
    }
    
    /**
     * This method is used to check if the consignment is blank. This is useful
     * to check if the order has passed through order grouping
     * 
     * @param orderID
     * @return notBlank 
     */

    public boolean consignmentIsNotBlank(String orderID) {
        boolean notBlank = false;
        String consignment = executeStatmentSingleResponse("select consignment from order_header where order_id = '" + orderID + "'");
        if (consignment.length() > 1) {
            notBlank = true;
        }
        return notBlank;
    }

    /**
     * This method returns the check string for the to location of the task 
     * that is currently in progress 
     * 
     * @return checkString 
     */
    
    public String getCheckStringForToLocation() {
        String checkString = executeStatmentSingleResponse("select check_string from location where location_id = (select to_loc_id from move_task where user_id = '" + applicationUser + "' and status = 'In Progress')");
        return checkString;
    }
    
    /**
     * This method is used to check if the task currently in progress is a 
     * trolley pick
     * 
     * @param orderID
     * @param lineID
     * @return isTrollyPick 
     */

    public boolean isTrolleyPick(String orderID, String lineID) {
        boolean isTrolleyPick = false;
        String trolleySlot = executeStatmentSingleResponse("select TROLLEY_SLOT_ID from move_task where task_id = '" + orderID + "' and line_id = '" + lineID + "'");
        try {
            if (!trolleySlot.equals("")) {
                isTrolleyPick = true;
            }
        } catch (NullPointerException ex) {

        }
        return isTrolleyPick;
    }

    /**
     * This method is used to check the status is released of a move task by the given
     * order id
     * 
     * @param orderNumber
     * @return  isReleased
     */
    public boolean checkMoveTaskStatus(String orderNumber) {
        String status = executeStatmentSingleResponse("select status from move_task where task_id = '" + orderNumber + "'");
        return status.equals("Released");
    }

    /**
     * This method is used to check if the given order has move tasks
     * with a list id
     * 
     * @param orderNumber
     * @return hasList 
     */
    public boolean hasListID(String orderNumber) {
        String listID = executeStatmentSingleResponse("select list_id from move_task where task_id = '" + orderNumber + "'");
        return !listID.equals("");
    }
    
    /**
     * This method checks to see if the move task has been through both sets
     * of clustering and that they have a to container with a length of 
     * 13 digits
     * 
     * @param orderNumber
     * @param lineID
     * @return digits 
     */

    public boolean has13digitContainer(String orderNumber, String lineID) {
        String container = executeStatmentSingleResponse("select to_container_id from move_task where task_id = '" + orderNumber + "' and line_id ='" + lineID + "'");
        boolean digits = false;
        try {
            digits = container.length() == 13;
        } catch (NullPointerException ex) {

        }
        return digits;
    }

    private void write(String string) {
        LogWriter.writeLogEntry(string);
        System.out.println(string);
    }

    /**
     * This method is used to check if the group id is bank.
     * 
     * @param orderNumber
     * @return notBlank
     */
    
    public boolean orderGroupIDIsNotBlank(String orderNumber) {
        boolean notBlank = false;
        String string = executeStatmentSingleResponse("select order_group_id from order_header where order_id = '" + orderNumber + "'");
        if (string != null) {
            notBlank = true;
        }
        return notBlank;

    }

    /**
     * This method is used to get the order type from the given order.
     * 
     * @param orderNumber
     * @return orderType
     */
    
    public String getOrderType(String orderNumber) {
        String orderType = executeStatmentSingleResponse("select order_type from order_header where order_id = '" + orderNumber + "'");
        return orderType;
    }
    
    /**
     * This method is used to check if the current pallet is a marshal task
     * 
     * @param pallet
     * @return isMarshalTask
     */

    public boolean isMarshalTask(String pallet) {
        String toLocation = executeStatmentSingleResponse("select to_loc_id from move_task where pallet_id ='" + pallet + "'");
        String substring = toLocation.substring(Math.max(toLocation.length() - 2, 0));
        System.err.println(toLocation + "::" + substring);
        return !substring.equals("SD");
    }

    /**
     * This method is used to get the line number of the task currently
     * in progress
     * 
     * @return lineNumber 
     */
    public String getLineNumberOfCurrentTask() {
        String lineNumber = executeStatmentSingleResponse(
                "SELECT line_id FROM order_line WHERE order_id = (SELECT task_id FROM move_task WHERE user_id = '" + applicationUser + "' and ROWNUM < 2)");

        return lineNumber;

    }

    /**
     * This method gets the inventory that remains in the location after the 
     * task that is currently in progress is complete
     * 
     * @return 
     */
    public int getRemainingInventoryForCurrentLocation() {
        String asString = executeStatmentSingleResponse("select QTY_ON_HAND from inventory where location_id = (select from_loc_id from move_task where user_id = '" + applicationUser + "' and status = 'In Progress')\n"
                + "and tag_id = (select tag_id from move_task where user_id = '" + applicationUser + "' and status = 'In Progress')");
        return Integer.parseInt(asString);
    }
    
    /**
     * This method returns the number of move tasks for the given task ID
     * 
     * @param orderNumber
     * @return 
     */
    public ArrayList<String> getMoveTasksForOrder(String orderNumber) {
        ArrayList<String> moveTasks = new ArrayList<>();
        int count = 0;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select list_id from move_task where task_id = '" + orderNumber + "'");
            while (rs.next()) {
                moveTasks.add(rs.getString(1));
            }
        } catch (SQLException ex) {

        } catch (NullPointerException ex) {

        }

        return moveTasks;
    }

    /**
     * This method is used to get the list id of the task that is currently 
     * in progress
     * 
     * @return 
     */
    
    public String getListOfCurrentTask() {
        String list = executeStatmentSingleResponse("select list_id from move_task where user_id = '" + applicationUser + "'");
        return list;
    }

    /**
     * This method is used to get the merge error for the given order id
     * 
     * @param orderNumber
     * @param numberOfLines
     * @return 
     */
    public String getOrderMergeError(String orderNumber, int numberOfLines) {
        String headerError = "";
        String errorToReturn = "";
        String header = executeStatmentSingleResponse("select order_id from order_header where order_id = '" + orderNumber + "'");
        if (!header.equals(orderNumber)) {
            headerError = executeStatmentSingleResponse("select text from LANGUAGE_TEXT where label = (select merge_error from interface_order_header where order_id = '" + orderNumber + "') and language = 'EN_GB'");
            errorToReturn = "Header Fail: " + orderNumber + "::" + headerError + "\n";
        } else {

            if (numberOfLines > Integer.parseInt(executeStatmentSingleResponse("select count(*) from order_line where order_id = '" + orderNumber + "'"))) {
                for (int i = 0; i < numberOfLines; i++) {
                    String fail = executeStatmentSingleResponse("select text from LANGUAGE_TEXT where label = (select merge_error from interface_order_line where order_id = '" + orderNumber + "' and line_id = '" + (i + 1) + "') and language = 'EN_GB'");
                    if (fail.equals("")) {
                        continue;
                    }
                    errorToReturn += "Line Fail:" + (i + 1) + "::" + fail + "\n";
                }
            }

        }

        return errorToReturn;
    }

    /**
     * This method is used to get the interface error for the given pre advice.
     * 
     * @param preAdviceId
     * @param numberOfLines
     * @return 
     */
    
    public String getPAMergeError(String preAdviceId, int numberOfLines) {
        String headerError = "";
        String errorToReturn = "";
        String header = executeStatmentSingleResponse("select pre_advice_id from pre_advice_header where pre_advice_id = '" + preAdviceId + "'");
        if (!header.equals(preAdviceId)) {
            headerError = executeStatmentSingleResponse("select text from LANGUAGE_TEXT where label = (select merge_error from interface_pre_advice_header where pre_advice_id = '" + preAdviceId + "') and language = 'EN_GB'");
            errorToReturn = "Header Fail: " + preAdviceId + "::" + headerError + "\n";
        } else {
            if (numberOfLines > Integer.parseInt(executeStatmentSingleResponse("select count(*) from pre_advice_line where pre_advice_id = '" + preAdviceId + "'"))) {
                for (int i = 0; i < numberOfLines; i++) {
                    String fail = executeStatmentSingleResponse("select text from LANGUAGE_TEXT where label = (select merge_error from interface_pre_advice_line where pre_advice_id = '" + preAdviceId + "' and line_id = '" + (i + 1) + "') and language = 'EN_GB'");
                    if (fail.equals("")) {
                        continue;
                    }
                    errorToReturn += "Line Fail:" + (i + 1) + "::" + fail + "\n";
                }
            }

        }

        return errorToReturn;
    }

    /**
     * This method is use to get the number of lines on the given pre advice
     * 
     * @param preAdviceId
     * @return 
     */
    
    public String getNumberOfPaLines(String preAdviceId) {
        return executeStatmentSingleResponse("select count(*) from pre_advice_line where pre_advice_id = '"+preAdviceId+"'");
    }

    /**
     * This method is used to check if the given SKU is a excise sku
     * 
     * @param sku
     * @return 
     */
    
    public boolean isCnEDutyStamp(String sku) {
        String string = executeStatmentSingleResponse("select CE_DUTY_STAMP from sku where sku_id ='"+sku+"'");
        boolean dutyStamp = false;
        if(string.equals("Y")){
            dutyStamp = true;
        }
        return dutyStamp;
    }

    /**
     * This method is used to check if the given pre advice is in error in the 
     * interface table
     * 
     * @param preAdviceID
     * @return 
     */
    public boolean isInterfaceError(String preAdviceID) {
        boolean isError = false;
        String lineError = executeStatmentSingleResponse("select count(*) from interface_pre_advice_line where pre_advice_id = '"+preAdviceID+"' and merge_status = 'Error'");
        String headerError = executeStatmentSingleResponse("select count(*) from interface_pre_advice_header where pre_advice_id = '"+preAdviceID+"' and merge_status = 'Error'");
        if(lineError.equals("")){
            lineError = "0";
        }
        if(headerError.equals("")){
            headerError = "0";
        }
        if(Integer.parseInt(lineError)+Integer.parseInt(headerError)>0){
            isError = true;
        }
        return isError;
    }

    /**
     * This method is used to return the number of tasks on the given list id
     * 
     * @param listOfCurrentTask
     * @return 
     */
    public int getNumberOfMoveTasksForList(String listOfCurrentTask) {        
        return Integer.parseInt(executeStatmentSingleResponse("select count(*) from move_task where list_id = '"+listOfCurrentTask+"'"));
    }

    /**
     * This method is used to get the number of list ids for the given order
     * 
     * @param orderNumber
     * @return 
     */
    
    public int getNumberOfListsForOrder(String orderNumber) {
        return Integer.parseInt(executeStatmentSingleResponse("select count(unique list_id) from move_task where task_id = '"+orderNumber+"'"));
    }
    
    /**
     * This method is used to execute a query that has multiple strings in 
     * the response
     * 
     * @param query
     * @return 
     */
    
    public ArrayList<String> execute1DStatment(String query) {
        ArrayList<String> moveTasks = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            String[] parts = rs.getString(1).split(",");
            for(int i=0;i<parts.length;i++){
                moveTasks.add(parts[i]);
            }
            
            
        } catch (SQLException ex) {

        } catch (NullPointerException ex) {

        }

        return moveTasks;
    }
    
}
