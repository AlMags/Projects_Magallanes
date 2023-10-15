import os

def clear():
    os.system('cls')
    
       
def starting_screen():
    # clear()
    print("\n\n")
    print("============================".center(50))
    print("Starting Screen".center(50))
    print("============================".center(50))
    print("\n")
    input("Press any key to continue...".center(50))
    clear()
    
    while True:
        print("Main Menu\n\n".center(50))
        print("[1] Take a test".center(46)) # Will go through all of the items regardless of the date
        print("[2] Review".center(40)) # Will check the date if they have something to review
        print("[0] Exit".center(38))
        choice = int(input("\t\tChoice : "))
        if(choice >= 0 and choice <= 2):
            break
        else:
            clear()
            print("\n\n")
            print("============================".center(50))
            print("Incorrect Input".center(50))
            print("============================".center(50))
            print("\n")
            input("Press any key to continue...".center(50))
            clear()
        clear()
    
    return choice