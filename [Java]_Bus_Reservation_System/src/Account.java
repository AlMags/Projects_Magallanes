package finals;

public class Account {
    private String acc_name, password;
   
        public void setName(String name){
            acc_name = name;
        }

        public void setPassword(String password){
            this.password = password;
        }
        
        public String getName(){
            return acc_name;
        }
        
        public String getPassword(){
            return password;
        }
    
    
}
