package tests;

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
import com.sun.net.httpserver.Authenticator;
import MANDSRegressionPack.File.CSVReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.spi.DirStateFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.sikuli.basics.Debug;
import org.sikuli.basics.Settings;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;
import MANDSRegressionPack.Interface.PreAdviceHeader;
import MANDSRegressionPack.Interface.PreAdviceLine;
import MANDSRegressionPack.SQL.Database;
import MANDSRegressionPack.WMS.RDT.RDTUser;
import MANDSRegressionPack.WMS.WebUser;


/**
 *
 * @author Tone Walters (tone_walters@yahoo.com)
 */
public class SikuliTest {

    

    /**
     * Data is all loaded from the data.csv file found in the config folder.
     */
    private static String username;
    private static String password;
    private static String dbAddress;
    private static String dbUserName;
    private static String dbPassword;

    
    private int numberOfPreAdvices = 1;

    /**
     *
     */
    
    private WebUser wms = new WebUser();
    private static Database db = new Database();
    private static RDTUser rdt = new RDTUser("",db);
    
    private Screen screen = new Screen();

    public SikuliTest() {
        String dateString = getDate().split(" ")[0].replace("-", "") + (getDate().split(" ")[1].replace(":", "")).substring(0, getDate().split(" ")[1].replace(":", "").length() - 8);
        Debug.setLogFile("log/Sikuli" + dateString + ".log");

        Settings.LogTime = true;
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

    

    //@Test
    public void testVintageCustomFields() {
        assertTrue(wms.logIn(username, password));
        assertTrue(wms.openInventoryScreen());
        assertTrue(wms.selectUserDefinedTab());
        assertTrue(wms.confirmCustomVintageLabel());
        assertTrue(wms.openOrderLineScreen());
        assertTrue(wms.selectUserDefinedTab());
        assertTrue(wms.confirmCustomVintageLabel());
        assertTrue(wms.openPickFaceScreen());
        assertTrue(wms.selectUserDefinedTab());
        assertTrue(wms.confirmCustomVintageLabel());
        assertTrue(wms.openSkuScreen());
        assertTrue(wms.selectUserDefinedTab());
        assertTrue(wms.confirmCustomVintageLabel());

    }

    

    

    

    

    private static String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        return strDate;
    }

    private String createTag() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date();
        String strDate = sdf.format(now);
        return strDate;
    }

}
