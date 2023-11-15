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
package tests;

import MANDSRegressionPack.File.CSVReader;
import MANDSRegressionPack.File.CSVWriter;
import MANDSRegressionPack.File.LogWriter;
import MANDSRegressionPack.Interface.OrderHeader;
import MANDSRegressionPack.Interface.OrderLine;
import MANDSRegressionPack.SQL.Database;
import MANDSRegressionPack.WMS.RDT.RDTUser;
import MANDSRegressionPack.WMS.WebUser;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.sikuli.basics.Debug;
import org.sikuli.basics.Settings;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.sikuli.script.ScreenImage;

/**
 *
 * @author Tone Walters (tone_walters@yahoo.com)
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderTests {

    private static String username;
    private static String password;
    private static String dbAddress;
    private static String dbUserName;
    private static String dbPassword;

    private int numberOfOrders = CSVReader.getNumberOfOrderHeaders();

    /**
     *
     */
    private WebUser wms = new WebUser();
    private static Database db = new Database();
    private static RDTUser rdt = new RDTUser("", db);

    private Screen screen = new Screen();
    private boolean mergeError;

    public OrderTests() {
        String dateString = getDate().split(" ")[0].replace("-", "") + (getDate().split(" ")[1].replace(":", "")).substring(0, getDate().split(" ")[1].replace(":", "").length() - 8);
        Debug.setLogFile("log/Sikuli" + dateString + ".log");

        Settings.LogTime = true;
        Settings.MinSimilarity = 0.9;
        Settings.setShowActions(true);
    }

    @BeforeClass
    public static void setUpClass() {
        ArrayList<String> data = CSVReader.readFile("config/data.csv");
        String currentDataType = "";
        for (int i = 0; i < data.size(); i++) {
            if (i == 0) {
                dbAddress = data.get(0).split(",")[3];
            } else if (i == 1) {
                username = data.get(1).split(",")[0];
                dbUserName = data.get(1).split(",")[2];
            } else if (i == 2) {
                password = data.get(2).split(",")[0];
                dbPassword = data.get(2).split(",")[2];
            }
        }
        db.connect(dbAddress, dbUserName, dbPassword);
        db.setApplicationUser(username);
        rdt.setUsername(username);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test1_OrderInterface() {
        OrderHeader oh = new OrderHeader();
        OrderLine ol = new OrderLine();
        write("Attempting to interface an Order");
        for (int i = 0; i < numberOfOrders; i++) {
            assertTrue(!oh.nextRecord());
            write("inserting order header: " + oh.getOrderNumber());
            assertTrue(db.insertInterfaceFile(oh.insertHeader()));
        }

        try {
            while (!ol.nextRecord()) {
                if (ol.getOrderName() != null) {
                    if (!ol.getOrderName().equals("")) {
                        write("inserting order line " + ol.getOrderName() + "::" + ol.getLineID());
                        assertTrue(db.insertInterfaceFile(ol.insertLine()));
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            write(ex.toString());
        }
        oh = new OrderHeader();
        oh.nextRecord();
        for (int i = 0; i < numberOfOrders; i++) {
            int seconds = 60;
            write("Waiting for merge...." + oh.getOrderNumber());
            while (seconds > 0) {
                write("Waiting for merge...." + seconds);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {

                }
                if (db.checkOrderExists(oh.getOrderNumber(), oh.getNumberOfLines())) {
                    break;
                }
                seconds--;
            }
            if (!db.checkOrderExists(oh.getOrderNumber(), oh.getNumberOfLines())) {
                write("Order not merged: "+oh.getOrderNumber());
                writeMultiLine(db.getOrderMergeError(oh.getOrderNumber(), oh.getNumberOfLines()));
                mergeError = true;
            }
            oh.nextRecord();

        }
        if (mergeError) {
            assertTrue(false);
        } else {
            write("Order " + oh.getOrderNumber() + " merged");
        }
        oh.nextRecord();
    }

    //@Test
    public void test2_OrderGrouping() {
        OrderHeader oh = new OrderHeader();
        OrderLine ol = new OrderLine();

        assertTrue(!oh.nextRecord());
        assertTrue(!ol.nextRecord());
        write("Logging into the application");
        assertTrue(wms.logIn(username, password));
        write("Running Order Grouping");
        assertTrue(wms.runOrderGrouping());
        write("Checking orders have been grouped");
        oh = new OrderHeader();
        for (int i = 0; i < numberOfOrders; i++) {
            assertTrue(!oh.nextRecord());
            //assertTrue(db.consignmentIsNotBlank(oh.getOrderNumber()));
            assertTrue(db.orderGroupIDIsNotBlank(oh.getOrderNumber()));
            write("Orders have grouped " + oh.getOrderNumber());
        }
    }

    //@Test
    public void test3_Allocation() {
        OrderLine ol = new OrderLine();
        for (int i = 0; i < numberOfOrders; i++) {
            assertTrue(!ol.nextRecord());
            write("Attempting Allocation " + ol.getOrderName());
            assertTrue(db.allocateOrder(ol.getOrderName()));
            write("Allocation succesful");
        }
    }

    //@Test
    public void test4_ReleaseOfMoveTasks() {
        OrderHeader oh = new OrderHeader();
        OrderLine ol = new OrderLine();
        for (int i = 0; i < numberOfOrders; i++) {
            assertTrue(!oh.nextRecord());
            assertTrue(!ol.nextRecord());
            while (true) {
                if (oh.getOrderNumber().equals(ol.getOrderName())) {
                    break;
                }
                assertTrue(!ol.nextRecord());
            }

            int seconds = 60;
            write("Releasing move tasks. " + oh.getOrderNumber());
            while (!db.has13digitContainer(oh.getOrderNumber(), ol.getLineID())) {
                write("Waiting for clusting....." + seconds);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
                seconds--;
            }
            if (db.checkMoveTaskStatus(oh.getOrderNumber())) {
                write("Move tasks already at status released");
                continue;
            }
            assertTrue(wms.releaseMoveTasks(oh.getOrderNumber()));
            write("Move tasks released");
        }
    }

   // @Test
    public void test5_RDTPick() {
        write("Logging in to RDT");
        assertTrue(rdt.login(username, password));
        write("Navigating to containerPicking");
        assertTrue(rdt.navigateToContainerPicking());
        OrderHeader oh = new OrderHeader();
        for (int i = 0; i < numberOfOrders; i++) {
            assertTrue(!oh.nextRecord());
            write("Picking order: " + oh.getOrderNumber());
            try {
                assertTrue(rdt.containerPicking(oh.getOrderNumber()));
            } catch (FindFailed ex) {
                write(ex.toString());
                screenCap("FindFailed");
            }
            write("Order picked");
        }
        assertTrue(rdt.logout());
        write("Logged out of RDT");
    }

    //@Test
    public void test6_rdtMarshal() {
        write("Logging in to RDT");
        assertTrue(rdt.login(username, password));
        write("Navigating to Marshal");
        assertTrue(rdt.navigateToMarshal());
        assertTrue(rdt.marshalPallets());
        //assertTrue(rdt.logout());
    }

    //@Test
    public void vehicleLoading() {
        assertTrue(wms.createTrailer());
        //Web link consignment to trailer
        //RDT load
    }

    private void write(String string) {
        LogWriter.writeLogEntry(string);
        System.out.println(string);
    }

    private void writeMultiLine(String string) {
        String[] parts = string.split("\n");
        for (int i = 0; i < parts.length; i++) {
            write(parts[i]);
        }
    }

    private static String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        return strDate;
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

}
