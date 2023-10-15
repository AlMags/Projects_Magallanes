import datetime
import time
import os
import data as d

# EF'= EF+(0.1-((5-q)*0.08)-((5-q)0.02))
# I = EF * (d + n - 1)

MIN_EF = 1.3
MAX_EF = 2.5


class Review:
    today = datetime.date.today()
    filename = "test.txt"
    database = []
    questionBank = []
    questionsViewed = []
    gameSession = 0
    newEF = 0.0
    newQuality = 0
    newInterval = 0

    # Initializes the database
    def __init__(self):
        self.log_user()
        self.get_gameSession()
        self.read_database()
        # self.display_database()

    # Log when user used the application, used to get Game Session value during gameplay
    def log_user(self):
        filename = "log.txt"
        today = str(datetime.date.today())
        if os.path.getsize(filename) == 0:
            with open(filename, "w") as f:
                f.write(today)
            f.close()
        else:
            with open(filename, "a") as f:
                f.write(f"\n{today}")
            f.close()

    # Get number of game sessions
    def get_gameSession(self):
        filename = "log.txt"
        with open(filename, "r") as f:
            lines = f.readlines()
            lines = [line.strip() for line in lines]
            self.gameSession = len(lines)

    # Prints the database contents
    def display_database(self):
        for data in self.database:
            print(f"Game Session: {data['GameSession']}\nEF: {data['EF']:.2f}\nInterval: {data['Interval']}\nTimes Viewed: {data['TimesViewed']}\nQuestion: {data['Question']}\nAnswer: {data['Answer']}\n")

    # Populate local database variable
    def read_database(self):
        # read from text and store in local database variable
        with open(self.filename, "r") as file:
            for line in file:
                parts = line.strip().split(", ")
                data = {"GameSession": int(parts[0]),
                        "EF": float(parts[1]),
                        "Interval": int(parts[2]),
                        "TimesViewed": int(parts[3]),
                        "Question": parts[4],
                        "Answer": parts[5]}

                self.database.append(data)
        file.close()
        

    # Updates database text file
    def write_database(self):
        with open(self.filename, 'w') as f:
            for data in self.database:
                line = f"{data['GameSession']}, {data['EF']}, {data['Interval']}, {data['TimesViewed']}, {data['Question']}, {data['Answer']}"
                f.write(line)
        f.close()
        del self.database

    def check_answer(self, answerKey, userAnswer):
        if answerKey == userAnswer:
            return True
        else:
            return False
        
    def SM_Leit(self, answerKey, UserAnswer, timeTotal, TimesViewed, EF_old):
        localEF = 0
        localQuality = 0
        localDifficultyLevel = 0
        localInterval = 0
        localGameSession = 0 
        localGameSession= self.gameSession
        localTimesViewed = 0 
        localTimesViewed = TimesViewed
        
        # Get Quality
        if(not self.check_answer(answerKey, UserAnswer)):
            if (timeTotal > 300):  # complete blackout, went over 5 minutes with incorrect answer
                localQuality = 0
            elif (timeTotal > 180 and timeTotal <= 300): # attempted to answer, was incorrect, between 3mins and 5mins
                localQuality = 1
            elif (timeTotal <= 180): # confident with answer but incorrect, less than 3 minutes, handles incorrect speedrun answer
                localQuality = 2
        else:  # if correct answer
            if (timeTotal > 60):  # user still got the right answer, had difficulty
                localQuality = 3
            elif (timeTotal >= 30 and timeTotal < 60):
                localQuality = 4
            elif (timeTotal >= 0 and timeTotal < 30):
                localQuality = 5
    
        # # Get EF
        # # Get new EF ->  EF'= EF+(0.1-((5-q)*0.08)-((5-q)0.02))        
        localEF = EF_old + (0.1-((5-localQuality)*0.08)-((5-localQuality)*0.02))
        localEF = float("{:.2f}".format(localEF))
        
        if (localEF < 1.3):
            localEF = 1.3
        elif (localEF > 2.5):
            localEF = 2.5
          
        # # Get Difficulty Level
        if (localEF >= 1.3 and localEF < 1.55):
            localDifficultyLevel = 0
        elif (localEF >= 1.55 and localEF < 2.14):
            localDifficultyLevel = 1
        else:
            localDifficultyLevel = 2 
        
        # Get new Interval
        # Get new interval -> # I = EF * (difficultyLevel + timesViewed - 1)
        localTimesViewed += 1        
        localInterval = int(localEF * (localDifficultyLevel + localTimesViewed -1))
        if(localInterval <= 0):
            localInterval = 1
        localGameSession += int(localInterval)
        
        # Update newInterval, newGameSession, newTimesViewed, newEF
        self.newInterval = localInterval
        self.newGameSession = localGameSession
        self.newTimesViewed = localTimesViewed
        self.newEF = localEF

    def take_test(self):
        d.clear()
        
        for data in self.database:
            if data["GameSession"] == self.gameSession:
                self.questionBank.append(data)
                
        for data in self.database:
            if data["GameSession"] == self.gameSession:
                self.database.remove(data)
        
        tempQuestions = self.questionBank
        sorted(tempQuestions, key=lambda x : x['Interval'], reverse=True)
        correct = 0
        incorrect = 0
        
        
        if(len(tempQuestions) == 0):
            print("===============================\n".center(50))
            print("No items for review on this game session\n".center(50))
            print("===============================\n".center(50))
            input("Press any key to continue".center(50))
            return 

        for question_data in tempQuestions:
            old_EF = question_data["EF"]
            timesViewed = question_data["TimesViewed"]
            question = question_data["Question"]
            answerKey = question_data["Answer"].lower()

            print(f"{question}".center(50))
            start = time.time()
            userAnswer = input("Please type your answer here : ").lower()
            end = time.time()
            timeTotal = int(end - start)

            if self.check_answer(answerKey, userAnswer):
                d.clear()
                print("===============================".center(50))
                print("Answer is correct".center(50))
                print("===============================".center(50))
                input("Press any key to continue".center(50))
                correct += 1
                d.clear()
            else:
                d.clear()
                print("===============================".center(50))
                print("Answer is incorrect\n".center(50))
                print("Question : ".center(30))
                print(f"\"{question}\"\n".center(30))
                print("Correct Answer : ".center(35))
                print(f"\"{answerKey}\"\n".center(50))
                print("User's Answer : ".center(33))
                print(f"\"{userAnswer}\"\n".center(50))
                print("===============================\n".center(50))
                input("Press any key to continue".center(50))
                incorrect += 1
                d.clear()
    
            # SuperMemo and Leitner
            # Update TimesViewed, EF, Interval, Game Session
            self.SM_Leit(answerKey, userAnswer, timeTotal, timesViewed, old_EF)
            question_data["GameSession"] = self.newGameSession
            question_data["Interval"] = self.newInterval
            question_data["EF"] = self.newEF
            question_data["TimesViewed"] = self.newTimesViewed
            self.questionsViewed.append(question_data)
            
        print("\n\n\n\n=====================================\n\n\n")    

        for data in self.database:
            print(f"GameSession: {data['GameSession']}\nEF: {data['EF']:.2f}\nInterval: {data['Interval']}\nTimes Viewed: {data['TimesViewed']}\nQuestion: {data['Question']}\nAnswer: {data['Answer']}\n")
            print("================================".center(50))
        
        input("pause")
        print("\n\n\n\n=====================================\n\n\n")
        
        for data in self.questionsViewed:
            print(f"GameSession: {data['GameSession']}\nEF: {data['EF']:.2f}\nInterval: {data['Interval']}\nTimes Viewed: {data['TimesViewed']}\nQuestion: {data['Question']}\nAnswer: {data['Answer']}\n")
            print("================================".center(50))
        input("pause")        
        
        self.database += self.questionsViewed
        self.write_database()
        
        print("===============================\n".center(50))
        print("Congratulations on finishing the test".center(50))
        print("Below is your performance : \n".center(50))
        print(f"Correct answers : {correct}\n".center(50))
        print(f"Incorrect answers : {incorrect}\n".center(50))
        print(f"Items : {correct + incorrect}\n".center(50))
        print(f"Percentage : {correct / (correct + incorrect)}\n\n".center(50))
        print("===============================\n".center(50))
        input("Press any key to continue".center(50))
        
           
        

