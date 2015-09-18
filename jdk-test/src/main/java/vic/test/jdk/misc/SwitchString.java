package vic.test.jdk.misc;

public class SwitchString {
	 
    public static void main(String[] args) throws Exception {
        printColorUsingSwitch("red");
        // switch case string is case sensitive
        printColorUsingSwitch("RED");

        Thread.sleep(500);
        printColorUsingSwitch(null);
    }
    
    private static void printColorUsingSwitch(String color) {
        switch (color) {
        case "blue":
            System.out.println("BLUE");
            break;
        case "red":
            System.out.println("RED");
            break;
        default:
            System.out.println("INVALID COLOR CODE");
        }
    }
 
}