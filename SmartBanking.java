import java.util.Arrays;
import java.util.Scanner;

public class SmartBanking {
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        final String CLEAR = "\033[H\033[2J";
        final String GREEN = "\033[32;1m";
        final String RESET = "\033[0;0m";
        final String BLUE = "\033[34;1m";
        final String RED = "\033[31;1m";

        final String DB = "Welcome to Smart Banking";
        final String CNA = "Create New Account";
        final String DEPO = "Cash Deposit";
        final String WD = "Cash Withdrawals";
        final String TRANS = "Transfer Money";
        final String CAB = "Check Account Balance";
        final String DA = "Delete Account";
        String screen = DB;

        String ER_MSG = String.format("\t%s%s%s\n", RED,"%s",RESET);
        String SUCSS_MSG = String.format("\t%s%s%s\n", GREEN,"%s",RESET);

        String[][] accDetails = new String[0][];

        do{
            String title = String.format("\n\t\t%s%s%s",BLUE,screen,RESET);
            System.out.println(CLEAR);
            System.out.println(title);

            switch(screen){
                case DB:
                    System.out.println("\n\n\t[1] Create New Account\n");
                    System.out.println("\t[2] Deposits\n");
                    System.out.println("\t[3] Withdrawals\n");
                    System.out.println("\t[4] Transfer\n");
                    System.out.println("\t[5] Check Account Balance\n");
                    System.out.println("\t[6] Delete Account\n");
                    System.out.println("\t[7] Exit\n");
                    System.out.print("\n\tEnter the option to continue: ");
                    int option = scan.nextInt();
                    scan.nextLine();

                    switch(option){
                        case 1: screen = CNA; break;
                        case 2: screen = DEPO; break;
                        case 3: screen = WD; break;
                        case 4: screen = TRANS; break;
                        case 5: screen = CAB; break;
                        case 6: screen = DA; break;
                        case 7: 
                            System.out.println(CLEAR);
                            System.exit(0);
                        default:continue;
                    }
                    break;
                
                case CNA:
                    String accNo;
                    String name;                
    
                    loop1:
                    do{
                        accNo = String.format("SDB-%05d",accDetails.length>0 ? (Integer.valueOf(accDetails[accDetails.length-1][0].substring(5))+1):1);
                        System.out.printf("\n\n\tID: %s\n",accNo);

                        
                        // Getting name
                        System.out.print("\n\tName: ");
                        name = scan.nextLine().strip();

                        if(name.isBlank()){
                            System.out.printf(ER_MSG,"Name can,t be empty");
                            continue;
                        }else{
                            for (int i = 0; i < name.length(); i++) {
                                if(!(Character.isLetter(name.charAt(i)) || Character.isSpaceChar(name.charAt(i)))){
                                    System.out.printf(ER_MSG,"Invalid name");
                                    continue loop1;
                                }
                            }
                        }
                        
                        break;

                    }while(true);

                    String deposit;
                    do{
                        System.out.print("\n\tInitial Deposit: ");
                        deposit = scan.nextLine();
                        if(Double.valueOf(deposit) < 5000){
                            System.out.printf(ER_MSG,"Insufficient Amount");
                            continue;
                        }
                        
                        break;
                    }while(true);

                    String[][] newAccDetails = new String[accDetails.length+1][3];
                        for (int i = 0; i < accDetails.length; i++) {
                            newAccDetails[i] = accDetails[i];
                        }
                        newAccDetails[newAccDetails.length-1][0] = accNo;
                        newAccDetails[newAccDetails.length-1][1] = name;
                        newAccDetails[newAccDetails.length-1][2] = deposit;
                        accDetails = newAccDetails;

                    System.out.printf("\n\t"+SUCSS_MSG,accNo+":"+name+" has been created successfully");

                    System.out.print("\n\tDo you want to continue(Y/n): ");
                    if(scan.nextLine().strip().toUpperCase().equals("Y")){
                        continue;
                    }

                    //Print the Array
                    /*
                    System.out.println();
                    for (int i = 0; i < accDetails.length; i++) {
                        System.out.println(Arrays.toString(accDetails[i]));
                    }
                    scan.nextLine();
                    */
                    screen = DB;
                    break;

            }
                        
        }while(true);

    }
}
