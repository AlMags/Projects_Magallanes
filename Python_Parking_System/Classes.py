from time import sleep
from Data import *

# new customer class
# creates a new customer entry 
class newCustomer():
    def write_user(self, username, password, email):
        if is_file_empty():
            with open("userDB.txt", "r") as f:
                for line in f:
                    temp_username, _ = line.strip().split(",")
                    if temp_username == username:
                        print("="*50)   
                        print("Username is already taken.".center(50))
                        print("="*50)
                        f.close()
                        return False

        with open("userDB.txt", "a") as f:
            f.write(f"{username}, {password}, {email}\n")
            clear()
            print("="*50)
            print("Registration Complete".center(50))
            print("Returning to main menu".center(50))
            print("="*50)
            sleep(5)
            f.close()
            clear()
            
# registered customer class
# get user information, get parking information, update parking information

class registeredCustomer():
    FLOOR = {   '1' : ["A", "B", "C", "D", "E"], 
                '2' : ["F", "G", "H", "I", "J"], 
                '3' : ["K", "L", "M", "N", "O"], 
                '4' : ["P", "Q", "R", "S", "T"], 
                '5' : ["U", "V", "W", "X", "Y", "Z"]}
    
    SLOTS = {}        
        
    # Declare and initialize userFilename, username, password, email
    def __init__(self, email, password):
        self.read()
        self.username, self.password, self.email = read_userfile(email, password)
        self.userFileName = self.username + ".txt"

    # Get parking database information
    def read(self):
        with open("parkingDB.txt", "r") as f:
            for line in f:
                key, values = line.strip().split(" : ")
                self.SLOTS[key] = eval(values)
        f.close()

    # Update parking slots file
    def update_parking_slots_file(self):
        with open("parkingDB.txt", "w") as f:
            for key, values in self.SLOTS.items():
                f.write(f"{key} : {values}\n")
        f.close()

    # Update parking slots local variable
    def update_slots(self, key):
        for i in range(len(self.SLOTS[key])):
            if self.SLOTS[key][i] == 0:
                self.SLOTS[key][i] = 1
                break

    # Get available slots of pillar
    def count_available_slots(self):
        count = 0
        for pillar, num in self.SLOTS.items():
            count += num.count(0)
        return count 

    # Check availability of parking in pillar
    def check_availability(self, key):
        taken = 1
        parkingslots = self.SLOTS.get(key)
        if all(taken == value for value in parkingslots):
            return False
        return True 
    
    # Create reciept for user
    def create_receipt(self):
        with open(self.userFileName, "w") as f:
            f.write("=======================================================\n")
            f.write(f"      USERNAME          :           {self.username}\n")
            f.write(f"      EMAIL             :           {self.email}\n")
            f.write(f"      FLOOR CHOICE      :           {self.floor_choice}\n")
            f.write(f"      PILLAR CHOICE     :           {self.pillar_choice}\n")
            f.write(f"      TIME              :           {self.time} HOURS\n")
            f.write(f"      AMOUNT PAID       :           Php {self.amount}\n")
            f.write(f"      STATUS            :           LOGGED IN\n")
            f.write("=======================================================\n")
        f.close()

    # Display reciept
    def show_reciept(self):
        clear()
        if os.path.exists(self.userFileName):
            clear()
            with open(self.userFileName, "r") as f:
                print(f.read())
            input('Press Enter to continue...')
            clear()
        else:
            clear()
            print("="*50)
            print("There is no parking history of user.".center(50))
            print("="*50)
            sleep(3)
        
    # Process payment
    def payment(self):
        clear()
        print("="*100)
        print("PAYMENT".center(100))
        print("="*100)

        verify = True

        while(verify):
            print("="*100)
            print("Rate : 50Php / 1 hour".center(100))
            available_slots = self.count_available_slots() # Get available slots
            print(f"Number of Slots available : {available_slots}".center(100)) #count number of 0s in the lists of the dictionary
            print("Available floor numbers : 1 2 3 4 5".center(100))
            print("="*100)
            
            floor_choice = input("Please choose a floor number : ")
            pillar_list = self.FLOOR.get(str(floor_choice)) # Get pillar list of the floor
            print(f"Floor number {floor_choice} : Available pillars : ")
            print(' '.join(pillar_list).center(100))
            pillar_choice = input("Please choose a pillar : ")

            # Check availability of slots of the pillar
            if(not self.check_availability(pillar_choice)):
                print(f"All slots are taken in floor {floor_choice}, pillar {pillar_choice}")
                print("Please try again and choose a different location")
                continue  

            time = int(input("Please enter amount of hours to be availed : "))
            amount = time*50
            confirm = input("Confirm Php%2.2f for %2d  hours: [Y] or [N] : " %(amount, time))

            if(confirm == 'Y' or 'y' ):
                clear()
                print("="*100)
                print("Availing complete".center(100))
                print("Will return to main menu shortly".center(100))
                print("="*100)
                sleep(5)
                clear()

                self.update_slots(pillar_choice)
                
                self.floor_choice = floor_choice
                self.pillar_choice = pillar_choice
                self.time = time
                self.amount = amount
                
                self.create_receipt()

                verify = False

        # return time, amount, floor_choice, pillar_choice
        self.update_parking_slots_file()

    # Log out method
    def log_out(self):
        clear()
        pillar_choice = ""
        
        with open(self.userFileName, "r") as f:
            for line in f:
                if "PILLAR CHOICE" in line:
                    pillar_choice = line.strip().split(":")[-1].strip()
                    break

            if 1 in self.SLOTS[pillar_choice]:
                index = self.SLOTS[pillar_choice].index(1)
                self.SLOTS[pillar_choice][index] = 0        
        
        f.close()

        self.update_parking_slots_file() # Update parking slots after logout

        # Read and stores user receipt to variable "lines"
        with open(self.userFileName, "r") as f:
            lines = f.readlines()

        # Update status of user receipt to "LOGGED OUT"
        for i, line in enumerate(lines):
            if "STATUS" in line:
                lines[i] = "      STATUS            :           LOGGED OUT\n"
                break
        
        # Update user receipt with updated status
        with open(self.userFileName, "w") as f:
            f.writelines(lines)
        
        f.close()

        print("="*50)
        print("Logout Successful".center(50))
        print("Returning to menu".center(50))
        print("="*50)
        sleep(5)

# Admin class
class admin():
    
    # Read and display specific user receipt
    def read_user_receipt(self):
        userFilename = input("Input username : ")
        userFilename = userFilename + ".txt"
        clear()
        if os.path.exists(userFilename):
            with open(userFilename, "r") as f:
                print(f.read())
            f.close()
            input('Press Enter to continue...')
            clear()
        else:
            clear()
            userFilename = userFilename.split(".")
            print(f"There is no history of {userFilename}")

    # Display all information of users from text file
    def view_users(self):
        clear()
        with open('userDB.txt', 'r') as f:
            print(f.read())
        f.close()
        input('Press Enter to continue...')
        clear()
    
    # Display all parking information of the system
    def view_parking(self):
        clear()
        with open('parkingDB.txt', 'r') as f:
            print(f.read())
        f.close()
        input('Press Enter to continue...')
        clear()




