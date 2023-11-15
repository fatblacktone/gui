/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnvms.fileOperators;

import dnvms.objects.Driver;
import dnvms.objects.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

/**
 *
 * @author Mathew Keegan
 */
public class FileWrite {

    public void appendFile(String input) {
    }

    public void writeNewFile() {
        try {
            FileWriter outFile = new FileWriter("C:/test.txt ");
            PrintWriter out = new PrintWriter(outFile);

            // Also could be written as follows on one line
            // Printwriter out = new PrintWriter(new FileWriter(args[0]));

            // Write text to file
            out.println("This is line 1");
            out.println("This is line 2");
            out.print("This is line3 part 1, ");
            out.println("this is line 3 part 2");
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void createUserFile(User[] users) {
        int i = 1;
        while (users[i] != null) {
            i++;
        }

        try {
            FileWriter outFile = new FileWriter("C:/users.txt ");
            PrintWriter out = new PrintWriter(outFile);
            for (int count = 0; count < i; count++) {

                out.print(users[count].getUserName() + ":");
                out.print(users[count].getPassword() + ":");
                switch (users[count].getClearance()) {

                    case SUPER:
                        out.print("SUPER");
                        break;
                    case USER:
                        out.print("USER");
                        break;
                    case MANAGER:
                        out.print("MANAGER");
                        break;
                    case ADMIN:
                        out.print("ADMIN");
                        break;
                }
                out.println();
            }
            out.close();
        } catch (IOException ex) {

            ex.printStackTrace();
        }

    }

    public void createDriverFile(Driver[] drivers) {
        int i = 0;
        while (drivers[i] != null) {
            i++;
        }

        try {
            FileWriter outFile = new FileWriter("C:/drivers.txt ");
            PrintWriter out = new PrintWriter(outFile);
            for (int count = 0; count < i; count++) {
                out.print(drivers[count].getName() + ",");
                out.print(drivers[count].getAddress() + ",");
                out.print(drivers[count].getDob() + ",");
                out.print(drivers[count].getLicenseNumber() + ",");
                out.print(drivers[count].getRun() + ",");
                out.print(drivers[count].getDriverNumber() + ",");
                out.print(drivers[count].getDriverPhoto().getAbsoluteFile());
                out.println();                
            }
            out.close();
        } catch (IOException ex) {

            ex.printStackTrace();
        }
    }
}
