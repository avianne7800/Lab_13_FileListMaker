import java.util.ArrayList;
import java.util.Scanner;

public class Listmaker {
    private static final ArrayList<String> myArrList = new ArrayList<>();
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        boolean done = false;
        String menuChoice;
        int tempInt = 0;
        String tempString = "";

        do{
            menuChoice = SafeInput.getNonZeroLenString(in,
                    "A - Add an item to the list\n" +
                    "D – Delete an item from the list\n" +
                    "I – Insert an item into the list\n" +
                    "P – Print (i.e. display) the list\n" +
                    "Q – Quit the program\n");
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
                case "P":
                    System.out.println(myArrList);
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
