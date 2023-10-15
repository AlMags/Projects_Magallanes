package finals;

import java.io.*;
import java.util.*;

public class Customer extends Account {
    
    //search for user
    public boolean ClientLogin(String name, String password) {
        boolean control = false;
        String nameDoc = name + ".txt";
        try{
            FileReader fr = new FileReader(nameDoc);
            Scanner scan = new Scanner(fr);
            super.setPassword(scan.nextLine());

            if(super.getPassword().equals(password)){
                control = true;
            }
            fr.close();
        }catch(Exception ex){
            
        }
        return control;
    }
    
    public void ClientRegister(String user, String password) throws FileNotFoundException{
        Members m = new Members();
        MainMenu menu = new MainMenu();
        
        //create new ,txt file
        m.Write(user, password);
        
        //make reservation
        menu.ReservationMenu();
    }
}
