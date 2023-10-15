package finals;

import java.io.*;
import java.util.*;

public class MainMenu{
    public int Menu(){
        int choice = 0;
        boolean flag = false;
        Scanner inp = new Scanner(System.in);
        do{
            //main menu
            try{
                System.out.println("********************************************");
                System.out.println("** BUS RESERVATION AND TICKETING SYSTEM   **");
                System.out.println("********************************************");
                System.out.println("                  LOGIN                     ");
                System.out.println("                                            ");
                System.out.println("                 Login[1]                   ");
                System.out.println("               Sign up[2]                   ");
                System.out.println("                  Exit[3]                   ");
                System.out.println("********************************************");
                System.out.print("Enter choice: ");
                choice = inp.nextInt();
                flag = true;
                
            }catch(Exception e){
                System.out.println("Invalid input please try again...\n\n\n\n");
                System.out.println("Press any key to continue...");
                new java.util.Scanner(System.in).nextLine();
            }
            
        }while(flag != true);

        return choice;
    }
    
    public void ReservationMenu() throws FileNotFoundException{
        boolean control = false, flag = false;
        int ch = 0, log = 0;
        int to=0, y=1, z=0, end=0, r=1, x = 1;
        int available[] = new int[6];
        int ticketI[][] = new int [100][3];
        String user, yn, search, again, choice;
        String ticketS[][] = new String[100][3];
        double ticketD[][] = new double [100][3];
        double pay[] = new double[20];
        double change[] = new double[20];
        
        //Class objects declaration
        Members m = new Members();
        MainMenu menu = new MainMenu();
        
        for(int o=1; o<=5; o++){
                available[o]=20;
        }
        
        do{
            Scanner inp = new Scanner(System.in);
            
            System.out.println("********************************************");
            System.out.println("** BUS RESERVATION AND TICKETING SYSTEM   **");
            System.out.println("********************************************");
            System.out.println("** [1] Destination                        **");
            System.out.println("** [2] Passengers                         **");
            System.out.println("** [3] Billing                            **");
            System.out.println("** [4] View                               **");
            System.out.println("** [5] Logout                               **");
            System.out.println("********************************************");
            System.out.println("********************************************\n");

            System.out.print("ENTER CHOICE: ");
            ch = inp.nextInt();
            
            switch(ch){
                    case 1:
                        m.Destination(available);
                        break;
                    case 2:
                        m.Destination(available);
                        m.Passenger(available, control, to, x, z, ticketS, ticketD, ticketI);
                        break;
                    case 3:
                        m.Billing(x, z, ticketS, ticketD, ticketI, pay, change);
                        break;
                    case 4:
                        m.Destination(available);
                        m.View(x, z, ticketS, ticketD, ticketI, pay, change);                        
                        break;
                    case 5:
                        control = true;
                        break;
                    default:
                        System.out.println("Invalid Input");
                        System.out.println("Please Try Again");
                        System.out.println("Press Any Key To Continue...");
                        new java.util.Scanner(System.in).nextLine();
                        System.out.println("\n\n\n\n\n\n\n");
                }

                if (ch != 5){
                    for(y=1; y==1;){
                        Scanner in = new Scanner(System.in);
                        System.out.print("Do you want another transaction? [Y/N]: ");
                        yn = in.nextLine();

                        if (yn.equalsIgnoreCase("y")){
                                y=2;
                        }
                        else if (yn.equalsIgnoreCase("n")){
                                y=2;
                                control = true;
                                break;
                        }
                        else{
                                System.out.println("Invalid Input");
                                System.out.println("Please try again");
                                System.out.println("Press Any Key To Continue...");
                                new java.util.Scanner(System.in).nextLine();
                                y=1;
                        }
                    }
                }
                
        }while(control != true);
    }
    
    public void LoginMenu() {
        int ch = 0;
        boolean flag = false;
        String user;
        do{
            Scanner inp = new Scanner(System.in);
            System.out.println("View Receipt[1]");
            System.out.println("Logout[2]");
            ch = Integer.parseInt(inp.nextLine());

            switch(ch){
                case 1:
                    try{
                        System.out.print("Enter username: ");
                        user = inp.nextLine();
                        user += ".txt";
                        File file = new File(user);
                        Scanner read = new Scanner (file);
                        read.nextLine(); //takes in password so it would not display on screen
                        while(read.hasNext()){
                            System.out.println(read.nextLine());
                        }
                        read.close();
                    }catch(Exception e){
                        System.out.println("User could not be found");
                    }
                    
                    
                    break;
                case 2:
                    flag = true;
                    break;
                default:

            }
        }while(flag != true);
        
    }

    
}