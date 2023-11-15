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
import MANDSRegressionPack.File.LogWriter;
import MANDSRegressionPack.Interface.PreAdviceHeader;
import MANDSRegressionPack.Interface.PreAdviceLine;
import MANDSRegressionPack.SQL.Database;
import MANDSRegressionPack.WMS.RDT.RDTUser;
import MANDSRegressionPack.WMS.WebUser;
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
import org.sikuli.script.Screen;

/**
 *
 * @author Tone Walters (tone_walters@yahoo.com)
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PreAdviceTests {

    private static String username;
    private static String password;
    private static String dbAddress;
    private static String dbUserName;
    private static String dbPassword;

    private static ArrayList<String> preAdviceStrings = new ArrayList<>();
    private static ArrayList<String> skuStrings = new ArrayList<>();
    private static ArrayList<String> upcStrings = new ArrayList<>();
    private static ArrayList<String> orderStrings = new ArrayList<>();

    private int numberOfPreAdvices = CSVReader.getNumberOfPreAdviceHeaders();

    /**
     *
     */
    private WebUser wms = new WebUser();
    private static Database db = new Database();
    private static RDTUser rdt = new RDTUser("", db);
    private boolean interfaceTests;
    private boolean receiptTests;

    private Screen screen = new Screen();
    private boolean mergeError;

    public PreAdviceTests() {
        String dateString = getDate().split(" ")[0].replace("-", "") + (getDate().split(" ")[1].replace(":", "")).substring(0, getDate().split(" ")[1].replace(":", "").length() - 8);
        Debug.setLogFile("log/Sikuli" + dateString + ".log");

        Settings.LogTime = true;
        Settings.MinSimilarity = 0.9;
        Settings.setShowActions(true);
    }

    public boolean isInterfaceTests() {
        return interfaceTests;
    }

    public void setInterfaceTests(boolean interfaceTests) {
        this.interfaceTests = interfaceTests;
    }

    public boolean isReceiptTests() {
        return receiptTests;
    }

    public void setReceiptTests(boolean receiptTests) {
        this.receiptTests = receiptTests;
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

    /**
     * This test is to validate the ability to interface pre-advices into JDA.
     *
     */
    @Test
    public void test1_PAInterface() {

        PreAdviceHeader pah = new PreAdviceHeader();
        PreAdviceLine pal = new PreAdviceLine();
        write("Trying to insert Pre Advice");
        for (int i = 0; i < numberOfPreAdvices; i++) {
            assertTrue(!pah.nextRecord());
            write("inserting header file: " + pah.getPreAdviceID());
            assertTrue(db.insertInterfaceFile(pah.insertHeader()));
        }

        while (!pal.nextRecord()) {
            if (pal.getPreAdviceID() != null) {
                write("inserting line file: "+pal.getPreAdviceID());
                assertTrue(db.insertInterfaceFile(pal.insertLine()));
            } else {
                break;
            }
        }
        pah = new PreAdviceHeader();
        
        for (int i = 0; i < numberOfPreAdvices; i++) {
            pah.nextRecord();
            int seconds = 60;
            write("Waiting for merge....." + pah.getPreAdviceID());
            while (seconds > 0) {
                
                
                if (db.checkPreAdviceExists(pah.getPreAdviceID(), Integer.parseInt(pah.getNumberOfLines()))) {
                    break;
                }if(db.isInterfaceError(pah.getPreAdviceID())){
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {

                }
                write("Waiting for merge....." + seconds);
                seconds--;
            }
            if (!db.checkPreAdviceExists(pah.getPreAdviceID(), Integer.parseInt(pah.getNumberOfLines()))) {
                write("Pre Advice not merged: " + pah.getPreAdviceID());
                writeMultiLine(db.getPAMergeError(pah.getPreAdviceID(), Integer.parseInt(pah.getNumberOfLines())));
                mergeError = true;
            }else{
                write("Pre Advice " + pah.getPreAdviceID() + " merged");
            }
         
        }
        if (mergeError) {
            assertTrue(false);
        } else {
            
        }
    }

    /**
     * This test is to validate the ability to receive stock into the system via
     * a pre advice receipt.
     *
     */
    //@Test
    public void test2_RDTPreAdviceReceipt() {
        if (!isReceiptTests()) {
            //return;
        }
        assertTrue(db.connect(dbAddress, dbUserName, dbPassword));
        PreAdviceHeader pah = new PreAdviceHeader();
        PreAdviceLine pal = new PreAdviceLine();
        for (int i = 0; i < numberOfPreAdvices; i++) {
            assertTrue(!pah.nextRecord());
            assertTrue(!pal.nextRecord());
            if (db.isCnESku(pal.getSku())) {
                assertTrue(wms.logIn(username, password));
                assertTrue(wms.createCnEConsignment());
                assertTrue(wms.cneConsignmentLinking(pah.getPreAdviceID(), db.getLastCnEconsignment()));
                wms.logout();
            }
            rdt.receivePreAdvice(pah.getPreAdviceID(), new PreAdviceLine(), username, password);
            assertTrue(rdt.logout());
        }

    }

    private static String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        return strDate;
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

}
