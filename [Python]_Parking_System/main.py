from Classes import *
from Data import *

def main():
    choice = 1
    while not (choice == 0):
        main_menu()

        choice = input("Enter your choice here: ")
        clear()

        match choice:
            case "1":
                nC = newCustomer()
                menu_1(nC)
            case "2":
                clear()
                verify = True
                while(verify):
                    print("="*50)
                    print("LOGIN".center(50))
                    print("="*50)
                    email = input("Please enter your email : ")
                    password = input("Please enter your password : ")
                    print("="*50)

                    if email == "admin@gmail.com" and password == "12345":
                        adminVariable = admin()
                        admin_menu(adminVariable)
                        break

                    if(read_user(email, password)):
                        rC = registeredCustomer(email, password)
                        menu_2(rC)  
                        break
                    else:
                        clear()
                        pass
                
                                          
                    #read from customer file (database)
                    #write to customer file (overwrite price and time)
                    #create customer reciept
            case "0":
                disp_ty()
                
  
main()



