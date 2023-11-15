/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnvms.fileOperators;

import dnvms.objects.Driver;
import dnvms.objects.User;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Mathew Keegan
 */
public class FileReader {

    public void readFile() {
        try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream("users.txt");
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                System.out.println(strLine);
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

    public User[] readUsers(){
        User[] users = new User[20];
        int numberOfUsers = 0;
        try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream("C:/users.txt");
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                String[] parts = strLine.split(":");
                User u = new User();
                u.setUserName(parts[0]);
                u.setPassword(parts[1]);
                if(parts[2].equalsIgnoreCase("ADMIN")){
                    u.setClearance(User.UserType.ADMIN);
                }else if(parts[2].equalsIgnoreCase("SUPER")){
                    u.setClearance(User.UserType.SUPER);
                }else if(parts[2].equalsIgnoreCase("USER")){
                    u.setClearance(User.UserType.USER);
                }else if(parts[2].equalsIgnoreCase("MANAGER")){
                    u.setClearance(User.UserType.MANAGER);
                }
                users[numberOfUsers] = u;
                numberOfUsers++;
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

        return users;
    }

    public Driver[] readDrivers(){
        Driver[] drivers = new Driver[100];
        int numberOfDrivers = 0;
        try{
            FileInputStream fstream = new FileInputStream("C:/drivers.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                String[] driverDetails = strLine.split(",");
                drivers[numberOfDrivers++] = new Driver(driverDetails[0],
                        driverDetails[1]+","+driverDetails[2]+","+driverDetails[3]
                        +","+driverDetails[4] , driverDetails[5], driverDetails[6]
                        , driverDetails[7], driverDetails[8],driverDetails[9]);
            }
            //Close the input stream
            in.close();

        }catch (Exception e){

        }

        return drivers;
    }
}

