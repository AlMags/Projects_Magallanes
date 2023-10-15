import os
from time import sleep

# Clears Prompt
def clear():
    os.system('cls')

# Check if userDB.txt is empty
def is_file_empty():
    try:
        return os.stat("userDB.txt").st_size == 0
    except FileNotFoundError:
        return True

# Check if user exists in userDB.txt
def read_user(email, password):
    with open("userDB.txt", "r") as f:
        for line in f:
            temp_username, temp_password, temp_email = line.strip().split(", ")
            print(temp_password, temp_password)
            if temp_email == email and temp_password == password:
                clear()
                print("="*50)
                print("Successful Login".center(50))
                print("="*50)
                sleep(3)
                clear()
                return True
    clear()
    print("="*50)
    print("Incorrect input please try again".center(50))
    print("="*50)
    sleep(3)
    return False

# Get user information from specified userfile
def read_userfile(email, password):
    with open("userDB.txt", "r") as f:
        for line in f:
            temp_username, temp_password, temp_email = line.strip().split(", ")
            if temp_email == email and temp_password == password:
                return temp_username, temp_password, temp_email

#Main Menu
def main_menu():
    print("="*50)
    print("WELCOME TO THE PARKING SLOT SYSTEM".center(50))
    print("Please select an option".center(50))
    print('%30s' % "[1] Register")
    print('%27s' % "[2] Login")
    print('%27s' % "[0} Exit\n")

# Menu Option 1 - Register
# Verify password of user, write new user into text file
def menu_1(nc):
    
    name = input("Please enter your name : ")
    email = input("Please enter your email : ")
    psw = input("Please enter your password : ")
    
    attempts = 0
    while attempts < 6:
        psw_ver = input("Please enter your password again: ")

        if not psw == psw_ver:
            print("="*50)
            print("\nPassword mismatch please try again\n")
            print("="*50)
            if not attempts < 5:
                clear()
                print("="*50)
                print("Maximum attempts exceeded.".center(50))
                print("="*50)
                sleep(5)
                disp_ty()
            
            attempts+=1
        else:
            verify = nc.write_user(name, psw, email)
            if(verify):
                break
            else:
                sleep(3)
                return
        
# Menu Option 2 - Login
# Payment, show reciept, logout
def menu_2(rc):
    while True:
        print("="*50)
        print("MENU".center(50))
        print("Please select an option".center(50))
        print('%25s' % "[1] Avail")
        print('%42s' % "[2] Display Latest Reciept")
        print('%26s' % "[3] Logout")
        print('%25s' % "[0] Exit\n")

        choice = input("Please enter your choice : ")

        match choice:
            case "1":
                rc.payment()
                clear()
            case "2":
                rc.show_reciept()
                clear()
            case "3":
                rc.log_out()
                clear()
            case "0":
                clear()
                print("="*50)
                print("Returning to Main Menu".center(50))
                print("="*50)
                sleep(3)
                clear()
                return
                

# Menu for Admin
# View user receipts, view all users, view all parking
def admin_menu(adminVariable):
    clear()
    while True:
        print("="*50)
        print("ADMIN MENU".center(50))
        print("Please select an option".center(50))
        print('%35s' % "[1] View User Receipt")
        print('%32s' % "[2] View All Users")
        print('%34s' % "[3} View All Parking")
        print('%23s' % "[0} Exit\n")
        print("="*50)

        choice = input("Enter choice here : ")
        match choice:
            case '1':
                adminVariable.read_user_receipt()
                clear()
            case '2':
                adminVariable.view_users()
                clear()
            case '3':
                adminVariable.view_parking()
                clear()
            case '0':
                print("="*50)
                print("Returning to main menu...".center(50))
                print("="*50)
                sleep(3)
                clear()
                return
            


# Menu Option 3
# Displays end screen 
def disp_ty():
    clear()
    print("="*50)
    print("Program will now end".center(50))
    print("="*50)
    sleep(5)
    clear()
    print("="*50)
    print("Thank you for using the program".center(50))
    print("="*50)
    os._exit(os.X_OK)