package finals;

import java.io.*;
import java.util.*;

public class Members {
    public String password, again, user, user_doc, search, cmpr, p_Name, p_Tickets, p_Destination, p_Price, p_Quantity, p_Discount, p_Total, p_Pay, p_Change, p_Status;
    
    //Validation if admin, register
//    public void Validation
    
    public void Write(String user, String password){
        user_doc = user + ".txt";
        File file = new File(user_doc);
        try{
            PrintWriter write = new PrintWriter(file);
            write.println(password);
            write.close();
        }catch(FileNotFoundException e){
            System.out.println("User not found");
        }
    }
    
    public void Destination(int available[]){
        System.out.println("***************************************");
        System.out.println("**   DESTINATION   |  FARE  |  SEAT  **");
        System.out.println("***************************************");
        System.out.println("** 1.)DAVAO CITY   | Php600 |   "+available[1]+"   **");
        System.out.println("** 2.)BUTUAN CITY  | Php170 |   "+available[2]+"   **");
        System.out.println("** 3.)CABADBARAN   | Php150 |   "+available[3]+"   **");
        System.out.println("** 4.)TANDAG       | Php200 |   "+available[4]+"   **");
        System.out.println("** 5.)CAGAYAN      | Php250 |   "+available[5]+"   **");
        System.out.println("***************************************");
        System.out.println("***************************************\n");
        System.out.println("PWD, STUDENT, & SENIOR CITIZEN with 20% DISCOUNT!!!\n");
        
    }
    
    public boolean Passenger(int available[], boolean control, int to, int x, int z, String ticketS[][], double ticketD[][], int ticketI[][]){
        int print = 1;
        if((available[1]==0)&&(available[2]==0)&&(available[3]==0)&&(available[4]==0)&&(available[5]==0)){
            System.out.println("Sorry, We don't  have available seats for all Destination!");
            control = false;
        }else{
            Scanner inp = new Scanner(System.in);
                //Passenger Name
                for(x=1; x==1;){
                    System.out.print("\nENTER PASSENGER'S NAME: ");
                    this.user = inp.nextLine();
                    ticketS[z][0] = this.user;
                    x=0;

                    //if Passenger's Name already used, display error and go back to Inputing//
                    for(int l=0; l<z; l++){
                        if(ticketS[l][0].equalsIgnoreCase(ticketS[z][0])){
                            System.out.println("Sorry, Passenger's name has already been used!");
                            x=1;
                        }
                    }
                }
                
                //Destination
                for(x=1; x==1;){
                    System.out.print("ENTER DESTINATION [number]: ");
                    to = Integer.parseInt(inp.nextLine());

                    //if Inputed integers are "<1" or ">5", display error and go back to Inputing//
                    if(to<1 || to>5){
                            System.out.println("Invalid Input!");
                            x=1;
                    }
                    //if available seat is eqaul to "Zero", display error and go back to Inputing//
                    for(int d=1; d<=5; d++){
                        if(to==d){
                            if(available[to]==0){
                                    System.out.println("Sorry, We don't have available seat!");
                                    x=1;
                            }
                            
                            x=0;
                        }
                    }
                }
                
                //Convert integer to string, then transfer to array
                String dest[] = { " ", "DAVAO CITY", "BUTUAN CITY", "CABADBARAN", "TANDAG", "CAGAYAN"};
    		double fare[] = { 0,600,170,150,200,250};
                
                //Convert integer to String then transfer to array
                ticketS[z][1] = dest[to];
    		ticketD[z][0] = fare[to];
                
                //Inputting number of passengers
                for(x=1; x==1;){
                    System.out.print("HOW MANY PASSENGERS ARE YOU?: ");
                    ticketI[z][0] = Integer.parseInt(inp.nextLine());

                    //subtract the available seat by the the number inputed//
                    for(int p=1; p<=5; p++){
                        if(to==p){
                            print=1;
                            available[to] = available[to]-ticketI[z][0];

                            //if the subtracted available seat is "<0", display error//
                            //add the inputed number to the subtracted seat, to back the original seat//
                            //display the available seat and back to the inputing//
                            if(available[to]<0){
                                System.out.print("Sorry, We don't have seat available for " +ticketI[z][0] +" person\n");
                                available[to] = available[to]+ticketI[z][0];
                                System.out.print("We only have " +available[to] +" seat available\n");
                                x=1;
                                print=0;
                            }
                            else{
                                x=0;
                            }
                        }
                    }
                }
                
                //inputing for Number of Discounted Passenger's
                for(x=1;x==1;){
                    System.out.print("HOW MANY PASSENGERS HAVE DISCOUNT?: ");
                    ticketI[z][1] = Integer.parseInt(inp.nextLine());
                    if(ticketI[z][1]>ticketI[z][0]){

                        System.out.println("Invalid Input!");
                        System.out.println("No. of Passengers are only " +ticketI[z][0] +"!");
                        x=1;
                    }
                    else{
                        break;
                    }
                }
                
                if(print==1){
                    System.out.println("\n***************************************");
                    System.out.println("**        PASSENGER'S DETAILS        **");
                    System.out.println("***************************************");
                    System.out.println("PASSENGER'S NAME: " + ticketS[z][0]);
                    System.out.println("PASSENGER'S DESTINATION : " + ticketS[z][1]);
                    System.out.println("FARE PRICE: Php " + ticketD[z][0]);
                    System.out.println("NO. OF PASSENGERS: " + ticketI[z][0]);
                    System.out.println("NO. OF PASSENGERS WITH DISCOUNT: " + ticketI[z][1]);
                    System.out.println("STATUS: NOT PAID");
                    System.out.println("***************************************");
                    System.out.println("***************************************\n");
                    ticketS[z][2]="0";
                    double discount=(ticketD[z][0]-(ticketD[z][0]*0.2))*ticketI[z][1];
                    ticketD[z][2]= ((ticketI[z][0]-ticketI[z][1])*ticketD[z][0])+discount;
                    x=0;
                }
            
            try{
                user_doc = user + ".txt";
                File file = new File(user_doc);
                Scanner scan = new Scanner(file);
                this.password = scan.nextLine();
                FileWriter fw = new FileWriter(file, false);
                PrintWriter wr = new PrintWriter(fw);
                wr.println(this.password);
                wr.println("\n***************************************");
                wr.println("**        PASSENGER'S DETAILS        **");
                wr.println("***************************************");
                wr.println("PASSENGER'S NAME: " + ticketS[z][0]);
                wr.println("PASSENGER'S DESTINATION : " + ticketS[z][1]);
                wr.println("FARE PRICE: Php " + ticketD[z][0]);
                wr.println("NO. OF PASSENGERS: " + ticketI[(z+1)][0]);
                wr.println("NO. OF PASSENGERS WITH DISCOUNT: " + ticketI[z][1]);
                wr.println("STATUS: NOT PAID");
                wr.println("***************************************");
                wr.println("***************************************\n");
                wr.close();
                fw.close();
            }catch(Exception e){
                System.out.println("User could not be found");
                System.out.println("Please try again");
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
                System.out.println("\n\n\n\n\n\n\n");
            }    
        }
        return control;
    }
    
