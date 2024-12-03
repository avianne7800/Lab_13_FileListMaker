import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE;

public class Listmaker {
    private static final ArrayList<String> myArrList = new ArrayList<>();
    private static final Scanner in = new Scanner(System.in);
    private static final JFileChooser jFile = new JFileChooser();

    public static void main(String[] args) {
    //Sets file being written
        File workingDirectory = new File(System.getProperty("user.dir"));
        File selectedFile;
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\data.txt");

        try {
            jFile.setCurrentDirectory(workingDirectory);

            if (jFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = jFile.getSelectedFile();
                file = selectedFile.toPath();
            }
            else {
                System.out.println("No file selected!!! ... exiting.\nRun the program again and select a file.");
            }
        } catch (HeadlessException e) {
            throw new RuntimeException(e);
        }

        //Writes data to myArrList
        try {
            FileReader fileReader = new FileReader(workingDirectory.getPath() + "\\src\\data.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            //storing the content of the file in String
            while ((line = bufferedReader.readLine()) != null) {
                myArrList.add(line);
            }
            bufferedReader.close();
            fileReader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    //Variable initialization
        boolean done = false;
        String menuChoice;
        int tempInt = 0;
        String tempString = "";
    //Main loop
        do{
            menuChoice = SafeInput.getNonZeroLenString(in,
                    "A - Add an item to the list\n" +
                    "D - Delete an item from the list\n" +
                    "I - Insert an item into the list\n" +
                    "M - Move an item to another spot in the list\n" +
                    "V - View the list\n" +
                    "O - Opens a list file from the disk\n" +
                    "S - Save the current file to disk\n" +
                    "C - Clears the entire list of all items\n" +
                    "Q - Quit the program\n");
            switch(menuChoice){
                case "A":
                    myArrList.add(SafeInput.getNonZeroLenString(in,"Enter new item"));
                    System.out.println("Item added.");
                    break;
                case "D":
                    myArrList.remove(SafeInput.getInt(in,"Enter item number to delete (Starting at 0)"));
                    System.out.println("Item deleted.");
                    break;
                case "I":
                    myArrList.set(SafeInput.getInt(in,"Enter item number to insert into (Starting at 0)"),
                            SafeInput.getNonZeroLenString(in,"Enter item to insert"));
                    System.out.println("Item inserted.");
                    break;
                case "M":
                    tempInt = SafeInput.getInt(in,"Enter item number to move (Starting at 0)");
                    tempString = myArrList.get(tempInt);
                    myArrList.remove(tempInt);
                    myArrList.set(SafeInput.getInt(in,"Enter item number to move selected item to"),tempString);
                    System.out.println("Item moved.");
                    break;
                case "V":
                    System.out.println(myArrList);
                    break;
                case "O":
                    try {
                        jFile.setCurrentDirectory(workingDirectory);

                        if (jFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                            selectedFile = jFile.getSelectedFile();
                            file = selectedFile.toPath();
                        }
                        else {
                            System.out.println("No file selected!!! ... exiting.\nRun the program again and select a file.");
                        }
                    } catch (HeadlessException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "S":
                    try {
                        OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

                        for (String rec : myArrList) {
                            writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                            // 0 is where to start (1st char) the write
                            // rec. length() is how many chars to write (all)
                            writer.newLine();  // adds the new line
                        }
                        writer.close(); // must close the file to seal it and flush buffer
                        System.out.println("Data file written.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("File saved.");
                    break;
                case "C":
                    if(SafeInput.getYNConfirm(in,"Are you sure you want to clear the list?")){
                        myArrList.clear();
                    }
                    break;
                case "Q":
                    done = SafeInput.getYNConfirm(in,"Are you sure you want to quit?");
                    break;
                default:
                    System.out.println("Your input is invalid.");
                    break;
            }
        }while(!done);
    }
}
