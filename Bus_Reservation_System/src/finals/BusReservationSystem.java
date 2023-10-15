package finals;

import java.util.*;
import java.io.*;

public class BusReservationSystem {
    
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner inp = new Scanner (System.in);
        int log;
        String user, password;
        
        boolean flag = false, ctr;
        
        //Class declaration
        MainMenu menu = new MainMenu();
        Admin admin = new Admin();
        Customer c = new Customer();
        
        do{
            //open main menu
            log = menu.Menu();            
            
            if (log == 1){ 
                System.out.print("\n\n\nEnter Username: ");
                user = inp.nextLine();
                System.out.print("Enter Password: ");
                password = inp.nextLine();//admin
                if(admin.Login(user, password)){
                    admin.AdminMenu();
                }else if (c.ClientLogin(user, password)){
                    menu.LoginMenu();               //login
                }else{
                    System.out.println("User does not exist");
                    System.out.println("Please try again...\n\n\n\n\n");
                }
            }else if(log == 2){
                System.out.print("\n\n\nEnter Username: ");
                user = inp.nextLine();
                System.out.print("Enter Password: ");
                password = inp.nextLine();
                c.ClientRegister(user, password);     //register      
            }else if(log == 3){
                    flag = true;  
            }else{
                System.out.println("Invalid Input");
                System.out.println("Please try again...");
            }
        }while(flag != true);
        
        
        System.out.println("\n\n\n\n********************************************");
        System.out.println("Thank you for using our services :D");
        System.out.println("********************************************\n\n\n");
    }
}