    public void Billing(int x, int z, String ticketS[][], double ticketD[][], int ticketI[][],  double pay[], double change[]){
        String search;	
        
        for(x=1; x==1;){
        Scanner in = new Scanner(System.in);
        
        System.out.print("ENTER PASSENGER'S NAME: ");
        search = in.nextLine();
        
        int s=1;
        
        for(int b=0;b<=z;b++){
            if(search.equalsIgnoreCase(ticketS[b][0])){
                System.out.println("***************************************");
                System.out.println("**        PASSENGER'S DETAILS        **");
                System.out.println("***************************************");
                System.out.println("PASSENGER'S NAME: " + ticketS[b][0]);
                System.out.println("PASSENGER'S DESTINATION : " + ticketS[b][1]);
                System.out.println("FARE PRICE: Php" + ticketD[b][0]);
                System.out.println("NO. OF PASSENGERS: " + ticketI[b][0]);
                System.out.println("NO. OF PASSENGERS WITH DISCOUNT: " + ticketI[b][1]);
                System.out.println("***************************************");
                System.out.println("***************************************");
                s=0;
                x=0;

            if(ticketS[b][2].equals("x")){
                System.out.println("Passenger's Already Paid!");
                x=0;
            }else{
                    ticketS[b][2]="x";
                    for(x=1; x==1;){
                        System.out.println("\nPASSENGER'S TOTAL FARE: Php "+ticketD[b][2]);
                        System.out.print("ENTER AMOUNT TO PAY: ");
                        pay[b] = Double.parseDouble(in.nextLine());
                        change[b]=pay[b]-ticketD[b][2];

                        if(change[b]<0){
                                System.out.println("Invalid Input!");
                                x=1;
                        }
                        else{
                                System.out.println("CHANGE: Php "+change[b]);
                                System.out.println("");
                                x=0;
                        }
                    }
                }
            }
        }
        if (s==1){
                System.out.println("\nPASSENGER'S NAME NOT FOUND!\n");
                for(int q=1; q==1;){

                System.out.print("Do you wish to continue with this transaction? [Y/N]: ");
                again=in.nextLine();

                    if(again.equalsIgnoreCase("y")){
                        q=0;
                    }
                    else if (again.equalsIgnoreCase("n")){
                        q=0;
                        x=0;
                    }
                    else{
                        System.out.println("\nInvalid input!\n");
                    }
               }
            }	
        }
    }
    
