from queue import Queue
import data, review_data, test_data

if __name__ == "__main__":
    
    while True:
        data.clear()
        match data.starting_screen():
            case 1 :
                object = test_data.Test()
                object.take_test()
                del object
            case 2:
                object = review_data.Review()
                object.take_test() #change to review_test instead
                del object
            case 0 : 
                break
    
    data.clear()
    print("===============================\n".center(50))
    print("Thank you for playing\n".center(50))
    print("===============================\n".center(50))
    input()

        
    
    