
package cwk8223;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main program for Coursework 1. Do not modify this file.
 * @author David Sutton
 */
public class Main {

    private static Scanner scan = new Scanner(System.in);
    private static Network network = null;
    private static Navigator navigator = new MyNavigator();
    private static NetworkReader reader = new MyNetworkReader();

    public static void main(String[] args) throws FileNotFoundException, IOException {

        String option;
        do {
            System.out.println("Options are:");
            System.out.println("q - quit");
            System.out.println("r - read network from file");
            System.out.println("l - list stations and direct connections");
            System.out.println("b - breadth first traversal");
            System.out.println("d - depth first traversal");
            System.out.print("Enter your option> ");

            option = scan.next();

            if (option.equalsIgnoreCase("r")) {
                readFile();
            } else if (option.equalsIgnoreCase("l")) {
                listStations();
            } else if (option.equalsIgnoreCase("b")) {
                breadthFirstTraversal();
            } else if (option.equalsIgnoreCase("d")) {
                depthFirstTraversal();
            }



        } while (!option.equalsIgnoreCase("q"));
    }

    private static void readFile() throws FileNotFoundException, IOException {
        System.out.print("Enter file name> ");
        String fileName = scan.next();
        File file = new File(fileName);
        if (file.canRead()) {
            FileInputStream stream = new FileInputStream(fileName);
            network = reader.read(stream);
            stream.close();
        } else {
            System.out.println("The file does not exist, or cannot be read!");
        }
    }

    private static void listStations() {
        for (String from : network.getStations()) {
            System.out.print(from + " with direct connections to: ");
            for (String to : network.getConnections(from)) {
                System.out.print(" " + to + " ");
            }
            System.out.println();
        }
    }

    private static void breadthFirstTraversal() {
        if (network == null) {
            System.out.println("You need to read a network file first!");
        } else {
            System.out.print("Enter start station> ");
            String start = scan.next();
            for (String station : navigator.breadthFirst(network, start)) {
                System.out.println(station);
            }
        }
    }

    private static void depthFirstTraversal() {
        if (network == null) {
            System.out.println("You need to read a network file first!");
        } else {
            System.out.print("Enter start station> ");
            String start = scan.next();
            if (!network.hasStation(start)) {
                System.out.println("Station " + start + " does not exist!");
            } else {
                for (String station : navigator.depthFirst(network, start)) {
                    System.out.println(station);
                }
            }
        }
    }
}