    public void View (int x, int z, String ticketS[][], double ticketD[][], int ticketI[][], double pay[], double change[]){
        String yn;
        for(int sx=1; sx<=3;){
        Scanner inp = new Scanner(System.in);
        String search;

        System.out.print("SEARCH PASSENGER'S NAME: ");
        search = inp.next();
            int s=1;
            for(x=0; x<=z; x++){
                //output to console
                if(search.equalsIgnoreCase(ticketS[x][0])){
                System.out.println("***************************************");
                System.out.println("**        PASSENGER'S DETAILS        **");
                System.out.println("***************************************");
                System.out.println("PASSENGER'S NAME: " + ticketS[x][0]);
                p_Name = ticketS[x][0];
                System.out.println("PASSENGER'S DESTINATION : " + ticketS[x][1]);
                p_Price = ticketS[x][1];
                System.out.println("FARE PRICE: Php" + ticketD[x][0]);
                p_Destination = Double.toString(ticketD[x][0]);
                System.out.println("NO. OF PASSENGERS: " + ticketI[x][0]);
                p_Quantity = Double.toString(ticketI[x][0]);
                System.out.println("NO. OF PASSENGERS WITH DISCOUNT: " + ticketI[x][1]);
                p_Discount = Integer.toString(ticketI[x][0]);
                System.out.println("TOTAL FARE PRICE: Php " + ticketD[x][2]);
                p_Total = Double.toString(ticketD[x][2]);

                if(ticketS[x][2].equals("x")){
                    System.out.println("PAY: Php " +pay[x]);
                    System.out.println("CHANGE: Php " +change[x]);
                    System.out.println("STATUS: PAID");
                    this.cmpr = "PAY: Php " + pay[x] + "\n\n" + 
                           "CHANGE: Php " + change[x] + "\n\n" +
                           "STATUS: PAID" + "\n\n";
                }else{
                    System.out.println("STATUS: NOT PAID");
                    this.cmpr = "STATUS: NOT PAID";
                }

                System.out.println("***************************************");
                System.out.println("***************************************");

                    //Write to File
                    try{
                        File file = new File(user_doc);
                        Scanner scan = new Scanner(file);
                        this.password = scan.nextLine();
                        FileWriter fw = new FileWriter(file, false);
                        PrintWriter pw = new PrintWriter(fw);
                        pw.println(password);
                        pw.println("***************************************");
                        pw.println("**        PASSENGER'S DETAILS        **");
                        pw.println("***************************************");
                        pw.println("PASSENGER'S NAME: " + p_Name + "\n");
                        pw.println("PASSENGER'S DESTINATION : " +  p_Destination + "\n");
                        pw.println("FARE PRICE: Php" + p_Price + "\n");
                        pw.println("NO. OF PASSENGERS: " +  p_Quantity + "\n");
                        pw.println("NO. OF PASSENGERS WITH DISCOUNT: " + p_Discount + "\n");
                        pw.println("TOTAL FARE PRICE: Php " + p_Total + "\n");
                        pw.println(this.cmpr);
                        pw.println("***************************************");
                        pw.println("***************************************");
                        pw.close();
                        fw.close();
                        
                    }catch(Exception e){
                        System.out.println("User could not be found");
                        System.out.println("Please try again");
                        System.out.println("Press Any Key To Continue...");
                        new java.util.Scanner(System.in).nextLine();
                        System.out.println("\n\n\n\n\n\n\n");
                        sx=4;
                    }
                }
            }
        
            
            try{
                search += ".txt";
                FileReader fr = new FileReader(search);
                Scanner scan = new Scanner(fr);
                Scanner in = new Scanner(System.in);
                System.out.print("Do you want search again? [Y/N]: ");
                yn = in.nextLine();

                if (yn.equalsIgnoreCase("y")){
                    sx++;
                    break;
                }
                else if (yn.equalsIgnoreCase("n")){
                    sx = 4;
                    break;
                }else{
                    System.out.println("Invalid Input");
                    System.out.println("Please try again");
                    System.out.println("Press Any Key To Continue...\n\n\n\n");
                    new java.util.Scanner(System.in).nextLine();
                }
                System.out.println("Press Any Key To Continue...");
                new java.util.Scanner(System.in).nextLine();
                fr.close();
             }catch(Exception e){
                 System.out.println("User Not Found");
             }
        }
        
        }
    
    
}
    


    
