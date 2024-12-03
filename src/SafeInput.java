import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SafeInput {
    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt){
        String retString = ""; // Set this to zero length. Loop runs until it isn't
        do
        {
            System.out.print("\n" +prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
        }while(retString.length() == 0);

        return retString;
    }

    public static int getInt(Scanner pipe, String prompt){
        int retInt;
        System.out.println("\n" + prompt + ": "); // show prompt add space
        while(!pipe.hasNextInt()) {
            System.out.println("Your input is invalid.");
            pipe.next(); // next input is not an int, so consume it and move on
        }
        retInt = pipe.nextInt();
        return retInt;
    }

    public static double getDouble(Scanner pipe, String prompt){
        double retDouble;
        System.out.println("\n" + prompt + ": "); // show prompt add space
        while(!pipe.hasNextDouble()) {
            System.out.println("Your input is invalid.");
            pipe.next(); // next input is not a double, so consume it and move on
        }
        retDouble = pipe.nextDouble();
        return retDouble;
    }

    public static int getRangedInt(Scanner pipe, String prompt, int low, int high){
        int retInt = -1;
        boolean done = false;
        System.out.println("\n" + prompt + " between " + low + " and " + high + ": ");
        do{
            if(pipe.hasNextInt()){
                retInt = pipe.nextInt();
                pipe.nextLine();
                if(retInt >= low && retInt <= high){
                    done = true;
                }
                else{
                    System.out.println("Your input is invalid.");
                }
            }
            else{
                System.out.println("Your input is invalid.");
                pipe.nextLine(); // next input is not an int, so consume it and move on
            }
        }while(!done);

        return retInt;
    }


    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high){
        double retdouble = -1;
        boolean done = false;
        System.out.println("\n" + prompt + " between " + low + " and " + high + ": ");
        do{
            if(pipe.hasNextDouble()){
                retdouble = pipe.nextDouble();
                pipe.nextLine();
                if(retdouble >= low && retdouble <= high){
                    done = true;
                }
                else{
                    System.out.println("Your input is invalid.");
                }
            }
            else{
                System.out.println("Your input is invalid.");
                pipe.nextLine(); // next input is not a double, so consume it and move on
            }
        }while(!done);

        return retdouble;
    }

    public static boolean getYNConfirm(Scanner pipe, String prompt){
        boolean retBool = false;
        String YN = "";
        boolean done = false;
        System.out.println("\n" + prompt + "[Y/N]: ");
        do{
            if(pipe.hasNext()){
                YN = pipe.next();
                pipe.nextLine();
                if(YN.equalsIgnoreCase("Y")){
                    retBool = true;
                    done = true;
                }
                else if(YN.equalsIgnoreCase("N")){
                    retBool = false;
                    done = true;
                }
                else{
                    System.out.println("Your input is invalid.");
                }
            }
            else{
                System.out.println("Your input is invalid.");
                pipe.nextLine();
            }
        }while(!done);

        return retBool;
    }

    public static String getRegExString(Scanner pipe, String prompt, String RegEx) {
        String stringToSearch = "";
        do {
            System.out.print("\n" + prompt + ": "); // show prompt add space
            stringToSearch = pipe.nextLine();
            Pattern regexPattern = Pattern.compile(RegEx);
            Matcher regexMatcher = regexPattern.matcher(stringToSearch);
            if (regexMatcher.find()) {
                return regexMatcher.group(0);
            }
        } while(true);
    }

    public static void prettyHeader(String msg) {
        if (msg.length() < 52) {
            int position = 0;
            int valSpacesAfter = 0;
            boolean isEvenLength = (msg.length() % 2 == 0);
            for (int i = 0; i < 3; i++) {
                if (i % 2 == 0) {
                    for(int j = 0; j < 60; j++) {
                        System.out.print("*");
                    }
                    System.out.println();
                } else {
                    position = (54 - msg.length());
                    System.out.print("***");
                    for (int k = 0; k < position / 2; k++) {
                        System.out.print(" ");
                    }
                    System.out.print(msg);
                    if (isEvenLength) {
                        valSpacesAfter = position / 2;
                    } else {
                        valSpacesAfter = position / 2 + 1;
                    }
                    for (int l = 0; l < valSpacesAfter; l++) {
                        System.out.print(" ");
                    }
                    System.out.println("***");
                }
            }
        } else {
            System.out.println("Your message is too long, please try again with a shorter message.");
        }
    }
}
