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

import MANDSRegressionPack.GUI.Interface;
import MANDSRegressionPack.Interface.PreAdviceLine;
import MANDSRegressionPack.SQL.Database;
import MANDSRegressionPack.WMS.RDT.IntelligentRDTUser;
import MANDSRegressionPack.enums.RDTTask;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 *
 * @author Tone Walters (tone_walters@yahoo.com)
 */
public class TestRunner {
    
    public static void main(String[] args) {
        IntelligentRDTUser rdt = new IntelligentRDTUser("OPTIMUS", "1234");
        rdt.setCurrentTask(RDTTask.PRE_ADVICE_RECEIVE);
        rdt.setPreAdviceId("2055734757");
        rdt.setSku("20001416");
        rdt.doScreen();


    }

}
