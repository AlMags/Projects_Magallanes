package finals;

import java.io.*;
import java.util.*;

public class Admin extends Account{
    
    //sets values for username and password
    public Admin() throws FileNotFoundException{
        File file = new File("Admin.txt");
        Scanner fr = new Scanner(file);
        super.setName(fr.nextLine());
        super.setPassword(fr.nextLine());
        
        fr.close();
    }
    
    //confirm admin login
    public boolean Login(String user, String password){
        boolean control = false;
        boolean flag = false;
        
        try{
            File file = new File("Admin.txt");
            Scanner fr = new Scanner(file);
            super.setName(fr.nextLine());
            super.setPassword(fr.nextLine());

            if(user.equals(super.getName())){
                if(password.equals(super.getPassword())){
                  System.out.println("Access Granted\n\n\n\n");
                  control = true; //exit loop
                  flag = true; //return statement
                }
            }else{
                flag = false;
            }
        }catch(FileNotFoundException e){
            System.out.println("Admin file could not be located");
            System.out.println("Please check your directories\n\n\n\n");
        }
       
        
        return flag;
    }
    
    public void AdminMenu(){
        int choice;
        String name, nameDoc, yn;
        boolean flag = false;
        
        do{ 
            Scanner inp = new Scanner(System.in);
            
            System.out.println("ADMIN");
            System.out.println("View[1]");
            System.out.println("Delete[2]");
            System.out.println("Logout[3");
            choice = Integer.parseInt(inp.nextLine());
            
            if(choice == 3){
                flag = true;
                break;
            }else{
                try{
                    System.out.println("Enter Passenger name: ");
                    name = inp.nextLine();
                    nameDoc = name + ".txt";
                    File file = new File(nameDoc);
                    switch(choice){
                        case 1:
                               FileReader fr = new FileReader(file);
                               Scanner scan = new Scanner(fr);
                               while(scan.hasNext()){
                                   System.out.println(scan.nextLine());
                               }
                               fr.close();
                               scan.close();
                            break;

                        case 2:
                            System.out.print("Do you want to delete: " + nameDoc);
                            System.out.println("[Y]/[N]");
                            yn = inp.nextLine();

                            if(yn.equalsIgnoreCase("y")){
                                if(file.delete()){
                                    System.out.println("File Successfully deleted");
                                }else{
                                    System.out.println("File could not be found");
                                }
                            }else if (yn.equalsIgnoreCase("n")){
                                continue;
                            }else{
                                System.out.println("Invalid input\n\n\n\n");
                            }
                            break;
                    }

                }catch(Exception e){
                    System.out.println("User Not Found");
                }
            }
        }while(flag != true);
        
        
        
        
        
    }
    
}
