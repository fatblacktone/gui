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
package MANDSRegressionPack.File;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.channels.FileChannel;

/**
 *
 * @author Tone Walters (tone_walters@yahoo.com)
 */
public class CSVWriter {

    public static void writeFile(String toWrite, String fileName) {
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
            writer.write(toWrite);
            writer.close();
        } catch (Exception ex) {

        }
    }
    
    /**
     * This method is used to write the pallet ids to a file. this file should 
     * be named orderPallets.csv and should be located in the folder csvData
     * 
     * @param palletID 
     */

    public static void writeToPalletFile(String palletID) {
        try {

            File file = new File("csvData/orderPallets.csv");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
            Writer writer = new BufferedWriter(fileWriter);
            writer.write(palletID + "\n");
            writer.close();
        } catch (Exception ex) {

        }
    }
    
    /**
     * This method is used to create a copy of a file.
     * 
     * @param sourceFile
     * @param destFile
     * @throws IOException 
     */

    public static void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }

}
